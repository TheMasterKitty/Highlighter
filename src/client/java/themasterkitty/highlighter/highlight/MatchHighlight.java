package themasterkitty.highlighter.highlight;

import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;

import java.awt.*;
import java.util.List;

public class MatchHighlight extends Highlight {
    private final List<String> ids;

    public MatchHighlight(String name, Color def, String... ids) {
        super(name, def);
        this.ids = List.of(ids);
    }

    @Override
    public boolean shouldHighlight(ItemStack item) {
        return ids.stream().anyMatch(id -> Registries.ITEM.getId(item.getItem()).getPath().endsWith(id));
    }
}
