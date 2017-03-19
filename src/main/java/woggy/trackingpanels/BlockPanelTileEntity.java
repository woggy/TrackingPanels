package woggy.trackingpanels;

import net.minecraft.tileentity.TileEntity;

public class BlockPanelTileEntity extends TileEntity 
{
	float angle;
	
	public void updateEntity()
	{
		if(Util.tickRate(this.getWorldObj(), 20))
		{
			angle = Util.getSunAngle(this.getWorldObj());
			if(angle < -90 && angle >= -105)
				angle = -90;
			if(angle < -135 && angle > -270)
				angle = -270;
			if(angle < -105 && angle > -135)
				angle = -6*(angle+105) - 90;
		}
	}
}
