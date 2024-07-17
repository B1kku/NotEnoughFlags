package your.group.name.projectname

import org.bukkit.plugin.java.JavaPlugin

class ProjectName : JavaPlugin()
{
  override fun onEnable()
  {
    logger.info("Plugin enabled with version: ${this.pluginMeta.version}")
  }
  
  override fun onDisable()
  {

  }

}
