/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.xenopyax.sounds.commands.SoundsCMD;
import io.github.xenopyax.sounds.data.SoundsInventory;
import io.github.xenopyax.sounds.events.InvClickEvent;
import io.github.xenopyax.xenoapi.XenoAPI;

public class Main extends JavaPlugin {
	
	private static Main instance;
	private static String version;
	private static SoundsInventory defaultInv;
	private static Map<UUID, SoundsInventory> playerInvs = new HashMap<>();
	
	private static XenoAPI api;
	
	@Override
	public void onEnable() {
		instance = this;
		api = new XenoAPI(this);
		version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		defaultInv = new SoundsInventory();
		
		getCommand("sounds").setExecutor(new SoundsCMD());
		Bukkit.getPluginManager().registerEvents(new InvClickEvent(), this);
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

	public SoundsInventory getDefaultInv() {
		return defaultInv;
	}

	public static Map<UUID, SoundsInventory> getPlayerInvs() {
		return playerInvs;
	}

}
