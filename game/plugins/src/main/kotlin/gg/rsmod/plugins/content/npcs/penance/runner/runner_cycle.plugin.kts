package gg.rsmod.plugins.content.npcs.runner

/**
 * Currently a proof of concept
 * None of the real runner mechanics are implemented as of yet
 */

val TARGET_FOOD_DELAY = TimerKey()

Runner.RUNNER_NPCS.forEach { runner ->
    on_npc_spawn(npc = runner) {
        val npc = npc
        npc.timers[TARGET_FOOD_DELAY] = 5
    }
}

on_timer(TARGET_FOOD_DELAY) {
    val runner = npc
    val foodOnGround = runner.world.getGroundItem { groundItem ->  groundItem.item == Items.TOFU || groundItem.item == Items.WORMS || groundItem.item == Items.CRACKERS }
    if (foodOnGround != null) {
        runner.walkTo(foodOnGround.tile)
        if (runner.tile.x == foodOnGround.tile.x && runner.tile.z == foodOnGround.tile.z) {
            runner.forceChat("Chomp!")
            runner.world.remove(foodOnGround)
            if ((runner.tile.x == 1901 || runner.tile.x == 1902 || runner.tile.x == 1900) && (runner.tile.z == 5475 || runner.tile.z == 5474 || runner.tile.z == 5473)) {
                runner.queue { kill_runner(this, runner) }
                return@on_timer
            }
        }
    }
    runner.timers[TARGET_FOOD_DELAY] = 5
}

suspend fun kill_runner(it: QueueTask, n: Npc) {
    it.wait(1)
    n.forceChat("Yikes!")
    it.wait(1)
    world.remove(n)
}