package me.downn_falls.discordBridge.player;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class DiscordInformationManager {

    public static final HashMap<Player, DiscordInformation> DISCORD_INFORMATION = new HashMap<>();

    public static DiscordInformation getDiscordInformation(Player player) {
        return DISCORD_INFORMATION.getOrDefault(player, null);
    }
}
