package io.github.xenopyax.sounds.menu;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class PlayerMenuUtility {

    @Getter private final Player player;
    @Getter @Setter private float pitch;

    public PlayerMenuUtility(Player player) {
        this.player = player;
    }

}
