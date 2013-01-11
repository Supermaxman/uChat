package me.supermaxman.uchat.utils;

import me.supermaxman.uchat.uChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class StringUtils {

	    public static String getVowelCase(String nextWord){
	    	if(nextWord.startsWith("a")||nextWord.startsWith("e")||nextWord.startsWith("i")||nextWord.startsWith("o")||nextWord.startsWith("u")){
		    	return "an ";
	    	}else{
		    	return "a ";
	    	}
	    	
	    	
	    }

}
