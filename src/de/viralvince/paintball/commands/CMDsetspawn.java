package de.viralvince.paintball.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.viralvince.paintball.main.Main;



public class CMDsetspawn implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(command.getName().equalsIgnoreCase("setspawn")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("pb.setspawn")) {
					if(args.length == 1) {
						
						
						if(args[0].equalsIgnoreCase("lobby")) {
							
							
							
							
							
						File file = new File("plugins/paintball/config.yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
						
						cfg.set("Lobby.Welt", p.getLocation().getWorld().getName());
						cfg.set("Lobby.X", p.getLocation().getX());
						cfg.set("Lobby.Y", p.getLocation().getY());
						cfg.set("Lobby.Z", p.getLocation().getZ());
						cfg.set("Lobby.Yaw", p.getLocation().getYaw());
						cfg.set("Lobby.Pitch", p.getLocation().getPitch());
						
						
						try {
							cfg.save(file);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
						
						
						} else if(args[0].equalsIgnoreCase("red")) {
							File file = new File("plugins/paintball/config.yml");
							YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							
							cfg.set("Red.Welt", p.getLocation().getWorld().getName());
							cfg.set("Red.X", p.getLocation().getX());
							cfg.set("Red.Y", p.getLocation().getY());
							cfg.set("Red.Z", p.getLocation().getZ());
							cfg.set("Red.Yaw", p.getLocation().getYaw());
							cfg.set("Red.Pitch", p.getLocation().getPitch());
							
							
							try {
								cfg.save(file);
							} catch (IOException e) {
								
								e.printStackTrace();
								
							
						}
						}  else if(args[0].equalsIgnoreCase("blue")) {
							File file = new File("plugins/paintball/config.yml");
							YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							
							cfg.set("Blue.Welt", p.getLocation().getWorld().getName());
							cfg.set("Blue.X", p.getLocation().getX());
							cfg.set("Blue.Y", p.getLocation().getY());
							cfg.set("Blue.Z", p.getLocation().getZ());
							cfg.set("Blue.Yaw", p.getLocation().getYaw());
							cfg.set("Blue.Pitch", p.getLocation().getPitch());
							
							try {
								cfg.save(file);
							} catch (IOException e) {
								
								e.printStackTrace();
								
							}
						
						
						}
						
					
					} else {
						p.sendMessage(Main.pr +" §cBitte benutze nur §6/setspawn <lobby/blue/red>§c!");
					}
					
					
				} else {
					p.sendMessage(Main.pr +" §cDazu hast du keine Rechte!");
				}
			
			}
		}
		
		
		return false;
	}

}
