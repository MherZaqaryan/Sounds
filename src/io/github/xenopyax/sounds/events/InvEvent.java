/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds.events;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.xenopyax.sounds.Main;
import io.github.xenopyax.sounds.data.SoundsInventory;
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
import io.github.xenopyax.xenoapi.interfaces.IQuestion;
import net.md_5.bungee.api.ChatColor;

public class InvEvent implements Listener {
	
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		if(Main.getInstance().getPlayerInvs().containsKey(e.getPlayer().getUniqueId())) {
			Main.getInstance().getPlayerInvs().remove(e.getPlayer().getUniqueId());
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getClickedInventory() == null) return;
		if(e.getCurrentItem() == null) return;
		
		if(Main.getInstance().getVersion().equals("v1_16_R3")) {
			if(!ItemUtils1_16_R3.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_16_R2")) {
			if(!ItemUtils1_16_R2.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_16_R1")) {
			if(!ItemUtils1_16_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_15_R1")) {
			if(!ItemUtils1_15_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_14_R1")) {
			if(!ItemUtils1_14_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_13_R2")) {
			if(!ItemUtils1_13_R2.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_13_R1")) {
			if(!ItemUtils1_13_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_12_R1")) {
			if(!ItemUtils1_12_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_11_R1")) {
			if(!ItemUtils1_11_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_10_R1")) {
			if(!ItemUtils1_10_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_9_R2")) {
			if(!ItemUtils1_9_R2.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_9_R1")) {
			if(!ItemUtils1_9_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_8_R3")) {
			if(!ItemUtils1_8_R3.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_8_R2")) {
			if(!ItemUtils1_8_R2.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else if(Main.getInstance().getVersion().equals("v1_8_R1")) {
			if(!ItemUtils1_8_R1.getNBT(e.getClickedInventory().getItem(0)).hasKey("sound")) return;
		}else {
			Main.getInstance().getServer().getConsoleSender().sendMessage("§a[Sounds] §4NMS version not supported! Disabling plugin.");
			Main.getInstance().getServer().getPluginManager().disablePlugin(Main.getInstance());
		}
		Player player = (Player) e.getWhoClicked();
		
		e.setCancelled(true);
		int page = 0;
		
		switch(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())) {
		case "Next Page":
			page = Integer.parseInt(e.getView().getTitle().split(" ")[3].split("/")[0]);
			if(!Main.getInstance().getPlayerInvs().containsKey(player.getUniqueId())) {
				Main.getInstance().getPlayerInvs().put(player.getUniqueId(), new SoundsInventory(player));
			}
			Main.getInstance().getPlayerInvs().get(player.getUniqueId()).open(player, page +1);
			break;
		case "Previous Page":
			page = Integer.parseInt(e.getView().getTitle().split(" ")[3].split("/")[0]);
			if(!Main.getInstance().getPlayerInvs().containsKey(player.getUniqueId())) {
				Main.getInstance().getPlayerInvs().put(player.getUniqueId(), new SoundsInventory(player));
			}
			Main.getInstance().getPlayerInvs().get(player.getUniqueId()).open(player, page-1);
			break;
		case "Search":
			player.closeInventory();
			Main.getInstance().getApi().getQuestionAPI().ask(player, "§aWhat are you looking for?", new IQuestion() {
				
				@Override
				public void onAnswer(String answer) {
					SoundsInventory inv = new SoundsInventory(player, answer);
					Main.getInstance().getPlayerInvs().put(player.getUniqueId(), inv);
					new BukkitRunnable() {
						
						@Override
						public void run() {
							inv.open(player, 1);
						}
					}.runTask(Main.getInstance());
				}
			});
			break;
		case "Close Page":
			player.closeInventory();
			break;
		case "Adjust Pitch":
			if(e.getClick() == ClickType.RIGHT) {
				float pitch = Main.getSoundSettings().get(player.getUniqueId());
				if(pitch > 0.1) {
					pitch -= 0.1;
					Main.getSoundSettings().put(player.getUniqueId(), pitch);
					updatePitch(player, e.getClickedInventory(), e.getClickedInventory().getItem(47), pitch);
				}
			}else if(e.getClick() == ClickType.LEFT) {
				float pitch = Main.getSoundSettings().get(player.getUniqueId());
				if(pitch < 1.0) {
					pitch += 0.1;
					Main.getSoundSettings().put(player.getUniqueId(), pitch);
					updatePitch(player, e.getClickedInventory(), e.getClickedInventory().getItem(47), pitch);
				}
			}
			break;
		default:
			if(Main.getInstance().getVersion().equals("v1_16_R3")) {
				if(ItemUtils1_16_R3.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_16_R3.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));				
				}
			}else if(Main.getInstance().getVersion().equals("v1_16_R2")) {
				if(ItemUtils1_16_R2.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_16_R2.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_16_R1")) {
				if(ItemUtils1_16_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_16_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_15_R1")) {
				if(ItemUtils1_15_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_15_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_14_R1")) {
				if(ItemUtils1_14_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_14_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_13_R2")) {
				if(ItemUtils1_13_R2.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_13_R2.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_13_R1")) {
				if(ItemUtils1_13_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_13_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_12_R1")) {
				if(ItemUtils1_12_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_12_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_11_R1")) {
				if(ItemUtils1_11_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_11_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_10_R1")) {
				if(ItemUtils1_10_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_10_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_9_R2")) {
				if(ItemUtils1_9_R2.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_9_R2.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_9_R1")) {
				if(ItemUtils1_9_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_9_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_8_R3")) {
				if(ItemUtils1_8_R3.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_8_R3.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_8_R2")) {
				if(ItemUtils1_8_R2.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_8_R2.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else if(Main.getInstance().getVersion().equals("v1_8_R1")) {
				if(ItemUtils1_8_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_8_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.playSound(player.getLocation(), sound, 1f, Main.getSoundSettings().get(player.getUniqueId()));
				}
			}else {
				Main.getInstance().getServer().getConsoleSender().sendMessage("§a[Sounds] §4NMS version not supported! Disabling plugin.");
				Main.getInstance().getServer().getPluginManager().disablePlugin(Main.getInstance());
			}
			break;
		}
		
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
