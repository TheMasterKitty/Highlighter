package themasterkitty.highlighter.mixins;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import themasterkitty.highlighter.Config;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin {
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItem(Lnet/minecraft/item/ItemStack;III)V"), method = "drawSlot")
    public void drawItem(DrawContext context, ItemStack stack, int x, int y, int seed) {
        Config.highlight(context, stack, x, y);
        context.drawItem(stack, x, y, seed);
    }
    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawItemWithoutEntity(Lnet/minecraft/item/ItemStack;III)V"), method = "drawSlot")
    public void drawItemWithoutEntity(DrawContext context, ItemStack stack, int x, int y, int seed) {
        Config.highlight(context, stack, x, y);
        context.drawItem(stack, x, y, seed);
    }
}