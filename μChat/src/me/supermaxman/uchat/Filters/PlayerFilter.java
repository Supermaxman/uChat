package me.supermaxman.uchat.Filters;


import me.supermaxman.uchat.utils.ColorUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerFilter {


    public static String addColorNames(String m, ChatColor c) {

//        Player[] names = s.getOnlinePlayers();
//        int i = 0;
//        while (i < names.length) {
//            if (m.contains(names[i].getName())) {
//                m = m.replaceAll(names[i].getName(), ChatColor.translateAlternateColorCodes('&', uChat.chat.getPlayerPrefix(names[i])) + names[i].getName() + c);
//            }
//            i++;
//        }
        String m2 = "";
        for (String s : m.split(" ")) {
            boolean added = false;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if(s.equalsIgnoreCase(player.getName())){
                    m2 += s.replaceAll(player.getName(), ColorUtils.getColoredName(player)) + c + " ";
                    added = true;
                }
            }
            if(!added){
                m2 += s + c + " ";

            }
        }



        return m2.substring(0, m2.length() - 1);
    }


}
