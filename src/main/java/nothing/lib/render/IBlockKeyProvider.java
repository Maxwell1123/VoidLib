package nothing.lib.render;

import net.minecraftforge.common.property.IExtendedBlockState;

public interface IBlockKeyProvider {

    String getExtendedBlockKey(IExtendedBlockState state);

}
