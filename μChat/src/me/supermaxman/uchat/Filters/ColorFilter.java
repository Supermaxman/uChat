package me.supermaxman.uchat.Filters;

import org.bukkit.ChatColor;

public class ColorFilter {


    public static String addColorChat(String m) {

        if (m.contains("&")) {
            for (ChatColor color : ChatColor.values()) {
                m = m.replaceAll("&" + color.name().toUpperCase(), "" + color);
                m = m.replaceAll("&" + color.name().toLowerCase(), "" + color);

            }
        }

        return m;
    }


}
