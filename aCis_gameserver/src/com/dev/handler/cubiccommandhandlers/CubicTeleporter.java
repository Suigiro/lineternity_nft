package com.dev.handler.cubiccommandhandlers;

import com.dev.datatable.TeleportTable;
import com.dev.handler.ICubicCommandHandler;
import com.dev.template.L2Teleport;

import net.sf.l2j.commons.concurrent.ThreadPool;

import net.sf.l2j.gameserver.enums.GaugeColor;
import net.sf.l2j.gameserver.enums.IntentionType;
import net.sf.l2j.gameserver.enums.ZoneId;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.location.Location;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;
import net.sf.l2j.gameserver.network.serverpackets.SetupGauge;

public class CubicTeleporter implements ICubicCommandHandler
{
	private static final String[] CUBIC_COMMANDS = new String[]
	{
		"cubic_teleport"
	};
	
	public boolean useCubicCommand(Player player, String command)
	{
		if (player.isCastingNow() || player.isSitting() || player.isMovementDisabled() || player.isOutOfControl() || player.isInOlympiadMode() || player.isInObserverMode() || player.isInJail() || player.isInDuel())
		{
			player.sendMessage("Your current state does not allow you to use the teleporter.");
			return false;
		}
		if (command.startsWith("cubic_teleport"))
		{
			int delay = (!player.isInCombat() && player.getPvpFlag() == 0 && player.getKarma() == 0 && player.isInsideZone(ZoneId.PEACE)) ? 3 : (player.isCursedWeaponEquipped() ? 90 : 30);
			delay *= 1000;
			player.getAI().setIntention(IntentionType.IDLE);
			player.setTarget(player);
			player.disableAllSkills();
			player.setIsCastingNow(true);
			player.broadcastPacket(new MagicSkillUse(player, 1050, 1, delay, 0));
			player.sendPacket(new SetupGauge(GaugeColor.BLUE, delay));
			player.setSkillCast(ThreadPool.schedule(() -> teleportFinalizer(player, command), delay));
			player.setCastInterruptTime(System.currentTimeMillis() + delay);
		}
		return true;
	}
	
	private static void teleportFinalizer(Player player, String command)
	{
		if (player.isDead() || player.isInDuel())
			return;
		player.enableAllSkills();
		player.setIsCastingNow(false);
		for (L2Teleport template : TeleportTable.getTemplate())
		{
			int teleportId = Integer.parseInt(command.substring(15));
			if (template.getTeleportId() != teleportId)
				continue;
			if (!player.destroyItemByItemId("cubic magical support", template.getFeeId(), template.getFeeAmount(), player, true))
				continue;
			Location loc = new Location(template.getX(), template.getY(), template.getZ());
			// player.teleToLocation(template.getX(), template.getY(), template.getZ(), 20);
			player.teleportTo(loc, 20);
		}
	}
	
	public String[] getCubicCommandList()
	{
		return CUBIC_COMMANDS;
	}
}
