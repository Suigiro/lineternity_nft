package net.sf.l2j.gameserver.handler;

import net.sf.l2j.gameserver.autofarm.AutofarmCommandHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class VoicedCommandHandler {
    private static final Map<Integer, IVoicedCommandHandler> VOICED_COMMANDS = new HashMap<>();

    private VoicedCommandHandler() {
        registerVoicedCommand(new AutofarmCommandHandler());
    }

    private void registerVoicedCommand(IVoicedCommandHandler voicedCommand) {
        Arrays.stream(voicedCommand.getVoicedCommands()).forEach(v -> VOICED_COMMANDS.put(v.intern().hashCode(), voicedCommand));
    }

    public IVoicedCommandHandler getVoicedCommand(String voicedCommand) {
        return VOICED_COMMANDS.get(voicedCommand.hashCode());
    }

    public int size() {
        return VOICED_COMMANDS.size();
    }

    public static final VoicedCommandHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final VoicedCommandHandler INSTANCE = new VoicedCommandHandler();
    }
}
