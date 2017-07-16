package de.viralvince.paintball.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.viralvince.paintball.main.Gamestate;
import de.viralvince.paintball.main.Main;

public class EVENTmove implements Listener{

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(Main.gameState == Gamestate.INGCD) {
			
			p.teleport(p.getLocation());
			
			
		}
	}
}
