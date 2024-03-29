package me.supermaxman.uchat.executors;

import me.supermaxman.uchat.uChat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

abstract class baseExecutor implements CommandExecutor {
    @SuppressWarnings("unused")
    private static uChat XE;
    // Permission permission = xEssentials.permission;

    baseExecutor(uChat XE) {
        baseExecutor.XE = XE;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        final String commandName = command.getName().toLowerCase();
        Player player;
        final boolean isPlayer = (sender instanceof Player);

        if (isPlayer) {
            player = (Player) sender;
        } else {
            return false;
        }

        this.run(player, args);

        return true;
    }

    protected abstract void run(Player player, String[] args);

}