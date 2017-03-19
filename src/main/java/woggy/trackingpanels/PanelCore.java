package woggy.trackingpanels;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = PanelCore.MODID, version = PanelCore.VERSION)
public class PanelCore
{
    public static final String MODID = "trackingpanels";
    public static final String MODNAME = "@NAME@";
    public static final String VERSION = "@VERSION@";

    
    @Instance(value = PanelCore.MODID)
    public static PanelCore instance;
    
    public static Block blockSmallPanel;
    public static Block blockAxle;
    public static Block blockAxleMount;
    public static Block blockInvisible;
    
    public static Item itemAxle;
    public static Item itemSmallPanel;

    public static final CreativeTabs creativeTab = new CreativeTabs("TrackingPanels")
    {
    	@Override
    	public Item getTabIconItem()
    	{
    		return Item.getItemFromBlock(Blocks.redstone_block);
    	}
    };
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	this.tileEntityRegistration();
		this.itemAndBlockRegistration();
		this.addRecipes();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	if(!OreDictionary.doesOreNameExist("ingotSteel"))
    		System.out.println("Steel ingots not found in oredict!");
    	else
    		System.out.println("Steel ingots found in oredict!");
		if(!OreDictionary.doesOreNameExist("ingotCopper"))
			System.out.println("Copper ingots not found in oredict!");
    	else
    		System.out.println("Copper ingots found in oredict!");
		if(!(OreDictionary.doesOreNameExist("ingotAluminum") ||
			  OreDictionary.doesOreNameExist("ingotAluminium")))
			System.out.println("Aluminum ingots not found in oredict!");
    	else
    		System.out.println("Aluminum ingots found in oredict!");
		if(!FluidRegistry.isFluidRegistered("steam"))
			System.out.println("Steam fluid not found in fluid registry!");
    	else
    		System.out.println("Steam fluid found in fluid registry!");
    }
    
    public void tileEntityRegistration()
    {
    	ClientRegistry.bindTileEntitySpecialRenderer(BlockAxleTileEntity.class, new AxleRenderer());
    	GameRegistry.registerTileEntity(BlockAxleTileEntity.class, "axle");

    	ClientRegistry.bindTileEntitySpecialRenderer(BlockSmallPanelTileEntity.class, new SmallPanelRenderer());
    	GameRegistry.registerTileEntity(BlockSmallPanelTileEntity.class, "smallPanel");
    	
    	GameRegistry.registerTileEntity(BlockAxleMountTileEntity.class, "axleMount");
    }
    
    public void itemAndBlockRegistration()
    {
    	blockSmallPanel = new BlockSmallPanel();
    	GameRegistry.registerBlock(blockSmallPanel, "BlockSmallPanel");
    	blockAxle = new BlockAxle();
    	GameRegistry.registerBlock(blockAxle, "BlockAxle");
    	blockAxleMount = new BlockAxleMount();
    	GameRegistry.registerBlock(blockAxleMount, "BlockAxleMount");
    	blockInvisible = new BlockInvisible();
    	GameRegistry.registerBlock(blockInvisible, "BlockInvisible");
    	
    	itemAxle = new ItemAxle();
    	GameRegistry.registerItem(itemAxle, "ItemAxle");
    	itemSmallPanel = new ItemSmallPanel();
    	GameRegistry.registerItem(itemSmallPanel, "ItemSmallPanel");
    }
    
    public void addRecipes()
    {
    	
    }
}
