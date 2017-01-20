package api.simplelib.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

abstract class AbstractMessageHandler<T extends IMessage> implements IMessageHandler<T, IMessage>
{
	public abstract IMessage handleClientMessage(EntityPlayer player, T data, MessageContext ctx);

	public abstract IMessage handleServerMessage(EntityPlayer player, T data, MessageContext ctx);

	@Override
	public IMessage onMessage(T message, MessageContext ctx)
	{
		if (ctx.side.isClient())
			return handleClientMessage(getSinglePlayer(), message, ctx);
		else
			return handleServerMessage(ctx.getServerHandler().playerEntity, message, ctx);
	}

	@SideOnly(Side.CLIENT)
	private EntityPlayer getSinglePlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
}