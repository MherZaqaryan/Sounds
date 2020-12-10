/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.xenopyax.sounds.api.XItem;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_10_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_11_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_12_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_13_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_13_R2;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_14_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_15_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_16_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_16_R2;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_16_R3;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_8_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_8_R2;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_8_R3;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_9_R1;
import io.github.xenopyax.sounds.api.nms.ItemUtils1_9_R2;
import io.github.xenopyax.sounds.commands.SoundsCMD;
import io.github.xenopyax.sounds.events.InvClickEvent;

public class Main extends JavaPlugin {
	
	private static Map<Integer, Inventory> invs = new HashMap<>();
	
	private static Main instance;
	private static String version;
	
	@Override
	public void onEnable() {
		
		instance = this;
		version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		if(!init()) return;
		getCommand("sounds").setExecutor(new SoundsCMD());
		Bukkit.getPluginManager().registerEvents(new InvClickEvent(), this);
		
	}
	
	private boolean init() {
		int maxPages = (Sound.values().length / 45);
		int page = 1;
		int slot = 0;
		
		Inventory inv = Bukkit.createInventory(null, 54, "Sounds | Page " + page + "/" + maxPages);
		for(Sound sound : Sound.values()) {
			if(slot < 45) {
				ItemStack item = new XItem(1, (byte)3).setName("§aPlay §3" + localize(sound.name())).setType(Material.WOOL)
						.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
				// TODO: implemtn version independecy
				if(getVersion().equals("v1_16_R3")) {
					inv.setItem(slot, ItemUtils1_16_R3.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_16_R2")) {
					inv.setItem(slot, ItemUtils1_16_R2.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_16_R1")) {
					inv.setItem(slot, ItemUtils1_16_R1.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_15_R1")) {
					inv.setItem(slot, ItemUtils1_15_R1.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_14_R1")) {
					inv.setItem(slot, ItemUtils1_14_R1.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_13_R2")) {
					inv.setItem(slot, ItemUtils1_13_R2.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_13_R1")) {
					inv.setItem(slot, ItemUtils1_13_R1.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_12_R1")) {
					inv.setItem(slot, ItemUtils1_12_R1.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_11_R1")) {
					inv.setItem(slot, ItemUtils1_11_R1.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_10_R1")) {
					inv.setItem(slot, ItemUtils1_10_R1.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_9_R2")) {
					inv.setItem(slot, ItemUtils1_9_R2.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_9_R1")) {
					inv.setItem(slot, ItemUtils1_9_R1.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_8_R3")) {
					inv.setItem(slot, ItemUtils1_8_R3.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_8_R2")) {
					inv.setItem(slot, ItemUtils1_8_R2.setHiddenData(item, "sound", sound.name()));
				}else if(getVersion().equals("v1_8_R1")) {
					inv.setItem(slot, ItemUtils1_8_R1.setHiddenData(item, "sound", sound.name()));
				}else {
					getServer().getConsoleSender().sendMessage("§a[Sounds] §4NMS version not supported! Disabling plugin.");
					getServer().getPluginManager().disablePlugin(Main.getInstance());
					return false;
				}
 				slot++;
			}else if(slot >= 45) {
			
				for(int i = 45; i < 54; i++) {
					inv.setItem(i, new XItem(1, (byte)15).setName("§0").setType(Material.STAINED_GLASS_PANE).build());
				}
				
				if(page > 1) inv.setItem(45, new XItem().setName("§aPrevious Page").setType(Material.ARROW).build());
				inv.setItem(49, new XItem(1, (byte)14).setName("§cClose Page").setType(Material.STAINED_GLASS_PANE).build());
				if(page < maxPages) inv.setItem(53, new XItem().setName("§aNext Page").setType(Material.ARROW).build());
				invs.put(page, inv);
				page++;
				slot = 0;
				inv = Bukkit.createInventory(null, 54, "Sounds | Page " + page + "/" + maxPages);
				
			}
		}
		
		for(int i = 45; i < 54; i++) {
			inv.setItem(i, new XItem(1, (byte)15).setName("§0").setType(Material.STAINED_GLASS_PANE).build());
		}
		
		if(page > 1) inv.setItem(45, new XItem().setName("§aPrevious Page").setType(Material.ARROW).build());
		inv.setItem(49, new XItem(1, (byte)14).setName("§cClose Page").setType(Material.STAINED_GLASS_PANE).build());
		if(page < maxPages) inv.setItem(53, new XItem().setName("§aNext Page").setType(Material.ARROW).build());
		invs.put(page, inv);
		
		return true;
	}

	private String localize(String name) {
		String localized = "";
		for(String split : name.split("_")) {
			if(localized.equals("")) {
				localized = split.substring(0, 1) + split.substring(1).toLowerCase();
			}else {
				localized += " " + split.substring(0, 1) + split.substring(1).toLowerCase();
			}
		}
		
		return localized;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public String getVersion() {
		return version;
	}

	public static Map<Integer, Inventory> getInvs() {
		return invs;
	}

}
