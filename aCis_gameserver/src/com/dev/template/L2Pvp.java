package com.dev.template;

import java.util.ArrayList;
import java.util.List;

import net.sf.l2j.commons.util.StatsSet;

import net.sf.l2j.gameserver.model.holder.IntIntHolder;

public class L2Pvp
{
	private final int _pvpAmount;
	
	private final int _nameColor;
	
	private final int _titleColor;
	
	private final List<IntIntHolder> _learnSkill = new ArrayList<>();
	
	public L2Pvp(StatsSet set)
	{
		this._pvpAmount = set.getInteger("pvp_amount");
		this._nameColor = set.getInteger("name_color");
		this._titleColor = set.getInteger("title_color");
	}
	
	public int getPvpAmount()
	{
		return this._pvpAmount;
	}
	
	public int getNameColor()
	{
		return this._nameColor;
	}
	
	public int getTitleColor()
	{
		return this._titleColor;
	}
	
	public List<IntIntHolder> getLearnSkills()
	{
		return this._learnSkill;
	}
	
	public void addLearnSkill(IntIntHolder holder)
	{
		this._learnSkill.add(holder);
	}
}
