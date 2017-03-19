package woggy.trackingpanels;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemSmallPanel extends Item
{
	private static int[] xOffset = {-1,-1,1,1};
	private static int[] yOffset = {0,1,1,0};
	private static int bufferBlocks = xOffset.length;
	
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
				if(checkRotationSpace(world, x, y, z))
				{
					world.setBlock(x, y+1, z, PanelCore.blockSmallPanel, 0, 3);
					for(int i=0;i<bufferBlocks;i++)
						world.setBlock(x+xOffset[i], y+yOffset[i], z, PanelCore.blockInvisible, 0, 3);
					
				    int offset = BlockSmallPanel.findRootBlockOffset(world, x, y+1, z);
				    TileEntity te = world.getTileEntity(x, y, z+offset);
				    ((BlockAxleMountTileEntity) te).registerPanel(offset,1);
				    
					world.playSoundEffect((double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), 
							PanelCore.blockSmallPanel.stepSound.func_150496_b(), (PanelCore.blockSmallPanel.stepSound.getVolume() + 1.0F) / 2.0F, PanelCore.blockSmallPanel.stepSound.getPitch() * 0.8F);
					items.stackSize--;
					return true;
				}
			}
		}
		return false;
    }
	
	private boolean checkRotationSpace(World world, int x, int y, int z)
	{
		for(int i=0;i<bufferBlocks;i++)
			if(!world.isAirBlock(x+xOffset[i], y+yOffset[i], z))
				return false;
		return true;
	}
}
