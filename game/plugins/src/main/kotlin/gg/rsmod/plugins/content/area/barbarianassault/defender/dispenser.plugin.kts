package gg.rsmod.plugins.content.area.barbarianassault.defender

on_obj_option(Objs.DEFENDER_ITEM_MACHINE, option = "Stock-Up") {
    player.queue {
        player.lock()
        player.animate(798)
        if (!player.inventory.contains(Items.DEFENDER_HORN)) {
            player.inventory.add(item = Items.DEFENDER_HORN, amount = 1, assureFullInsertion = false)
        }
        while (player.inventory.hasSpace) {
            player.inventory.add(item = Items.CRACKERS, amount = 1)
            player.inventory.add(item = Items.TOFU, amount = 1)
            player.inventory.add(item = Items.WORMS, amount = 1)
        }
        wait(1)
        player.unlock()
    }
}
