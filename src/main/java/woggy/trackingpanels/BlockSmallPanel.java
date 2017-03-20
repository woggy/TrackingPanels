package woggy.trackingpanels;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSmallPanel extends BlockPanel implements ITileEntityProvider
{	
	private static int[] xOffset = {-1,-1,1,1};
	private static int[] yOffset = {-1,0,0,-1};
	private static int bufferBlocks = xOffset.length;
	
	private int tier;
	
	public BlockSmallPanel(int tier)
	{
		super();
		this.setBlockName("smallPanel"+tier);
		this.setBlockTextureName("trackingpanels:block.smallPanel"+tier+".break");
		super.setBlockBounds(0, 0, 0, 1, 0.125f, 1);
		this.tier = tier;
	}
	
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new BlockSmallPanelTileEntity(tier);
	}
    
    public Item getItemDropped(int meta, Random foo, int bar)
    {
    	return PanelCore.itemSmallPanel[tier];
    }
    
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		//Still connected to an axle?
	    if(world.getBlock(x,y-1,z) == PanelCore.blockAxle)
	    	return;
	    
	    //Nope. Unregister in root block and drop self.    
	    this.dropBlockAsItem(world, x,y,z, new ItemStack(PanelCore.itemSmallPanel[tier]));
	    super.breakBlock(world, x, y, z, block, 0);
        world.setBlockToAir(x,y,z);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block brokenBlock, int meta)
	{
		super.breakBlock(world, x, y, z, brokenBlock, meta);

        for(int i=0;i<bufferBlocks;i++)
        	world.setBlockToAir(x+xOffset[i], y+yOffset[i], z);
	}
}