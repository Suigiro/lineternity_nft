package com.dev.handler.cubiccommandhandlers;

import com.dev.datatable.MagicalSupportTable;
import com.dev.handler.ICubicCommandHandler;
import com.dev.template.L2MagicalSupport;

import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;

public class CubicMagicalSupport implements ICubicCommandHandler
{
	private static final String[] CUBIC_COMMANDS = new String[]
	{
		"cubic_service_id",
		"cubic_skill_id"
	};
	
	@Override
	public boolean useCubicCommand(Player player, String command)
	{
		Creature creature = player;
		if (player.isCastingNow() || player.isMovementDisabled() || player.isOutOfControl() || player.isInOlympiadMode() || player.isInObserverMode() || player.isInJail() || player.isInDuel())
		{
			player.sendMessage("Your current state does not allow you to use the magical support.");
			return false;
		}
		
		if (command.startsWith("cubic_service_id"))
		{
			String[] serviceArray = command.substring(command.indexOf("cubic_service_id") + 17).split(" ");
			for (String serviceList : serviceArray)
			{
				if (serviceList != null)
				{
					int serviceId = Integer.parseInt(serviceList);
					switch (serviceId)
					{
						case 1:
							creature.setCurrentCp(creature.getMaxCp());
							break;
						case 2:
							creature.setCurrentHp(creature.getMaxHp());
							break;
						case 3:
							creature.setCurrentMp(creature.getMaxMp());
							break;
						case 4:
							
							if (creature.getTarget() == player || creature.getTarget() == null)
								creature.stopAllEffects();
							
							if (creature.isInParty())
							{
								for (Creature members : player.getParty().getMembers())
								{
									members.stopAllEffects();
									if (members.getSummon() != null)
									{
										members.getSummon().stopAllEffects();
									}
								}
							}
							break;
					}
				}
			}
		}
		else if (command.startsWith("cubic_skill_id"))
		{
			String[] templateArray = command.substring(command.indexOf("cubic_skill_id") + 15).split(" ");
			for (String templateList : templateArray)
			{
				if (templateList != null)
					for (L2MagicalSupport template : MagicalSupportTable.getTemplate())
					{
						int skillId = Integer.parseInt(templateList);
						if (template.getSkillId() != skillId)
							continue;
						L2Skill skill = SkillTable.getInstance().getInfo(template.getSkillId(), template.getSkillLevel());
						if (skill == null)
							continue;
						if (!player.destroyItemByItemId("cubic magical support", template.getFeeId(), template.getFeeAmount(), creature, true))
							continue;
						
						if (creature.isInParty())
						{
							if (creature.getTarget() == null)
							{
								
								for (Creature member : creature.getParty().getMembers())
								{
									
									skill.getEffects(creature, member);
									
								}
								
							}
							else if (creature.getTarget() != null)
							{
								
								WorldObject obj = creature.getTarget();
								Creature target = (Creature) obj;
								
								if (target == player)
								{
									skill.getEffects(creature, target);
								}
								else
								{
									for (Creature member : creature.getParty().getMembers())
									{
										
										if (member == target || member.getSummon() == target)
										{
											skill.getEffects(creature, target);
										}
										
									}
								}
							}
						}
						else
						{
							skill.getEffects(creature, creature);
						}
						
						player.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.YOU_FEEL_S1_EFFECT).addSkillName(skill));
					}
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
