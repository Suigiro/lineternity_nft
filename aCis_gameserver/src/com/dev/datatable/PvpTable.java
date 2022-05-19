package com.dev.datatable;

import com.dev.template.L2Pvp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.sf.l2j.commons.util.StatsSet;

import net.sf.l2j.gameserver.data.xml.factory.XMLDocumentFactory;
import net.sf.l2j.gameserver.model.holder.IntIntHolder;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class PvpTable
{
	private static final Logger _log = Logger.getLogger(PvpTable.class.getName());
	
	private static final List<L2Pvp> _templates = new ArrayList<>();
	
	public static void load()
	{
		try
		{
			File f = new File("./data/xml/dev/pvp_color.xml");
			Document doc = XMLDocumentFactory.getInstance().loadDocument(f);
			Node n = doc.getFirstChild();
			for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
			{
				if (d.getNodeName().equalsIgnoreCase("template"))
				{
					NamedNodeMap attrs = d.getAttributes();
					int pvpAmount = Integer.valueOf(attrs.getNamedItem("pvp_amount").getNodeValue()).intValue();
					int nameColor = Integer.decode("0x" + attrs.getNamedItem("name_color").getNodeValue()).intValue();
					int titleColor = Integer.decode("0x" + attrs.getNamedItem("title_color").getNodeValue()).intValue();
					String learnSkill = attrs.getNamedItem("learn_skill").getNodeValue().trim();
					StatsSet set = new StatsSet();
					set.set("pvp_amount", pvpAmount);
					set.set("name_color", nameColor);
					set.set("title_color", titleColor);
					L2Pvp template = new L2Pvp(set);
					if (learnSkill != null)
					{
						String[] property = learnSkill.split(";");
						for (String data : property)
						{
							String[] holder = data.split(",");
							template.addLearnSkill(new IntIntHolder(Integer.parseInt(holder[0]), Integer.parseInt(holder[1])));
						}
					}
					_templates.add(template);
				}
			}
		}
		catch (Exception e)
		{
			_log.severe("Exception: PvpTable load: " + e);
		}
		_log.info("PvpColorTable: Loaded " + _templates.size() + " template(s).");
	}
	
	public static List<L2Pvp> getTemplate()
	{
		return _templates;
	}
}
