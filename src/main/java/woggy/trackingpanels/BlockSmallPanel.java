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
	public BlockSmallPanel()
	{
		super();
		this.setBlockName("smallPanel");
		this.setBlockTextureName("trackingpanels:block.smallPanel.break");
		super.setBlockBounds(0, 0, 0, 1, 0.125f, 1);
	}
	
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new BlockSmallPanelTileEntity();
	}
    
    public Item getItemDropped(int meta, Random foo, int bar)
    {
    	return PanelCore.itemSmallPanel;
    }
    
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
		//Still connected to an axle?
	    if(world.getBlock(i,j-1,k) == PanelCore.blockAxle)
	    	return;
	    
	    //Nope. Unregister in root block and drop self.
	    int offset = BlockSmallPanel.findRootBlockOffset(world, i, j, k);
	    if(offset != 0)
	    {
	    	TileEntity te = world.getTileEntity(i, j-1, k+offset);
	    	((BlockAxleMountTileEntity) te).unregisterPanel(offset);
	    }
	    
	    this.dropBlockAsItem(world, i, j, k, new ItemStack(PanelCore.itemSmallPanel));
        world.setBlockToAir(i, j, k);
        world.removeTileEntity(i, j, k);
	}
}