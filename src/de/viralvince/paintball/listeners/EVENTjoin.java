package de.viralvince.paintball.listeners;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import de.viralvince.paintball.main.CDPB;
import de.viralvince.paintball.main.Gamestate;
import de.viralvince.paintball.main.Main;





public class EVENTjoin implements Listener{
	
	public static void  NewStats(Player p) throws Exception{
		String UUID = p.getUniqueId().toString();
		 p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		  ScoreboardManager manager = Bukkit.getScoreboardManager();
			Scoreboard  board = manager.getNewScoreboard();
			Objective objective = board.registerNewObjective("kit","kit");
			objective.setDisplayName(p.getDisplayName());
			objective.setDisplaySlot(DisplaySlot.SIDEBAR);
//			Score deinkit = objective.getScore(Bukkit.getOfflinePlayer("§fDein Kit:   "));
//			deinkit.setScore(5);
//			if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 1 || EVENTinventoryclick.Starter.contains(p)) {
//			Score kit = objective.getScore(Bukkit.getOfflinePlayer(">> §6Starter"));
//			kit.setScore(4);
//			} else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 2|| EVENTinventoryclick.Enderman.contains(p)) {
//				Score kit = objective.getScore(Bukkit.getOfflinePlayer(">> §6Enderman"));
//				kit.setScore(4);
//			} else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 3|| EVENTinventoryclick.Darkness.contains(p)) {
//				Score kit = objective.getScore(Bukkit.getOfflinePlayer(">> §6Darkness"));
//				kit.setScore(4);
//			} else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 4|| EVENTinventoryclick.Light.contains(p)) {
//				Score kit = objective.getScore(Bukkit.getOfflinePlayer(">> §6Light"));
//				kit.setScore(4);
//			}
//			Score nix = objective.getScore(Bukkit.getOfflinePlayer(" §f"));
//			nix.setScore(3);
			Score deinecoins = objective.getScore(Bukkit.getOfflinePlayer("§fDeine Coins:"));
			deinecoins.setScore(2);
			Score coins = objective.getScore(Bukkit.getOfflinePlayer(">> §6" + Main.getInstance().getCoinsapi().getCoins(UUID)));
			coins.setScore(1);
			
			p.setScoreboard(board);
			
		}
	
	File file = new File("plugins/paintball/config.yml");
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	int taskID;
	int idleID;
	int seconds = 60;
	public boolean isIdling = false, isRunning = false;
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Main.playing.add(p);
		
		p.setHealth(20);
		p.setFoodLevel(20);
		p.getInventory().clear();
		p.setLevel(0);
		String UUID = p.getUniqueId().toString();
		if(!Main.getInstance().getCoinsapi().existCoinsPlayer(UUID)) {
			Main.getInstance().getCoinsapi().createCoinsPlayer(UUID);
		}
		if(!Main.getInstance().getKitapi().existKitPlayer(UUID)) {
			Main.getInstance().getKitapi().createKitPlayer(UUID);
		}
		if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 0) {
			Main.getInstance().getKitapi().setDefaultKit(UUID, 1);
		}
		
		try {
			NewStats(p);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		
		
		
		
		ItemStack kit = new ItemStack(Material.CHEST);
		ItemMeta kitim = kit.getItemMeta();
		
		kitim.setDisplayName(Main.pr + " §7Wähle Kit");
		kit.setItemMeta(kitim);
		p.getInventory().setItem(1, kit);
		
		
		ItemStack team = new ItemStack(Material.WOOL);
		ItemMeta teamim = team.getItemMeta();
		
		teamim.setDisplayName(Main.pr + " §7Wähle dein Team");
		team.setItemMeta(teamim);
		p.getInventory().setItem(0, team);
		if(p.hasPermission("pb.start")) {
			ItemStack start = new ItemStack(Material.ANVIL);
			ItemMeta startim = start.getItemMeta();
			
			startim.setDisplayName("§aSpiel starten");
			start.setItemMeta(startim);
			p.getInventory().setItem(4, start);
		}
		
		
		
		

			
		
		
		
		World w = Bukkit.getWorld(cfg.getString("Lobby.Welt"));
		double x = cfg.getDouble("Lobby.X");
		double y = cfg.getDouble("Lobby.Y");
		double z = cfg.getDouble("Lobby.Z");
		float yaw = (float) cfg.getDouble("Lobby.Yaw");
		float pitch = (float) cfg.getDouble("Lobby.Pitch");
		Location loc = new Location(w, x, y, z, yaw, pitch);
		p.teleport(loc);
		ItemStack air = new ItemStack(Material.AIR);
		
		
		p.getInventory().setBoots(air);
		p.getInventory().setLeggings(air);
		p.getInventory().setHelmet(air);
		p.getInventory().setChestplate(air);

		e.setJoinMessage("§aDer Spieler §6" +p.getDisplayName() + " §aist beigetreten! §6(" + Main.playing.size() + "/" + cfg.getString("Maxplayers") + ")");
		
		
		if(Main.playing.size() > cfg.getInt("Maxplayers")) {
			
			
		}
		
			
				if(Main.playing.size() >= cfg.getInt("Minplayers")) {
					if(!(Main.gameState == Gamestate.Ingame)) {
						if(!(Main.gameState == Gamestate.End)) {
							Main.gameState = Gamestate.Countdown;
							for(Player a : Bukkit.getOnlinePlayers()) {
								
							if(Main.playing.contains(a)) {	
							CDPB.LobbyCD(a);
							}
							
							}
							
						}
				
					}
				}
				
				
//				taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
//					
//					@Override
//					public void run() {
//						seconds--;
//						for(Player a : Main.playing) a.setLevel(seconds);
//						if(seconds == 50) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 50 Sekunden");
//						if(seconds == 40) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 40 Sekunden");
//						if(seconds == 30) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 30 Sekunden");
//						if(seconds == 20) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 20 Sekunden");
//						if(seconds == 10) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 10 Sekunden");
//						if(seconds == 5) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 5 Sekunden");
//						if(seconds == 3) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 3 Sekunden");
//						if(seconds == 2) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 2 Sekunden");
//						if(seconds == 1) Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in 1 Sekunde");
//						if(seconds == 0){
//							Bukkit.getScheduler().cancelTask(taskID);
//							Main.gameState = Gamestate.Ingame;
//						}
//						
//						
//					}
//				}, 0, 20);
		
	}												
}
