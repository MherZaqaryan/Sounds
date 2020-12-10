/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import io.github.xenopyax.sounds.Main;

public class SoundsCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			sender.sendMessage("This is not supported use a player!");
			return true;
		}else {
			Player player = (Player)sender;
			player.openInventory(Main.getInstance().getDefaultInv().get(1));
		}
		return true;
		
	}

}
