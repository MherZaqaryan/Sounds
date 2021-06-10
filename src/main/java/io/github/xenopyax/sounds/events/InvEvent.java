/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds.events;

import io.github.xenopyax.sounds.menu.Menu;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class InvEvent implements Listener {

	@EventHandler
	public void onMenuClick(InventoryClickEvent e) {
		InventoryHolder ih = e.getInventory().getHolder();
		if (!(ih instanceof Menu)) return;
		e.setCancelled(true);
		if (e.getCurrentItem() == null) return;
		if (e.getCurrentItem().getType().equals(Material.AIR)) return;
		((Menu) ih).handleMenu(e);
	}

}
