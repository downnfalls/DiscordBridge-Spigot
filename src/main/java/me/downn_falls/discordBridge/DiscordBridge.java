package me.downn_falls.discordBridge;

import me.downn_falls.discordBridge.bungee.Messaging;
import me.downn_falls.discordBridge.event.PlayerEvents;
import me.downn_falls.discordBridge.hook.PlaceholderAPIHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class DiscordBridge extends JavaPlugin {

    private static DiscordBridge plugin;
    private static PlaceholderAPIHook placeholderAPIHook;

    @Override
    public void onEnable() {

        plugin = this;

        checkIfBungee();
        if ( !getServer().getPluginManager().isPluginEnabled( this ) ) return;

        getServer().getMessenger().registerIncomingPluginChannel( this, "discordbridge:bungeecord", new Messaging() ); // we register the incoming channel
        getServer().getMessenger().registerOutgoingPluginChannel( this, "discordbridge:spigot" );

        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);

        placeholderAPIHook = new PlaceholderAPIHook();
        placeholderAPIHook.register();

        for (Player player : Bukkit.getOnlinePlayers()) {
            Messaging.request(player);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        placeholderAPIHook.unregister();
    }

    private void checkIfBungee()
    {
        if ( !getServer().spigot().getConfig().getConfigurationSection("settings").getBoolean( "bungeecord" ) )
        {
            getLogger().severe( "This server is not BungeeCord." );
            getLogger().severe( "If the server is already hooked to BungeeCord, please enable it into your spigot.yml aswell." );
            getLogger().severe( "Plugin disabled!" );
            getServer().getPluginManager().disablePlugin( this );
        }
    }

    public static DiscordBridge getPlugin() { return plugin; }
}
