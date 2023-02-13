package gg.rsmod.plugins.content.area.barbarianassault.healer

import gg.rsmod.plugins.content.combat.dealHit
import gg.rsmod.plugins.content.mechanics.poison.Poison

on_obj_option(Objs.HEALER_ITEM_MACHINE, option = "Stock-Up") {
    player.queue {
        player.lock()
        player.animate(798)
        if (!player.inventory.contains(Items.HEALER_HORN)) {
            player.inventory.add(item = Items.HEALER_HORN, amount = 1, assureFullInsertion = false)
        }
        if (!player.inventory.contains(Items.HEALING_VIAL)) {
            player.inventory.add(item = Items.HEALING_VIAL, amount = 1, assureFullInsertion = false)
        }
        while (player.inventory.hasSpace) {
            player.inventory.add(item = Items.POISONED_TOFU, amount = 1)
            player.inventory.add(item = Items.POISONED_WORMS, amount = 1)
            player.inventory.add(item = Items.POISONED_MEAT, amount = 1)
        }
        wait(1)
        player.unlock()
    }
}
