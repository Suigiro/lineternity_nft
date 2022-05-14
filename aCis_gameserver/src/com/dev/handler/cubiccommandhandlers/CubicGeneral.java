package com.dev.handler.cubiccommandhandlers;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.SystemMessageId;

public class CubicGeneral implements ICubicCommandHandler
{
	private static final String[] CUBIC_COMMANDS = new String[]
	{
		"cubic_message_refusal",
		"cubic_message_acceptance",
		"cubic_trade_refusal",
		"cubic_trade_acceptance"
	};
	
	public boolean useCubicCommand(Player player, String command)
	{
		if (player.isCastingNow() || player.isMovementDisabled() || player.isOutOfControl() || player.isInOlympiadMode() || player.isInObserverMode() || player.isInJail() || player.isInDuel())
		{
			player.sendMessage("Your current state does not allow you to use the general settings.");
			return false;
		}
		if (command.equalsIgnoreCase("cubic_message_refusal"))
		{
			player.setInRefusalMode(true);
			player.sendPacket(SystemMessageId.MESSAGE_REFUSAL_MODE);
		}
		if (command.equalsIgnoreCase("cubic_message_acceptance"))
		{
			player.setInRefusalMode(false);
			player.sendPacket(SystemMessageId.MESSAGE_ACCEPTANCE_MODE);
		}
		if (command.equalsIgnoreCase("cubic_trade_refusal"))
		{
			player.setTradeRefusal(true);
			player.sendMessage("Trade refusal mode.");
		}
		if (command.equalsIgnoreCase("cubic_trade_acceptance"))
		{
			player.setTradeRefusal(false);
			player.sendMessage("Trade acceptance mode.");
		}
		return true;
	}
	
	public String[] getCubicCommandList()
	{
		return CUBIC_COMMANDS;
	}
}
