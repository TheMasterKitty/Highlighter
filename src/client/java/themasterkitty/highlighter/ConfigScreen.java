package themasterkitty.highlighter;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import dev.isxander.yacl3.gui.controllers.ColorController;
import net.minecraft.text.Text;
import themasterkitty.highlighter.highlight.Highlight;

import java.awt.*;

public class ConfigScreen implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        ConfigCategory.Builder colors = ConfigCategory.createBuilder()
                .name(Text.literal("Colors"));
        ConfigCategory.Builder toggles = ConfigCategory.createBuilder()
                .name(Text.literal("Toggles"));

        for (Highlight h : Highlights.highlights) {
            colors.option(Option.<Color>createBuilder()
                    .name(Text.literal(h.name + " Color"))
                    .binding(h.getDefaultColor(), h::getColor, h::setColor)
                    .customController(opt -> new ColorController(opt, true))
                    .build());

            toggles.option(Option.<Boolean>createBuilder()
                    .name(Text.literal(h.name + " Highlighting Enabled"))
                    .binding(true, h::isEnabled, h::setEnabled)
                    .customController(opt -> new BooleanController(opt, true))
                    .build());
        }

        ConfigCategory.Builder options = ConfigCategory.createBuilder()
                .name(Text.literal("Options"));

        options.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Highlighter Enabled"))
                .binding(true, () -> Main.enabled, value -> Main.enabled = value)
                .customController(opt -> new BooleanController(opt, true))
                .build());
        options.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Color Hotbar"))
                .description(OptionDescription.of(Text.literal("Enable to color your hotbar when outside of your inventory")))
                .binding(false, () -> Main.hotbar, value -> Main.hotbar = value)
                .customController(opt -> new BooleanController(opt, true))
                .build());
        options.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Use Potion Color"))
                .description(OptionDescription.of(Text.literal("Uses a potion's default color rather than a static one")))
                .binding(false, () -> Main.potionColor, value -> Main.potionColor = value)
                .customController(opt -> new BooleanController(opt, true))
                .build());
        options.option(Option.<Boolean>createBuilder()
                .name(Text.literal("Use Shulker Color"))
                .description(OptionDescription.of(Text.literal("Uses a shulker box's color rather than a static one")))
                .binding(false, () -> Main.shulkerColor, value -> Main.shulkerColor = value)
                .customController(opt -> new BooleanController(opt, true))
                .build());

        return parent ->
            YetAnotherConfigLib.createBuilder()
                    .save(ConfigManager::save)
                    .title(Text.literal("Highlighter Settings"))
                    .category(options.build())
                    .category(colors.build())
                    .category(toggles.build())
                    .build()
                    .generateScreen(parent);
    }
}
