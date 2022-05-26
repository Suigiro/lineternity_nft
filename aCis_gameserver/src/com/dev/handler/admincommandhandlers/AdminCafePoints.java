/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY?; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.dev.handler.admincommandhandlers;

import java.util.StringTokenizer;

import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.actor.Player;

/**
 * This class handles following admin commands:
 * <ul>
 * <li>show_invetory</li>
 * <li>delete_inventory_item</li>
 * </ul>
 * 
 * @author Sorameshi
 */
public class AdminCafePoints implements IAdminCommandHandler {
	private static final String[] ADMIN_COMMANDS = { "admin_pcbang_add", "admin_pcbang_remove", "admin_pcbang_show" };

	@Override
	public boolean useAdminCommand(String command, Player activeChar) {
		if (activeChar == null)
			return false;

		WorldObject targetChar = activeChar.getTarget();

		StringTokenizer st = new StringTokenizer(command, " ");
		String actualCommand = st.nextToken(); // Get actual command

		String val = "";
		if (st.countTokens() >= 1)
			val = st.nextToken();

		if (actualCommand.equalsIgnoreCase("admin_pcbang_add")) {
			Player targetPlayer = (Player) targetChar;

			if (targetPlayer == null)
				targetPlayer = activeChar;

			try {
				targetPlayer.increasePcCafePoints(Integer.parseInt(val), false);
			} catch (NumberFormatException e) {
				activeChar.sendMessage("Wrong number format.");
				return false;
			}
		} else if (actualCommand.equalsIgnoreCase("admin_pcbang_remove")) {
			try {

				Player targetPlayer = (Player) targetChar;

				if (targetPlayer == null)
					targetPlayer = activeChar;

				int pontos = Integer.parseInt(val);

				int pcpoints = targetPlayer.getPcCafePoints();

				if (pcpoints >= pontos)
					targetPlayer.decreasePcCafePoints(pontos);
				else if (pcpoints == 0)
					activeChar.sendMessage("O alvo nao possui pontos para serem removidos!");
				else
					activeChar.sendMessage("O alvo tem apenas " + pcpoints + " para poderem ser consumidos!");

			} catch (NumberFormatException e) {
				activeChar.sendMessage("Precisa ser um numero valido inteiro!");
				return false;
			}
		} else if (actualCommand.equalsIgnoreCase("admin_pcbang_show")) {

			Player targetPlayer = (Player) targetChar;

			if (targetPlayer == null)
				targetPlayer = activeChar;

			int pcpoints = targetPlayer.getPcCafePoints();
			activeChar.sendMessage(targetPlayer.getName() + " possui: " + pcpoints + " PC Bang Points");

		}
		return true;
	}

	@Override
	public String[] getAdminCommandList() {
		return ADMIN_COMMANDS;
	}
}