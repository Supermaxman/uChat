package me.supermaxman.uchat;

import me.supermaxman.uchat.Filters.PlayerFilter;
import me.supermaxman.uchat.Objects.uChannel;
import me.supermaxman.uchat.utils.ColorUtils;
import me.supermaxman.uchat.utils.StringUtils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kitteh.tag.PlayerReceiveNameTagEvent;

import java.text.DecimalFormat;

public class uChatListener implements Listener {
    final uChat plugin;

    public uChatListener(uChat plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!uChat.g.getPlayers().contains(player.getName())) {
            uChat.g.addPlayer(player);
        }
        if (!uChat.channelIn.containsKey(player)) {
            uChat.channelIn.put(player, uChat.g);
        }
        event.setJoinMessage(ColorUtils.getColoredName(player) + ChatColor.YELLOW + " Joined the game.");
        uChat.bot.sendMessage(uChat.conf.getString("IRC.Channel"), ChatColor.stripColor(event.getJoinMessage()));

    }

    @EventHandler
    public void onDie(PlayerDeathEvent event){
        String msg = event.getDeathMessage();
//        msg = msg.replaceAll(event.getEntity().getName(), ChatColor.translateAlternateColorCodes('&', uChat.chat.getPlayerPrefix(event.getEntity())) + event.getEntity().getName())
        msg = PlayerFilter.addColorNames(msg, ChatColor.RED);
        if(event.getEntity().getKiller() != null){
            Player killer = event.getEntity().getKiller();
            msg += " with " + StringUtils.getVowelCase(killer.getItemInHand().getType().name().replaceAll("_", " ").toLowerCase())
                    +  killer.getItemInHand().getType().name().replaceAll("_", " ").toLowerCase()
                    + " from "
                    + new DecimalFormat("#.#").format(killer.getLocation().distance(event.getEntity().getLocation())) + " blocks away.";


        }
        event.setDeathMessage(msg);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(ColorUtils.getColoredName(player) + ChatColor.YELLOW + " Quit the game.");
        uChat.bot.sendMessage(uChat.conf.getString("IRC.Channel"), ChatColor.stripColor(event.getQuitMessage()));
        
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(event.isCancelled()){
          return;
        }
        Player p = event.getPlayer();
        if (!uChat.channelIn.containsKey(p)) {
            uChat.channelIn.put(p, uChat.g);
        }
        
        
        
        if (!uChat.isWhispering.containsKey(p)) {
            uChat.isWhispering.put(p, false);
        }
        
        if (uChat.isWhispering.get(p)) {
            String m = event.getMessage();
            String name = p.getName();
            Player r = uChat.whisper.get(p);
            String message = uChatFormater.formatWhisper(p, m, name, r);

            r.sendMessage(message);
            message = uChatFormater.formatWhisperTo(p, m, name, r);
            p.sendMessage(message);
            event.getRecipients().clear();
        } else {
            if (uChat.channelIn.get(p).getName().equalsIgnoreCase("G")) {
                String m = event.getMessage();
                String name = p.getName();
                String world = p.getWorld().getName();

                //String ch = uChat.conf.getString("defaultChannel");

                String message = uChatFormater.format(p, m, name, world, uChat.g);

                event.setFormat(message);
                event.getRecipients().clear();
                for(String s:uChat.g.getPlayers()){
                	if(uChat.XE.getServer().getPlayerExact(s)!=null){
                		event.getRecipients().add(uChat.XE.getServer().getPlayerExact(s));
                	}
                }
                if (!m.equalsIgnoreCase("u00a74u00a75u00a73u00a74v|1")) {
                    uChat.bot.sendMessage(uChat.conf.getString("IRC.Channel"), ChatColor.stripColor(name + ": " + m));
                }
            } else if (uChat.channelIn.get(p).getName().equalsIgnoreCase("l")) {
                String m = event.getMessage();
                String name = p.getName();
                String world = p.getWorld().getName();

                String message = uChatFormater.format(p, m, name, world, uChat.l);

                for (Entity e : p.getNearbyEntities(uChat.conf.getInt("localdistence"), 300, uChat.conf.getInt("localdistence"))) {
                    if (e instanceof Player) {
                        Player r = (Player) e;

                        r.sendMessage(message);

                    }
                }
                p.sendMessage(message);
                event.getRecipients().clear();
            } else {

                String m = event.getMessage();
                String name = p.getName();
                String world = p.getWorld().getName();
                uChannel channel = uChat.channelIn.get(p);
                String message = uChatFormater.format(p, m, name, world, uChat.channelIn.get(p));
                channel.sendString(message);

                event.getRecipients().clear();
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onNameTag(PlayerReceiveNameTagEvent event) {
      event.setTag(ColorUtils.getColoredName(event.getNamedPlayer()));
    }


    
    
    
}
