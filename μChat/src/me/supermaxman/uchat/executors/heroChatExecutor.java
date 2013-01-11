package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 05:20
 */
public class heroChatExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "[uChat]: Herp");
        } else if (args.length > 0) {
            String[] args2 = Arrays.copyOfRange(args, 1, args.length);
            if(args[0].equalsIgnoreCase("join")){
                uChat.joinExecutor.run(player,args2);
            }
            else if(args[0].equalsIgnoreCase("leave"))
            {
               uChat.channelLeaveExecutor.run(player,args2);
            }
            else {
                uChat.joinExecutor.run(player,args);
            }
        }
    }

    public heroChatExecutor(uChat XE) {
        super(XE);
    }
}
