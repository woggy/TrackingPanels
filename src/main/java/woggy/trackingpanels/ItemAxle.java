package woggy.trackingpanels;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAxle extends Item
{
	public ItemAxle()
	{
		this.setCreativeTab(PanelCore.creativeTab);
		this.setUnlocalizedName("itemAxle");
		this.setTextureName("trackingpanels:item.axle");
	}
	
	public boolean onItemUse(ItemStack items, EntityPlayer player, World world, int x, int y, int z, int side, float player_x, float player_y, float player_z)
    {
		if(!(side == 2 || side == 3))
			return false;
		
		Block rootBlock = world.getBlock(x, y, z);
		int zOffset = (side==2 ? -1 : 1);
		
		if(player.canPlayerEdit(x, y, z+zOffset, side, items))
		{
			if(rootBlock == PanelCore.blockAxleMount)
			{
				world.setBlock(x, y, z+zOffset, PanelCore.blockAxle, 1, 3);
				world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
						PanelCore.blockAxle.stepSound.func_150496_b(), (PanelCore.blockAxle.stepSound.getVolume() + 1.0F) / 2.0F, PanelCore.blockAxle.stepSound.getPitch() * 0.8F);   
				items.stackSize--;
				return true;
			}
			if(rootBlock == PanelCore.blockAxle)
			{	
				int axleNum = world.getBlockMetadata(x, y, z);
				if(axleNum < BlockAxle.numAxles)
					{
						world.setBlock(x, y, z+zOffset, PanelCore.blockAxle, axleNum+1,3);
						world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
								PanelCore.blockAxle.stepSound.func_150496_b(), (PanelCore.blockAxle.stepSound.getVolume() + 1.0F) / 2.0F, PanelCore.blockAxle.stepSound.getPitch() * 0.8F);
						items.stackSize--;
						return true;
					}
			}
		}
		return false;
    }
}
