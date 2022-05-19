package com.dev.handler.cubiccommandhandlers;

import java.util.List;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.gameserver.data.manager.BuyListManager;
import net.sf.l2j.gameserver.data.xml.MultisellData;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.buylist.NpcBuyList;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.BuyList;
import net.sf.l2j.gameserver.network.serverpackets.SellList;

public class CubicItemStore implements ICubicCommandHandler {
	private static final String[] CUBIC_COMMANDS = new String[] { "cubic_buy", "cubic_sell", "cubic_multisell",
			"cubic_exc_multisell" };

	@Override
	public boolean useCubicCommand(Player player, String command) {
		if (player.isCastingNow() || player.isMovementDisabled() || player.isOutOfControl() || player.isInOlympiadMode()
				|| player.isInObserverMode() || player.isInJail() || player.isInDuel()) {
			player.sendMessage("Your current state does not allow you to use the item store.");
			return false;
		}
		if (command.startsWith("cubic_buy")) {
			int val = Integer.parseInt(command.substring(10));
			NpcBuyList list = BuyListManager.getInstance().getBuyList(val);
			if (list != null) {
				player.tempInventoryDisable();
				player.setIsCubicBypass(true);
				player.sendPacket(new BuyList(list, player.getAdena(), 0.0D));
			}
		}
		if (command.equalsIgnoreCase("cubic_sell")) {
			List<ItemInstance> items = player.getInventory().getSellableItems();
			player.setIsCubicBypass(true);
			player.sendPacket(new SellList(player.getAdena(), items));
		}
		if (command.startsWith("cubic_multisell")) {
			String name = command.substring(16);
			player.setIsCubicBypass(true);
			MultisellData.getInstance().separateAndSend(name, player, null, false);
		}
		if (command.startsWith("cubic_exc_multisell")) {
			String name = command.substring(20);
			player.setIsCubicBypass(true);
			MultisellData.getInstance().separateAndSend(name, player, null, true);
		}
		return true;
	}

	@Override
	public String[] getCubicCommandList() {
		return CUBIC_COMMANDS;
	}
}
