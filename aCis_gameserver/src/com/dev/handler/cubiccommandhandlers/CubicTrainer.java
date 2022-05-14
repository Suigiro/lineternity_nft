package com.dev.handler.cubiccommandhandlers;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.data.xml.SkillTreeData;
import net.sf.l2j.gameserver.model.EnchantSkillData;
import net.sf.l2j.gameserver.model.EnchantSkillLearn;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ExEnchantSkillList;
import net.sf.l2j.gameserver.network.serverpackets.ExShowVariationCancelWindow;
import net.sf.l2j.gameserver.network.serverpackets.ExShowVariationMakeWindow;

public class CubicTrainer implements ICubicCommandHandler
{
	private static final String[] CUBIC_COMMANDS = new String[]
	{
		"cubic_make_augment",
		"cubic_cancel_augment",
		"cubic_enchant_skill"
	};
	
	@Override
	public boolean useCubicCommand(Player player, String command)
	{
		if (player.isCastingNow() || player.isMovementDisabled() || player.isOutOfControl() || player.isInOlympiadMode() || player.isInObserverMode() || player.isInJail() || player.isInDuel())
		{
			player.sendMessage("Your current state does not allow you to use the trainer.");
			return false;
		}
		if (command.equalsIgnoreCase("cubic_make_augment"))
		{
			player.sendPacket(SystemMessageId.SELECT_THE_ITEM_TO_BE_AUGMENTED);
			player.sendPacket(ExShowVariationMakeWindow.STATIC_PACKET);
		}
		if (command.equalsIgnoreCase("cubic_cancel_augment"))
		{
			player.sendPacket(SystemMessageId.SELECT_THE_ITEM_FROM_WHICH_YOU_WISH_TO_REMOVE_AUGMENTATION);
			player.sendPacket(ExShowVariationCancelWindow.STATIC_PACKET);
		}
		if (command.equalsIgnoreCase("cubic_enchant_skill"))
		{
			if (player.getClassId().level() < 3)
			{
				player.sendMessage("You must have completed the 3rd class transfer.");
				return false;
			}
			ExEnchantSkillList list = new ExEnchantSkillList();
			boolean hasSkill = false;
			for (EnchantSkillLearn esl : SkillTreeData.getInstance().getAvailableEnchantSkills(player))
			{
				L2Skill skill = SkillTable.getInstance().getInfo(esl.getId(), esl.getLevel());
				if (skill == null)
					continue;
				EnchantSkillData esd = SkillTreeData.getInstance().getEnchantSkillData(esl.getEnchant());
				if (esd == null)
					continue;
				list.addSkill(esl.getId(), esl.getLevel(), esd.getCostSp(), esd.getCostExp());
				hasSkill = true;
			}
			if (hasSkill)
			{
				player.setIsCubicBypass(true);
				player.sendPacket(list);
			}
		}
		return true;
	}
	
	@Override
	public String[] getCubicCommandList()
	{
		return CUBIC_COMMANDS;
	}
}
