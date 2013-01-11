package me.supermaxman.uchat.utils;

import me.supermaxman.uchat.uChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * User: Benjamin
 * Date: 12/08/12
 * Time: 19:09
 */
public class ColorUtils {

    public static String getColoredName(Player player){
       return ChatColor.translateAlternateColorCodes('&', uChat.chat.getPlayerPrefix(player)) + player.getName();
    }

}
