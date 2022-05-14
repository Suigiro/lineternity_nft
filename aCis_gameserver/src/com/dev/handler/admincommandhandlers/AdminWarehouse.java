
package com.dev.handler.admincommandhandlers;

import com.dev.datatable.IconTable;

import java.util.ArrayList;
import java.util.List;

import net.sf.l2j.commons.util.SysUtil;

import net.sf.l2j.gameserver.handler.IAdminCommandHandler;
import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

/**
 * This class handles following admin commands:
 * <ul>
 * <li>show_warehouse</li>
 * <li>delete_warehouse_item</li>
 * </ul>
 * @author Sorameshi
 */
public class AdminWarehouse implements IAdminCommandHandler
{
	
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_show_warehouse",
		"admin_delete_warehouse_item"
	};
	
	@Override
	public boolean useAdminCommand(String command, Player activeChar)
	{
		if ((activeChar.getTarget() == null))
		{
			activeChar.sendMessage("Selecione um alvo");
			return false;
		}
		
		if (!(activeChar.getTarget() instanceof Player))
		{
			activeChar.sendMessage("O alvo precisa ser um jogador");
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
			// System.out.println("Contagem dos itens: " + player.getWarehouse().getItemByObjectId(Integer.parseInt(val)).getCount());
			
			player.destroyItem("GM Deletou!", Integer.parseInt(val), player.getWarehouse().getItemByObjectId(Integer.parseInt(val)).getCount(), null, true);
			showItemsPage(activeChar, 0);
		}
		
		return true;
	}
	
	private static void showItemsPage(Player activeChar, int page)
	{
		final Player target = activeChar.getTarget().getActingPlayer();
		
		@SuppressWarnings("unused")
		final List<ItemInstance> items = new ArrayList<ItemInstance>();
		items.addAll(target.getWarehouse().getItems());
		
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
		adminReply.setFile("data/html/admin/show_warehouse.htm");
		adminReply.replace("%PLAYER_NAME%", target.getName());
		
		StringBuilder sbPages = new StringBuilder();
		for (int x = 0; x < maxPages; x++)
		{
			int pagenr = x + 1;
			sbPages.append("<td><button value=\"" + String.valueOf(pagenr) + "\" action=\"bypass -h admin_show_warehouse " + String.valueOf(x) + "\" width=14 height=14 back=\"sek.cbui67\" fore=\"sek.cbui67\"></td>");
		}
		
		adminReply.replace("%PAGES%", sbPages.toString());
		
		StringBuilder sbItems = new StringBuilder();
		
		for (int i = itemsStart; i < itemsEnd; i++)
		{
			sbItems.append("<tr><td><img src=\"" + IconTable.getInstance().getIcon(items.get(i).getItemId()) + "\" width=32 height=32></td>");
			sbItems.append("<td width=60>" + items.get(i).getName() + "</td></tr><br>");
			sbItems.append("<tr><td><button action=\"bypass -h admin_delete_warehouse_item " + String.valueOf(items.get(i).getObjectId()) + "\" width=16 height=16 back=\"L2UI.bbs_delete\" fore=\"L2UI.bbs_delete\">" + "</td>");
		}
		
		adminReply.replace("%ITEMS%", sbItems.toString());
		
		activeChar.sendPacket(adminReply);
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
}