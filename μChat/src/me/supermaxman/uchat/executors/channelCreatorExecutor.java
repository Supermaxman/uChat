package me.supermaxman.uchat.executors;

import java.util.Map;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.Objects.uChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class channelCreatorExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String channelName = args[0];

            String name = player.getName();
            ChatColor color = ChatColor.WHITE;

            if (args.length >= 3) {
                try {
                    if (args[2].toUpperCase().equalsIgnoreCase("MAGIC")) {
                        player.sendMessage(ChatColor.RED + "[uChat]: ERROR, Specified Chat Color Is Not Allowed, Defaulting to White.");
                    } else {
                        color = ChatColor.valueOf(args[2].toUpperCase());
                    }
                } catch (IllegalArgumentException e) {
                    player.sendMessage(ChatColor.RED + "[uChat]: ERROR, Specified Chat Color Does Not Exist, Defaulting to White.");
                }
            }
            boolean canCreate = true;
            for (Map.Entry<String, uChannel> channelEntry : uChat.channels.entrySet()) {
                if(channelName.equalsIgnoreCase(channelEntry.getKey())){
                   	canCreate = false;
                   	break;
                }
            }
            if (canCreate) {
                int amt = 0;
                int max = 0;
                if (uChat.permission.has(player, "xechat.channels.create")) {
                    if (uChat.permission.has(player, "xechat.channels.2")) {
                        max = 2;
                    }
                    if (uChat.permission.has(player, "xechat.channels.4")) {
                        max = 4;
                    }
                    if (uChat.permission.has(player, "xechat.channels.6")) {
                        max = 6;
                    }
                    if (uChat.permission.has(player, "xechat.channels.8")) {
                        max = 8;
                    }

                    for (uChannel ch : uChat.channels.values()) {
                        if (ch.getCreatorName().equalsIgnoreCase(name)) {
                            amt++;
                        }
                    }

                    if (amt < max) {

                        uChat.isWhispering.put(player, false);
                        uChannel channel = new uChannel(channelName, name, color);
                        if (args.length >= 2) {
                            String pass = args[1];
                            channel.setPrivate(true, pass);
                        }
                        
                        channel.addPlayer(player);
                        channel.save();
                        uChat.channels.put(channelName, channel);
                        uChat.channelIn.put(player, channel);
                        player.sendMessage(ChatColor.AQUA + "[uChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                    } else {
                        player.sendMessage(ChatColor.RED + "[uChat]: You Have Hit Your Maximum Amount Of Channels.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "[uChat]: Your Rank Cannot Create Channels.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[uChat]: Channel " + channelName + " Already Exists.");
            }

        } else if (args.length == 0) {

            player.sendMessage(ChatColor.RED + "[uChat]: SYNTAX ERROR, type /create [ChannelName] <Password> <Color>.");

        }
    }

    public channelCreatorExecutor(uChat XE) {
        super(XE);
    }
}
