package de.viralvince.paintball.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.viralvince.paintball.main.Main;

public class CMDaddkit implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("addKit")) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("pb.addkit")) {
					if(args.length == 2) { 
						Player target = Bukkit.getPlayer(args[0]);
						if(target != null) {
							
							String argument = args[1];
							Integer x = Integer.valueOf(argument);
							if(x <= 27) {
							String targetUUID = target.getUniqueId().toString();
							if(Main.getInstance().getKitapi().existKitPlayer(targetUUID)) {
							Main.getInstance().getKitapi().addKit(targetUUID, x);
							
							p.sendMessage(Main.pr + " �aDer Spieler �6" +target.getName()+ " �ahat das Kit erhalten");
							
							}
							} else {
								p.sendMessage(Main.pr + " Dieses Kit gibt es nicht");
							}
							
						
							
						} else {
							p.sendMessage(Main.pr +" �cDer Spieler �6"+ args[0] + " �cIst nicht auf dem Server");
						}
					
					} else {
						p.sendMessage(Main.pr +" �cBitte benutze nur �6/addkit <Spieler> <KitNummer>�c!");
					}
					
					
				} else {
					
					p.sendMessage(Main.pr +" �cDazu hast du keine Rechte!");
				}
			
		}
	}
		
		return false;
}

}
