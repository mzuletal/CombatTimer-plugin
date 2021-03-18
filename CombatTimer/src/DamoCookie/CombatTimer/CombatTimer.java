package DamoCookie.CombatTimer;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import DamoCookie.CombatTimer.commands.CommandPlugin;
import DamoCookie.CombatTimer.events.EnterCombat;
import DamoCookie.CombatTimer.events.PlayerDeath;
import DamoCookie.CombatTimer.events.PlayerJoin;
import DamoCookie.CombatTimer.events.PlayerTeleport;

public class CombatTimer extends JavaPlugin
{
	public String rutaConfig;
	PluginDescriptionFile pdffile = getDescription();
	public String version = pdffile.getVersion();
	public String name = ChatColor.translateAlternateColorCodes('&', "&f[&e"+pdffile.getName()+"&f]");
	
	private ArrayList<String> TaggedEntities;
	private ArrayList<String> TaskIDS;
	
	public void addEntity(Entity ent)
	{
		TaggedEntities.add(ent.getName());
	}
	
	public void addTaskID(int TaskID)
	{
		TaskIDS.add(String.valueOf(TaskID));
	}
	
	public void removeEntity(Entity ent)
	{
		TaggedEntities.remove(ent.getName());
	}
	
	public void removeTaskID(int TaskID)
	{
		TaskIDS.remove(String.valueOf(TaskID));
	}
	
	public int getTaskID(int pos)
	{
		return Integer.parseInt(TaskIDS.get(pos));
	}
	
	public int testEntity(Entity ent)
	{
		if(TaggedEntities.contains(ent.getName()))
		{ return TaggedEntities.indexOf(ent.getName());}
		else
		{ return -1;}
	}
	
	public void onEnable()
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', name+" &aPlugin has been enabled"));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', name+" &aVersion: &e"+version));
		registerEvents();
		registerCommands();
		Config();
		
		TaggedEntities = new ArrayList<String>();
		TaskIDS = new ArrayList<String>();
	}
	
	public void onDisable()
	{
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', name+" &cPlugin has been disabled"));
	}
	
	public void registerEvents()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new EnterCombat(this), this);
		pm.registerEvents(new PlayerTeleport(this), this);
		pm.registerEvents(new PlayerDeath(this), this);
		pm.registerEvents(new PlayerJoin(this), this);
	}
	
	public void registerCommands()
	{
		this.getCommand("combattimer").setExecutor(new CommandPlugin(this));
	}
	
	public void Config()
	{
		File config = new File(this.getDataFolder(),"config.yml");
		rutaConfig = config.getPath();
		if(!config.exists())
		{
			this.getConfig().options().copyDefaults(true);
			saveConfig();
			Bukkit.getConsoleSender().sendMessage(name+ChatColor.WHITE+" Creating Config.yml file");
		}
		else
		{
			Bukkit.getConsoleSender().sendMessage(name+ChatColor.WHITE+" Config.yml file found");
		}
	}
	
}