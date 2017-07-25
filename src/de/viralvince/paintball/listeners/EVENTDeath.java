package de.viralvince.paintball.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import de.viralvince.paintball.main.Main;

public class EVENTDeath implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
		e.setDeathMessage(null);
 
		Player p = e.getEntity();
		
		Player k = Main.getInstance().killed.get(p);
		
		p.sendMessage(Main.pr + " §cDu wurdest von " + p.getDisplayName() + " §cgetötet.");
		k.sendMessage(Main.pr + " §aDu hast " + ((Player) e).getDisplayName() + " §rgetötet.");
	}

}
