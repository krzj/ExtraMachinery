package net.lmor.botanicalextramachinery.blocks.containers.mechanicalIndustrialAgglomerationFactory;

import de.melanx.botanicalmachinery.helper.UnrestrictedOutputSlot;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenAddInventory;
import net.lmor.botanicalextramachinery.blocks.screens.uitlScreen.ScreenInventory;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalIndustrialAgglomerationFactory.BlockEntityIndustrialAgglomerationFactoryAdvanced;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.menu.BlockEntityMenu;

public class ContainerIndustrialAgglomerationFactoryAdvanced extends BlockEntityMenu<BlockEntityIndustrialAgglomerationFactoryAdvanced> {
    public final static int WIDTH_GUI = 196;
    public final static int HEIGHT_GUI = 131;

    public ContainerIndustrialAgglomerationFactoryAdvanced(MenuType<? extends BlockEntityMenu<?>> type, int windowId, Level level, BlockPos pos, Inventory playerContainer, Player player) {
        super(type, windowId, level, pos, playerContainer, player, 9, 15);
        IItemHandlerModifiable inventory = this.blockEntity.getInventory();

        this.addSlot(new SlotItemHandler(inventory, 0, 17, 61));
        this.addSlot(new SlotItemHandler(inventory, 1, 163, 61));

        int index = this.addSlotBox(inventory, 2, 36, 39, 7, 18, 1, 18);
        this.addSlotBox(inventory, index, 45, 75, 6, 18, 1, 18, UnrestrictedOutputSlot::new);

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
