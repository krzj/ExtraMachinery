package net.lmor.botanicalextramachinery.blocks.containers.mechanicalAlfheimMarket;

import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketAdvanced;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerAlfheimMarketAdvanced extends BlockEntityMenu<BlockEntityAlfheimMarketAdvanced> {
    public final static int WIDTH_GUI = 196;
    public final static int HEIGHT_GUI = 124;

    public ContainerAlfheimMarketAdvanced(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 10, 19);
        IItemHandlerModifiable inventory = this.blockEntity.getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 90, 81));

        int index = this.addSlotBox(inventory, 1, 29, 36, 3, 18, 3, 18);
        this.addSlotBox(inventory, index, 115, 36, 3, 18, 3, 18, UnrestrictedOutputSlot::new);

        int[] x_y = ScreenAddInventory.getCoordInventorySlot(ScreenInventory.ADVANCED, WIDTH_GUI, HEIGHT_GUI);
        this.layoutPlayerInventorySlots(x_y[0], x_y[1]);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        if (level != null && level.getBlockState(pos).isAir()) {
            player.closeContainer();
            return false;
        }
        return super.stillValid(player);
    }
}
