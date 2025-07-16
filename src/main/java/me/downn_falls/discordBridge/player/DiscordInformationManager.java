package me.downn_falls.discordBridge.bungee.player;

import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;

public class DiscordInformationManager {

    public static final HashMap<ProxiedPlayer, DiscordInformation> DISCORD_INFORMATION = new HashMap<>();

    public static DiscordInformation getDiscordInformation(ProxiedPlayer player) {
        return DISCORD_INFORMATION.getOrDefault(player, null);
    }
}
