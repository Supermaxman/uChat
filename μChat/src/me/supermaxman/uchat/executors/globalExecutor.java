package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 05:20
 */
public class globalExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            player.sendMessage(ChatColor.RED + "[uChat]: Error, type /global to talk in global.");
        } else if (args.length == 0) {
            uChat.isWhispering.put(player, false);
            uChat.channelIn.put(player, uChat.g);
            player.sendMessage(ChatColor.AQUA + "[uChat]: Now Talking In " + uChat.g.getColor() + uChat.g.getName() + ChatColor.AQUA + ".");
        }
    }

    public globalExecutor(uChat XE) {
        super(XE);
    }
}
