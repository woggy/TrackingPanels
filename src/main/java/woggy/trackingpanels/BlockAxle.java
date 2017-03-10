package woggy.trackingpanels;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAxle extends Block implements ITileEntityProvider
{	
	public static int numAxles = 4;
	
	public BlockAxle()
	{
		super(Material.iron);
		this.setCreativeTab(null);
		this.setBlockName("axle");
		this.setBlockTextureName("trackingpanels:block.axle.break");
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 1);
		super.setBlockBounds(0.375f, 0.375f, 0, 0.625f, 1, 1);
	}
	
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new BlockAxleTileEntity();
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
    
    public Item getItemDropped(int meta, Random foo, int bar)
    {
    	return PanelCore.itemAxle;
    }
    
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
		//Still connected to an axle mount?
	    if(world.getBlock(i,j,k+1) == PanelCore.blockAxleMount)
	    	return;
	    if(world.getBlock(i,j,k-1) == PanelCore.blockAxleMount)
	    	return;
	    
	    //Still connected to a lower-numbered axle?
		int meta = world.getBlockMetadata(i, j, k);
	    if(world.getBlock(i,j,k+1) == PanelCore.blockAxle && world.getBlockMetadata(i,j,k+1) < meta)
	    	return;
	    if(world.getBlock(i,j,k-1) == PanelCore.blockAxle && world.getBlockMetadata(i,j,k-1) < meta)
	    	return;
	    
	    //Nope. Drop self.
	    this.dropBlockAsItem(world, i, j, k, new ItemStack(PanelCore.itemAxle));
        world.setBlockToAir(i, j, k);
        world.removeTileEntity(i, j, k);
	}
}