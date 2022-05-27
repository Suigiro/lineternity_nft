package net.sf.l2j.gameserver.dressme;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public class DressMe2CommandHandler implements IVoicedCommandHandler {

	@Override
	public void useVoicedCommand(Player activeChar, String command) {
		if (command.equals("dressme2") && Config.CMD_SKIN)
			showDressMe2Html(activeChar);
	}

	private static void showDressMe2Html(Player activeChar) {
		NpcHtmlMessage html = new NpcHtmlMessage(0);
		html.setFile("data/html/dev/dressme/DressMe-2.htm");
		activeChar.sendPacket(html);
	}

	@Override
	public String[] getVoicedCommands() {
		return new String[] { "dressme2" };
	}

}