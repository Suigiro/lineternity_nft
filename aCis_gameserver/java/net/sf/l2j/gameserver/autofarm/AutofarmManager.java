package net.sf.l2j.gameserver.autofarm;

import net.sf.l2j.commons.concurrent.ThreadPool;
import net.sf.l2j.gameserver.model.actor.Player;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

public enum AutofarmManager {
    INSTANCE;
    
    private final Long iterationSpeedMs = 450L;
    
    private final ConcurrentHashMap<Integer, AutofarmPlayerRoutine> activeFarmers = new ConcurrentHashMap<>();
    private final ScheduledFuture<?> onUpdateTask = ThreadPool.scheduleAtFixedRate(onUpdate(), 1000, iterationSpeedMs);
    
    private Runnable onUpdate() {
        return () -> activeFarmers.forEach((integer, autofarmPlayerRoutine) -> autofarmPlayerRoutine.executeRoutine());
    }

    public void startFarm(Player player){
        if(isAutofarming(player)) {
            player.sendMessage("You are already autofarming");
            return;
        }
        
        activeFarmers.put(player.getObjectId(), new AutofarmPlayerRoutine(player));
        player.sendMessage("Autofarming activated");
    }
    
    public void stopFarm(Player player){
        if(!isAutofarming(player)) {
            player.sendMessage("You are not autofarming");
            return;
        }

        activeFarmers.remove(player.getObjectId());
        player.sendMessage("Autofarming deactivated");
    }
    
    public void toggleFarm(Player player){
        if(isAutofarming(player)) {
            stopFarm(player);
            return;
        }
        
        startFarm(player);
    }
    
    public Boolean isAutofarming(Player player){
        return activeFarmers.containsKey(player.getObjectId());
    }
    
    public void onPlayerLogout(Player player){
        stopFarm(player);
    }

    public void onDeath(Player player) {
        if(isAutofarming(player)) {
            activeFarmers.remove(player.getObjectId());
        }
    }
}
