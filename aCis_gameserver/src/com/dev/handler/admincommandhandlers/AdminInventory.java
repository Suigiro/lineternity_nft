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

import com.dev.datatable.IconTable;

import java.util.ArrayList;
import java.util.List;

import net.sf.l2j.commons.logging.CLogger;
import net.sf.l2j.commons.util.SysUtil;

import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

/**
 * This class handles following admin commands:
 * <ul>
 * <li>show_invetory</li>
 * <li>delete_inventory_item</li>
 * </ul>
 * @author Sorameshi
 */
public class AdminInventory implements IAdminCommandHandler
{
	private static final CLogger LOGGER = new CLogger(AdminInventory.class.getName());
	
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_show_inventory",
		"admin_delete_inventory_item"
	};
	
	@Override
	public boolean useAdminCommand(String command, Player activeChar)
	{
		if ((activeChar.getTarget() == null))
		{
			activeChar.sendMessage("Select a target");
			return false;
		}
		
		if (!(activeChar.getTarget() instanceof Player))
		{
			activeChar.sendMessage("Target need to be player");
			return false;
		}
		
		Player player = activeChar.getTarget().getActingPlayer();
		
		if (command.startsWith(ADMIN_COMMANDS[0]))
		{
			if (command.length() > ADMIN_COMMANDS[0].length())
			{
				String com = command.substring(ADMIN_COMMANDS[0].length() + 1);
				if (SysUtil.isDigit(com))
				{
					showItemsPage(activeChar, Integer.parseInt(com));
				}
			}
			
			else
			{
				showItemsPage(activeChar, 0);
			}
		}
		else if (command.contains(ADMIN_COMMANDS[1]))
		{
			String val = command.substring(ADMIN_COMMANDS[1].length() + 1);
			
			// System.out.println("Vai servir!");
			// System.out.println("Val STRING: " + val);
			// System.out.println("Val (parseado): " + Integer.parseInt(val));
			// System.out.println("Contagem dos itens: " + player.getInventory().getItemByObjectId(Integer.parseInt(val)).getCount());
			
			player.destroyItem("GM Destroy", Integer.parseInt(val), player.getInventory().getItemByObjectId(Integer.parseInt(val)).getCount(), null, true);
			showItemsPage(activeChar, 0);
		}
		
		return true;
	}
	
	private static void showItemsPage(Player activeChar, int page)
	{
		final Player target = activeChar.getTarget().getActingPlayer();
		
		@SuppressWarnings(
		{
			"unused"
		})
		final List<ItemInstance> items = new ArrayList<ItemInstance>();
		items.addAll(target.getInventory().getItems());
		
		int maxItemsPerPage = 16;
		int maxPages = items.size() / maxItemsPerPage;
		if (items.size() > (maxItemsPerPage * maxPages))
		{
			maxPages++;
		}
		
		if (page > maxPages)
		{
			page = maxPages;
		}
		
		int itemsStart = maxItemsPerPage * page;
		int itemsEnd = items.size();
		if ((itemsEnd - itemsStart) > maxItemsPerPage)
		{
			itemsEnd = itemsStart + maxItemsPerPage;
		}
		
		final NpcHtmlMessage adminReply = new NpcHtmlMessage(0);
		adminReply.setFile("data/html/admin/show_inventory.htm");
		adminReply.replace("%PLAYER_NAME%", target.getName());
		
		StringBuilder sbPages = new StringBuilder();
		for (int x = 0; x < maxPages; x++)
		{
			int pagenr = x + 1;
			sbPages.append("<td><button value=\"" + String.valueOf(pagenr) + "\" action=\"bypass -h admin_show_inventory " + String.valueOf(x) + "\" width=14 height=14 back=\"sek.cbui67\" fore=\"sek.cbui67\"></td>");
		}
		
		adminReply.replace("%PAGES%", sbPages.toString());
		
		StringBuilder sbItems = new StringBuilder();
		
		for (int i = itemsStart; i < itemsEnd; i++)
		{
			
			IconTable.getInstance();
			sbItems.append("<tr><td><img src=\"" + IconTable.getIcon(items.get(i).getItemId()) + "\" width=32 height=32></td>");
			sbItems.append("<td width=60>" + items.get(i).getName() + "</td></tr><br>");
			sbItems.append("<tr><td><button action=\"bypass -h admin_delete_inventory_item " + String.valueOf(items.get(i).getObjectId()) + "\" width=16 height=16 back=\"L2UI.bbs_delete\" fore=\"L2UI.bbs_delete\">" + "</td>");
			
		}
		
		adminReply.replace("%ITEMS%", sbItems.toString());
		
		activeChar.sendPacket(adminReply);
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		LOGGER.info("Commands: " + ADMIN_COMMANDS.hashCode());
		return ADMIN_COMMANDS;
	}
	
	/*
	 * (non-Javadoc)
	 * @see net.sf.l2j.gameserver.handler.IAdminCommandHandler#useAdminCommand(java.lang.String, net.sf.l2j.gameserver.model.actor.Player)
	 */
	
}