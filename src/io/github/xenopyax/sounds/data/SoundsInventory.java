/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import io.github.xenopyax.sounds.Main;
import io.github.xenopyax.sounds.nms.ItemUtils1_10_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_11_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_12_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_13_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_13_R2;
import io.github.xenopyax.sounds.nms.ItemUtils1_14_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_15_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_16_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_16_R2;
import io.github.xenopyax.sounds.nms.ItemUtils1_16_R3;
import io.github.xenopyax.sounds.nms.ItemUtils1_8_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_8_R2;
import io.github.xenopyax.sounds.nms.ItemUtils1_8_R3;
import io.github.xenopyax.sounds.nms.ItemUtils1_9_R1;
import io.github.xenopyax.sounds.nms.ItemUtils1_9_R2;
import io.github.xenopyax.xenoapi.api.XItem;

public class SoundsInventory {
	
	private Map<Integer, Inventory> invs = new HashMap<>();
	
	public SoundsInventory(Player player) {
		generateInventories(player, null);
	}
	
	public SoundsInventory(Player player, String filter) {
		generateInventories(player, filter);
	}
	
	private void generateInventories(Player player, String filter) {
		int maxPages = getMaxPages(filter);
		int page = 1;
		int slot = 0;
		
		Inventory inv = Bukkit.createInventory(player, 54, "Sounds | Page " + page + "/" + maxPages);
		for(Sound sound : Sound.values()) {
			if(slot < 45) {
				ItemStack item = new XItem(Material.WOOL, (byte)3).setName("§aPlay §3" + localize(sound.name()))
						.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build();
				if(filter == null || sound.name().toLowerCase().contains(filter.toLowerCase())) {
					if(Main.getInstance().getVersion().equals("v1_16_R3")) {
						inv.setItem(slot, ItemUtils1_16_R3.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_16_R2")) {
						inv.setItem(slot, ItemUtils1_16_R2.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_16_R1")) {
						inv.setItem(slot, ItemUtils1_16_R1.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_15_R1")) {
						inv.setItem(slot, ItemUtils1_15_R1.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_14_R1")) {
						inv.setItem(slot, ItemUtils1_14_R1.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_13_R2")) {
						inv.setItem(slot, ItemUtils1_13_R2.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_13_R1")) {
						inv.setItem(slot, ItemUtils1_13_R1.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_12_R1")) {
						inv.setItem(slot, ItemUtils1_12_R1.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_11_R1")) {
						inv.setItem(slot, ItemUtils1_11_R1.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_10_R1")) {
						inv.setItem(slot, ItemUtils1_10_R1.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_9_R2")) {
						inv.setItem(slot, ItemUtils1_9_R2.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_9_R1")) {
						inv.setItem(slot, ItemUtils1_9_R1.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_8_R3")) {
						inv.setItem(slot, ItemUtils1_8_R3.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_8_R2")) {
						inv.setItem(slot, ItemUtils1_8_R2.setHiddenData(item, "sound", sound.name()));
					}else if(Main.getInstance().getVersion().equals("v1_8_R1")) {
						inv.setItem(slot, ItemUtils1_8_R1.setHiddenData(item, "sound", sound.name()));
					}else {
						Bukkit.getServer().getConsoleSender().sendMessage("§a[Sounds] §4NMS version not supported! Disabling plugin.");
						Bukkit.getServer().getPluginManager().disablePlugin(Main.getInstance());
						return;
					}
	 				slot++;
				}
			}else if(slot >= 45) {
			
				for(int i = 45; i < 54; i++) {
					inv.setItem(i, new XItem(Material.STAINED_GLASS_PANE, (byte)15).setName("§0").setType(Material.STAINED_GLASS_PANE).build());
				}
				
				if(page > 1) inv.setItem(45, new XItem().setName("§aPrevious Page").setType(Material.ARROW).build());
				List<String> lore = Arrays.asList("§6Pitch: " + Main.getSoundSettings().get(player.getUniqueId()), "§cRight-click: -0.1", "§aLeft-click: +0.1");
				inv.setItem(47, new XItem().setName("§aAdjust Pitch").setLore(lore).setType(Material.REDSTONE_TORCH_ON).build());
				inv.setItem(49, new XItem(Material.STAINED_GLASS_PANE, (byte)14).setName("§cClose Page").setType(Material.STAINED_GLASS_PANE).build());
				inv.setItem(51, new XItem().setType(Material.SIGN).setName("§bSearch").build());
				if(page < maxPages) inv.setItem(53, new XItem().setName("§aNext Page").setType(Material.ARROW).build());
				invs.put(page, inv);
				page++;
				slot = 0;
				inv = Bukkit.createInventory(null, 54, "Sounds | Page " + page + "/" + maxPages);
				
			}
		}
		
		for(int i = 45; i < 54; i++) {
			inv.setItem(i, new XItem(Material.STAINED_GLASS_PANE, (byte)15).setName("§0").setType(Material.STAINED_GLASS_PANE).build());
		}
		
		if(page > 1) inv.setItem(45, new XItem().setName("§aPrevious Page").setType(Material.ARROW).build());
		inv.setItem(47, new XItem().setName("§aAdjust Pitch").setLore(Arrays.asList("§6Pitch: %pitch%", "§cRight-click: -0.1", "§aLeft-click: +0.1")).setType(Material.REDSTONE_TORCH_ON).build());
		inv.setItem(49, new XItem(Material.STAINED_GLASS_PANE, (byte)14).setName("§cClose Page").setType(Material.STAINED_GLASS_PANE).build());
		inv.setItem(51, new XItem().setType(Material.SIGN).setName("§bSearch").build());
		if(page < maxPages) inv.setItem(53, new XItem().setName("§aNext Page").setType(Material.ARROW).build());
		invs.put(page, inv);
	}
	
	private int getMaxPages(String filter) {
		int sounds = 0;
		for(Sound s : Sound.values()) {
			if(filter == null || s.name().toLowerCase().contains(filter.toLowerCase())) {
				sounds++;
			}
		}
		return (sounds / 45) < 1 ? 1 : (sounds / 45);
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

	public Inventory get(int i) {
		return invs.get(i);
	}

	public void open(Player player, int page) {
		Inventory inv = invs.get(page);
		updatePitch(player, inv, inv.getItem(47), Main.getSoundSettings().get(player.getUniqueId()));
		player.openInventory(inv);
	}
	
	private void updatePitch(Player player, Inventory inventory, ItemStack item, float pitch) {
		XItem xitem = new XItem(item);
		List<String> lore = item.getItemMeta().getLore();
		List<String> temp = new ArrayList<>();
		String lore1 = "§6Pitch: " + new DecimalFormat("0.0").format(pitch);
		lore.remove(0);
		temp.add(lore1);
		temp.addAll(lore);
		inventory.setItem(47, xitem.setLore(temp).build());		
	}

}
