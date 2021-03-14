package DamoCookie.CombatTimer.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import DamoCookie.CombatTimer.CombatTimer;
import net.md_5.bungee.api.ChatColor;

public class CountDown 
{
	int TaskID;
	private CombatTimer plugin;
	int time;
	private Entity entity;
	
	public CountDown(CombatTimer plugin,int time,Player entity)
	{
		this.plugin = plugin;
		this.time = time;
		this.entity = entity;
	}
	
	public void execute()
	{
		BukkitScheduler sh = Bukkit.getServer().getScheduler();
		TaskID = sh.scheduleSyncRepeatingTask(plugin, new Runnable() 
		{
			public void run()
			{
				if(time == 0)
				{
					Bukkit.getScheduler().cancelTask(TaskID);
					entity.sendMessage(ChatColor.translateAlternateColorCodes('&',"&eYou have left combat"));
					plugin.removeEntity(entity);
					plugin.removeTaskID(TaskID);
					return;
				}
				else
				{ 
					entity.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bTime Left: &f"+time));
					time--;
				}
			}
		}, 0L, 20);
		plugin.addEntity(entity);
		plugin.addTaskID(TaskID);
	}
}