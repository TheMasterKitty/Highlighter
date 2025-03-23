package themasterkitty.highlighter.highlight;

import net.minecraft.item.ItemStack;

import java.awt.*;

public abstract class Highlight {
    public final String name;
    private final Color def;

    private boolean enabled = true;
    private Color color;

    public Highlight(String name, Color def) {
        this.name = name;
        this.color = def;
        this.def = def;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getDefaultColor() {
        return def;
    }

    public abstract boolean shouldHighlight(ItemStack item);
}
