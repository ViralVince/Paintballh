package de.viralvince.paintball.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.viralvince.paintball.main.CDPB;
import de.viralvince.paintball.main.Main;

public class CMDstart implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(command.getName().equalsIgnoreCase("start")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("pb.start")) {
					if(args.length == 0) {
						File file = new File("plugins/paintball/config.yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
						
						if(Main.playing.size() >= cfg.getInt("Minplayers")) {
						if(CDPB.lobbysec >= 11) {
						 
							CDPB.lobbysec = 10;
							p.sendMessage(Main.pr + " §aDas Spiel wir gestarted...");
						} else {
							p.sendMessage(Main.pr + " §cDas Spiel started bereits");
						}
						} else {
							p.sendMessage(Main.pr + " §cEs werden mehr Spieler benötigt un das Spiel zu starten");
						}
					
					} else {
						p.sendMessage(Main.pr +" §cBitte benutze nur §6/start§c!");
					}
					
					
				} else {
					p.sendMessage(Main.pr +" §cDazu hast du keine Rechte!");
				}
			
			}
		}
		
		
		return false;
	}

}
