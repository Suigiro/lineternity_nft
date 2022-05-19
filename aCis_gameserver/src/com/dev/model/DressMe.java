package com.dev.model;

import net.sf.l2j.commons.util.StatsSet;

/**
 * @author Williams
 */
public class DressMe
{
    private final int _itemId;
    private final int _hairId;
    private final int _chestId;
    private final int _legsId;
    private final int _glovesId;
    private final int _feetId;
    
    private final int _duration;
    
    public DressMe(StatsSet set)
    {
        _itemId = set.getInteger("itemId", 0);
        _hairId = set.getInteger("hairId", 0);
        _chestId = set.getInteger("chestId", 0);
        _legsId = set.getInteger("legsId", 0);
        _glovesId = set.getInteger("glovesId", 0);
        _feetId = set.getInteger("feetId", 0);
        _duration = set.getInteger("duration", 0);
    }
    
    public final int getItemId()
    {
        return _itemId;
    }
    
    public int getHairId()
    {
        return _hairId;
    }
    
    public int getChestId()
    {
        return _chestId;
    }
    
    public int getLegsId()
    {
        return _legsId;
    }
    
    public int getGlovesId()
    {
        return _glovesId;
    }
    
    public int getFeetId()
    {
        return _feetId;
    }
    
    public int getDuration()
    {
        return _duration;
    }
}
