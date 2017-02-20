package nothing.lib.render;

import net.minecraft.item.ItemStack;

public interface IItemKeyProvider {

    String getExtendedItemKey(ItemStack stack);

}
