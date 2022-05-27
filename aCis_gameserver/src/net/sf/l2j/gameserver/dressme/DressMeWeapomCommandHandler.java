package net.sf.l2j.gameserver.dressme;

import com.dev.dressme.DressMeDataWeapom;
import com.dev.dressme.DressMeWeapom;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.enums.ZoneId;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public class DressMeWeapomCommandHandler implements IVoicedCommandHandler {

	@Override
	public void useVoicedCommand(Player activeChar, String command) {
		if (command.equals("dressmeweapom") && Config.CMD_SKIN)
			showDressMeWeapomHtml(activeChar);

		else if (command.equals("bp_changedressmestatus_weapom")) {
			if (activeChar.getDressWeapom() == null) {
				activeChar.sendMessage("you are not equipped with dressme");
			} else if (activeChar.isDressMeWeapomEnabled()) {
				activeChar.setDressMeWeapomEnabled(false);
				activeChar.broadcastUserInfo();
			} else {
				activeChar.setDressMeWeapomEnabled(true);
				activeChar.broadcastUserInfo();
			}
			showDressMeWeapomHtml(activeChar);
		} else if (command.equals("skins_weapom")) {
			if (activeChar.getDressWeapom() != null) {
				activeChar.setDressWeapom(null);
			}
			final DressMeWeapom dressWeapom = DressMeDataWeapom.getInstance().getItemId(0);
			activeChar.setDressWeapom(dressWeapom);
			activeChar.broadcastUserInfo();
		} else if (command.equals("disable_skin_weapom")) {

			if (activeChar.getDressWeapom() != null) {
				activeChar.setDressWeapom(null);
				activeChar.broadcastUserInfo();
			}

		} else {
			if (!activeChar.isInsideZone(ZoneId.TOWN)) {
				activeChar.sendMessage("This command can only be used within a City.");

			}

			if (activeChar.getDressWeapom() != null) {
				activeChar.sendMessage("Wait, you are experiencing a skin.");

			}

			int skinId = 0;

			if (command.contains(" "))
				skinId = Integer.parseInt(command.split(" ")[1]);

			final DressMeWeapom dressWeapom = DressMeDataWeapom.getInstance().getItemId(skinId);

			if (dressWeapom != null) {
				activeChar.setDressWeapom(dressWeapom);
				activeChar.broadcastUserInfo();
			} else {
				activeChar.sendMessage("Invalid skin.");
			}

		}

	}

	private static void showDressMeWeapomHtml(Player activeChar) {
		NpcHtmlMessage html = new NpcHtmlMessage(0);
		html.setFile("data/html/dev/dressme/DressMeWeapom.htm");
		activeChar.sendPacket(html);
	}

	@Override
	public String[] getVoicedCommands() {
		return new String[] { "dressmeweapom", "bp_changedressmestatus_weapom", "skins_weapom", "disable_skin_weapom" };
	}

}