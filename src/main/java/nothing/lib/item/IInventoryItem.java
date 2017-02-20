package nothing.lib.item;

import net.minecraft.item.ItemStack;

public interface IInventoryItem {

    ItemStack[] getInventoryItems(ItemStack stack);

    void setInventoryItems(ItemStack stack, ItemStack[] stackList);

    int getInventorySlots(ItemStack stack);

}
