package woggy.trackingpanels;

import net.minecraft.block.BlockAir;
import net.minecraft.world.IBlockAccess;

public class BlockInvisible extends BlockAir
{
	public BlockInvisible()
	{
		super();
		this.setCreativeTab(null);
	}
	
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
    	return false;
    }
}
