package woggy.trackingpanels;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockAxleMountTileEntity extends TileEntity implements IEnergyProvider
{
	protected EnergyStorage storage;
	private int delay;
	private int rate;
	
	
	public BlockAxleMountTileEntity()
	{
		storage = new EnergyStorage(8000);
		delay = (int) Math.floor(20*Math.random());
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(Util.tickRate(this.getWorldObj(), 20, delay))
		{
			rate = 0;
			for(int i=-4;i<5;i++)
				if(this.getWorldObj().getBlock(this.xCoord, this.yCoord+1, this.zCoord+i) == PanelCore.blockSmallPanel)
				{
					float angle = Util.getSunAngle(this.getWorldObj());
					if(angle > -90 || angle < -270)
						if(Util.checkSightline(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord+i))
							rate++;
				}
		}
		
		//System.out.println(rate);
		storage.receiveEnergy(rate, false);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
        nbt.setInteger("rate", this.rate);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		rate = nbt.getInteger("rate");
	}

	/* IEnergyConnection */
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return !(from == ForgeDirection.NORTH || from == ForgeDirection.SOUTH);
	}

	/* IEnergyProvider */
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		System.out.println("test? Attempting to extract " + maxExtract + " RF.");
		return storage.extractEnergy(maxExtract, simulate);
	}

	/* IEnergyReceiver and IEnergyProvider */
	@Override
	public int getEnergyStored(ForgeDirection from) 
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return storage.getMaxEnergyStored();
	}

}
