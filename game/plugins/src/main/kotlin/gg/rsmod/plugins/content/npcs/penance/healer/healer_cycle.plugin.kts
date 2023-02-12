package gg.rsmod.plugins.content.npcs.penance.healer

val HEALER_TICK_0 = TimerKey()

Healer.HEALER_NPCS.forEach { healer ->
    on_npc_spawn(npc = healer) {
        val npc = npc
        npc.timers[HEALER_TICK_0] = 1
    }
}

on_timer(HEALER_TICK_0) {
    // do stuff
}
