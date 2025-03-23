package themasterkitty.highlighter.highlight;

import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.Objects;

public class EnchantHighlight extends Highlight {
    private final String type;

    public EnchantHighlight(String name, Color def, String type) {
        super(name, def);
        this.type = type;
    }

    @Override
    public boolean shouldHighlight(ItemStack item) {
        return hasEnchant(item, type);
    }

    private static boolean hasEnchant(ItemStack stack, String name) {
        return stack.getEnchantments()
                .getEnchantments().stream()
                .filter(e -> e.getKey().isPresent())
                .anyMatch(e -> Objects.equals(e.getKey().get().getValue().getPath(), name));
    }
}
