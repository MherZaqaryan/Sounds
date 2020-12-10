/*
Author: XenoPyax
Github: https://github.com/XenoPyax
Discord: XenoPyax#5647
*/

package io.github.xenopyax.sounds.nms;

import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_9_R2.NBTTagCompound;

public class ItemUtils1_9_R2 {
	
	public static ItemStack setHiddenData(ItemStack item, String key, Object value) {
		net.minecraft.server.v1_9_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemcompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		itemcompound.setString(key, value.toString());
		nmsItem.setTag(itemcompound);
		item = CraftItemStack.asBukkitCopy(nmsItem);
		return item;
	}
	
	public static NBTTagCompound getNBT(ItemStack item) {
		net.minecraft.server.v1_9_R2.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemcompound = (nmsItem.hasTag()) ? nmsItem.getTag() : new NBTTagCompound();
		return itemcompound;
	}
	
}
