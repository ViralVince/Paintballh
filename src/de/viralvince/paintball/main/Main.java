package de.viralvince.paintball.main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.viralvince.paintball.coins.CoinsAPI;
import de.viralvince.paintball.coins.MySQL;
import de.viralvince.paintball.commands.CMDaddcoins;
import de.viralvince.paintball.commands.CMDaddkit;
import de.viralvince.paintball.commands.CMDcoins;
import de.viralvince.paintball.commands.CMDsetmaxplayers;
import de.viralvince.paintball.commands.CMDsetminplayers;
import de.viralvince.paintball.commands.CMDsetspawn;
import de.viralvince.paintball.commands.CMDstart;
import de.viralvince.paintball.kits.KitAPI;
import de.viralvince.paintball.listeners.EVENTDeath;
import de.viralvince.paintball.listeners.EVENTbuild;
import de.viralvince.paintball.listeners.EVENTinteract;
import de.viralvince.paintball.listeners.EVENTinventoryclick;
import de.viralvince.paintball.listeners.EVENTjoin;
import de.viralvince.paintball.listeners.EVENTmove;
import de.viralvince.paintball.listeners.EVENTpickup;
import de.viralvince.paintball.listeners.EVENTquit;





public class Main extends JavaPlugin implements Listener {
	
	private static Main instance;
	private MySQL mysql;
	private CoinsAPI coinsApi;
	private KitAPI kitApi;
	
 	
// 	p.getWorld().strikeLightning(loc);
	private static Main plugin;
	public static final String pr = "§5Paintball §7>>";
	
	File file = new File("plugins/paintball/config.yml");
	YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public int maxplayers = cfg.getInt("Minplayers");
	public int minplayers = cfg.getInt("Minplayers");
	
	public static Gamestate gameState = Gamestate.Lobby;
	
	public static ArrayList<Player> playing = new ArrayList<>();
	public static ArrayList<Player> spectating = new ArrayList<>();
	public static ArrayList<Player> red = new ArrayList<>();
	public static ArrayList<Player> blue = new ArrayList<>();
	public static ArrayList<Player> spattack = new ArrayList<>();
	public HashMap<Player, Player> killed = new HashMap<Player, Player>();
	public ArrayList<Player> slime = new ArrayList<Player>();
	
	public  CoinsAPI getCoinsapi() { 
		return this.coinsApi;
	}
	public  KitAPI getKitapi() { 
		return this.kitApi;
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	
	
	public void onEnable() {
		
		instance = this;
	    mysql = new MySQL();
	    mysql.connect();
	    mysql.createCoinsTable();
	    mysql.createKitTable();
	    coinsApi = new CoinsAPI();
	    coinsApi.setMysql(mysql);
	    kitApi = new KitAPI();
	    kitApi.setMysql(mysql);
	   
	    
	    	    	    	    	 	    
		plugin = this;
				
		this.getCommand("setspawn").setExecutor(new CMDsetspawn());
		this.getCommand("setminplayers").setExecutor(new CMDsetminplayers());
		this.getCommand("setmaxplayers").setExecutor(new CMDsetmaxplayers());
		this.getCommand("start").setExecutor(new CMDstart());
		this.getCommand("coins").setExecutor(new CMDcoins());
		this.getCommand("addcoins").setExecutor(new CMDaddcoins());
		this.getCommand("addkit").setExecutor(new CMDaddkit());
	
	
		
//		Gamestate gameState = Gamestate.Lobby;
		
		org.bukkit.plugin.PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new EVENTjoin(), this);
		pm.registerEvents(new EVENTinteract(), this);
		pm.registerEvents(new EVENTinventoryclick(), this);
		pm.registerEvents(this, this);
		pm.registerEvents(new EVENTmove(), this);
		pm.registerEvents(new EVENTbuild(), this);
		pm.registerEvents(new EVENTquit(), this);
		pm.registerEvents(new EVENTpickup(), this);
		pm.registerEvents(new EVENTDeath(), this);
		
		System.out.println("[Paintball] Das Plugin wurde erfolgreich gestartet");
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
	}
	
	 
	
	
	


	@EventHandler
	  public void onFullJoin(PlayerJoinEvent e) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        
        	try
            {
        		if(Main.playing.size() > cfg.getInt("Maxplayers")) {
              out.writeUTF("Connect");
              out.writeUTF("Lobby");
        		}
            } catch (IOException ex) {
              System.err.println("Es gab einen Fehler:");
              ex.printStackTrace();
            }
            
        	Player p = e.getPlayer();
        	p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
            
        }
	
	  public void onDisable()
	  {
		  MySQL mysql = new MySQL();
	    mysql.close();
	  }

	  public void log(String log) {
	    Bukkit.getConsoleSender().sendMessage(log);
	  }

	  public static Main getInstance()
	  {
	    return instance;
	  }
	  public MySQL getMysql() {
	    return this.mysql;
	  }
	  
	public ArrayList<Player> getRed() {
			  return red;
		  }
	public ArrayList<Player> getBlue() {
		  return blue;
	}
	  
	  public String getTeam(Player p) {
		  
		if(Main.blue.contains(p)) {
			  
			  return "blue";
			  
		  } else if(Main.red.contains(p)) {
			  
			  return "red";
				  
			  }
		
		return null;
		
		}
	  }
	  

//Kits
//1 : Starter	  
//2 : Enderman		
//3 : Darkness
//4 : Light







