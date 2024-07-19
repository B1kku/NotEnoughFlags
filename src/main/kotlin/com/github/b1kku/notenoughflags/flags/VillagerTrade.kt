package com.github.b1kku.notenoughflags.flags

import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.entity.EntityType
import com.github.b1kku.notenoughflags.utils.WorldGuardUtils
import com.sk89q.worldguard.protection.flags.StateFlag

class VillagerTrade: Listener, FlagImplementor {

  override val flag = StateFlag("villager-trade", true)
  override val description = "Asd"

  @EventHandler(ignoreCancelled = true)
  fun preventVillagerTrading(event: PlayerInteractEntityEvent) {
    if (event.hand != EquipmentSlot.HAND) return;
    if (event.rightClicked.type != EntityType.VILLAGER) return;
    // Flag is allowed, so don't prevent it
    if (WorldGuardUtils.isFlagAllowed(event.player, this.flag)) return;
    event.isCancelled = true
  }

}
