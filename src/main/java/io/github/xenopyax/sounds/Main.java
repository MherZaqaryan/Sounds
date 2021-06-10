/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds;

import java.util.HashMap;

import io.github.xenopyax.sounds.menu.PlayerMenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.xenopyax.sounds.commands.SoundsCMD;
import io.github.xenopyax.sounds.events.InvEvent;
import io.github.xenopyax.xenoapi.XenoAPI;

public class Main extends JavaPlugin {
	
	private static Main instance;
	private static String version;
	private static HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();
	
	private static XenoAPI api;
	
	@Override
	public void onEnable() {
		instance = this;
		api = new XenoAPI(this);
		version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		
		getCommand("sounds").setExecutor(new SoundsCMD());
		Bukkit.getPluginManager().registerEvents(new InvEvent(), this);
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public String getVersion() {
		return version;
	}

	public XenoAPI getApi() {
		return api;
	}

	public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
		PlayerMenuUtility playerMenuUtility;
		if (playerMenuUtilityMap.containsKey(p)) return playerMenuUtilityMap.get(p);
		playerMenuUtility = new PlayerMenuUtility(p);
		playerMenuUtilityMap.put(p, playerMenuUtility);
		return playerMenuUtility;
	}

}
