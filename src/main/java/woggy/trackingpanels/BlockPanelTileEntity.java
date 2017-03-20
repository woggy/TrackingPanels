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
			if(angle < -60 && angle >= -120)
				angle = -60;
			if(angle < -240 && angle > -300)
				angle = -300;
			if(angle < -120 && angle > -240)
				angle = -(180+angle);
		}
	}
}
