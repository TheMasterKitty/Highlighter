package themasterkitty.highlighter;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import themasterkitty.highlighter.highlight.Highlight;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main implements ClientModInitializer {
    public static boolean enabled = true, autoDisable = false, hotbar = false, potionColor = false, shulkerColor = false;
    private static final HashMap<Item, Color> shulkers = new HashMap<>();
    static {
        shulkers.put(Items.SHULKER_BOX, new Color(0xC7B7CE));
        shulkers.put(Items.WHITE_SHULKER_BOX, new Color(0xF9FFFE));
        shulkers.put(Items.ORANGE_SHULKER_BOX, new Color(0xF9801D));
        shulkers.put(Items.MAGENTA_SHULKER_BOX, new Color(0xC74EBD));
        shulkers.put(Items.LIGHT_BLUE_SHULKER_BOX, new Color(0x3AB3DA));
        shulkers.put(Items.YELLOW_SHULKER_BOX, new Color(0xFED83D));
        shulkers.put(Items.LIME_SHULKER_BOX, new Color(0x80C71F));
        shulkers.put(Items.PINK_SHULKER_BOX, new Color(0xF38BAA));
        shulkers.put(Items.GRAY_SHULKER_BOX, new Color(0x474F52));
        shulkers.put(Items.LIGHT_GRAY_SHULKER_BOX, new Color(0x9D9D97));
        shulkers.put(Items.CYAN_SHULKER_BOX, new Color(0x169C9C));
        shulkers.put(Items.PURPLE_SHULKER_BOX, new Color(0x8932B8));
        shulkers.put(Items.BLUE_SHULKER_BOX, new Color(0x3C44AA));
        shulkers.put(Items.BROWN_SHULKER_BOX, new Color(0x835432));
        shulkers.put(Items.GREEN_SHULKER_BOX, new Color(0x5E7C16));
        shulkers.put(Items.RED_SHULKER_BOX, new Color(0xB02E26));
        shulkers.put(Items.BLACK_SHULKER_BOX, new Color(0x1D1D21));
    }

    @Override
    public void onInitializeClient() {
        ConfigManager.load();
    }

    public static void highlight(DrawContext context, ItemStack stack, int x, int y) {
        if (!enabled) return;
        if (stack == null || stack.isEmpty()) return;
        Color color = new Color(0, 0, 0, 0);

        for (Highlight h : Highlights.highlights) {
            if (!h.isEnabled()) continue;
            if (!h.shouldHighlight(stack)) continue;

            color = h.getColor();
            break;
        }

        if (List.of(Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION).contains(stack.getItem()) && potionColor) {
            if (stack.getComponents().contains(DataComponentTypes.POTION_CONTENTS))
                color = new Color(Objects.requireNonNull(stack.getComponents().get(DataComponentTypes.POTION_CONTENTS)).getColor());
        }
        if (shulkers.containsKey(stack.getItem()) && shulkerColor) {
            color = shulkers.get(stack.getItem());
        }
        if (color.getAlpha() == 0) return;

        context.fill(x, y, x + 16, y + 16, color.getRGB());
    }
}
