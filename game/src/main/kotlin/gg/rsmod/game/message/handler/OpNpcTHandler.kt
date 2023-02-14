package gg.rsmod.game.message.handler

import gg.rsmod.game.action.PawnPathAction
import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.OpNpcTMessage
import gg.rsmod.game.model.World
import gg.rsmod.game.model.attr.*
import gg.rsmod.game.model.entity.Client
import gg.rsmod.game.model.entity.Entity
import gg.rsmod.game.model.priv.Privilege
import java.lang.ref.WeakReference

/**
 * @author Tom <rspsmods@gmail.com>
 *
 *     This is a very bad hack
 *     But ... TODO: Need to implement a real solution
 *     Upstream fix broke pathing, this works for now.
 *
 */
class OpNpcTHandler : MessageHandler<OpNpcTMessage> {

    override fun handle(client: Client, world: World, message: OpNpcTMessage) {
        val child = message.componentHash and 0xFFFF
        /** Item on NPC **/
        if (child == 0) {
            val index = message.npcIndex
            val npc = world.npcs[index] ?: return

            if (!client.lock.canNpcInteract() || !client.lock.canItemInteract()) {
                return
            }

            val movementType = message.movementType
            val itemId = message.verify
            val itemSlot = message.componentSlot

            val item = client.inventory[itemSlot] ?: return

            if (item.id != itemId) {
                return
            }

            log(client, "Item on npc: movement=%d, item=%s, slot=%d, npc=%s, index=%d", movementType, item, itemSlot, npc, index)

            if (movementType == 1 && world.privileges.isEligible(client.privilege, Privilege.ADMIN_POWER)) {
                client.moveTo(world.findRandomTileAround(npc.tile, 1) ?: npc.tile)
            }

            client.closeInterfaceModal()
            client.interruptQueues()
            client.resetInteractions()

            client.attr[INTERACTING_NPC_ATTR] = WeakReference(npc)
            client.attr[INTERACTING_ITEM] = WeakReference(item)
            client.attr[INTERACTING_ITEM_ID] = item.id
            client.attr[INTERACTING_ITEM_SLOT] = itemSlot
            client.executePlugin(PawnPathAction.itemUsePlugin)
        /** Spell on NPC **/
        } else {
            val npc = world.npcs[message.npcIndex] ?: return
            val parent = message.componentHash shr 16

            if (!client.lock.canNpcInteract()) {
                return
            }

            log(client, "Spell on npc: npc=%d. index=%d, component=[%d:%d], movement=%d", npc.id, message.npcIndex, parent, child, message.movementType)

            client.interruptQueues()
            client.resetInteractions()

            if (message.movementType == 1 && world.privileges.isEligible(client.privilege, Privilege.ADMIN_POWER)) {
                client.moveTo(world.findRandomTileAround(npc.tile, 1) ?: npc.tile)
            }

            client.closeInterfaceModal()
            client.interruptQueues()
            client.resetInteractions()

            client.attr[INTERACTING_NPC_ATTR] = WeakReference(npc)
            client.attr[INTERACTING_COMPONENT_PARENT] = parent
            client.attr[INTERACTING_COMPONENT_CHILD] = child

            // TODO: add verification of active spellbook add in 196
            if (!world.plugins.executeSpellOnNpc(client, parent, child)) {
                client.writeMessage(Entity.NOTHING_INTERESTING_HAPPENS)
                if (world.devContext.debugMagicSpells) {
                    client.writeMessage("Unhandled magic spell: [$parent, $child]")
                }
            }
        }
    }
}