/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds.events;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
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
import io.github.xenopyax.xenoapi.interfaces.IQuestion;
import net.md_5.bungee.api.ChatColor;

public class InvClickEvent implements Listener {
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getClickedInventory() == null) return;
		if(e.getCurrentItem() == null) return;
		// TODO: implemtn version independecy
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
		Inventory inv;
		
		switch(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())) {
		case "Next Page":
			page = Integer.parseInt(e.getView().getTitle().split(" ")[3].split("/")[0]);
			if(!Main.getPlayerInvs().containsKey(player.getUniqueId())) {
				inv = Main.getInstance().getDefaultInv().get(page+1);
			}else {
				inv = Main.getPlayerInvs().get(player.getUniqueId()).get(page+1);
			}
			player.openInventory(inv);
			break;
		case "Previous Page":
			page = Integer.parseInt(e.getView().getTitle().split(" ")[3].split("/")[0]);
			if(!Main.getPlayerInvs().containsKey(player.getUniqueId())) {
				inv = Main.getInstance().getDefaultInv().get(page-1);
			}else {
				inv = Main.getPlayerInvs().get(player.getUniqueId()).get(page-1);
			}
			player.openInventory(inv);
			break;
		case "Search":
			player.closeInventory();
			Main.getInstance().getApi().getQuestionAPI().ask(player, "§aWhat are you looking for?", new IQuestion() {
				
				@Override
				public void onAnswer(String answer) {
					SoundsInventory inv = new SoundsInventory(answer);
					Main.getPlayerInvs().put(player.getUniqueId(), inv);
					new BukkitRunnable() {
						
						@Override
						public void run() {
							player.openInventory(inv.get(1));
						}
					}.runTask(Main.getInstance());
				}
			});
			break;
		case "Close Page":
			player.closeInventory();
			break;
		default:
			if(Main.getInstance().getVersion().equals("v1_16_R3")) {
				if(ItemUtils1_16_R3.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_16_R3.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);				}
			}else if(Main.getInstance().getVersion().equals("v1_16_R2")) {
				if(ItemUtils1_16_R2.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_16_R2.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_16_R1")) {
				if(ItemUtils1_16_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_16_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_15_R1")) {
				if(ItemUtils1_15_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_15_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_14_R1")) {
				if(ItemUtils1_14_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_14_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_13_R2")) {
				if(ItemUtils1_13_R2.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_13_R2.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_13_R1")) {
				if(ItemUtils1_13_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_13_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_12_R1")) {
				if(ItemUtils1_12_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_12_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_11_R1")) {
				if(ItemUtils1_11_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_11_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_10_R1")) {
				if(ItemUtils1_10_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_10_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_9_R2")) {
				if(ItemUtils1_9_R2.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_9_R2.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_9_R1")) {
				if(ItemUtils1_9_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_9_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_8_R3")) {
				if(ItemUtils1_8_R3.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_8_R3.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_8_R2")) {
				if(ItemUtils1_8_R2.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_8_R2.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else if(Main.getInstance().getVersion().equals("v1_8_R1")) {
				if(ItemUtils1_8_R1.getNBT(e.getCurrentItem()).hasKey("sound")) {
					Sound sound = Sound.valueOf(ItemUtils1_8_R1.getNBT(e.getCurrentItem()).getString("sound"));
					player.getWorld().playSound(player.getLocation(), sound, 1f, 1f);
				}
			}else {
				Main.getInstance().getServer().getConsoleSender().sendMessage("§a[Sounds] §4NMS version not supported! Disabling plugin.");
				Main.getInstance().getServer().getPluginManager().disablePlugin(Main.getInstance());
			}
			break;
		}
		
	}

}
