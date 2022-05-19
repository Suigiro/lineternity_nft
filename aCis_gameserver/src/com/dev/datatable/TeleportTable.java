package com.dev.datatable;

import com.dev.template.L2Teleport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.sf.l2j.commons.util.StatsSet;

import net.sf.l2j.gameserver.data.xml.factory.XMLDocumentFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class TeleportTable
{
	private static final Logger _log = Logger.getLogger(TeleportTable.class.getName());
	
	private static final List<L2Teleport> _templates = new ArrayList<>();
	
	public static void load()
	{
		try
		{
			File f = new File("./data/xml/cubic/teleport.xml");
			Document doc = XMLDocumentFactory.getInstance().loadDocument(f);
			Node n = doc.getFirstChild();
			for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
			{
				if (d.getNodeName().equalsIgnoreCase("template"))
				{
					NamedNodeMap attrs = d.getAttributes();
					int teleportId = Integer.valueOf(attrs.getNamedItem("teleport_id").getNodeValue()).intValue();
					int x = Integer.valueOf(attrs.getNamedItem("x").getNodeValue()).intValue();
					int y = Integer.valueOf(attrs.getNamedItem("y").getNodeValue()).intValue();
					int z = Integer.valueOf(attrs.getNamedItem("z").getNodeValue()).intValue();
					int feeId = Integer.valueOf(attrs.getNamedItem("fee_id").getNodeValue()).intValue();
					int feeAmount = Integer.valueOf(attrs.getNamedItem("fee_amount").getNodeValue()).intValue();
					StatsSet set = new StatsSet();
					set.set("teleport_id", teleportId);
					set.set("x", x);
					set.set("y", y);
					set.set("z", z);
					set.set("fee_id", feeId);
					set.set("fee_amount", feeAmount);
					_templates.add(new L2Teleport(set));
				}
			}
		}
		catch (Exception e)
		{
			_log.severe("Exception: TeleportTable load: " + e);
		}
		_log.info("TeleportTable: Loaded " + _templates.size() + " template(s).");
	}
	
	public static List<L2Teleport> getTemplate()
	{
		return _templates;
	}
}
