package me.downn_falls.discordBridge.hook;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.downn_falls.discordBridge.player.DiscordInformation;
import me.downn_falls.discordBridge.player.DiscordInformationManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "downn_falls"; //
    }

    @Override
    public String getIdentifier() {
        return "discordbridge"; //
    }

    @Override
    public String getVersion() {
        return "1.0.0"; //
    }

    @Override
    public String onRequest(OfflinePlayer p, String params) {
        if (p instanceof Player player) {

            DiscordInformation information = DiscordInformationManager.getDiscordInformation(player);

            switch (params) {
                case "verify" -> {
                    return String.valueOf(information != null);
                }
                case "username" -> {
                    if (information != null) {
                        return information.getUsername();
                    } else return "";
                }
                case "email" -> {
                    if (information != null) {
                        return information.getEmail();
                    } else return "";
                }
                case "id" -> {
                    if (information != null) {
                        return information.getId();
                    } else return "";
                }
                default -> {
                    return "";
                }
            }
        } else {
            return "";
        }
    }
}
