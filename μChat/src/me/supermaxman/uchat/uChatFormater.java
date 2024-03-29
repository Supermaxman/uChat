package me.supermaxman.uchat;

import me.supermaxman.uchat.Filters.*;
import me.supermaxman.uchat.Objects.uChannel;
import me.supermaxman.uchat.utils.ColorUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class uChatFormater {

    public static String format(Player p, String m, String name, String world, uChannel channel) {
        ChatColor dcolor = channel.getColor();
        name = dcolor + ColorUtils.getColoredName(p) + dcolor + ":";

        String ch = dcolor + "[" + dcolor + channel.getName() + dcolor + "]";
        m = PlayerFilter.addColorNames(m, dcolor);
        m = censorChat(m, p);
        if (p.isOp()) {
            m = ColorFilter.addColorChat(m);
        }
        m = m.replaceAll("%", "%%");
        if (SpamFilter.checkSpam(m, p, channel)) {
            if (uChat.conf.getBoolean("worldinchat")) {
                return String.format("%s %s %s %s", ch, ChatColor.WHITE + "[" + world + "]", name, m);

            } else {
                return (String.format("%s %s %s", ch, name, m));

            }
        } else {
            return ChatColor.RED + "Was Kicked For Spam";
        }
    }

    public static String formatWhisper(Player p, String m, String name, Player r) {
        ChatColor dcolor = ChatColor.LIGHT_PURPLE;
        name = dcolor + ChatColor.translateAlternateColorCodes('&', uChat.chat.getPlayerPrefix(p)) + name + dcolor + ":";

        String ch = dcolor + "[" + dcolor + "From" + "]";
        m = censorChat(m, p);
        m = PlayerFilter.addColorNames(m, dcolor);
        m = ColorFilter.addColorChat(m);
        return (String.format("%s %s %s", ch, name, m));
    }

    public static String formatWhisperTo(Player p, String m, String name, Player r) {
        ChatColor dcolor = ChatColor.LIGHT_PURPLE;
        name = dcolor + ColorUtils.getColoredName(r) + dcolor + ":";

        String ch = dcolor + "[" + dcolor + "To" + "]";
        m = censorChat(m, p);
        m = PlayerFilter.addColorNames(m, dcolor);
        m = ColorFilter.addColorChat(m);
        return (String.format("%s %s %s", ch, name, m));
    }


    public static String censorChat(String m, Player p) {

        ArrayList<String> kickCensor = KickFilter.getCensored();

        for (String s : kickCensor) {
            if (m.toLowerCase().contains(s.toLowerCase())) {
                m = ChatColor.RED + "Was Kicked For Foul Language";
                p.kickPlayer(ChatColor.RED + "Kicked For Foul Language.");
                return m;
            }
        }

        ArrayList<String> censored = DefaultFilter.getCensored();
        for (String s : censored) {
            if (m.toLowerCase().contains(s.toLowerCase())) {
                String stars = "";
                for (int i = 1; i <= s.length(); i++) {
                    stars += "*";
                }

                m = m.toLowerCase().replaceAll(s.toLowerCase(), stars);

            }
        }


        return m;
    }

}