package com.dev.handler.itemhandlers;

import com.dev.dressme.DressMeDataWeapom;
import com.dev.dressme.DressMeWeapom;

import net.sf.l2j.gameserver.handler.IItemHandler;
import net.sf.l2j.gameserver.model.actor.Playable;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;

public class DressMeSkinsWeapon implements IItemHandler {
	@Override
	public void useItem(Playable playable, ItemInstance item, boolean forceUse) {

		if (!(playable instanceof Player))
			return;

		final DressMeWeapom dress3 = DressMeDataWeapom.getInstance().getItemId(item.getItemId());
		if (dress3 == null)
			return;

		final Player player = (Player) playable;
		player.setDressWeapom(dress3);
		player.broadcastPacket(new MagicSkillUse(player, player, 1036, 1, 4000, 0));
		player.broadcastUserInfo();
	}
}