package com.dev.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.dev.handler.itemhandlers.BuffPortable;
import com.dev.handler.itemhandlers.Cubic;
import com.dev.handler.itemhandlers.NameChange;
import com.dev.handler.itemhandlers.Service;
import com.dev.handler.itemhandlers.ShopPortable;

import net.sf.l2j.gameserver.model.item.kind.EtcItem;

public class ItemHandler {
	private static final Logger _log = Logger.getLogger(ItemHandler.class.getName());

	private static final Map<Integer, IItemHandler> _datatable = new HashMap<>();

	public static void load() {
		registerItemHandler(new Cubic());
		registerItemHandler(new ShopPortable());
		registerItemHandler(new BuffPortable());
		registerItemHandler(new NameChange());
		registerItemHandler(new Service());
		_log.info("ItemHandler: Loaded " + _datatable.size() + " handler(s).");
	}

	public static void registerItemHandler(IItemHandler handler) {
		_datatable.put(Integer.valueOf(handler.getClass().getSimpleName().intern().hashCode()), handler);
	}

	public static IItemHandler getItemHandler(EtcItem item) {
		if (item == null || item.getHandlerName() == null)
			return null;
		return _datatable.get(Integer.valueOf(item.getHandlerName().hashCode()));
	}
}
