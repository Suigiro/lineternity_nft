package com.dev.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.dev.handler.cubiccommandhandlers.CubicAugmentation;
import com.dev.handler.cubiccommandhandlers.CubicBanking;
import com.dev.handler.cubiccommandhandlers.CubicChat;
import com.dev.handler.cubiccommandhandlers.CubicGeneral;
import com.dev.handler.cubiccommandhandlers.CubicItemStore;
import com.dev.handler.cubiccommandhandlers.CubicMagicalSupport;
import com.dev.handler.cubiccommandhandlers.CubicSymbolMaker;
import com.dev.handler.cubiccommandhandlers.CubicTeleporter;

import net.sf.l2j.Config;

public class CubicCommandHandler {
	private static final Logger _log = Logger.getLogger(CubicCommandHandler.class.getName());

	private static final Map<Integer, ICubicCommandHandler> _datatable = new HashMap<>();

	public static void load() {
		registerCubicCommandHandler(new CubicChat());
		registerCubicCommandHandler(new CubicGeneral());
		registerCubicCommandHandler(new CubicItemStore());
		registerCubicCommandHandler(new CubicMagicalSupport());
		registerCubicCommandHandler(new CubicSymbolMaker());
		registerCubicCommandHandler(new CubicTeleporter());
		registerCubicCommandHandler(new CubicAugmentation());
		if (Config.BANKING_SYSTEM_ENABLED)
			registerCubicCommandHandler(new CubicBanking());
		_log.info("CubicCommandHandler: Loaded " + _datatable.size() + " handler(s).");
	}

	public static void registerCubicCommandHandler(ICubicCommandHandler handler) {
		for (String id : handler.getCubicCommandList())
			_datatable.put(Integer.valueOf(id.hashCode()), handler);
	}

	public static ICubicCommandHandler getCubicCommandHandler(String cubicCommand) {
		String command = cubicCommand;
		if (cubicCommand.indexOf(" ") != -1)
			command = cubicCommand.substring(0, cubicCommand.indexOf(" "));
		return _datatable.get(Integer.valueOf(command.hashCode()));
	}
}
