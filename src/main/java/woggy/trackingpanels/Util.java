package woggy.trackingpanels;

import net.minecraft.world.World;

public class Util
{	
	public static float getSunAngle(World world)
	{
		return world.getCelestialAngle(0)*-360;
	}
	
	public static boolean tickRate(World world, int numTicks, int offset)
	{
		return (world.getTotalWorldTime() + offset) % numTicks == 0;
	}
	
	public  static boolean tickRate(World world, int numTicks)
	{
		return tickRate(world, numTicks, 0);
	}
}
