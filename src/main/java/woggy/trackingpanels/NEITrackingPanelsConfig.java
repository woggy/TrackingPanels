package woggy.trackingpanels;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import net.minecraft.item.ItemStack;


public class NEITrackingPanelsConfig implements IConfigureNEI
{
	public String getName()
	{
		return PanelCore.MODNAME;
	}
	
	public String getVersion()
	{
		return PanelCore.VERSION;
	}
	
	public void loadConfig()
	{
		API.hideItem(new ItemStack(PanelCore.blockInvisible));
		API.hideItem(new ItemStack(PanelCore.blockAxle));
		
		for(int i=0;i<PanelCore.numTiers;i++)
			API.hideItem(new ItemStack(PanelCore.blockSmallPanel[i]));
	}
}
