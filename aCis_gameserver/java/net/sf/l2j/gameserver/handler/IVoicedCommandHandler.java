package net.sf.l2j.gameserver.handler;

import net.sf.l2j.gameserver.model.actor.Player;

public interface IVoicedCommandHandler {
    void useVoicedCommand(Player activeChar, String command);
    String[] getVoicedCommands();
}