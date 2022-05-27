package com.dev.handler.itemhandlers;

import com.dev.dressme.DressMe;
import com.dev.dressme.DressMeData;

import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;

public class DressMeSkins implements IItemHandler {
	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {

		if (!(playable instanceof Player))
			return;

		final DressMe dress = DressMeData.getInstance().getItemId(item.getItemId());
		if (dress == null)
			return;

		final Player player = (Player) playable;
		player.setDress(dress);
		player.broadcastPacket(new MagicSkillUse(player, player, 1036, 1, 4000, 0));
		player.broadcastUserInfo();
	}
}