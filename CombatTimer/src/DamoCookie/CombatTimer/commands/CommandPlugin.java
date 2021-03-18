package DamoCookie.CombatTimer.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DamoCookie.CombatTimer.CombatTimer;

public class CommandPlugin implements CommandExecutor
{
	private CombatTimer plugin;
	
	public CommandPlugin(CombatTimer plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command comando, String label, String[] args) 
	{
		if(!(sender instanceof Player))
		{
			if (args.length > 0)
			{
				if(args[0].equalsIgnoreCase("reload"))
				{
					plugin.reloadConfig();
					Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.name+" &aHas been reloaded"));
					return true;
				}
				else
				{
					Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&cUnknown Command"));
					return false;
				}
			}
			else
			{
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&aCombatTimer Plugin"));
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&fDeveloper -> &bDamoCookie"));
				Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',"&aReload executing /CombatTimer reload"));
				return false;
			}
		}
		else
		{
			Player player = (Player) sender;
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCommands cant be executed by player"));
			return false;
		}
	}
}
