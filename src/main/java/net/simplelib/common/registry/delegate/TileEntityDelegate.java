package net.simplelib.common.registry.delegate;

import api.simplelib.utils.TypeUtils;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.simplelib.HelperMod;
import api.simplelib.registry.ASMRegistryDelegate;
import net.simplelib.common.DebugLogger;
import api.simplelib.LoadingDelegate;
import api.simplelib.registry.ModTileEntity;

/**
 * @author ci010
 */
@LoadingDelegate
public class TileEntityDelegate extends ASMRegistryDelegate<ModTileEntity>
{
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		Class<? extends net.minecraft.tileentity.TileEntity> tile = TypeUtils.cast(this.getAnnotatedClass());
		String name = this.getAnnotation().value().equals("") ? tile.getName() : this
				.getAnnotation().value();
		ModTileEntity.Render render = this.getAnnotatedClass().getAnnotation(ModTileEntity.Render.class);
		if (HelperMod.proxy.isClient() && (render != null))
		{
			try
			{
				ClientRegistry.registerTileEntity(tile, name, render.value().newInstance());
			}
			catch (InstantiationException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		else
			GameRegistry.registerTileEntity(tile, name);
		DebugLogger.info("Register TileEntity: [{}] <- [{}:{}]", name, this.getModid(), this.getAnnotatedClass().getName
				());
	}
}
