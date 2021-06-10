package io.github.xenopyax.sounds.menu.menus;

import io.github.xenopyax.sounds.Main;
import io.github.xenopyax.sounds.menu.Menu;
import io.github.xenopyax.sounds.menu.PlayerMenuUtility;
import io.github.xenopyax.xenoapi.api.XItem;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SoundsMenu extends Menu {

    private final List<Sound> sounds;
    private final int maxPages;
    private int page = 0;
    private final int maxItemsPerPage = 45;
    private final HashMap<Integer, Sound> soundMap = new HashMap<>();

    public SoundsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
        this.sounds = Arrays.asList(Sound.values());
        this.maxPages = getMaxPages(null);
    }

    public SoundsMenu(PlayerMenuUtility playerMenuUtility, String filter) {
        super(playerMenuUtility);
        this.sounds = Arrays.stream(Sound.values()).filter(s -> localize(s.name()).toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
        this.maxPages = getMaxPages(filter);
    }

    @Override
    public String getMenuName() {
        return "Sounds | Page " + (page+1) + "/" + maxPages;
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        switch(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())) {
            case "Next Page":
                if ((page+1) >= maxPages) return;
                page++;
                open();
                break;
            case "Previous Page":
                if ((page+1) <= 0) return;
                page--;
                open();
                break;
            case "Search":
                playerMenuUtility.getPlayer().closeInventory();
                Main.getInstance().getApi().getQuestionAPI().ask(playerMenuUtility.getPlayer(), "§aWhat are you looking for?", answer -> new SoundsMenu(playerMenuUtility, answer).open());
                break;
            case "Close Page":
                playerMenuUtility.getPlayer().closeInventory();
                break;
            case "Adjust Pitch":
                float pitch = playerMenuUtility.getPitch();
                switch (e.getClick()) {
                    case RIGHT:
                        if(pitch > 0.0) {
                            pitch -= 0.1;
                            playerMenuUtility.setPitch(pitch);
                            setBorders();
                        }
                        break;
                    case LEFT:
                        if(pitch <= 2.0) {
                            pitch += 0.1;
                            playerMenuUtility.setPitch(pitch);
                            setBorders();
                        }
                        break;
                }
                break;
            default:
                if (!soundMap.containsKey(e.getSlot())) return;
                playerMenuUtility.getPlayer().playSound(playerMenuUtility.getPlayer().getLocation(), soundMap.get(e.getSlot()), 1.0F, playerMenuUtility.getPitch());
                break;
        }
    }

    public void setBorders() {
        for(int i = 45; i < 54; i++) inventory.setItem(i, new XItem(Material.STAINED_GLASS_PANE, (byte)15).setName("§0").setType(Material.STAINED_GLASS_PANE).build());
        if((page+1) > 1) inventory.setItem(45, new XItem().setName("§aPrevious Page").setType(Material.ARROW).build());
        inventory.setItem(47, new XItem().setName("§aAdjust Pitch").setLore(Arrays.asList("§6Pitch: "+new DecimalFormat("0.0").format(playerMenuUtility.getPitch()), "§cRight-click: -0.1", "§aLeft-click: +0.1")).setType(Material.REDSTONE_TORCH_ON).build());
        inventory.setItem(49, new XItem(Material.STAINED_GLASS_PANE, (byte) 14).setName("§cClose Page").setType(Material.STAINED_GLASS_PANE).build());
        inventory.setItem(51, new XItem().setType(Material.SIGN).setName("§bSearch").setLore(Arrays.asList("","§7Sounds: §8" + Sound.values().length,"§7On This Page: §8" + sounds.size())).build());
        if((page+1) < maxPages) inventory.setItem(53, new XItem().setName("§aNext Page").setType(Material.ARROW).build());
    }

    @Override
    public void setMenuItems() {
        int index;
        for (int i = 0; i < maxItemsPerPage; i++) {
            index = maxItemsPerPage * page + i;
            if (index >= sounds.size()) break;
            Sound sound = sounds.get(index);
            inventory.setItem(i, new XItem(Material.NOTE_BLOCK, (byte)0).setName("§aPlay §3" + localize(sound.name())).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).build());
            soundMap.put(i, sound);
        }
        setBorders();
    }

    private int getMaxPages(String filter) {
        int sounds = 0;
        for (Sound s : Sound.values()) {
            if (filter == null || s.name().toLowerCase().contains(filter.toLowerCase())) sounds++;
        }
        sounds = (int) Math.ceil(sounds*1D/ maxItemsPerPage);
        return sounds;
    }

    private String localize(String name) {
        StringBuilder localized = new StringBuilder();
        for(String split : name.split("_")) {
            if(localized.toString().equals("")) {
                localized = new StringBuilder(split.charAt(0) + split.substring(1).toLowerCase());
            }else {
                localized.append(" ").append(split.charAt(0)).append(split.substring(1).toLowerCase());
            }
        }
        return localized.toString();
    }

}
