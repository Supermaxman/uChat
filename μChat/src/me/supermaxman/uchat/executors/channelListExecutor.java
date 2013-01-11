package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.Objects.uChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class channelListExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String s = ChatColor.AQUA + "[uChat]: Channels: ";
            String name = args[0];
            for (uChannel c : uChat.channels.values()) {
                if (c.getCreatorName().equalsIgnoreCase(name)) {
                    s = s + c.getColor() + c.getName() + ChatColor.AQUA + ",";
                }
            }
            player.sendMessage(s);


        } else if (args.length == 0) {
            String s = ChatColor.AQUA + "[uChat]: Channels: ";
            for (uChannel c : uChat.channels.values()) {
                s = s + c.getColor() + c.getName() + ChatColor.AQUA + ",";

            }
            player.sendMessage(s);


        }
    }

    public channelListExecutor(uChat XE) {
        super(XE);
    }
}