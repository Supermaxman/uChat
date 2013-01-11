package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;

import org.bukkit.entity.Player;

public class configReloaderExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if(player.isOp()){
        	uChat.conf = uChat.XE.getConfig();
        }
    }
    public configReloaderExecutor(uChat XE) {
        super(XE);
    }
}
