package de.viralvince.paintball.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.viralvince.paintball.main.Main;

public class CMDaddcoins implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("addcoins")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("pb.addcoins")) {
					if(args.length == 2) { 
						Player target = Bukkit.getPlayer(args[0]);
						if(target != null) {
							String argument = args[1];
							Integer x = Integer.valueOf(argument);
							String targetUUID = target.getUniqueId().toString();
							if(Main.getInstance().getCoinsapi().existCoinsPlayer(targetUUID)) {
							Main.getInstance().getCoinsapi().addCoins(targetUUID, x);
							
							p.sendMessage(Main.pr + " �aDer Spieler �6" +target.getName()+ " �ahat �6 " + args[1] + " �aCoins erhalten");
					
							}						
						} else {
							p.sendMessage(Main.pr +" �cDer Spieler �6"+ args[0] + " �cIst nicht auf dem Server");
						}
					
					} else {
						p.sendMessage(Main.pr +" �cBitte benutze nur �6/addcoins <Spieler> <Anzahl>�c!");
					}
					
					
				} else {
					
					p.sendMessage(Main.pr +" �cDazu hast du keine Rechte!");
				}
			
		}
	}
		
		return false;
}

}
