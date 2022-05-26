package net.sf.l2j.gameserver.autofarm;

import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;

public class AutofarmCommandHandler implements IVoicedCommandHandler {

	@Override
	public void useVoicedCommand(Player activeChar, String command) {
		switch (command) {
		case "farm":
			AutofarmManager.INSTANCE.toggleFarm(activeChar);
			break;
		case "farmon":
			AutofarmManager.INSTANCE.startFarm(activeChar);
			break;
		case "farmoff":
			AutofarmManager.INSTANCE.stopFarm(activeChar);
			break;
		}
	}

	@Override
	public String[] getVoicedCommands() {
		return new String[] { "farm", "farmon", "farmoff" };
	}
}
