package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.uChatFormater;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class replyExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            if (uChat.whisper.containsKey(player)) {
                Player r = uChat.whisper.get(player);
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


                String message = uChatFormater.formatWhisper(player, m, name, r);
                r.sendMessage(message);
                message = uChatFormater.formatWhisperTo(player, m, name, r);
                player.sendMessage(message);
            } else {
                player.sendMessage(ChatColor.RED + "[uChat]: ERROR, You Are Not Whispering Anyone.");
            }
        } else {
            if (uChat.whisper.containsKey(player)) {
                Player r = uChat.whisper.get(player);
                uChat.isWhispering.put(player, true);
                player.sendMessage(ChatColor.AQUA + "[uChat]: Now Whispering " + ChatColor.translateAlternateColorCodes('&', uChat.chat.getPlayerPrefix(r)) + r.getName() + ChatColor.AQUA + ".");
            }else{
                player.sendMessage(ChatColor.RED + "[uChat]: ERROR, You Are Not Whispering Anyone.");
            }
        }
    }

    public replyExecutor(uChat XE) {
        super(XE);
    }
}
