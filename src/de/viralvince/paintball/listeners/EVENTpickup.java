package de.viralvince.paintball.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerPickupItemEvent;

import de.viralvince.paintball.main.Gamestate;
import de.viralvince.paintball.main.Main;

public class EVENTpickup {
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if(Main.gameState == Gamestate.Ingame) {
			e.setCancelled(true);
		}
	}

}
