package nothing.lib.internal.proxy;

import codechicken.lib.model.ModelRegistryHelper;
import codechicken.lib.packet.PacketCustom;
import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import nothing.lib.block.BlockBase;
import nothing.lib.block.IMetaBlock;
import nothing.lib.internal.VoidLib;
import nothing.lib.internal.network.VoidLibCPH;
import nothing.lib.item.ItemBase;
import nothing.lib.render.CustomBlockRenderer;
import nothing.lib.render.CustomItemRenderer;
import nothing.lib.render.IBlockRenderingHandler;
import nothing.lib.render.IItemRenderingHandler;
import nothing.lib.texture.IconRegister;
import nothing.lib.util.ClientUtils;

import java.util.ArrayList;

public class ClientProxy implements IVoidLibProxy {

    private ArrayList<BlockBase> blocksToHandle = Lists.newArrayList();
    private ArrayList<ItemBase> itemsToHandle = Lists.newArrayList();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(IconRegister.INSTANCE);

        for (BlockBase block : this.blocksToHandle) {
            IconRegister.INSTANCE.registerBlock(block);
            this.registerRenderingHandler(block, block.getRenderingHandler());
        }

        for (ItemBase item : this.itemsToHandle) {
            IconRegister.INSTANCE.registerItem(item);
            this.registerRenderingHandler(item, item.getRenderingHandler());
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        PacketCustom.assignHandler(VoidLib.INSTANCE, new VoidLibCPH());
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void handleBlock(BlockBase block) {
        this.blocksToHandle.add(block);
    }

    @Override
    public void handleItem(ItemBase item) {
        this.itemsToHandle.add(item);
    }

    private void registerRenderingHandler(Block block, IBlockRenderingHandler renderer) {
        if (block == null || renderer == null) {
            throw new IllegalArgumentException("Block or rendering handler can't be null !");
        }
        if (!(block instanceof BlockBase)) {
            throw new IllegalArgumentException("Block must be an instance of BlockBase !");
        }

        BlockBase blockBase = (BlockBase) block;
        ResourceLocation rl = new ResourceLocation(blockBase.getRegistryName().getResourceDomain(), blockBase.getInternalName());

        if (blockBase instanceof IMetaBlock) {
            IMetaBlock iface = (IMetaBlock) blockBase;
            CustomBlockRenderer blockRenderer = new CustomBlockRenderer(renderer);
            CustomItemRenderer itemRenderer = new CustomItemRenderer(renderer);
            ModelResourceLocation locationInventory = new ModelResourceLocation(rl, "inventory");

            for (int i = 0; i < iface.getSubNames().length; i++) {
                ModelResourceLocation location = new ModelResourceLocation(rl, "type=" + iface.getSubNames()[i]);
                ModelLoader.setCustomStateMapper(blockBase, new ClientUtils.SimpleStateMapper(location));
                ModelRegistryHelper.register(location, blockRenderer);
            }

            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockBase), 0, locationInventory);
            ModelRegistryHelper.registerItemRenderer(Item.getItemFromBlock(blockBase), itemRenderer);
        } else {
            CustomBlockRenderer blockRenderer = new CustomBlockRenderer(renderer);
            CustomItemRenderer itemRenderer = new CustomItemRenderer(renderer);
            ModelResourceLocation location = new ModelResourceLocation(rl, "normal");
            ModelResourceLocation locationInventory = new ModelResourceLocation(rl, "inventory");
            ModelLoader.setCustomStateMapper(blockBase, new ClientUtils.SimpleStateMapper(location));
            ModelRegistryHelper.register(location, blockRenderer);
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockBase), 0, locationInventory);
            ModelRegistryHelper.registerItemRenderer(Item.getItemFromBlock(blockBase), itemRenderer);
        }
    }

    private void registerRenderingHandler(Item item, IItemRenderingHandler renderer) {
        if (item == null || renderer == null) {
            throw new IllegalArgumentException("Item or rendering handler can't be null !");
        }
        if (!(item instanceof ItemBase)) {
            throw new IllegalArgumentException("Item must be an instance of ItemBase !");
        }

        ItemBase itemBase = (ItemBase) item;
        ResourceLocation rl = new ResourceLocation(itemBase.getRegistryName().getResourceDomain(), itemBase.getInternalName());

        if (itemBase.getSubNames() != null) {
            CustomItemRenderer itemRenderer = new CustomItemRenderer(renderer);
            ModelResourceLocation location = new ModelResourceLocation(rl, "inventory");

            for (int i = 0; i < itemBase.getSubNames().length; i++) {
                ModelLoader.setCustomModelResourceLocation(itemBase, 0, location);
            }

            ModelRegistryHelper.registerItemRenderer(itemBase, itemRenderer);
        } else {
            CustomItemRenderer itemRenderer = new CustomItemRenderer(renderer);
            ModelResourceLocation location = new ModelResourceLocation(rl, "inventory");
            ModelLoader.setCustomModelResourceLocation(itemBase, 0, location);
            ModelRegistryHelper.registerItemRenderer(itemBase, itemRenderer);
        }
    }

}
