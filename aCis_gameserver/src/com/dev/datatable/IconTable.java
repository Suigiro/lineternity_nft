package com.dev.datatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.l2j.commons.util.SysUtil;

//import net.sf.l2j.commons.util.CloseUtil;

import net.sf.l2j.L2DatabaseFactory;

public class IconTable
{
	private static final Logger _log = Logger.getLogger(IconTable.class.getName());
	
	private static Map<Integer, String> _icons;
	
	public static final IconTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	@SuppressWarnings("unused")
	protected IconTable()
	{
		_icons = new HashMap<Integer, String>();
		load();
	}
	
	public void reload()
	{
		_icons.clear();
		load();
	}
	
	@SuppressWarnings("resource")
	private void load()
	{
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			final PreparedStatement stmt = con.prepareStatement("SELECT * FROM icons");
			final ResultSet rset = stmt.executeQuery();
			int id = 1;
			String value = null;
			
			while (rset.next())
			{
				id = rset.getInt("itemId");
				value = rset.getString("iconName");
				_icons.put(id, value);
			}
			rset.close();
			stmt.close();
			
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "IconTable: Error loading from database:" + e.getMessage(), e);
		}
		finally
		{
			SysUtil.close(con);
		}
		
		_log.info("IconTable: Loaded " + _icons.size() + " icons.");
	}
	
	public static String getIcon(int id)
	{
		if (_icons.get(id) == null)
			return "icon.NOIMAGE";
		
		return _icons.get(id);
	}
	
	private static class SingletonHolder
	{
		protected static final IconTable _instance = new IconTable();
	}
}