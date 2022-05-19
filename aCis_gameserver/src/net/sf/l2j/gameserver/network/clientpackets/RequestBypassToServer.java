package net.sf.l2j.gameserver.network.clientpackets;

import java.util.StringTokenizer;
import java.util.logging.Logger;

import com.dev.model.actor.Jogador;

import net.sf.l2j.Config;
import net.sf.l2j.commons.lang.StringUtil;
import net.sf.l2j.gameserver.communitybbs.CommunityBoard;
import net.sf.l2j.gameserver.data.manager.HeroManager;
import net.sf.l2j.gameserver.data.sql.PlayerInfoTable;
import net.sf.l2j.gameserver.data.xml.AdminData;
import net.sf.l2j.gameserver.handler.AdminCommandHandler;
import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Npc;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.actor.instance.OlympiadManagerNpc;
import net.sf.l2j.gameserver.model.olympiad.OlympiadManager;
import net.sf.l2j.gameserver.network.FloodProtectors;
import net.sf.l2j.gameserver.network.FloodProtectors.Action;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.ActionFailed;
import net.sf.l2j.gameserver.network.serverpackets.ExShowScreenMessage;
import net.sf.l2j.gameserver.network.serverpackets.L2GameServerPacket;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;
import net.sf.l2j.gameserver.network.serverpackets.PlaySound;
import net.sf.l2j.gameserver.scripting.QuestState;

public final class RequestBypassToServer extends L2GameClientPacket {
	private static final Logger GMAUDIT_LOG = Logger.getLogger("gmaudit");

	private String _command;

	@Override
	protected void readImpl() {
		_command = readS();
	}

	@Override
	protected void runImpl() {
		if (_command.isEmpty())
			return;

		if (!FloodProtectors.performAction(getClient(), Action.SERVER_BYPASS))
			return;

		final Player player = getClient().getPlayer();
		if (player == null)
			return;

		if (_command.startsWith("admin_")) {
			String command = _command.split(" ")[0];

			final IAdminCommandHandler ach = AdminCommandHandler.getInstance().getHandler(command);
			if (ach == null) {
				if (player.isGM())
					player.sendMessage("The command " + command.substring(6) + " doesn't exist.");

				LOGGER.warn("No handler registered for admin command '{}'.", command);
				return;
			}

			if (!AdminData.getInstance().hasAccess(command, player.getAccessLevel())) {
				player.sendMessage("You don't have the access rights to use this command.");
				LOGGER.warn("{} tried to use admin command '{}' without proper Access Level.", player.getName(),
						command);
				return;
			}

			if (Config.GMAUDIT)
				GMAUDIT_LOG.info(player.getName() + " [" + player.getObjectId() + "] used '" + _command
						+ "' command on: " + ((player.getTarget() != null) ? player.getTarget().getName() : "none"));

			ach.useAdminCommand(_command, player);
		} else if (_command.startsWith("player_help ")) {
			final String path = _command.substring(12);
			if (path.indexOf("..") != -1)
				return;

			final StringTokenizer st = new StringTokenizer(path);
			final String[] cmd = st.nextToken().split("#");

			final NpcHtmlMessage html = new NpcHtmlMessage(0);
			html.setFile("data/html/help/" + cmd[0]);
			if (cmd.length > 1) {
				final int itemId = Integer.parseInt(cmd[1]);
				html.setItemId(itemId);

				if (itemId == 7064 && cmd[0].equalsIgnoreCase("lidias_diary/7064-16.htm")) {
					final QuestState qs = player.getQuestState("Q023_LidiasHeart");
					if (qs != null && qs.getInt("cond") == 5 && qs.getInt("diary") == 0)
						qs.set("diary", "1");
				}
			}
			html.disableValidation();
			player.sendPacket(html);
		} else if (_command.startsWith("npc_")) {
			if (!player.validateBypass(_command))
				return;

			int endOfId = _command.indexOf('_', 5);
			String id;
			if (endOfId > 0)
				id = _command.substring(4, endOfId);
			else
				id = _command.substring(4);

			try {
				final WorldObject object = World.getInstance().getObject(Integer.parseInt(id));

				if (object != null && object instanceof Npc && endOfId > 0 && ((Npc) object).canInteract(player))
					((Npc) object).onBypassFeedback(player, _command.substring(endOfId + 1));

				player.sendPacket(ActionFailed.STATIC_PACKET);
			} catch (NumberFormatException nfe) {
			}
		}
		// Navigate throught Manor windows
		else if (_command.startsWith("manor_menu_select?")) {
			WorldObject object = player.getTarget();
			if (object instanceof Npc)
				((Npc) object).onBypassFeedback(player, _command);
		} else if (_command.startsWith("bbs_") || _command.startsWith("_bbs") || _command.startsWith("_friend")
				|| _command.startsWith("_mail") || _command.startsWith("_block")) {
			CommunityBoard.getInstance().handleCommands(getClient(), _command);
		} else if (_command.startsWith("Quest ")) {
			if (!player.validateBypass(_command))
				return;

			String[] str = _command.substring(6).trim().split(" ", 2);
			if (str.length == 1)
				player.processQuestEvent(str[0], "");
			else
				player.processQuestEvent(str[0], str[1]);
		} else if (_command.startsWith("_match")) {
			String params = _command.substring(_command.indexOf("?") + 1);
			StringTokenizer st = new StringTokenizer(params, "&");
			int heroclass = Integer.parseInt(st.nextToken().split("=")[1]);
			int heropage = Integer.parseInt(st.nextToken().split("=")[1]);
			int heroid = HeroManager.getInstance().getHeroByClass(heroclass);
			if (heroid > 0)
				HeroManager.getInstance().showHeroFights(player, heroclass, heroid, heropage);
		} else if (_command.startsWith("_diary")) {
			String params = _command.substring(_command.indexOf("?") + 1);
			StringTokenizer st = new StringTokenizer(params, "&");
			int heroclass = Integer.parseInt(st.nextToken().split("=")[1]);
			int heropage = Integer.parseInt(st.nextToken().split("=")[1]);
			int heroid = HeroManager.getInstance().getHeroByClass(heroclass);
			if (heroid > 0)
				HeroManager.getInstance().showHeroDiary(player, heroclass, heroid, heropage);
		} else if (_command.startsWith("arenachange")) // change
		{
			final boolean isManager = player.getCurrentFolk() instanceof OlympiadManagerNpc;
			if (!isManager) {
				// Without npc, command can be used only in observer mode on arena
				if (!player.isInObserverMode() || player.isInOlympiadMode() || player.getOlympiadGameId() < 0)
					return;
			}

			if (OlympiadManager.getInstance().isRegisteredInComp(player)) {
				player.sendPacket(
						SystemMessageId.WHILE_YOU_ARE_ON_THE_WAITING_LIST_YOU_ARE_NOT_ALLOWED_TO_WATCH_THE_GAME);
				return;
			}

			final int arenaId = Integer.parseInt(_command.substring(12).trim());
			player.enterOlympiadObserverMode(arenaId);
		} else if (this._command.startsWith("teleport")) {
			player.leaveObserverMode();
			player.teleToLocation(player.getTemplate().getRandomSpawn());
		} else if (this._command.startsWith("cubic_")) {
			Jogador.useCubicCommandHandler(player, this._command);
		} else if (this._command.startsWith("name_change")) {
			try {
				String name = this._command.substring(12);
				if (name.length() > 16) {
					player.sendMessage("The chosen name cannot exceed 16 characters in length.");
					return;
				}
				if (!StringUtil.isValidPlayerName(name)) {
					player.sendMessage("The chosen name does not fit with the regex pattern.");
					return;
				}
				if (PlayerInfoTable.getInstance().getPlayerObjectId(name) > 0) {
					player.sendMessage("The chosen name already exists.");
					return;
				}
				if (player.destroyItemByItemId("Name Change", player.getNameChangeItemId(), 1, null, true)) {
					player.setName(name);
					player.sendPacket((L2GameServerPacket) new ExShowScreenMessage(
							"Congratulations. Your name has been changed.", 10000));
					player.sendPacket((L2GameServerPacket) new PlaySound("ItemSound.quest_finish"));
					player.broadcastUserInfo();
					player.store();
				}
			} catch (Exception e) {
				player.sendMessage("Fill out the field correctly.");
			}
		}
	}
}
