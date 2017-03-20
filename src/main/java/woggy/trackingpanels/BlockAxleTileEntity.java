package woggy.trackingpanels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class BlockAxleTileEntity extends TileEntity 
{
	float angle;
	
	public void updateEntity()
	{
		if(Util.tickRate(this.getWorldObj(), 20))
		{
			angle = Util.getSunAngle(this.getWorldObj());
			if(angle < -60 && angle >= -120)
				angle = -60;
			if(angle < -240 && angle > -300)
				angle = -300;
			if(angle < -120 && angle > -240)
				angle = -(180+angle);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1);
        return bb;
    }
}
