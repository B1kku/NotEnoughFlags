package com.github.b1kku.notenoughflags.flags

import com.github.b1kku.notenoughflags.logger
import com.sk89q.worldguard.WorldGuard
import com.sk89q.worldguard.protection.flags.Flag
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException

sealed interface FlagImplementor {
  val flag: Flag<*>
  val description: String

  fun register() {
    logger.info("Registering WG flag: ${flag.name}")
    try {
      WorldGuard.getInstance().flagRegistry.register(flag)
    } catch (e: FlagConflictException) {
      logger.severe(
          """
              Failed to register ${flag.name}, likely due to another one already exiting with the same name.
              Crashing the plugin now for safety."
              """
      )
      throw e
    }
  }
}
