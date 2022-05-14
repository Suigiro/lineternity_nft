package com.dev.handler.cubiccommandhandlers;

import com.dev.handler.ICubicCommandHandler;

import net.sf.l2j.gameserver.model.actor.Player;
import net.sf.l2j.gameserver.network.serverpackets.L2GameServerPacket;
import net.sf.l2j.gameserver.network.serverpackets.NpcHtmlMessage;

public class CubicMenu implements ICubicCommandHandler
{
	private static final String[] CUBIC_COMMANDS = new String[]
	{
		"cubic_menu",
		"cubic_buffprot",
		"cubic_tradeprot",
		"cubic_ssprot",
		"cubic_pmref",
		"cubic_xpnot",
		"cubic_partyin"
	};
	
	public boolean useCubicCommand(Player player, String command)
	{
		if (command.equalsIgnoreCase("cubic_menu"))
			mainHtml(player);
		if (command.equalsIgnoreCase("cubic_buffprot"))
			if (player.isBuffProtected())
			{
				player.setIsBuffProtected(false);
				player.sendMessage("Setting: Buff protect disabled");
				mainHtml(player);
			}
			else
			{
				player.setIsBuffProtected(true);
				player.sendMessage("Setting: Buff protect activated");
				mainHtml(player);
			}
		if (command.equalsIgnoreCase("cubic_tradeprot"))
			if (player.getTradeRefusal())
			{
				player.setTradeRefusal(false);
				player.sendMessage("Setting: Trade protect disabled");
				mainHtml(player);
			}
			else
			{
				player.setTradeRefusal(true);
				player.sendMessage("Setting: Trade protect activated");
				mainHtml(player);
			}
		if (command.equalsIgnoreCase("cubic_ssprot"))
			if (player.isSSDisabled())
			{
				player.setIsSSDisabled(false);
				player.sendMessage("Setting: Shots effect disabled");
				mainHtml(player);
			}
			else
			{
				player.setIsSSDisabled(true);
				player.sendMessage("Setting: Shots effect activated");
				mainHtml(player);
			}
		if (command.equalsIgnoreCase("cubic_pmref"))
			if (player.isInRefusalMode())
			{
				player.setInRefusalMode(false);
				player.sendMessage("Setting: Personal refusal disabled");
				mainHtml(player);
			}
			else
			{
				player.setInRefusalMode(true);
				player.sendMessage("Setting: Personal refusal activated");
				mainHtml(player);
			}
		if (command.equalsIgnoreCase("cubic_xpnot"))
			if (player.getGainXpSpEnable())
			{
				player.setGainXpSpEnable(false);
				player.sendMessage("Setting: GainXP/SP disabled");
				mainHtml(player);
			}
			else
			{
				player.setGainXpSpEnable(true);
				player.sendMessage("Setting: GainXP/SP activated");
				mainHtml(player);
			}
		if (command.equalsIgnoreCase("cubic_partyin"))
			if (player.isPartyInvProt())
			{
				player.setIsPartyInvProt(false);
				player.sendMessage("Setting: Party protect disabled");
				mainHtml(player);
			}
			else
			{
				player.setIsPartyInvProt(true);
				player.sendMessage("Setting: Party protect activated");
				mainHtml(player);
			}
		return true;
	}
	
	private static final void mainHtml(Player activeChar)
	{
		NpcHtmlMessage nhm = new NpcHtmlMessage(5);
		StringBuilder tb = new StringBuilder("");
		tb.append("<html><title>Menu</title><body>");
		tb.append("<center><br>");
		tb.append("<img src=\"l2ui.bbs_lineage2\" width=78 height=13>");
		tb.append("<font color=\"FF6600\">Personal settings</font>");
		tb.append("<table width=\"100%\" align=\"center\">");
		tb.append("<tr><td><font color=4F4F4F>_____________________________________________</font></td></tr>");
		tb.append("</table><br>");
		tb.append("<table width=\"250\">");
		tb.append("<tr>");
		tb.append("<td width=\"45\" valign=\"top\" align=\"center\"><img src=\"L2ui_ch3.menubutton4\" width=\"38\" height=\"38\"></td>");
		tb.append("<br1><td><font color=\"00FF00\">" + activeChar.getName() + "</font>, use this menu for everything related to your gameplay.<br1></td>");
		tb.append("</tr>");
		tb.append("</table>");
		tb.append("<table width=\"100%\" align=\"center\">");
		tb.append("<tr><td><font color=4F4F4F>_____________________________________________</font></td></tr>");
		tb.append("</table><br>");
		tb.append("<table width=\"165\" height=\"26\">");
		tb.append("<tr>");
		tb.append("<td><button value=\"Buff Protected\" action=\"bypass -h cubic_buffprot\" width=140 height=21 back=\"bigbutton3_over\" fore=\"bigbutton3\"></td>");
		if (activeChar.isBuffProtected())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_buffprot\" width=16 height=16 back=\"L2UI_CH3.joypad_lock_down\" fore=\"L2UI_CH3.joypad_lock\"></td>");
		if (!activeChar.isBuffProtected())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_buffprot\" width=16 height=16 back=\"L2UI_CH3.joypad_unlock_down\" fore=\"L2UI_CH3.joypad_unlock\"></td>");
		tb.append("</tr><tr>");
		tb.append("<td><button value=\"Pesonal Refusal\" action=\"bypass -h cubic_pmref\" width=140 height=21 back=\"bigbutton3_over\" fore=\"bigbutton3\"></td>");
		if (activeChar.isInRefusalMode())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_pmref\" width=16 height=16 back=\"L2UI_CH3.joypad_lock_down\" fore=\"L2UI_CH3.joypad_lock\"></td>");
		if (!activeChar.isInRefusalMode())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_pmref\" width=16 height=16 back=\"L2UI_CH3.joypad_unlock_down\" fore=\"L2UI_CH3.joypad_unlock\"></td>");
		tb.append("</tr><tr>");
		tb.append("<td><button value=\"Trade Protected\" action=\"bypass -h cubic_tradeprot\" width=140 height=21 back=\"bigbutton3_over\" fore=\"bigbutton3\"></td>");
		if (activeChar.getTradeRefusal())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_tradeprot\" width=16 height=16 back=\"L2UI_CH3.joypad_lock_down\" fore=\"L2UI_CH3.joypad_lock\"></td>");
		if (!activeChar.getTradeRefusal())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_tradeprot\" width=16 height=16 back=\"L2UI_CH3.joypad_unlock_down\" fore=\"L2UI_CH3.joypad_unlock\"></td>");
		tb.append("</tr><tr>");
		tb.append("<td><button value=\"Shots Effect\" action=\"bypass -h cubic_ssprot\" width=140 height=21 back=\"bigbutton3_over\" fore=\"bigbutton3\"></td>");
		if (activeChar.isSSDisabled())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_ssprot\" width=16 height=16 back=\"L2UI_CH3.joypad_lock_down\" fore=\"L2UI_CH3.joypad_lock\"></td>");
		if (!activeChar.isSSDisabled())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_ssprot\" width=16 height=16 back=\"L2UI_CH3.joypad_unlock_down\" fore=\"L2UI_CH3.joypad_unlock\"></td>");
		tb.append("</tr><tr>");
		tb.append("<td><button value=\"Party Invite Protected\" action=\"bypass -h cubic_partyin\" width=140 height=21 back=\"bigbutton3_over\" fore=\"bigbutton3\"></td>");
		if (activeChar.isPartyInvProt())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_partyin\" width=16 height=16 back=\"L2UI_CH3.joypad_lock_down\" fore=\"L2UI_CH3.joypad_lock\"></td>");
		if (!activeChar.isPartyInvProt())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_partyin\" width=16 height=16 back=\"L2UI_CH3.joypad_unlock_down\" fore=\"L2UI_CH3.joypad_unlock\"></td>");
		tb.append("</tr><tr>");
		tb.append("<td><button value=\"Experience Gain\" action=\"bypass -h cubic_xpnot\" width=140 height=21 back=\"bigbutton3_over\" fore=\"bigbutton3\"></td>");
		if (activeChar.getGainXpSpEnable())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_xpnot\" width=16 height=16 back=\"L2UI_CH3.joypad_unlock_down\" fore=\"L2UI_CH3.joypad_unlock\"></td>");
		if (!activeChar.getGainXpSpEnable())
			tb.append("<td width=\"16\"><button action=\"bypass -h cubic_xpnot\" width=16 height=16 back=\"L2UI_CH3.joypad_lock_down\" fore=\"L2UI_CH3.joypad_lock\"></td>");
		tb.append("</tr>");
		tb.append("</table>");
		tb.append("<table width=\"100%\" align=\"center\">");
		tb.append("<tr><td><font color=4F4F4F>_____________________________________________</font></td></tr>");
		tb.append("</table>");
		tb.append("</center>");
		tb.append("</body></html>");
		nhm.setHtml(tb.toString());
		activeChar.sendPacket((L2GameServerPacket) nhm);
	}
	
	public String[] getCubicCommandList()
	{
		return CUBIC_COMMANDS;
	}
}
