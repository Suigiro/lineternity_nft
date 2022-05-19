package com.dev.template;

import net.sf.l2j.commons.util.StatsSet;

public class L2Reward
{
	private int _pvpAmount;
	
	private int _itemId;
	
	private int _itemCount;
	
	private double _chance;
	
	public L2Reward(StatsSet set)
	{
		this._pvpAmount = set.getInteger("pvpAmount");
		this._itemId = set.getInteger("itemId");
		this._itemCount = set.getInteger("itemCount");
		this._chance = set.getDouble("chance");
	}
	
	public int getPvpAmount()
	{
		return this._pvpAmount;
	}
	
	public int getItemId()
	{
		return this._itemId;
	}
	
	public int getItemCount()
	{
		return this._itemCount;
	}
	
	public double getChance()
	{
		return this._chance;
	}
}
