package net.lmor.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket;

import net.lmor.botanicalextramachinery.ModItems;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenDrawLabelText;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketUltimate;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.lmor.botanicalextramachinery.gui.AllBars;
import net.lmor.botanicalextramachinery.gui.Bars;
import net.lmor.botanicalextramachinery.gui.SlotInfo;
import net.lmor.botanicalextramachinery.util.GhostItemRenderer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.client.core.helper.RenderHelper;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenAlfheimMarketUltimate extends ExtraScreenBase<ContainerAlfheimMarketUltimate> {

    BlockEntityAlfheimMarketUltimate blockEntity;
    ScreenAddInventory screenAddInventory = new ScreenAddInventory(ScreenInventory.ULTIMATE);
    Bars bars;
    SlotInfo slotInfo;

    public ScreenAlfheimMarketUltimate(ContainerAlfheimMarketUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title);

        bars = new Bars(this);
        slotInfo = new SlotInfo(this);

        this.imageWidth = ContainerAlfheimMarketUltimate.WIDTH_GUI;
        this.imageHeight = ContainerAlfheimMarketUltimate.HEIGHT_GUI;

        this.bars.setBar(AllBars.MANA);
        this.bars.setDrawCoord(33, 118);

        blockEntity = this.menu.getBlockEntity();

        Map<Integer, int[]> slots = new HashMap<>();
        slots.put(0, new int[] {90, 96});

        slotInfo.setCoord(slots);
        slotInfo.setTranslatableText(new String[] { "botanicalextramachinery.tooltip.screen.upgrade_slot" });
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.ULTIMATE_ALFHEIM_MARKET_GUI, screenAddInventory,
                new int[] {blockEntity.getCurrentMana()}, new int[] {blockEntity.getMaxMana()}, bars, slotInfo);

        ScreenDrawLabelText.drawLabelText(guiGraphics, this.font, "block.botanicalextramachinery.ultimate_alfheim_market",
                new int[] {this.leftPos, this.topPos}, new int[] {this.imageWidth, this.imageHeight}, 6);

        if (blockEntity.getInventory().getStackInSlot(0).isEmpty() && this.minecraft != null){
            GhostItemRenderer.renderGhostItem(new ItemStack(ModItems.catalystManaInfinity), guiGraphics, this.leftPos + 90 , this.topPos + 96);
        }

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            RenderHelper.drawTexturedModalRect(guiGraphics, LibResources.ULTIMATE_ALFHEIM_MARKET_GUI, this.leftPos + 90, this.topPos + 61, this.imageWidth, 0, Math.round(16.0F * pct), 16);
        }

        slotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getInventory());
        bars.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getCurrentMana(), blockEntity.getMaxMana(), 0);
    }
}
