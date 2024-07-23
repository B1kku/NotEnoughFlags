package com.github.b1kku.notenoughflags

import com.github.b1kku.notenoughflags.flags.ConfigurableFlag
import com.github.b1kku.notenoughflags.flags.FlagImplementor
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.collections.first
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

lateinit var logger: Logger

class NotEnoughFlags : JavaPlugin() {
  override fun onEnable() {
    com.github.b1kku.notenoughflags.logger = logger

    logger.log(Level.INFO, "Plugin enabled with version: ${pluginMeta.version}")

    var customFlags = getAllFlags()

    for (flag in customFlags) {
      var defaultConfig: Map<String, Any> = mapOf("enable" to true)
      if (flag is ConfigurableFlag) {
        // Override defaultConfig, meaning if flag contains enable false, it'll override this
        defaultConfig = defaultConfig + flag.defaultConfig
      }
      config.addDefault(flag.flag.name, defaultConfig)
    }
    config.options().copyDefaults(true)
    saveConfig()
    customFlags = customFlags.filter { config.getBoolean("${it.flag.name}.enable") }
    for (flag in customFlags) {
      flag.register()
      if (flag is ConfigurableFlag) {
        flag.updateConfig(config.getConfigurationSection(flag.flag.name))
      }
      if (flag is Listener) {
        server.pluginManager.registerEvents(flag, this)
      }
    }
  }

  override fun onDisable() {}

  fun getAllFlags(): List<FlagImplementor> {
    return FlagImplementor::class.sealedSubclasses.map { flagClass ->
      flagClass.constructors.first().call()
    }
  }
}
