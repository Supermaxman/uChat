package me.supermaxman.uchat.Filters;

import me.supermaxman.uchat.uChat;

import java.util.ArrayList;

public class KickFilter {

    /*
    *
    *   ERMAHGERD NSFW!
    *
    */

    static ArrayList<String> censored = null;

    public static ArrayList<String> getCensored() {
        if (censored == null) {
            censored = new ArrayList<String>();
            if (uChat.conf.isSet("kickswears")) {
                censored.addAll(uChat.conf.getStringList("kickswears"));
            } else {
                censored.add("fuck");
                uChat.conf.set("kickswears", censored);
                uChat.XE.saveConfig();
            }

        }
        return censored;
    }
}
