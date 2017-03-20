package woggy.trackingpanels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

public class BlockSmallPanelTileEntity extends BlockPanelTileEntity 
{	
	int tier;
	
	public BlockSmallPanelTileEntity(int tier)
	{
		this.tier = tier;
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord-1, yCoord-1, zCoord, xCoord + 2, yCoord+.0125f, zCoord+1);
        return bb;
    }
}
