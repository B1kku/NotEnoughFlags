package com.github.b1kku.notenoughflags.flags

import com.github.b1kku.notenoughflags.utils.WorldGuardUtils
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityChangeBlockEvent

class GravityBlocksFall : Listener, FlagImplementor {

  override val flag = StateFlag("gravity-blocks-fall", true)
  override val description = "Prevent gravity-affected blocks from falling"

  @EventHandler(ignoreCancelled = true)
  fun onBlockChange(event: EntityChangeBlockEvent) {
    if (!event.block.type.hasGravity()) return
    if (WorldGuardUtils.isFlagAllowed(event.block, this.flag)) return
    event.isCancelled = true
  }
}
