package gg.rsmod.plugins.content.npcs.penance.healer

import gg.rsmod.game.model.attr.NPC_ALIVE_SINCE_ATTR
import gg.rsmod.plugins.content.mechanics.poison.poison

fun poisonHealer(n: Npc, p: Player): () -> Unit = {
    if (n.timers.isNotEmpty) {
        n.timers.clear()
    }

    // Determine if the healer has been spawned for no more than 4t
    if (world.currentCycle - n.attr[NPC_ALIVE_SINCE_ATTR]!! <= 4) {
        p.message("Sending auto poison")
        var autoPoisonTimer = TimerKey()
        n.timers[autoPoisonTimer] = 2
        on_timer(autoPoisonTimer) {
            n.hit(4, HitType.POISON)
        }
    }

    val poisonDelay = TimerKey()
    var damageCount = 20
    var poisonDamage = 4

    n.timers[poisonDelay] = 0
    on_timer(poisonDelay) {
        if (damageCount % 5 == 0 && damageCount != 20) {
            poisonDamage--
        }
        n.hit(poisonDamage, HitType.POISON)
        damageCount--
        n.timers[poisonDelay] = 5
    }
}

Healer.HEALER_NPCS.forEach { healer ->
    on_npc_spawn(npc = healer) {
        npc.attr[NPC_ALIVE_SINCE_ATTR] = world.currentCycle
    }

    on_item_on_npc(item = Items.POISONED_TOFU, npc = healer) {
        player.queue {
            val npc = player.getInteractingNpc()
            npc.poison(4, poisonHealer(npc, player))
            player.inventory.remove(Items.POISONED_TOFU, 1)
        }
    }

    on_item_on_npc(item = Items.POISONED_WORMS, npc = healer) {
        player.queue {
            val npc = player.getInteractingNpc()
            npc.poison(4, poisonHealer(npc, player))
            player.inventory.remove(Items.POISONED_WORMS, 1)
        }
    }

    on_item_on_npc(item = Items.POISONED_MEAT, npc = healer) {
        player.queue {
            val npc = player.getInteractingNpc()
            npc.poison(4, poisonHealer(npc, player))
            player.inventory.remove(Items.POISONED_MEAT, 1)
        }
    }
}

// Wave 1
set_combat_def(Npcs.PENANCE_HEALER_5766) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 27
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}

// Wave 2
set_combat_def(Npcs.PENANCE_HEALER_5767) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 32
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}

// Wave 3
set_combat_def(Npcs.PENANCE_HEALER_5768) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 37
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}

// Wave 4
set_combat_def(Npcs.PENANCE_HEALER_5769) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 43
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}

// Wave 5
set_combat_def(Npcs.PENANCE_HEALER_5770) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 49
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}

// Wave 6
set_combat_def(Npcs.PENANCE_HEALER_5771) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 55
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}

// Wave 7
set_combat_def(Npcs.PENANCE_HEALER_5772) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 60
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}

// Wave 8
set_combat_def(Npcs.PENANCE_HEALER_5773) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 67
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}

// Wave 9
set_combat_def(Npcs.PENANCE_HEALER_5774) {
    configs {
        attackSpeed = 100
        respawnDelay = 50
    }

    stats {
        hitpoints = 76
        magic = 0
        defence = 0
    }

    anims {
        attack = 729
        block = 5391
        death = 5106
    }
}