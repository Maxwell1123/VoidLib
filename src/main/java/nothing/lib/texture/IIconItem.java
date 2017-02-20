package nothing.lib.texture;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public interface IIconItem {

    void registerIcons(IIconRegister registrar);

    TextureAtlasSprite getIcon(int meta);

}
