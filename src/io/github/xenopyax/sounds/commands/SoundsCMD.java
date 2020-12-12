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
import io.github.xenopyax.sounds.data.SoundsInventory;

public class SoundsCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			sender.sendMessage("This is not supported use a player!");
			return true;
		}else {
			Player player = (Player)sender;
			if(!Main.getSoundSettings().containsKey(player.getUniqueId())) {
				Main.getSoundSettings().put(player.getUniqueId(), 1.0f);
			}
			if(!Main.getInstance().getPlayerInvs().containsKey(player.getUniqueId())) {
				Main.getInstance().getPlayerInvs().put(player.getUniqueId(), new SoundsInventory(player));
			}
			Main.getInstance().getPlayerInvs().get(player.getUniqueId()).open(player, 1);
		}
		return true;
		
	}

}
