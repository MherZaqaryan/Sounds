/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds.api;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class XItem {
	
	private ItemStack item;
	private ItemMeta meta;
	
	public XItem() {
		this.item = new ItemStack(Material.STICK);
		meta = item.getItemMeta();
	}
	
	public XItem(Integer amount, short data) {
		this.item = new ItemStack(Material.STICK, amount, data);
		meta = item.getItemMeta();
	}
	
	public XItem(ItemStack item) {
		this.item = item;
		meta = item.getItemMeta();
	}
	
	// ItemStack
	public XItem setType(Material material) {
		item.setType(material);
		item.setItemMeta(meta);
		return this;
	}
	
	public XItem addEnchantment(Enchantment ench, int level) {
		item.addUnsafeEnchantment(ench, level);
		item.setItemMeta(meta);
		return this;
	}
	
	public XItem setAmount(int amount) {
		item.setAmount(amount);
		item.setItemMeta(meta);
		return this;
	}
	
	// Meta data
	public XItem setName(String name) {
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return this;
	}
	
	public XItem setLore(List<String> lore) {
		meta.setLore(lore);
		item.setItemMeta(meta);
		return this;
	}
	
	public XItem addItemFlag(ItemFlag... itemFlags) {
		meta.addItemFlags(itemFlags);
		item.setItemMeta(meta);
		return this;
	}
	
	public XItem setDurability(int damage) {
    	Damageable d = (Damageable)meta;
    	if(damage > item.getType().getMaxDurability()) {
    		d.setDamage(item.getType().getMaxDurability());
    	}else {
        	Integer dmg = damage - item.getType().getMaxDurability();
    		d.setDamage(dmg);
    	}
    	meta = (ItemMeta) d;
		item.setItemMeta(meta);
    	return this;
    }
	
	public ItemStack build() {
		item.setItemMeta(meta);
		return item;
	}

}
