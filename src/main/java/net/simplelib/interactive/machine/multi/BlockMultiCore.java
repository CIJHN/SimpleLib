package net.simplelib.interactive.machine.multi;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.simplelib.interactive.InteractiveMetadata;
import net.simplelib.interactive.block.BlockMachine;

/**
 * @author ci010
 */
public class BlockMultiCore extends BlockMachine
{
	MachineMetaMulti machine;
	final PropertyBool ENABLE = PropertyBool.create("enable");
	protected ImmutableList<BlockPos> subs;    //Pattern3D

	public BlockMultiCore()
	{
		super(Material.air);//TODO this...
		this.setDefaultState(this.blockState.getBaseState().withProperty(ENABLE, false));
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(worldIn, pos, state);
		subs = machine.pattern.transferTo(pos);
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, ENABLE);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		//determine if its subBlock fulfilling the pattern

		for (BlockPos sub : subs)
			if (!(worldIn.getBlockState(sub).getBlock() == machine.sub))
				return false;
		if (!(Boolean) state.getValue(ENABLE))
		{
			// TODO: 2015/12/7 make all subBlock point to this
			worldIn.setBlockState(pos, state.withProperty(ENABLE, true));
		}
//		machine.interactWith(playerIn, pos);
		return true;
	}

	@Override
	public InteractiveMetadata getMachine()
	{
		return machine;
	}
}