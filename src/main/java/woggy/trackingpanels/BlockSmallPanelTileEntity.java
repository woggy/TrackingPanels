package woggy.trackingpanels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class BlockSmallPanelTileEntity extends TileEntity 
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
		if(angle<-90)
			angle=180-angle;
	}
	
	@SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord-1, yCoord-1, zCoord, xCoord + 1, yCoord, zCoord+1);
        return bb;
    }
}
