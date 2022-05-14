package com.dev.handler.cubiccommandhandlers;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.gameserver.model.actor.Player;

public class CubicVersion implements ICubicCommandHandler
{
	private static final String[] CUBIC_COMMANDS = new String[]
	{
		"cubic_version"
	};
	
	@Override
	public boolean useCubicCommand(Player player, String command)
	{
		if (command.equalsIgnoreCase("cubic_version"))
			player.sendMessage("Dev revision: 0.2");
		return true;
	}
	
	@Override
	public String[] getCubicCommandList()
	{
		return CUBIC_COMMANDS;
	}
}
