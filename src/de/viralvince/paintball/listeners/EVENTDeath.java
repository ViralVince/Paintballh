package de.viralvince.paintball.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EVENTDeath implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		
		e.setDeathMessage(null);
		
	}

}
