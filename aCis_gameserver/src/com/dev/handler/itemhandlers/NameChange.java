package com.dev.handler.itemhandlers;

import com.dev.handler.IItemHandler;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.L2GameServerPacket;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public class NameChange implements IItemHandler
{
	@SuppressWarnings("cast")
	@Override
	public void useItem(Player playable, ItemInstance item)
	{
		if (!(playable instanceof Player))
			return;
		Player activeChar = playable;
		activeChar.setNameChangeItemId(item.getItemId());
		NpcHtmlMessage html = new NpcHtmlMessage(0);
		html.setFile("data/html/dev/NameChange.htm");
		html.replace("%servername%", Config.SERVER_NAME);
		html.replace("%player%", activeChar.getName());
		activeChar.sendPacket((L2GameServerPacket) html);
		activeChar.sendPacket((L2GameServerPacket) ActionFailed.STATIC_PACKET);
	}
}
