package com.dev.dressme;

import net.sf.l2j.commons.util.StatsSet;

public class DressMeWeapom {
	private final int _itemId;
	private final int _lhandId;
	private final int _rhandId;

	public DressMeWeapom(StatsSet set) {
		_itemId = set.getInteger("itemId", 0);
		_lhandId = set.getInteger("lhandId", 0);
		_rhandId = set.getInteger("rhandId", 0);
	}

	public final int getItemId() {
		return _itemId;
	}

	public int getLHandId() {
		return _lhandId;
	}

	public int getRHandId() {
		return _rhandId;
	}

	public int setLHandId(int val) {
		return _lhandId;
	}

	public int setRHandId(int val) {
		return _rhandId;
	}
}