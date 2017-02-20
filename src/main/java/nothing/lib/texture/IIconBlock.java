package nothing.lib.texture;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public interface IIconBlock {

    void registerBlockIcons(IIconRegister registrar);

    TextureAtlasSprite getIcon(int meta, int side);

}
