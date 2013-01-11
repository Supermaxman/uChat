package me.supermaxman.uchat.Filters;

import me.supermaxman.uchat.Objects.uChannel;

import org.bukkit.entity.Player;

public class SpamFilter {

    public static boolean checkSpam(String m, final Player p, uChannel ch) {
        //Does not prevent worlds end or other spam bots, will cause major leaving spam.
        /*
        m = ch.getName() + ":" + m;
        if (uChat.lastchat.containsKey(p)) {
            if (m.equalsIgnoreCase(uChat.lastchat.get(p))) {
                p.kickPlayer(ChatColor.RED + "Kicked For Spam.");
                return false;
            }
        }
        uChat.lastchat.put(p, m);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(uChat.XE, new Runnable() {
            public void run() {
                uChat.lastchat.remove(p);
            }
        }, 40);
        */
        return true;
    }

}
