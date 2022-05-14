package com.dev.template;

import net.sf.l2j.commons.util.StatsSet;

public class L2MagicalSupport
{
	private final int _skillId;
	
	private final int _skillLevel;
	
	private final int _feeId;
	
	private final int _feeAmount;
	
	public L2MagicalSupport(StatsSet set)
	{
		this._skillId = set.getInteger("skill_id");
		this._skillLevel = set.getInteger("skill_level");
		this._feeId = set.getInteger("fee_id");
		this._feeAmount = set.getInteger("fee_amount");
	}
	
	public int getSkillId()
	{
		return this._skillId;
	}
	
	public int getSkillLevel()
	{
		return this._skillLevel;
	}
	
	public int getFeeId()
	{
		return this._feeId;
	}
	
	public int getFeeAmount()
	{
		return this._feeAmount;
	}
}
