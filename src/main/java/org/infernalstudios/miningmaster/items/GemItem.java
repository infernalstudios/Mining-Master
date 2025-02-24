package org.infernalstudios.miningmaster.items;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class GemItem extends Item {
    public GemItem() {
        super(new Item.Properties());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(
                Language.getInstance().has(this.getDescriptionId() + ".tooltip") ?
                    Component.translatable(this.getDescriptionId() + ".tooltip") :
                    Component.translatable("miningmaster.item.tooltip.fallback_gem")
            );
        } else {
            tooltip.add(Component.translatable("miningmaster.item.tooltip.hold_shift"));
        }
    }
}
