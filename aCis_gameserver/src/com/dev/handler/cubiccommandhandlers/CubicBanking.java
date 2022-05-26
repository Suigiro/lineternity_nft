package com.dev.handler.cubiccommandhandlers;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.ItemList;
import net.sf.l2j.gameserver.network.serverpackets.L2GameServerPacket;

public class CubicBanking implements ICubicCommandHandler {
	private static final String[] CUBIC_COMMANDS = new String[] { "cubic_bank", "cubic_withdraw", "cubic_deposit" };

	public boolean useCubicCommand(Player player, String command) {
		if (command.equalsIgnoreCase("cubic_bank"))
			player.sendMessage(".deposit (" + Config.BANKING_SYSTEM_ADENA + " Adena = " + Config.BANKING_SYSTEM_GOLDBARS
					+ " Goldbar) / .withdraw (" + Config.BANKING_SYSTEM_GOLDBARS + " Goldbar = "
					+ Config.BANKING_SYSTEM_ADENA + " Adena)");
		if (command.equalsIgnoreCase("cubic_withdraw"))
			if (player.getInventory().getInventoryItemCount(3470, 0) >= Config.BANKING_SYSTEM_GOLDBARS) {
				player.getInventory().destroyItemByItemId("Adena", 3470, Config.BANKING_SYSTEM_GOLDBARS, player, null);
				player.getInventory().addAdena("Adena", Config.BANKING_SYSTEM_ADENA, player, null);
				player.getInventory().updateDatabase();
				player.sendPacket((L2GameServerPacket) new ItemList(player, true));
				player.sendMessage("Thank you, now you have " + Config.BANKING_SYSTEM_ADENA + " Adena, and "
						+ Config.BANKING_SYSTEM_GOLDBARS + " less Goldbar(s).");
			} else {
				player.sendMessage(
						"You do not have any Goldbars to turn into " + Config.BANKING_SYSTEM_ADENA + " Adena.");
			}
		if (command.equalsIgnoreCase("cubic_deposit"))
			if (player.getInventory().getInventoryItemCount(57, 0) >= Config.BANKING_SYSTEM_ADENA) {
				player.getInventory().reduceAdena("Goldbar", Config.BANKING_SYSTEM_ADENA, player, null);
				player.getInventory().addItem("Goldbar", 3470, Config.BANKING_SYSTEM_GOLDBARS, player, null);
				player.getInventory().updateDatabase();
				player.sendPacket((L2GameServerPacket) new ItemList(player, true));
				player.sendMessage("Thank you, now you have " + Config.BANKING_SYSTEM_GOLDBARS + " Goldbar(s), and "
						+ Config.BANKING_SYSTEM_ADENA + " less adena.");
			} else {
				player.sendMessage("You do not have enough Adena to convert to Goldbar(s), you need "
						+ Config.BANKING_SYSTEM_ADENA + " Adena.");
			}
		return true;
	}

	public String[] getCubicCommandList() {
		return CUBIC_COMMANDS;
	}
}
