package com.dev.handler.itemhandlers;

import com.dev.handler.IItemHandler;

import net.sf.l2j.commons.concurrent.ThreadPool;
import net.sf.l2j.gameserver.data.SkillTable;
import net.sf.l2j.gameserver.enums.actors.Sex;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.CreatureSay;
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
import net.sf.l2j.gameserver.network.serverpackets.MagicSkillUse;
import net.sf.l2j.gameserver.network.serverpackets.SocialAction;
//import net.sf.l2j.gameserver.taskmanager.PremiumTaskManager;

public class Service implements IItemHandler {
	@Override
	public void useItem(Player player, ItemInstance item) {

		long remainingTime;
		int time;

		if (player.isCastingNow() || player.isOutOfControl() || player.isInOlympiadMode() || player.isInObserverMode()
				|| player.isInJail() || player.isInDuel()) {
			player.sendMessage("Your current state does not allow you to use.");
			return;
		}
		switch (item.getServiceId()) {
		case 1:
			if (player.getPkKills() < 0) {
				player.sendMessage("You do not have enough Pk kills for clean.");
				return;
			}
			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
			player.sendPacket(new CreatureSay(0, 17, "Service", "Congratulations, your PK count has been set to 0."));
			player.sendPacket(new ExShowScreenMessage("Your PK count has been set to 0", 10000));
			player.setPkKills(0);
			player.broadcastUserInfo();
			break;
		case 2:
			if (player.getKarma() < 0) {
				player.sendMessage("You do not have enough karma for clean.");
				return;
			}
			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
			player.sendPacket(new CreatureSay(0, 17, "Service", "Congratulations, your karma has been cleared."));
			player.sendPacket(new ExShowScreenMessage("Your karma has been cleared", 10000));
			player.broadcastPacket(new MagicSkillUse(player, player, 1426, 1, 1000, 0));
			player.setKarma(0);
			break;
		case 3:
			if (player.getRecomHave() == 255) {
				player.sendMessage("You already have full recommends.");
				return;
			}
			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
			player.sendPacket(
					new CreatureSay(0, 17, "Service", "Congratulations, your recommend score has been increased."));
			player.sendPacket(new ExShowScreenMessage("Your recommend score has been increased", 10000));
			player.setRecomHave(255);
			// player.getLastRecomUpdate();
			player.broadcastUserInfo();
			break;
		case 4:
			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
			player.sendPacket(new CreatureSay(0, 17, "Service",
					"Congratulations, your gender has been changed,you will be disconected in 3 seconds."));
			player.sendPacket(new ExShowScreenMessage("Your gender has been changed", 10000));
			player.getAppearance().setSex((player.getAppearance().getSex() == Sex.MALE) ? Sex.FEMALE : Sex.MALE);
			player.broadcastUserInfo();
			player.decayMe();
			player.spawnMe();
			ThreadPool.schedule(() -> player.logout(false), 3000L);
			break;
//		case 5:
//
//			player.setVip(true);
//			PremiumTaskManager.getInstance().add(player);
//
//			remainingTime = player.getMemos().getLong("vipTime", 0);
//			time = 30;
//			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
//
//			if (remainingTime > 0) {
//				player.getMemos().set("vipTime", remainingTime + TimeUnit.DAYS.toMillis(time));
//				player.sendMessage(player.getName() + " your VIP was extended by " + time + " day(s).");
//			} else {
//				player.getMemos().set("vipTime", System.currentTimeMillis() + TimeUnit.DAYS.toMillis(time));
//				player.sendMessage(player.getName() + " now you are a VIP, your duration is " + time + " day(s).");
//
//				for (IntIntHolder itemVip : Config.LIST_VIP_ITEMS) {
//					if (itemVip.getId() > 0)
//						player.addItem("Add", itemVip.getId(), itemVip.getValue(), player, true);
//				}
//			}
//			break;
//		case 6:
//
//			player.setAio(true);
//			PremiumTaskManager.getInstance().add(player);
//
//			remainingTime = player.getMemos().getLong("aioTime", 0);
//			time = 30;
//			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
//
//			if (remainingTime > 0) {
//				player.getMemos().set("aioTime", remainingTime + TimeUnit.DAYS.toMillis(time));
//				player.sendMessage(player.getName() + " his Aio was extended by " + time + " day(s).");
//			} else {
//				player.getMemos().set("aioTime", System.currentTimeMillis() + TimeUnit.DAYS.toMillis(time));
//				player.sendMessage(player.getName() + " now you are Aio, your duration is " + time + " day(s).");
//
//				for (IntIntHolder itemAIO : Config.LIST_AIO_ITEMS) {
//					if (itemAIO.getId() > 0) {
//						player.addItem("Add", itemAIO.getId(), itemAIO.getValue(), player, true);
//						player.getInventory()
//								.equipItemAndRecord(player.getInventory().getItemByItemId(itemAIO.getId()));
//					}
//				}
//			}
//
//			break;
		case 7:
			if (player.isNoble()) {
				player.sendMessage("Your already have noblesse privileges.");
				return;
			}
			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
			player.sendPacket(new CreatureSay(0, 17, "Service",
					"Congratulations, you are now a noble, you are granted with noblesse status and noblesse skills."));
			player.sendPacket(
					new ExShowScreenMessage("You are now a noble, you are granted with noblesse status", 10000));
			player.setNoble(true, true);
			player.broadcastPacket(new SocialAction(player, 16));
			player.getInventory().addItem("Tiara", 7694, 1, player, null);
			player.broadcastUserInfo();
			break;
		case 8:
			if (!player.isClanLeader()) {
				player.sendMessage("Only leader of a clan can use it.");
				return;
			}
			if (player.getClan().getLevel() == 8) {
				player.sendMessage("Your clan is already level 8.");
				return;
			}
			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
			player.sendPacket(new CreatureSay(0, 17, "Service", "Congratulations, your clan now has all privileges."));
			player.sendPacket(new ExShowScreenMessage("Your clan now has all privileges", 10000));
			player.getClan().changeLevel(8);
			player.getClan().broadcastClanStatus();
			player.broadcastPacket(new MagicSkillUse(player, player, 5103, 1, 1000, 0));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(370, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(371, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(372, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(373, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(374, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(375, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(376, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(377, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(378, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(379, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(380, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(381, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(382, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(383, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(384, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(385, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(386, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(387, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(388, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(389, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(390, 3));
			player.getClan().addNewSkill(SkillTable.getInstance().getInfo(391, 1));
			break;
//		case 9:
//
//			player.setHero(true);
//			PremiumTaskManager.getInstance().add(player);
//
//			time = 30;
//
//			remainingTime = player.getMemos().getLong("heroTime", 0);
//			player.destroyItem("Consume", item.getObjectId(), 1, null, true);
//
//			if (remainingTime > 0) {
//				player.getMemos().set("heroTime", remainingTime + TimeUnit.DAYS.toMillis(time));
//				player.sendMessage(player.getName() + " your Hero was extended by " + time + " dias(s).");
//			} else {
//				player.getMemos().set("heroTime", System.currentTimeMillis() + TimeUnit.DAYS.toMillis(time));
//				player.sendMessage(player.getName() + " now you are a Hero, your duration is " + time + " dia(s).");
//			}
//
//			player.broadcastUserInfo();
//			break;
		}
	}

	public static void Classes(Player player, String command) {
		for (L2Skill skill : player.getSkills().values())
			player.removeSkill(skill.getId(), false);
		String classes = command.substring(command.indexOf("_") + 1);
		switch (classes) {
		case "duelist":
			player.setClassId(88);
			player.setBaseClass(88);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "dreadnought":
			player.setClassId(89);
			player.setBaseClass(89);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "phoenix":
			player.setClassId(90);
			player.setBaseClass(90);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "hell":
			player.setClassId(91);
			player.setBaseClass(91);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "sagittarius":
			player.setClassId(92);
			player.setBaseClass(92);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "adventurer":
			player.setClassId(93);
			player.setBaseClass(93);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "archmage":
			player.setClassId(94);
			player.setBaseClass(94);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "soultaker":
			player.setClassId(95);
			player.setBaseClass(95);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "arcana":
			player.setClassId(96);
			player.setBaseClass(96);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "cardinal":
			player.setClassId(97);
			player.setBaseClass(97);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "hierophant":
			player.setClassId(98);
			player.setBaseClass(98);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "evas":
			player.setClassId(99);
			player.setBaseClass(99);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "muse":
			player.setClassId(100);
			player.setBaseClass(100);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "windrider":
			player.setClassId(101);
			player.setBaseClass(101);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "sentinel":
			player.setClassId(102);
			player.setBaseClass(102);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "mystic":
			player.setClassId(103);
			player.setBaseClass(103);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "elemental":
			player.setClassId(104);
			player.setBaseClass(104);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "saint":
			player.setClassId(105);
			player.setBaseClass(105);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "templar":
			player.setClassId(106);
			player.setBaseClass(106);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "dancer":
			player.setClassId(107);
			player.setBaseClass(107);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "hunter":
			player.setClassId(108);
			player.setBaseClass(108);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "gsentinel":
			player.setClassId(109);
			player.setBaseClass(109);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "screamer":
			player.setClassId(110);
			player.setBaseClass(110);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "master":
			player.setClassId(111);
			player.setBaseClass(111);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "ssaint":
			player.setClassId(112);
			player.setBaseClass(112);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "titan":
			player.setClassId(113);
			player.setBaseClass(113);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "khavatari":
			player.setClassId(114);
			player.setBaseClass(114);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "domi":
			player.setClassId(115);
			player.setBaseClass(115);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "doom":
			player.setClassId(116);
			player.setBaseClass(116);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "fortune":
			player.setClassId(117);
			player.setBaseClass(117);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		case "maestro":
			player.setClassId(118);
			player.setBaseClass(118);
			player.store();
			player.broadcastUserInfo();
			player.sendSkillList();
			player.rewardSkills();
			player.sendMessage("Your base class has been changed! You will Be Disconected in 5 Seconds!");
			ThreadPool.schedule(() -> player.logout(false), 5000L);
			break;
		}
	}
}
