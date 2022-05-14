package com.dev.handler.cubiccommandhandlers;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public class CubicChat implements ICubicCommandHandler
{
	private static final String[] CUBIC_COMMANDS = new String[]
	{
		"cubic_chat"
	};
	
	public boolean useCubicCommand(Player player, String command)
	{
		if (command.startsWith("cubic_chat"))
		{
			NpcHtmlMessage html = new NpcHtmlMessage(0);
			html.setFile("data/html/dev/cubic/" + command.substring(11));
			player.sendPacket(html);
			player.sendPacket(ActionFailed.STATIC_PACKET);
		}
		return true;
	}
	
	public String[] getCubicCommandList()
	{
		return CUBIC_COMMANDS;
	}
}
