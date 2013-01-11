package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.Objects.uChannel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class channelDeleteExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
        if (args.length > 0) {
            String channelName = args[0];


            if (uChat.channels.containsKey(channelName)) {
                uChannel channel = uChat.channels.get(channelName);
                if ((channel.getCreatorName().equalsIgnoreCase(player.getName())) || (uChat.permission.has(player, "xechat.delete.any"))) {
                    if (!channel.isPermanent()) {
                        uChat.channels.remove(channelName);
                        channel.delete();
                        for (Player p : player.getServer().getOnlinePlayers()) {
                            if (channel.getPlayers().contains(p.getName())) {
                                uChat.channelIn.put(p, uChat.g);
                                p.sendMessage(ChatColor.AQUA + "[uChat]: " + channelName + " Has Been Deleted, You Were Moved To " + uChat.g.getColor() + uChat.g.getName() + ChatColor.AQUA + ".");
                            }
                        }
                        channel.getPlayers().clear();
                    } else {
                        player.sendMessage(ChatColor.RED + "[uChat]: You Cannot Delete " + channelName + ".");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "[uChat]: You Do Not Have Permission to Delete " + channelName + ".");
                }
            } else {
                player.sendMessage(ChatColor.RED + "[uChat]: The Channel " + channelName + " Does Not Exist.");
            }

        } else if (args.length == 0) {

            player.sendMessage(ChatColor.RED + "[uChat]: SYNTAX ERROR, type /delete [ChannelName].");

        }
    }

    public channelDeleteExecutor(uChat XE) {
        super(XE);
    }
}
