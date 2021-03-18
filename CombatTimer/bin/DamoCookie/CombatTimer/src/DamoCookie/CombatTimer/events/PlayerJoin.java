package DamoCookie.CombatTimer.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import DamoCookie.CombatTimer.CombatTimer;
import net.md_5.bungee.api.ChatColor;

public class PlayerJoin implements Listener
{
	private CombatTimer plugin;
	
	public PlayerJoin(CombatTimer plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void OnJoin(PlayerJoinEvent Event)
	{
		Entity Player = (Entity) Event.getPlayer();
		FileConfiguration config = plugin.getConfig();
		int time = Integer.valueOf(config.getString("Config.Time"));
		CountDown Count = new CountDown(plugin,time,(Player) Player);
		
		if (plugin.testEntity(Player) != -1)
		{
			int TaskID = plugin.getTaskID(plugin.testEntity(Player));
			Bukkit.getScheduler().cancelTask(TaskID);
			plugin.removeTaskID(TaskID);
			plugin.removeEntity(Player);
			Count.execute();
			Player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou logged out in combat, Restarting Time"));
		}
	}
}
