package com.github.b1kku.notenoughflags.flags

import com.github.b1kku.notenoughflags.utils.WorldGuardUtils
import com.sk89q.worldguard.protection.flags.StateFlag
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemDamageEvent

class ItemDamage : Listener, FlagImplementor {

  override val flag = StateFlag("item-damage", true)
  override val description =
      """Prevent item damage, bedrock takes durability damage even when the block wasn't broken,
  this fires even when the event is cancelled to prevent that."""

  @EventHandler
  fun onItemDamage(event: PlayerItemDamageEvent) {
    if (WorldGuardUtils.isFlagAllowed(event.player, this.flag)) return
    event.isCancelled = true
  }
}
