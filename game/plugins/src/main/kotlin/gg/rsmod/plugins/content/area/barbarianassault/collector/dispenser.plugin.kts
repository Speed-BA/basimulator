package gg.rsmod.plugins.content.area.barbarianassault.collector

on_obj_option(Objs.COLLECTOR_CONVERTER, option = "Convert") {
    player.queue {
        player.lock()
        player.animate(798)
        if (player.inventory.contains(Items.BLUE_EGG)) {
            player.inventory.replace(Items.BLUE_EGG, Items.GREEN_EGG)
        }
        if (player.inventory.contains(Items.GREEN_EGG)) {
            player.inventory.replace(Items.GREEN_EGG, Items.RED_EGG)
        }
        if (player.inventory.contains(Items.RED_EGG)) {
            player.inventory.replace(Items.RED_EGG, Items.GREEN_EGG)
        }
        wait(1)
        player.unlock()
    }
}
