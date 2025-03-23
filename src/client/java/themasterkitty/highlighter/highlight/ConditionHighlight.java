package themasterkitty.highlighter.highlight;

import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.function.Predicate;

public class ConditionHighlight extends Highlight {
    private final Predicate<ItemStack> shouldHighlight;

    public ConditionHighlight(String name, Color def, Predicate<ItemStack> shouldHighlight) {
        super(name, def);
        this.shouldHighlight = shouldHighlight;
    }

    @Override
    public boolean shouldHighlight(ItemStack item) {
        return shouldHighlight.test(item);
    }
}
