package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.uChatFormater;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class tellExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length >= 2) {
            if (player.getServer().getPlayer(args[0]) != null) {
                Player r = player.getServer().getPlayer(args[0]);
                String m = "";
                int i = 0;
                for (String s : args) {
                    if (i == 0) {
                    } else {
                        m = m + " " + s;
                    }
                    i++;
                }
                String name = player.getName();


                String message = uChatFormater.formatWhisper(player, m, name, r);
                r.sendMessage(message);
                message = uChatFormater.formatWhisperTo(player, m, name, r);
                player.sendMessage(message);
                uChat.whisper.put(player, r);
                uChat.whisper.put(r, player);
            } else {
                player.sendMessage(ChatColor.RED + "[uChat]: ERROR, The Player " + args[0] + " Is Not Online Or Does Not Exist.");
            }
        } else if (args.length == 1) {
            if (player.getServer().getPlayer(args[0]) != null) {
                uChat.whisper.put(player, player.getServer().getPlayer(args[0]));
                uChat.whisper.put(player.getServer().getPlayer(args[0]), player);
                uChat.isWhispering.put(player, true);
                player.sendMessage(ChatColor.AQUA + "[uChat]: Now Whispering " + ChatColor.translateAlternateColorCodes('&', uChat.chat.getPlayerPrefix(player.getServer().getPlayer(args[0]))) + player.getServer().getPlayer(args[0]).getName() + ChatColor.AQUA + ".");
            } else {
                player.sendMessage(ChatColor.RED + "[uChat]: ERROR, The Player " + args[0] + " Is Not Online Or Does Not Exist.");
            }
        } else {
            player.sendMessage(ChatColor.RED + "[uChat]: SYNTAX ERROR, Type /tell [username] [message].");
        }
    }

    public tellExecutor(uChat XE) {
        super(XE);
    }
}
