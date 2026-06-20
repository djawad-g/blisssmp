package com.example.testplugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class TestPlugin extends JavaPlugin {

    private final Random random = new Random();

    @Override
    public void onEnable() {
        getLogger().info("TestPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TestPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("randomnumber")) {
            int roll = random.nextInt(100) + 1;
            sender.sendMessage("§aYou rolled: §e" + roll + "§a/100");
            return true;
        }

        if (command.getName().equalsIgnoreCase("randomteleport")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command.");
                return true;
            }

            Player player = (Player) sender;
            double offsetX = (random.nextDouble() - 0.5) * 20;
            double offsetZ = (random.nextDouble() - 0.5) * 20;

            Location loc = player.getLocation().add(offsetX, 0, offsetZ);
            int highestY = player.getWorld().getHighestBlockYAt(loc);
            loc.setY(highestY + 1);

            player.teleport(loc);
            player.sendMessage("§bTeleported you somewhere random nearby!");
            return true;
        }

        return false;
    }
}
