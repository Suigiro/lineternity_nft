package com.dev.handler.cubiccommandhandlers;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.HennaEquipList;
import net.sf.l2j.gameserver.network.serverpackets.HennaRemoveList;

public class CubicSymbolMaker implements ICubicCommandHandler
{
	private static final String[] CUBIC_COMMANDS = new String[]
	{
		"cubic_draw_symbol",
		"cubic_delete_symbol"
	};
	
	@Override
	public boolean useCubicCommand(Player player, String command)
	{
		if (player.isCastingNow() || player.isMovementDisabled() || player.isOutOfControl() || player.isInOlympiadMode() || player.isInObserverMode() || player.isInJail() || player.isInDuel())
		{
			player.sendMessage("Your current state does not allow you to use the symbol maker.");
			return false;
		}
		if (command.equalsIgnoreCase("cubic_draw_symbol"))
			player.sendPacket(new HennaEquipList(player));
		if (command.equalsIgnoreCase("cubic_delete_symbol"))
		{
			boolean hasHennas = false;
			for (int i = 1; i <= 3; i++)
			{
				if (player.getHennaList() != null)
					hasHennas = true;
			}
			if (hasHennas)
			{
				player.sendPacket(new HennaRemoveList(player));
			}
			else
			{
				player.sendPacket(SystemMessageId.SYMBOL_NOT_FOUND);
			}
		}
		return true;
	}
	
	@Override
	public String[] getCubicCommandList()
	{
		return CUBIC_COMMANDS;
	}
}
