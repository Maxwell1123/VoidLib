package nothing.lib.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

public class TileEntityBase extends TileEntity {

    private EnumFacing orientation = EnumFacing.NORTH;

    public void setOrientation(EnumFacing orientation){
        this.orientation = orientation;
    }

    public EnumFacing getOrientation(){
        return this.orientation;
    }

}
