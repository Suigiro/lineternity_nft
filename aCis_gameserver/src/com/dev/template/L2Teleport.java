package com.dev.template;

import net.sf.l2j.commons.util.StatsSet;

public class L2Teleport
{
	private final int _teleportId;
	
	private final int _x;
	
	private final int _y;
	
	private final int _z;
	
	private final int _feeId;
	
	private final int _feeAmount;
	
	public L2Teleport(StatsSet set)
	{
		this._teleportId = set.getInteger("teleport_id");
		this._x = set.getInteger("x");
		this._y = set.getInteger("y");
		this._z = set.getInteger("z");
		this._feeId = set.getInteger("fee_id");
		this._feeAmount = set.getInteger("fee_amount");
	}
	
	public int getTeleportId()
	{
		return this._teleportId;
	}
	
	public int getX()
	{
		return this._x;
	}
	
	public int getY()
	{
		return this._y;
	}
	
	public int getZ()
	{
		return this._z;
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
