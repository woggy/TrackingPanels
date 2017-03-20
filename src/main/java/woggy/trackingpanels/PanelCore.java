package woggy.trackingpanels;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
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

    public static final int numTiers = 1;
    
    @Instance(value = PanelCore.MODID)
    public static PanelCore instance;
    
    public static Block blockAxleMount;
    public static Block blockInvisible;
    
    public static Item itemAxle;
    public static Block blockAxle;
    
    public static Item[] itemSmallPanel;
    public static Block[] blockSmallPanel;

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
//    	if(!OreDictionary.doesOreNameExist("ingotSteel"))
//    		System.out.println("Steel ingots not found in oredict. Some recipes may not work!");
		if(!OreDictionary.doesOreNameExist("ingotCopper"))
			System.out.println("Copper ingots not found in oredict. Some recipes may not work!");
		if(!OreDictionary.doesOreNameExist("ingotAluminum"))
			System.out.println("Aluminum ingots not found in oredict. Some recipes may not work!");
//		if(!FluidRegistry.isFluidRegistered("steam"))
//			System.out.println("Steam fluid not found in fluid registry. Some recipes may not work!");
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
    	itemAxle = new ItemAxle();
    	GameRegistry.registerItem(itemAxle, "ItemAxle");
    	blockAxle = new BlockAxle();
    	GameRegistry.registerBlock(blockAxle, "BlockAxle");
    	
    	blockAxleMount = new BlockAxleMount();
    	GameRegistry.registerBlock(blockAxleMount, "BlockAxleMount");
    	blockInvisible = new BlockInvisible();
    	GameRegistry.registerBlock(blockInvisible, "BlockInvisible");
    	
    	itemSmallPanel = new Item[numTiers];
    	blockSmallPanel = new Block[numTiers];
    	for(int i=0;i<numTiers;i++)
    	{
    		itemSmallPanel[i] = new ItemSmallPanel(i);
    		GameRegistry.registerItem(itemSmallPanel[i], "ItemSmallPanel");
        	blockSmallPanel[i] = new BlockSmallPanel(i);
        	GameRegistry.registerBlock(blockSmallPanel[i], "BlockSmallPanel");
    	}
	}
    
    public void addRecipes()
    {
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemAxle), "aca", 'a', "ingotAluminum", 'c', "ingotCopper"));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(blockAxleMount), "iai", "ara", "iai",
				'a', "ingotAluminum", 'r', "blockRedstone", 'i', "ingotIron"));
    	
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(itemSmallPanel[0]), "ppp", "lrl", "aca",
    			'a', "ingotAluminum", 'c', "ingotCopper", 'l', new ItemStack(Items.dye, 1, 4), 'p', "paneGlass", 'r', "dustRedstone"));
    }
}
