package de.viralvince.paintball.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.viralvince.paintball.main.Main;

public class CMDsetmaxplayers implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(command.getName().equalsIgnoreCase("setmaxplayers")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("pb.setmaxplayers")) {
					if(args.length == 1) {
						
					
						
						
						try { 
							String argument = args[0];
							Integer x = Integer.valueOf(argument);
							File file = new File("plugins/paintball/config.yml");							
							YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							cfg.set("Maxplayers", x);
							p.sendMessage(Main.pr + " §aDie maximale Spieleranzahl wurde auf §6 "+ x + " §agesetzt");
							try {
								cfg.save(file);
							} catch (IOException e) {
								
								e.printStackTrace();
							}
							} catch(NumberFormatException e1) {
								p.sendMessage(Main.pr +" §cGib eine gültige Zahl an");	
							}
						
						
						
						
						
					} else {
						p.sendMessage(Main.pr +" §cBitte benutze nur §6/setmaxplayers <Anzahl> §c!");
					}
					
					
				} else {
					p.sendMessage(Main.pr +" §cDazu hast du keine Rechte!");
				}
			
			}
		}
		
		
		return false;
	}
}
