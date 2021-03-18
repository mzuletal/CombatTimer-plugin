package DamoCookie.CombatTimer.events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import DamoCookie.CombatTimer.CombatTimer;
import net.md_5.bungee.api.ChatColor;

public class PlayerTeleport implements Listener
{
	private CombatTimer plugin;
	
	public PlayerTeleport(CombatTimer plugin)
	{
		this.plugin = plugin;
	}
	
	@EventHandler
	public void OnTeleport(PlayerTeleportEvent Event)
	{
		Player player = Event.getPlayer();
		if (plugin.testEntity((Entity) player) != -1)
		{
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou are Combat Tagged, you can not teleport"));
			Event.setCancelled(true);
		}
	}
}
