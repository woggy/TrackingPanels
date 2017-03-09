package woggy.trackingpanels;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PanelCore.MODID, version = PanelCore.VERSION)
public class PanelCore
{
    public static final String MODID = "trackingpanels";
    public static final String MODNAME = "@NAME@";
    public static final String VERSION = "@VERSION@";

    
    @Instance(value = PanelCore.MODID)
    public static PanelCore instance;

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
}
