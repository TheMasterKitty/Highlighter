package themasterkitty.highlighter;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import dev.isxander.yacl3.gui.controllers.ColorController;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.Objects;

public class Config implements ModMenuApi {
    @SerialEntry
    public static boolean hotbar = false;
    @SerialEntry
    public static Color piercing = new Color(0xFF0000);
    @SerialEntry
    public static Color multishot = new Color(0x00FF00);
    @SerialEntry
    public static Color breach = new Color(0x00FFD9);
    @SerialEntry
    public static Color density = new Color(0x00AAFF);
    @SerialEntry
    public static Color totem = new Color(0xFFC800);
    @SerialEntry
    public static Color elytra = new Color(0xDBDBDB);
    @SerialEntry
    public static Color rocket = new Color(0xF26939);
    @SerialEntry
    public static Color pearl = new Color(0x13A889);
    @SerialEntry
    public static Color gaps = new Color(0xF7C109);
    @SerialEntry
    public static Color xp = new Color(0xAFF26A);
    @SerialEntry
    public static Color crystal = new Color(0xF238F5);
    @SerialEntry
    public static Color obsidian = new Color(0x2D2B2B);
    @SerialEntry
    public static Color anchor = new Color(0x8F13E5);
    @SerialEntry
    public static Color glowstone = new Color(0xFFDD00);
    @SerialEntry
    public static Color shield = new Color(0x9FF127);
    @SerialEntry
    public static Color axe = new Color(0xD5500A);
    @SerialEntry
    public static Color pickaxe = new Color(0xFD5662);

    private static boolean hasEnchant(ItemStack stack, String name) {
        return stack.getEnchantments()
                .getEnchantments().stream()
                .filter(e -> e.getKey().isPresent())
                .anyMatch(e -> Objects.equals(e.getKey().get().getValue().getPath(), name));
    }
    public static void highlight(DrawContext context, ItemStack stack, int x, int y) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (stack != null && !stack.isEmpty()  && client.world != null) {
            Item type = stack.getItem();
            String id = Registries.ITEM.getId(type).getPath();
            Color color = new Color(0, 0, 0, 0);
            if (type == Items.CROSSBOW) {
                if (hasEnchant(stack, "multishot"))
                    color = multishot;
                else if (hasEnchant(stack, "piercing"))
                    color = piercing;
            }
            else if (type == Items.MACE) {
                if (hasEnchant(stack, "breach"))
                    color = breach;
                else if (hasEnchant(stack, "density"))
                    color = density;
            }
            else if (type == Items.TOTEM_OF_UNDYING)
                color = totem;
            else if (type == Items.ELYTRA)
                color = elytra;
            else if (type == Items.FIREWORK_ROCKET)
                color = rocket;
            else if (type == Items.ENDER_PEARL)
                color = pearl;
            else if (type == Items.GOLDEN_APPLE)
                color = gaps;
            else if (type == Items.EXPERIENCE_BOTTLE)
                color = xp;
            else if (type == Items.END_CRYSTAL)
                color = crystal;
            else if (type == Items.OBSIDIAN)
                color = obsidian;
            else if (type == Items.RESPAWN_ANCHOR)
                color = anchor;
            else if (type == Items.GLOWSTONE)
                color = glowstone;
            else if (type == Items.SHIELD)
                color = shield;
            else if (id.endsWith("_axe"))
                color = axe;
            else if (id.endsWith("_pickaxe"))
                color = pickaxe;

            if (color.getAlpha() != 0) {
                context.fill(x, y, x + 16, y + 16, color.getRGB());
            }
        }
    }
    public static final ConfigClassHandler<Config> CONFIG = ConfigClassHandler.createBuilder(Config.class)
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("highlighter.json"))
                    .build())
            .build();
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent ->
            YetAnotherConfigLib.create(CONFIG, ((defaults, config, builder) ->
                    builder
                        .save(CONFIG::save)
                        .title(Text.literal("Highlighter Settings"))
                            .category(ConfigCategory.createBuilder()
                                    .name(Text.literal("Colors"))
                                    .option(Option.<Boolean>createBuilder()
                                            .name(Text.literal("Color Hotbar"))
                                            .description(OptionDescription.of(Text.literal("Enable to color your hotbar when outside of your inventory")))
                                            .binding(hotbar, () -> hotbar, value -> hotbar = value)
                                            .customController(opt -> new BooleanController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Piercing Color"))
                                            .description(OptionDescription.of(Text.literal("Color to highlight crossbows with piercing")))
                                            .binding(piercing, () -> piercing, value -> piercing = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Multishot Color"))
                                            .description(OptionDescription.of(Text.literal("Color to highlight crossbows with multishot")))
                                            .binding(multishot, () -> multishot, value -> multishot = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Breach Color"))
                                            .description(OptionDescription.of(Text.literal("Color to highlight maces with breach")))
                                            .binding(breach, () -> breach, value -> breach = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Density Color"))
                                            .description(OptionDescription.of(Text.literal("Color to highlight maces with density")))
                                            .binding(density, () -> density, value -> density = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Totem Color"))
                                            .binding(totem, () -> totem, value -> totem = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Elytra Color"))
                                            .binding(elytra, () -> elytra, value -> elytra = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Rocket Color"))
                                            .binding(rocket, () -> rocket, value -> rocket = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Pearl Color"))
                                            .binding(pearl, () -> pearl, value -> pearl = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Gapple Color"))
                                            .binding(gaps, () -> gaps, value -> gaps = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("XP Bottle Color"))
                                            .binding(xp, () -> xp, value -> xp = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Crystal Color"))
                                            .binding(crystal, () -> crystal, value -> crystal = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Obsidian Color"))
                                            .binding(obsidian, () -> obsidian, value -> obsidian = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Respawn Anchor Color"))
                                            .binding(anchor, () -> anchor, value -> anchor = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Glowstone Color"))
                                            .binding(glowstone, () -> glowstone, value -> glowstone = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Shield Color"))
                                            .binding(shield, () -> shield, value -> shield = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Axe Color"))
                                            .binding(axe, () -> axe, value -> axe = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .option(Option.<Color>createBuilder()
                                            .name(Text.literal("Pickaxe Color"))
                                            .binding(pickaxe, () -> pickaxe, value -> pickaxe = value)
                                            .customController(opt -> new ColorController(opt, true))
                                            .build())
                                    .build())
            )).generateScreen(parent);
    }
}
