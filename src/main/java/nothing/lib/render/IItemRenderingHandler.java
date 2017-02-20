package nothing.lib.render;

import codechicken.lib.render.CCRenderState;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;

public interface IItemRenderingHandler {

    void renderItem(CCRenderState renderState, ItemStack stack, long rand);

    Pair<? extends IBakedModel, Matrix4f> handlePerspective(IBakedModel model, ItemCameraTransforms.TransformType cameraTransformType);

    boolean useRenderCache();

    boolean useStandardItemLighting();

}
