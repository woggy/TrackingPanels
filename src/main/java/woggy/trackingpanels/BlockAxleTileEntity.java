package woggy.trackingpanels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class BlockAxleTileEntity extends TileEntity 
{
	public int delay=0;
	public float angle;
	
	public void updateEntity()
	{
		if(delay>0)
		{
			delay--;
			return;
		}
		delay=20;
		angle = this.getWorldObj().getCelestialAngle(0)*-360;
		if(angle < -90 && angle >= -105)
			angle = -90;
		if(angle < -135 && angle > -270)
			angle = -270;
		if(angle < -105 && angle > -135)
			angle = -6*(angle+105) - 90;
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord+1, yCoord+1, zCoord+1);
        return bb;
    }
}
