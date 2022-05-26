package com.dev.handler.cubiccommandhandlers;

import java.util.List;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.gameserver.data.xml.SkillTreeData;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.holder.skillnode.EnchantSkillNode;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ExEnchantSkillList;
import net.sf.l2j.gameserver.network.serverpackets.ExShowVariationCancelWindow;
import net.sf.l2j.gameserver.network.serverpackets.ExShowVariationMakeWindow;
import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;

public class CubicAugmentation implements ICubicCommandHandler {
	private static final String[] CUBIC_COMMANDS = new String[] { "cubic_make_augment", "cubic_cancel_augment",
			"cubic_enchant_skill" };

	@Override
	public boolean useCubicCommand(Player player, String command) {
		if (player.isCastingNow() || player.isMovementDisabled() || player.isOutOfControl() || player.isInOlympiadMode()
				|| player.isInObserverMode() || player.isInJail() || player.isInDuel()) {
			player.sendMessage("Your current state does not allow you to use the trainer.");
			return false;
		}
		if (command.equalsIgnoreCase("cubic_make_augment")) {
			player.sendPacket(SystemMessageId.SELECT_THE_ITEM_TO_BE_AUGMENTED);
			player.sendPacket(ExShowVariationMakeWindow.STATIC_PACKET);
		}
		if (command.equalsIgnoreCase("cubic_cancel_augment")) {
			player.sendPacket(SystemMessageId.SELECT_THE_ITEM_FROM_WHICH_YOU_WISH_TO_REMOVE_AUGMENTATION);
			player.sendPacket(ExShowVariationCancelWindow.STATIC_PACKET);
		}
		if (command.equalsIgnoreCase("cubic_enchant_skill")) {
			if (player.getClassId().level() < 3) {
				player.sendMessage("You must have completed the 3rd class transfer.");
				return false;
			}

			final List<EnchantSkillNode> skills = SkillTreeData.getInstance().getEnchantSkillsFor(player);
			if (skills.isEmpty()) {
				player.sendPacket(SystemMessageId.THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT);

				if (player.getLevel() < 74)
					player.sendPacket(SystemMessage
							.getSystemMessage(SystemMessageId.DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN_S1).addNumber(74));
				else
					player.sendPacket(SystemMessageId.NO_MORE_SKILLS_TO_LEARN);
			} else {
				player.setIsCubicBypass(false);
				player.sendPacket(new ExEnchantSkillList(skills));

			}
		}
		return true;
	}

	@Override
	public String[] getCubicCommandList() {
		return CUBIC_COMMANDS;
	}
}
