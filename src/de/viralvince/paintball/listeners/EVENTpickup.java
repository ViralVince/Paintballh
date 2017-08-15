package de.viralvince.paintball.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class EVENTpickup implements Listener {
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		
			e.setCancelled(true);
		
	}

}
