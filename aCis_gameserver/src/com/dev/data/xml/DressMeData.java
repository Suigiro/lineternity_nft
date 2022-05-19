package com.dev.data.xml;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.dev.model.DressMe;

import net.sf.l2j.commons.data.xml.IXmlReader;

import org.w3c.dom.Document;

/**
 * @author Williams and Stinkymadness
 */
public class DressMeData implements IXmlReader
{
    private final List<DressMe> _entries = new ArrayList<>();
    
    public DressMeData()
    {
        load();
    }
    
    public void reload()
    {
        _entries.clear();
        load();
    }
    
    @Override
    public void load()
    {
        parseFile("./data/xml/dev/skins.xml");
        LOGGER.info("Loaded {} skins templates.", _entries.size());
    }
    
    @Override
    public void parseDocument(Document doc, Path path)
    {
        forEach(doc, "list", listNode -> forEach(listNode, "skin", dressNode -> _entries.add(new DressMe(parseAttributes(dressNode)))));
    }
    
    public DressMe getItemId(int itemId)
    {
        return _entries.stream().filter(x -> x.getItemId() == itemId).findFirst().orElse(null);
    }
    
    public List<DressMe> getEntries()
    {
        return _entries;
    }
    
    public static DressMeData getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
    
    private static class SingletonHolder
    {
        protected static final DressMeData INSTANCE = new DressMeData();
    }
}
