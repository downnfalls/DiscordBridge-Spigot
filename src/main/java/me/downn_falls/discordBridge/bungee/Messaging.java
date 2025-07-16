package me.downn_falls.discordBridge.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonParser;
import me.downn_falls.discordBridge.DiscordBridge;
import me.downn_falls.discordBridge.player.DiscordInformation;
import me.downn_falls.discordBridge.player.DiscordInformationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class Messaging implements PluginMessageListener {


    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes)
    {
        if (!channel.equalsIgnoreCase("discordbridge:bungeecord")) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
        String subChannel = in.readUTF();
        if ( subChannel.equalsIgnoreCase("Information")) {

            String status = in.readUTF();
            if (status.equalsIgnoreCase("ok")) {
                Player p = Bukkit.getPlayer(UUID.fromString(in.readUTF()));
                String id = in.readUTF(); // this data could be whatever you want
                String email = in.readUTF(); // this data could be whatever you want
                String username = in.readUTF();
                String json = in.readUTF();

                if (p != null) {
                    DiscordInformationManager.DISCORD_INFORMATION.put(p, new DiscordInformation(p.getUniqueId(), id, username, email, JsonParser.parseString(json).getAsJsonObject()));
                }
            }
        } else if (subChannel.equalsIgnoreCase("Revoke")) {
            String status = in.readUTF();
            if (status.equalsIgnoreCase("ok")) {
                Player p = Bukkit.getPlayer(UUID.fromString(in.readUTF()));

                if (p != null) {
                    DiscordInformationManager.DISCORD_INFORMATION.remove(p);
                }
            }
        }
    }

    public static void request(Player player) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF("RequestInformation");
            out.writeUTF(player.getUniqueId().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        player.sendPluginMessage(DiscordBridge.getPlugin(), "discordbridge:spigot", stream.toByteArray());
    }


}
