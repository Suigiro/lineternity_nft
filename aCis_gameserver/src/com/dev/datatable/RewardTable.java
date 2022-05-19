package com.dev.datatable;

import com.dev.template.L2Reward;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.sf.l2j.commons.util.StatsSet;

import net.sf.l2j.gameserver.data.xml.factory.XMLDocumentFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class RewardTable
{
	private static final Logger _log = Logger.getLogger(RewardTable.class.getName());
	
	private static final List<L2Reward> _templates = new ArrayList<>();
	
	public static void load()
	{
		try
		{
			File f = new File("./data/xml/dev/pvp_reward.xml");
			Document doc = XMLDocumentFactory.getInstance().loadDocument(f);
			Node n = doc.getFirstChild();
			for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
			{
				if (d.getNodeName().equalsIgnoreCase("template"))
				{
					NamedNodeMap attrs = d.getAttributes();
					int pvpAmount = Integer.valueOf(attrs.getNamedItem("pvpAmount").getNodeValue()).intValue();
					int itemId = Integer.valueOf(attrs.getNamedItem("itemId").getNodeValue()).intValue();
					int itemCount = Integer.valueOf(attrs.getNamedItem("itemCount").getNodeValue()).intValue();
					double chance = Double.valueOf(attrs.getNamedItem("chance").getNodeValue()).doubleValue();
					StatsSet set = new StatsSet();
					set.set("pvpAmount", pvpAmount);
					set.set("itemId", itemId);
					set.set("itemCount", itemCount);
					set.set("chance", chance);
					_templates.add(new L2Reward(set));
				}
			}
		}
		catch (Exception e)
		{
			_log.severe("Exception: PvpRewardTable load: " + e);
		}
		_log.info("PvpRewardTable: Loaded " + _templates.size() + " template(s).");
	}
	
	public static List<L2Reward> getTemplate()
	{
		return _templates;
	}
}
