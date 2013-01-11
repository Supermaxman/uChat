package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.uChatFormater;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class tradeExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String m = "";
            int i = 0;
            for (String s : args) {
                if (i == 0) {
                    m = m + s;
                } else {
                    m = m + " " + s;
                }
                i++;
            }
            String name = player.getName();
            String world = player.getWorld().getName();


            String message = uChatFormater.format(player, m, name, world, uChat.trade);
            if (!uChat.trade.getPlayers().contains(player.getName())) {
                uChat.trade.addPlayer(player);
            }
            uChat.trade.sendString(message);


        } else if (args.length == 0) {
            uChat.channelIn.put(player, uChat.trade);
            uChat.isWhispering.put(player, false);
            if (!uChat.trade.getPlayers().contains(player.getName())) {

                uChat.trade.addPlayer(player);
            }
            player.sendMessage(ChatColor.AQUA + "[uChat]: Now Talking In " + uChat.trade.getColor() + uChat.trade.getName() + ChatColor.AQUA + ".");
        }
    }

    public tradeExecutor(uChat XE) {
        super(XE);
    }
}
