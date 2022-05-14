package com.dev.datatable;

import com.dev.template.L2MagicalSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.sf.l2j.commons.util.StatsSet;

import net.sf.l2j.gameserver.data.xml.factory.XMLDocumentFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class MagicalSupportTable
{
	private static final Logger _log = Logger.getLogger(MagicalSupportTable.class.getName());
	
	private static final List<L2MagicalSupport> _templates = new ArrayList<>();
	
	public static void load()
	{
		try
		{
			File f = new File("./data/xml/cubic/magical_support.xml");
			Document doc = XMLDocumentFactory.getInstance().loadDocument(f);
			Node n = doc.getFirstChild();
			for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
			{
				if (d.getNodeName().equalsIgnoreCase("template"))
				{
					NamedNodeMap attrs = d.getAttributes();
					int skillId = Integer.valueOf(attrs.getNamedItem("skill_id").getNodeValue()).intValue();
					int skillLevel = Integer.valueOf(attrs.getNamedItem("skill_level").getNodeValue()).intValue();
					int feeId = Integer.valueOf(attrs.getNamedItem("fee_id").getNodeValue()).intValue();
					int feeAmount = Integer.valueOf(attrs.getNamedItem("fee_amount").getNodeValue()).intValue();
					StatsSet set = new StatsSet();
					set.set("skill_id", skillId);
					set.set("skill_level", skillLevel);
					set.set("fee_id", feeId);
					set.set("fee_amount", feeAmount);
					_templates.add(new L2MagicalSupport(set));
				}
			}
		}
		catch (Exception e)
		{
			_log.severe("Exception: MagicalSupportTable load: " + e);
		}
		_log.info("MagicalSupportTable: Loaded " + _templates.size() + " template(s).");
	}
	
	public static List<L2MagicalSupport> getTemplate()
	{
		return _templates;
	}
}
