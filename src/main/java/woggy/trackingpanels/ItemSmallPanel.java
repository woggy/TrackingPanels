package woggy.trackingpanels;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSmallPanel extends Item
{
	public ItemSmallPanel()
	{
		this.setCreativeTab(PanelCore.creativeTab);
		this.setUnlocalizedName("itemSmallPanel");
		this.setTextureName("trackingpanels:item.smallPanel");
	}
	
	public boolean onItemUse(ItemStack items, EntityPlayer player, World world, int x, int y, int z, int side, float player_x, float player_y, float player_z)
    {
		if(!(side == 1))
			return false;
		
		Block rootBlock = world.getBlock(x, y, z);
		
		if(player.canPlayerEdit(x, y+1, z, side, items))
		{
			if(rootBlock == PanelCore.blockAxle)
			{	
				world.setBlock(x, y+1, z, PanelCore.blockSmallPanel, 0, 3);
				world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
						PanelCore.blockSmallPanel.stepSound.func_150496_b(), (PanelCore.blockSmallPanel.stepSound.getVolume() + 1.0F) / 2.0F, PanelCore.blockSmallPanel.stepSound.getPitch() * 0.8F);
				items.stackSize--;
				return true;
			}
		}
		return false;
    }
}
