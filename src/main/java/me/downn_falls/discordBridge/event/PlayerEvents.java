package me.downn_falls.discordBridge.event;

import me.downn_falls.discordBridge.DiscordBridge;
import me.downn_falls.discordBridge.bungee.Messaging;
import me.downn_falls.discordBridge.player.DiscordInformationManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(DiscordBridge.getPlugin(), () -> {
            Messaging.request(event.getPlayer());
        }, 40);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        DiscordInformationManager.DISCORD_INFORMATION.remove(event.getPlayer());
    }
}
