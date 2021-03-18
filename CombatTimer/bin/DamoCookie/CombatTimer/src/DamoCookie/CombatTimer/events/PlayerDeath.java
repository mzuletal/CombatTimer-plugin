package DamoCookie.CombatTimer.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import DamoCookie.CombatTimer.CombatTimer;
import net.md_5.bungee.api.ChatColor;

public class PlayerDeath implements Listener
{
	private CombatTimer plugin;
	
	public PlayerDeath(CombatTimer plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void OnDeath(PlayerDeathEvent Event)
	{
		Entity Death = (Entity) Event.getEntity();
		if (plugin.testEntity(Death) != -1)
		{
			int TaskID = plugin.getTaskID(plugin.testEntity(Death));
			Bukkit.getScheduler().cancelTask(TaskID);
			plugin.removeTaskID(TaskID);
			plugin.removeEntity(Death);
			Death.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have died, You are no longer in combat"));
		}
	}
}
