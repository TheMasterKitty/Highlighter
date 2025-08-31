package themasterkitty.highlighter;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import themasterkitty.highlighter.highlight.*;

import java.awt.*;
import java.util.ArrayList;

public class Highlights {
    public static final ArrayList<Highlight> highlights = new ArrayList<>();
    static {
        highlights.add(new ConditionHighlight("Totem (When Missing)", new Color(0xFFC800),
            i -> i.getItem() == Items.TOTEM_OF_UNDYING &&
                MinecraftClient.getInstance().player != null &&
                (
                    MinecraftClient.getInstance().player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING ||
                    MinecraftClient.getInstance().player.getInventory().main.stream().limit(9).noneMatch(item -> item.getItem() == Items.TOTEM_OF_UNDYING)
                )
            )
        );

        highlights.add(new EnchantHighlight("Piercing Crossbow", new Color(0xFF0000), "piercing"));
        highlights.add(new EnchantHighlight("Multishot Crossbow", new Color(0x00FF00), "multishot"));
        highlights.add(new EnchantHighlight("Breach Mace", new Color(0x00FFD9), "breach"));
        highlights.add(new EnchantHighlight("Density Mace", new Color(0x00AAFF), "density"));
        highlights.add(new EnchantHighlight("Silk Touch", new Color(0x919191), "silk_touch"));
        highlights.add(new EnchantHighlight("Fortune", new Color(0x36BA2D), "fortune"));

        highlights.add(new ItemHighlight("Totem", new Color(0xFFC800), Items.TOTEM_OF_UNDYING));
        highlights.add(new ItemHighlight("Elytra", new Color(0xDBDBDB), Items.ELYTRA));
        highlights.add(new ItemHighlight("Rocket", new Color(0xF26939), Items.FIREWORK_ROCKET));
        highlights.add(new ItemHighlight("Pearl", new Color(0x13A889), Items.ENDER_PEARL));
        highlights.add(new ItemHighlight("Gapple", new Color(0xF7C109), Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE));
        highlights.add(new ItemHighlight("XP", new Color(0xAFF26A), Items.EXPERIENCE_BOTTLE));
        highlights.add(new ItemHighlight("Crystal", new Color(0xF238F5), Items.END_CRYSTAL));
        highlights.add(new ItemHighlight("Obsidian", new Color(0x2D2B2B), Items.OBSIDIAN));
        highlights.add(new ItemHighlight("Anchor", new Color(0x8F13E5), Items.RESPAWN_ANCHOR));
        highlights.add(new ItemHighlight("Glowstone", new Color(0xFFDD00), Items.GLOWSTONE));
        highlights.add(new ItemHighlight("Shield", new Color(0x9FF127), Items.SHIELD));
        highlights.add(new ItemHighlight("Mace", new Color(0xC9FBFF), Items.MACE));
        highlights.add(new ItemHighlight("Potion", new Color(0x951DC4), Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION));
        highlights.add(new ItemHighlight("Cobweb", new Color(0xF5F5F5), Items.COBWEB));
        highlights.add(new ItemHighlight("Arrow", new Color(0xDA53F5), Items.ARROW, Items.SPECTRAL_ARROW, Items.TIPPED_ARROW));
        highlights.add(new ItemHighlight("Rail", new Color(0xD1B666), Items.RAIL, Items.POWERED_RAIL, Items.ACTIVATOR_RAIL, Items.DETECTOR_RAIL));
        highlights.add(new ItemHighlight("TNT Cart", new Color(0xE33B4F), Items.TNT_MINECART));

        highlights.add(new MatchHighlight("Sword", new Color(0xC7CED3), "_sword"));
        highlights.add(new MatchHighlight("Axe", new Color(0xD5500A), "_axe"));
        highlights.add(new MatchHighlight("Pickaxe", new Color(0xFD5662), "_pickaxe"));
        highlights.add(new MatchHighlight("Shulker", new Color(0x4A2F8A), "shulker_box"));
        highlights.add(new MatchHighlight("Armor", new Color(0x590094), "_helmet", "_chestplate", "_leggings", "_boots"));
    }
}
