package com.dev.handler.itemhandlers;

import com.dev.handler.IItemHandler;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public class Cubic implements IItemHandler
{
	public void useItem(Player player, ItemInstance item)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(0);
		if (Config.SHOP_PORTABLE.trim() == "")
		{
			html.setFile("data/html/dev/cubic/main.htm");
		}
		else
		{
			html.setFile(Config.SHOP_AIO);
		}
		player.sendPacket(html);
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
}
