package woggy.trackingpanels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockAxleMount extends Block
{	
	@SideOnly(Side.CLIENT)
	private IIcon sideTexture, topTexture;

	public BlockAxleMount()
	{
		super(Material.iron);
		this.setCreativeTab(PanelCore.creativeTab);
		this.setBlockName("axleMount");
		this.setBlockTextureName("trackingpanels:block.axlemount");
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setStepSound(soundTypePiston);
		this.setHarvestLevel("pickaxe", 1);
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if(side==0 || side==1)
			return this.topTexture;
		if(side==2 || side==3)
			return this.sideTexture;
		return this.blockIcon;
	}
	
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon(this.getTextureName() + ".we");
        this.sideTexture = register.registerIcon(this.getTextureName() + ".ns");
        this.topTexture = register.registerIcon(this.getTextureName() + ".ud");
    }
}