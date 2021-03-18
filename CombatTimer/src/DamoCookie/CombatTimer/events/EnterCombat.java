package DamoCookie.CombatTimer.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import DamoCookie.CombatTimer.CombatTimer;
import net.md_5.bungee.api.ChatColor;

public class EnterCombat implements Listener
{
	private CombatTimer plugin;
	
	public EnterCombat(CombatTimer plugin)
	{
		this.plugin = plugin;
	}
	
	int TaskID;
	
	@EventHandler
	public void OnHit(EntityDamageByEntityEvent event)
	{
		Entity Victime = event.getEntity();
		Entity Agressor = event.getDamager();
		FileConfiguration config = plugin.getConfig();
		int time = Integer.valueOf(config.getString("Config.Time"));
		
		if (Victime.getType().equals(EntityType.PLAYER) && Agressor.getType().equals(EntityType.PLAYER))
		{
			CountDown Count = new CountDown(plugin,time,(Player) Victime);
			if (plugin.testEntity(Victime) == -1)
			{
				Count.execute();
				Victime.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCombat Log Started"));
			}
			else
			{
				TaskID = plugin.getTaskID(plugin.testEntity(Victime));
				Bukkit.getScheduler().cancelTask(TaskID);
				plugin.removeTaskID(TaskID);
				plugin.removeEntity(Victime);
				Count.execute();
				Victime.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCombat Log Restarted"));
			}
			
			CountDown Count2 = new CountDown(plugin,time,(Player) Agressor);
			if (plugin.testEntity(Agressor) == -1)
			{
				Count2.execute();
				Agressor.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCombat Log Started"));
			}
			else
			{
				TaskID = plugin.getTaskID(plugin.testEntity(Agressor));
				Bukkit.getScheduler().cancelTask(TaskID);
				plugin.removeTaskID(TaskID);
				plugin.removeEntity(Agressor);
				Count2.execute();
				Agressor.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCombat Log Restarted"));
			}
			
			return;
		}
		else if (Victime instanceof Player && Agressor instanceof Projectile && ((Projectile) Agressor).getShooter() instanceof Player)
		{
			CountDown Count = new CountDown(plugin,time,(Player) Victime);
			Agressor = (Entity) ((Projectile) Agressor).getShooter();
			
			if (plugin.testEntity(Victime) == -1)
			{
				Count.execute();
				Victime.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCombat Log Started"));
			}
			else
			{
				TaskID = plugin.getTaskID(plugin.testEntity(Victime));
				Bukkit.getScheduler().cancelTask(TaskID);
				plugin.removeTaskID(TaskID);
				plugin.removeEntity(Victime);
				Count.execute();
				Victime.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCombat Log Restarted"));
			}
			
			CountDown Count2 = new CountDown(plugin,time,(Player) Agressor);
			if (plugin.testEntity(Agressor) == -1)
			{
				Count2.execute();
				Agressor.sendMessage("Combat Log Started");
			}
			else
			{
				TaskID = plugin.getTaskID(plugin.testEntity(Agressor));
				Bukkit.getScheduler().cancelTask(TaskID);
				plugin.removeTaskID(TaskID);
				plugin.removeEntity(Agressor);
				Count2.execute();
				Agressor.sendMessage("Combat Log Restarted");
			}
			
			return;
		}
	}
}