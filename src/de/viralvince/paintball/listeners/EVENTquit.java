package de.viralvince.paintball.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.viralvince.paintball.main.CDPB;
import de.viralvince.paintball.main.Gamestate;
import de.viralvince.paintball.main.Main;

public class EVENTquit implements Listener {

	
	@EventHandler
	public void onquit(PlayerQuitEvent e) {
		
		File file = new File("plugins/paintball/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		Main.playing.remove(e.getPlayer());
		if(Main.red.contains(e.getPlayer())) {
			Main.red.remove(e.getPlayer());
		}
		if(Main.blue.contains(e.getPlayer())) {
			Main.blue.remove(e.getPlayer());
		}
		e.setQuitMessage(Main.pr +" §cDer Spieler §6" +e.getPlayer().getDisplayName() + " §chat das Spiel verlassen! §6(" + Main.playing.size() + "/" + cfg.getString("Maxplayers") + ")");
			if(Main.gameState == Gamestate.Countdown) {
				if(Main.playing.size() >= cfg.getInt("Minplayers")) {
					Bukkit.getScheduler().cancelTask(CDPB.lobbycd);
					
					CDPB.lobbysec = 60;
					Main.gameState = Gamestate.Lobby;
			}
		}
		
	}
}
