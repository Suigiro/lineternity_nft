package com.dev;

import com.dev.datatable.MagicalSupportTable;
import com.dev.datatable.TeleportTable;
import com.dev.handler.CubicCommandHandler;
import com.dev.handler.ItemHandler;

public class Main {
	public static void init() {
		// carregamentos de lineternity
		MagicalSupportTable.load();
//		PvpTable.load();
//		RewardTable.load();
		TeleportTable.load();
		CubicCommandHandler.load();
		ItemHandler.load();
	}
}
