package net.sf.l2j.gameserver.dressme;

import java.util.StringTokenizer;

import com.dev.dressme.DressMe;
import com.dev.dressme.DressMeData;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.enums.ZoneId;
import net.sf.l2j.gameserver.handler.IVoicedCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.EtcStatusUpdate;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public class DressMeCommandHandler implements IVoicedCommandHandler {

	@Override
	public void useVoicedCommand(Player activeChar, String command) {

		if (command.equals("dressme") && Config.CMD_SKIN)
			showDressMeHtml(activeChar, 0);
		if (command.equals("dressme0") && Config.CMD_SKIN)
			showDressMeHtml(activeChar, 1);

		else if (command.equals("bp_changedressmestatus")) {
			if (activeChar.getDress() == null) {
				activeChar.sendMessage("you are not equipped with dressme");
			} else if (activeChar.isDressMeEnabled()) {
				activeChar.setDressMeEnabled(false);
				activeChar.broadcastUserInfo();
			} else {
				activeChar.setDressMeEnabled(true);
				activeChar.broadcastUserInfo();
			}
			showDressMeHtml(activeChar, 0);
		} else if (command.equals("skins")) {
			if (activeChar.getDress() != null) {
				activeChar.setDress(null);
			}
			final DressMe dress = DressMeData.getInstance().getItemId(0);
			activeChar.setDress(dress);
			activeChar.broadcastUserInfo();
		} else if (command.equals("disable_skin")) {
			// remover // abaixo para por utilidade VIP/Premium
			// if(activeChar.isPremium())
			// {
			if (activeChar.getDress() != null) {
				activeChar.setDress(null);
				activeChar.broadcastUserInfo();
				activeChar.removeSkill(Config.DRESS_SKILL_ID, false);
				activeChar.sendSkillList();
				activeChar.sendPacket(new EtcStatusUpdate(activeChar));
			}
			// }
		} else {
			if (!activeChar.isInsideZone(ZoneId.TOWN)) {
				activeChar.sendMessage("This command can only be used within a City.");

			}
			// remover // abaixo para por utilidade VIP/Premium
			// if (!activeChar.isPremium())
			// {
			if (activeChar.getDress() != null) {
				activeChar.sendMessage("Wait, you are experiencing a skin.");

			}
			// }

			// remover // abaixo para por utilidade VIP/Premium
			// if (activeChar.isPremium())
			// {
			// {
			StringTokenizer st = new StringTokenizer(command);
			st.nextToken();
			int skinId = Integer.parseInt(st.nextToken());

			final DressMe dress = DressMeData.getInstance().getItemId(skinId);

			if (dress != null) {
				activeChar.setDress(dress);
				activeChar.broadcastUserInfo();
				activeChar.addSkill(SkillTable.getInstance().getInfo(Config.DRESS_SKILL_ID, Config.DRESS_SKILL_LVL),
						false);
				activeChar.sendSkillList();
				activeChar.sendPacket(new EtcStatusUpdate(activeChar));

			} else {
				activeChar.sendMessage("Invalid skin.");

			}

			// }

			// }
			// remover /* e */ abaixo para por utilidade VIP/Premium
			/*
			 * 
			 * StringTokenizer st = new StringTokenizer(command); st.nextToken(); int skinId
			 * = Integer.parseInt(st.nextToken());
			 * 
			 * final DressMe dress = DressMeData.getInstance().getItemId(skinId); final
			 * DressMe dress2 = DressMeData.getInstance().getItemId(0);
			 * 
			 * if (dress != null) { activeChar.setDress(dress);
			 * activeChar.broadcastUserInfo(); ThreadPool.schedule(() -> {
			 * activeChar.setDress(dress2); activeChar.broadcastUserInfo(); },5000L);
			 * 
			 * activeChar.sendPacket(new ExShowScreenMessage("Experiencing a Premium Skin!",
			 * 5000)); } else { activeChar.sendMessage("Invalid skin."); return false; }
			 */
		}

	}

	private static void showDressMeHtml(Player activeChar, int select) {

		NpcHtmlMessage html = new NpcHtmlMessage(0);

		switch (select) {
		case 0:
			html.setFile("data/html/dev/dressme/menu.htm");
			activeChar.sendPacket(html);
			break;
		case 1:
			html.setFile("data/html/dev/dressme/DressMe.htm");
			activeChar.sendPacket(html);
			break;
		}
	}

	@Override
	public String[] getVoicedCommands() {
		return new String[] { "dressme", "dressme0", "bp_changedressmestatus", "skins", "disable_skin" };
	}

}