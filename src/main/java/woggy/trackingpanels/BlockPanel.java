package woggy.trackingpanels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPanel extends Block
{		
	public BlockPanel()
	{
		super(Material.glass);
		this.setCreativeTab(null);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 0);
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.blockIcon;
	}

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(this.getTextureName());
    }
	
	@Override
	public void breakBlock(World world, int i, int j, int k, Block brokenBlock, int meta)
	{
		super.breakBlock(world, i, j, k, brokenBlock, meta);
		
		//Make sure the root block forgets about us.
		int offset = BlockPanel.findRootBlockOffset(world, i, j, k);
	    if(offset != 0)
	    {
	    	TileEntity te = world.getTileEntity(i, j-1, k+offset);
	    	((BlockAxleMountTileEntity) te).unregisterPanel(offset);
	    }	
	}
	
	public static int findRootBlockOffset(World world, int i, int j, int k)
	{
		for(int offset = -BlockAxle.numAxles; offset <= BlockAxle.numAxles ; offset++)
		{
			if(world.getBlock(i, j-1, k+offset) == PanelCore.blockAxleMount)
				return offset;
		}
		return 0;	//should only happen when breaking a panel whose root has already been broken.
	}
}