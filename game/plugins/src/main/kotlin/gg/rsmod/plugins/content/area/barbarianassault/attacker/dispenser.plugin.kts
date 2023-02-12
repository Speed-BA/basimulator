package gg.rsmod.plugins.content.area.barbarianassault.attacker

on_obj_option(Objs.ATTACKER_ITEM_MACHINE, option = "Take-Arrows") {
    player.queue {
        player.lock()
        player.animate(798)
        if (!player.inventory.contains(Items.ATTACKER_HORN)) {
            player.inventory.add(item = Items.ATTACKER_HORN, amount = 1, assureFullInsertion = false)
        }
        player.inventory.add(item = Items.BULLET_ARROW_22227, amount = 250, assureFullInsertion = false)
        player.inventory.add(item = Items.FIELD_ARROW_22228, amount = 250, assureFullInsertion = false)
        player.inventory.add(item = Items.BLUNT_ARROW_22229, amount = 250, assureFullInsertion = false)
        player.inventory.add(item = Items.BARBED_ARROW_22230, amount = 250, assureFullInsertion = false)
        wait(1)
        player.unlock()
    }
}
