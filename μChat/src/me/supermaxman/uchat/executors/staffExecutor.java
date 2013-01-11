package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.uChatFormater;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class staffExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (uChat.permission.has(player, "xechat.staff.chat")) {
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

                String message = uChatFormater.format(player, m, name, world, uChat.z);
                if (!uChat.z.getPlayers().contains(player.getName())) {

                    uChat.z.addPlayer(player);
                }
                uChat.z.sendString(message);

            } else if (args.length == 0) {
                uChat.channelIn.put(player, uChat.z);
                uChat.isWhispering.put(player, false);
                if (!uChat.z.getPlayers().contains(player.getName())) {

                    uChat.z.addPlayer(player);
                }
                player.sendMessage(ChatColor.AQUA + "[uChat]: Now Talking In " + uChat.z.getColor() + uChat.z.getName() + ChatColor.AQUA + ".");
            }


        } else {
            player.sendMessage(ChatColor.RED + "[uChat]: ERROR, You do not have permission to enter " + uChat.z.getColor() + uChat.z.getName() + ChatColor.RED + ".");
        }
    }

    public staffExecutor(uChat XE) {
        super(XE);
    }
}
