package nothing.lib.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

import javax.annotation.Nullable;

public class TileEntityBase extends TileEntity {

    private EnumFacing orientation = EnumFacing.NORTH;

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        orientation = EnumFacing.getFront(tag.getInteger("tile_base_orientation"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger("tile_base_orientation", orientation.getIndex());
        return tag;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(getPos(), 255, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());
    }

    @Override
    public void markDirty() {
        super.markDirty();

        if (getWorld() != null) {
            IBlockState state = getWorld().getBlockState(pos);

            if (state != null) {
                state.getBlock().updateTick(getWorld(), getPos(), state, getWorld().rand);
                getWorld().notifyBlockUpdate(getPos(), state, state, 3);
            }
        }
    }

    public void setOrientation(EnumFacing orientation) {
        orientation = orientation;
    }

    public EnumFacing getOrientation() {
        return this.orientation;
    }

}
