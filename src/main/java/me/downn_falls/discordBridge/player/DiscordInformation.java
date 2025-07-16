package me.downn_falls.discordBridge.bungee.player;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class DiscordInformation {

    private final UUID player;
    private final String id;
    private final String username;
    private final String email;
    private final JsonObject identificationJson;

    public DiscordInformation(UUID player, String id, String username, String email, JsonObject identificationJson) {
        this.player = player;
        this.id = id;
        this.username = username;
        this.email = email;
        this.identificationJson = identificationJson;
    }

    public UUID getPlayer() {
        return player;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public JsonObject getIdentificationJson() {
        return identificationJson;
    }
}
