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

public class BlockSmallPanel extends Block implements ITileEntityProvider
{	
	public BlockSmallPanel()
	{
		super(Material.glass);
		this.setCreativeTab(null);
		this.setBlockName("smallPanel");
		this.setBlockTextureName("trackingpanels:block.smallPanel.break");
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 0);
		super.setBlockBounds(0, -0.375f, 0, 1, -0.25f, 1);
	}
	
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new BlockSmallPanelTileEntity();
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
    	return PanelCore.itemSmallPanel;
    }
    
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
		//Still connected to an axle?
	    if(world.getBlock(i,j-1,k) == PanelCore.blockAxle)
	    	return;
	    
	    //Nope. Drop self.
	    this.dropBlockAsItem(world, i, j, k, new ItemStack(PanelCore.itemSmallPanel));
        world.setBlockToAir(i, j, k);
        world.removeTileEntity(i, j, k);
	}
}