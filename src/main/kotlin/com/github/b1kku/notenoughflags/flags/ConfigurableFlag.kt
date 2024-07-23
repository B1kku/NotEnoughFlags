package com.github.b1kku.notenoughflags.flags

import org.bukkit.configuration.ConfigurationSection

interface ConfigurableFlag {
  
  val defaultConfig : Map<String, Any>

  fun updateConfig(config: ConfigurationSection?)

}
