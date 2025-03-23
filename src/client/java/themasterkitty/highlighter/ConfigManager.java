package themasterkitty.highlighter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigManager {
    private static final Gson gson = new Gson();
    private static final File config = FabricLoader.getInstance().getConfigDir().resolve("highlighter.config.json").toFile();

    public static void save() {
        JsonObject save = new JsonObject();

        save.addProperty("enabled", Main.enabled);
        save.addProperty("hotbar", Main.hotbar);
        save.addProperty("potion-color", Main.potionColor);
        save.addProperty("shulker-color", Main.shulkerColor);

        Highlights.highlights.forEach(h -> {
            JsonObject obj = new JsonObject();

            obj.addProperty("enabled", h.isEnabled());
            obj.addProperty("color", h.getColor().getRGB());

            save.add(h.name, obj);
        });

        try (FileWriter writer = new FileWriter(config)) {
            writer.write(gson.toJson(save));
        }
        catch (IOException e) {
            System.out.println("Failed to save highlighter config!\n" + e.getLocalizedMessage());
        }
    }
    public static void load() {
        if (!config.exists()) {
            save();
            return;
        }
        try (Scanner reader = new Scanner(config)) {
            StringBuilder json = new StringBuilder();
            while (reader.hasNextLine()) json.append(reader.nextLine()).append("\n");

            JsonObject read = JsonParser.parseString(json.toString().trim()).getAsJsonObject();

            if (read.has("enabled"))
                Main.enabled = read.get("enabled").getAsBoolean();
            if (read.has("hotbar"))
                Main.hotbar = read.get("hotbar").getAsBoolean();
            if (read.has("potion-color"))
                Main.potionColor = read.get("potion-color").getAsBoolean();
            if (read.has("shulker-color"))
                Main.potionColor = read.get("shulker-color").getAsBoolean();

            Highlights.highlights.forEach(h -> {
                if (!read.has(h.name)) return;
                JsonObject obj = read.get(h.name).getAsJsonObject();

                h.setEnabled(obj.get("enabled").getAsBoolean());
                h.setColor(new Color(obj.get("color").getAsInt()));
            });
        }
        catch (IOException e) {
            System.out.println("Failed to read highlighter config!\n" + e.getLocalizedMessage());
        }
    }
}
