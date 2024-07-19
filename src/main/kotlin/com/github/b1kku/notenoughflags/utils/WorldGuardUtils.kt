package com.github.b1kku.notenoughflags.utils

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.bukkit.WorldGuardPlugin
import com.sk89q.worldguard.protection.flags.StateFlag
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException
import org.bukkit.Bukkit
import org.bukkit.block.Block
import org.bukkit.entity.Player

object WorldGuardUtils {
  private val sessionManager = WorldGuard.getInstance().platform.sessionManager
  private val regionContainer = WorldGuard.getInstance().platform.regionContainer

  fun isFlagAllowed(player: Player, flag: StateFlag): Boolean {
    val localPlayer = WorldGuardPlugin.inst().wrapPlayer(player)
    if (sessionManager.hasBypass(localPlayer, localPlayer.world)) {
      return true
    }
    val applicableRegions = regionContainer.createQuery().getApplicableRegions(localPlayer.location)
    return applicableRegions.testState(null, flag)
  }

  fun isFlagAllowed(block: Block, flag: StateFlag): Boolean {
    val applicableRegions =
        regionContainer.createQuery().getApplicableRegions(BukkitAdapter.adapt(block.location))
    return applicableRegions.testState(null, flag)
  }
}
