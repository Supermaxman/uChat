package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.Objects.uChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class channelLeaveExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String channelName = args[0];

            uChannel channel = null;
            for (Map.Entry<String, uChannel> channelEntry : uChat.channels.entrySet()) {
                if(channelName.equalsIgnoreCase(channelEntry.getKey())){
                    channel = channelEntry.getValue();
                }
            }
            if (channel != null) {
                if (channel.getPlayers().contains(player.getName())) {
                    channel.removePlayer(player);
                    uChat.channelIn.put(player, uChat.g);
                    player.sendMessage(ChatColor.AQUA + "[uChat]: You Have Left " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                } else {
                    player.sendMessage(ChatColor.RED + "[uChat]: You Are Not In " + channelName + ".");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[uChat]: The Channel " + channelName + " Does Not Exist.");
            }

        } else if (args.length == 0) {

            player.sendMessage(ChatColor.RED + "[uChat]: SYNTAX ERROR, type /leave [ChannelName].");

        }
    }

    public channelLeaveExecutor(uChat XE) {
        super(XE);
    }
}
