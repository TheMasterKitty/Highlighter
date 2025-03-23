package themasterkitty.highlighter.highlight;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.List;

public class ItemHighlight extends Highlight {
    private final List<Item> types;

    public ItemHighlight(String name, Color def, Item... types) {
        super(name, def);
        this.types = List.of(types);
    }

    @Override
    public boolean shouldHighlight(ItemStack item) {
        return types.contains(item.getItem());
    }
}
