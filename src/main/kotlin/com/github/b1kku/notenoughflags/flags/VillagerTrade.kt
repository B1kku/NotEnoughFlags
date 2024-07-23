package com.github.b1kku.notenoughflags.flags

import com.github.b1kku.notenoughflags.utils.WorldGuardUtils
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.configuration.ConfigurationSection

class VillagerTrade : Listener, FlagImplementor, ConfigurableFlag {

  override val flag = StateFlag("villager-trade", true)
  override val description = "Asd"

  val wanderingTraderConfig = "include-wandering-trader" to true
  override val defaultConfig = mapOf(wanderingTraderConfig)

  var isValidEntity: (EntityType) -> Boolean = { entityType ->
    when (entityType) {
      EntityType.VILLAGER, EntityType.WANDERING_TRADER -> true
      else -> false
    }
  }

  override fun updateConfig(config: ConfigurationSection?) {
    config!!.getBoolean(wanderingTraderConfig.first).let {
      if (!it) isValidEntity = { entityType -> entityType == EntityType.VILLAGER }
    }
  }

  @EventHandler(ignoreCancelled = true)
  fun preventVillagerTrading(event: PlayerInteractEntityEvent) {
    if (event.hand != EquipmentSlot.HAND) return
    val mobType = event.rightClicked.type
    if (!isValidEntity(mobType)) return
    if (WorldGuardUtils.isFlagAllowed(event.player, flag)) return
    event.isCancelled = true
  }
}
