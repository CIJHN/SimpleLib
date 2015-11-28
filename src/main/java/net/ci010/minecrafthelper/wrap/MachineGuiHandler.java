package net.ci010.minecrafthelper.wrap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * @author ci010
 */
public class MachineGuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return Machine.list.get(ID).getContainer(player, (TileEntityWrap) world.getTileEntity(new BlockPos(x, y, z)));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return Machine
				.list.get(ID).getGuiContainer(player, (TileEntityWrap) world.getTileEntity(new BlockPos(x, y, z)));
	}
}