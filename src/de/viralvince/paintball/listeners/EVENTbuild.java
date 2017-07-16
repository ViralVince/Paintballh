package de.viralvince.paintball.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class EVENTbuild implements Listener{
	@EventHandler
	public void onBuild(BlockPlaceEvent e) {
		
		e.setCancelled(true);
		
		
	}

}
