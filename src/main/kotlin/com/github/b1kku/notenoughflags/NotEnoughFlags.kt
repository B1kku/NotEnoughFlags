package com.github.b1kku.notenoughflags

import java.util.logging.Level
import java.util.logging.Logger
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.PluginLogger
import org.bukkit.event.Listener
import org.bukkit.Bukkit
import com.github.b1kku.notenoughflags.flags.VillagerTrade
import com.github.b1kku.notenoughflags.flags.FlagImplementor
import com.github.b1kku.notenoughflags.logger

lateinit var logger : Logger


class NotEnoughFlags : JavaPlugin() {
  override fun onEnable() {
    com.github.b1kku.notenoughflags.logger = logger
  
    logger.log(Level.INFO, "Plugin enabled with version: ${pluginMeta.version}")
    val flagClasses = FlagImplementor::class.sealedSubclasses
    for (flagClass in flagClasses) {
      val flagInstance = flagClass.constructors.first().call()
      flagInstance.register()
      if (flagInstance is Listener) {
        server.pluginManager.registerEvents(flagInstance, this)
      }
    }
  }

  override fun onDisable() {}
}
