package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;
import me.supermaxman.uchat.uChatFormater;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 21/07/12
 * Time: 05:18
 */
public class localExecutor extends baseExecutor {
    @Override
    protected void run(Player player, String[] args) {
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


            String message = uChatFormater.format(player, m, name, world, uChat.l);


            for (Entity e : player.getNearbyEntities(uChat.conf.getInt("localdistence"), 300, uChat.conf.getInt("localdistence"))) {
                if (e instanceof Player) {
                    Player r = (Player) e;

                    r.sendMessage(message);

                }
            }
            player.sendMessage(message);

        } else if (args.length == 0) {
            uChat.channelIn.put(player, uChat.l);
            uChat.isWhispering.put(player, false);
            player.sendMessage(ChatColor.AQUA + "[uChat]: Now Talking In " + uChat.l.getColor() + uChat.l.getName() + ChatColor.AQUA + ".");
        }
    }

    public localExecutor(uChat XE) {
        super(XE);
    }
}
