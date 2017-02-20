package nothing.lib.render;

import codechicken.lib.render.CCRenderState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import nothing.lib.texture.IIconBlock;

public interface IBlockRenderingHandler {

    void renderBlock(CCRenderState renderState, IBlockState state, EnumFacing face, BlockRenderLayer layer, long rand);

    void renderItem(CCRenderState renderState, ItemStack stack, long rand);

    TextureAtlasSprite getParticleTexture(IIconBlock block, int meta);

    boolean hasDynamicItemRendering();

}
