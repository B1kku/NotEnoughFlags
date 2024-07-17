package com.github.b1kku.notenoughflags

import org.bukkit.plugin.java.JavaPlugin

class NotEnoughFlags : JavaPlugin()
{
  override fun onEnable()
  {
    logger.info("Plugin enabled with version: ${pluginMeta.version}")
  }
  
  override fun onDisable()
  {

  }

}
