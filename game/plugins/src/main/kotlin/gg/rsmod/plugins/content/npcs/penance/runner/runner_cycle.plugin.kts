package gg.rsmod.plugins.content.npcs.runner

val TARGET_FOOD_DELAY = TimerKey()
val KILL_RUNNER_PHASE_2 = TimerKey()
var KILL_RUNNER_PHASE_1 = TimerKey()

Runner.RUNNER_NPCS.forEach { runner ->
    on_npc_spawn(npc = runner) {
        val npc = npc
        npc.timers[TARGET_FOOD_DELAY] = 6
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
                runner.timers[KILL_RUNNER_PHASE_1] = 2
                return@on_timer
            }
        }
    }
    runner.timers[TARGET_FOOD_DELAY] = 6
}

on_timer(KILL_RUNNER_PHASE_1) {
    val runner = npc
    runner.forceChat("Yikes!")
    runner.timers[KILL_RUNNER_PHASE_2] = 4
}

on_timer(KILL_RUNNER_PHASE_2) {
    val runner = npc
    runner.world.remove(runner)
}