package gg.rsmod.plugins.content.area.barbarianassault.spawns

import gg.rsmod.plugins.api.cfg.Items
import gg.rsmod.plugins.api.cfg.Npcs

/*** Barbarian Assault Defender Items ***/
spawn_item(item = Items.LOGS, amount = 1, x = 1885, z = 5487)
spawn_item(item = Items.LOGS, amount = 1, x = 1886, z = 5486)
spawn_item(item = Items.HAMMER, amount = 1, x = 1887, z = 5485)

/***
 * Would probably use something like on_timer to make waves
 * ***/
spawn_npc(npc= Npcs.PENANCE_RUNNER_5748, x = 1892, z = 5487, walkRadius = 0, direction = Direction.SOUTH)
spawn_npc(npc= Npcs.PENANCE_HEALER_5766, x = 1898, z = 5485, walkRadius = 0, direction = Direction.SOUTH)
