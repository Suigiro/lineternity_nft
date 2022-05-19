package com.dev.model.actor;

import com.dev.datatable.PvpTable;
import com.dev.datatable.RewardTable;
import com.dev.handler.CubicCommandHandler;
import com.dev.handler.ICubicCommandHandler;
import com.dev.handler.IItemHandler;
import com.dev.handler.ItemHandler;
import com.dev.template.L2Pvp;
import com.dev.template.L2Reward;

import net.sf.l2j.commons.random.Rnd;

import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.holder.IntIntHolder;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;

public class Jogador
{
	public static void devPvpColor(Player player)
	{
		for (L2Pvp template : PvpTable.getTemplate())
		{
			if (template.getPvpAmount() > player.getPvpKills())
				continue;
			player.getAppearance().setNameColor(template.getNameColor());
			player.getAppearance().setTitleColor(template.getTitleColor());
			for (IntIntHolder holder : template.getLearnSkills())
			{
				L2Skill skill = SkillTable.getInstance().getInfo(holder.getId(), holder.getValue());
				if (skill != null)
					player.addSkill(skill, false);
			}
		}
		player.broadcastUserInfo();
	}
	
	public static void devPvpReward(Player player)
	{
		for (L2Reward pvpReward : RewardTable.getTemplate())
		{
			if (Rnd.nextDouble() < pvpReward.getChance() && player.getPvpKills() >= pvpReward.getPvpAmount())
				player.addItem("Pvp Reward", pvpReward.getItemId(), pvpReward.getItemCount(), (WorldObject) player, true);
		}
	}
	
	public static void onLogin(Player player)
	{
		devPvpColor(player);
	}
	
	public static void onLogout(Player player)
	{
	}
	
	public static void useCubicCommandHandler(Player player, String command)
	{
		ICubicCommandHandler handler = CubicCommandHandler.getCubicCommandHandler(command.split(" ")[0]);
		if (handler != null)
			handler.useCubicCommand(player, command);
	}
	
	public static void useItemHandler(Player player, ItemInstance item)
	{
		IItemHandler handler = ItemHandler.getItemHandler(item.getEtcItem());
		if (handler != null)
			handler.useItem(player, item);
	}
}
