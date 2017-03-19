package woggy.trackingpanels;

import net.minecraft.tileentity.TileEntity;

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

}
