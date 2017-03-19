package woggy.trackingpanels;

import java.util.HashMap;
import java.util.Map;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockAxleMountTileEntity extends TileEntity implements IEnergyProvider
{
	protected EnergyStorage storage;
	private int delay;
	private int rate;
	private Map<Integer, Integer> panelMap = new HashMap<Integer, Integer>();
	
	
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
			float angle = Util.getSunAngle(this.getWorldObj());
			if(angle > -90 || angle < -270)
				for(int key : panelMap.keySet())
					if(Util.checkSightline(this.getWorldObj(), this.xCoord, this.yCoord, this.zCoord-key))
						rate += panelMap.get(key);
		}
		
		storage.receiveEnergy(rate, false);
		
		if(storage.getEnergyStored() > 0)
		{
			for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
			{
				if(canConnectEnergy(dir))
				{
					TileEntity tile = this.getWorldObj().getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
					if(tile instanceof IEnergyReceiver)
					{
						int availableEnergy = storage.extractEnergy(storage.getMaxExtract(), true);
						int energyMoved = ((IEnergyReceiver) tile).receiveEnergy(dir.getOpposite(), availableEnergy, false);
						storage.extractEnergy(energyMoved, false);
					}
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
		NBTTagCompound nbtPanelMap = nbt.getCompoundTag("panelMap");
		for (Object key : nbtPanelMap.func_150296_c())	//func_150296_c returns the keyset.
		{
			panelMap.put(Integer.parseInt((String)key), nbtPanelMap.getInteger((String)key));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
		NBTTagCompound nbtPanelMap = new NBTTagCompound();
		for(Integer key: panelMap.keySet())
		{
			nbtPanelMap.setInteger(key.toString(), panelMap.get(key));
		}
        nbt.setTag("panelMap", nbtPanelMap);
	}	
	
	//Methods for syncing server & client NBT data.
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
		super.onDataPacket(manager, packet);
		NBTTagCompound nbt = packet.func_148857_g();
		readFromNBT(nbt);
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
	
	public void registerPanel (int offset, int panelSize)
	{
		panelMap.put(offset, panelSize);
	}

	public void unregisterPanel(int offset)
	{
		panelMap.remove(offset);
	}

}
