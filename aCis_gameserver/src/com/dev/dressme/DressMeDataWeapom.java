package com.dev.dressme;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import net.sf.l2j.commons.data.xml.IXmlReader;

public class DressMeDataWeapom implements IXmlReader {
	private final List<DressMeWeapom> _entries = new ArrayList<>();

	public DressMeDataWeapom() {
		load();
	}

	public void reload() {
		_entries.clear();
		load();
	}

	@Override
	public void load() {
		parseFile("./data/xml/dressmeweapon.xml");
		LOGGER.info("Loaded {} DressMe Weapon templates.", _entries.size());
	}

	@Override
	public void parseDocument(Document doc, Path path) {
		forEach(doc, "list", listNode2 -> forEach(listNode2, "dressmew",
				dressNode2 -> _entries.add(new DressMeWeapom(parseAttributes(dressNode2)))));
	}

	public DressMeWeapom getItemId(int itemId) {
		return _entries.stream().filter(x -> x.getItemId() == itemId).findFirst().orElse(null);
	}

	public List<DressMeWeapom> getEntries() {
		return _entries;
	}

	public static DressMeDataWeapom getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		protected static final DressMeDataWeapom INSTANCE = new DressMeDataWeapom();
	}
}