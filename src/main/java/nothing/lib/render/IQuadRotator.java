package nothing.lib.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;

public interface IQuadRotator {

    EnumFacing getRotation(IBlockState state);

}
