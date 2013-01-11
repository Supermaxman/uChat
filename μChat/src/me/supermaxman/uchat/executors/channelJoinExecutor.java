package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.Objects.uChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class channelJoinExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String channelName = args[0];


            if (args.length >= 1) {
                uChat.isWhispering.put(player, false);
                uChannel channel = null;
                for (Map.Entry<String, uChannel> channelEntry : uChat.channels.entrySet()) {
                    if(channelName.equalsIgnoreCase(channelEntry.getKey())){
                        channel = channelEntry.getValue();
                        break;
                    }
                }

//                uChannel channel = uChat.channels.get(channelName);
                if (channel != null) {
                    if (!channel.getPlayers().contains(player.getName())) {
                        if (channel.isPrivate()) {
                            if (args.length > 1) {
                                String pass = args[1];
                                if (pass.equalsIgnoreCase(channel.getPassword())||player.isOp()||uChat.permission.has(player, "xechat.channel.joinall")) {
                                    channel.addPlayer(player);
                                    uChat.channelIn.put(player, channel);

                                    player.sendMessage(ChatColor.AQUA + "[uChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                                } else {
                                    player.sendMessage(ChatColor.RED + "[uChat]: Incorrect Password For  " + channelName + ".");
                                }
                            } else {
                                player.sendMessage(ChatColor.RED + "[uChat]: The Channel " + channelName + " Requires A Password.");
                            }
                        } else {
                            channel.addPlayer(player);
                            uChat.channelIn.put(player, channel);

                            player.sendMessage(ChatColor.AQUA + "[uChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                        }
                    } else {
                        uChat.channelIn.put(player, channel);

                        player.sendMessage(ChatColor.AQUA + "[uChat]: Now Talking In " + channel.getColor() + channel.getName() + ChatColor.AQUA + ".");
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "[uChat]: The Channel " + channelName + " Does Not Exist. Type /create " + channelName + " To  Create It.");
            }

        } else if (args.length != 1) {

            player.sendMessage(ChatColor.RED + "[uChat]: SYNTAX ERROR, type /join [ChannelName].");

        }
    }

    public channelJoinExecutor(uChat XE) {
        super(XE);
    }
}
