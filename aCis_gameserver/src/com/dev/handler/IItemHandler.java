package com.dev.handler;

import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;

public interface IItemHandler
{
	void useItem(Player paramL2PcInstance, ItemInstance paramItemInstance);
}
