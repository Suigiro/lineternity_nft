package com.dev.handler;

import net.sf.l2j.gameserver.model.actor.Player;

public interface ICubicCommandHandler
{
	boolean useCubicCommand(Player paramL2PcInstance, String paramString);
	
	String[] getCubicCommandList();
}
