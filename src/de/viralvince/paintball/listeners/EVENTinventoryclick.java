package de.viralvince.paintball.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import de.viralvince.paintball.main.Main;

public class EVENTinventoryclick implements Listener{
	
	public static ArrayList<Player> Starter = new ArrayList<>();
	public static ArrayList<Player> Enderman = new ArrayList<>();
	public static ArrayList<Player> Darkness = new ArrayList<>();
	public static ArrayList<Player> Light = new ArrayList<>();
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {	
		Player p = (Player) e.getWhoClicked();
		
				
		String UUID = p.getUniqueId().toString();
		
		e.setCancelled(true);
		if(e.getCurrentItem().getType() != Material.AIR) {
			
			if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�9Blau")) {
				
				if(!Main.blue.contains(p)) {
					int isinred = 0;
					if(Main.red.contains(p)){
						isinred = 1;
					}										
					if(Main.blue.size()+1-(Main.red.size()-isinred)< 2) {
							p.closeInventory();		
							p.setDisplayName("�9"+ p.getName()+ "�f");															
							
							ItemStack red = new ItemStack(Material.WOOL, 1, DyeColor.BLUE.getData());
							ItemMeta redim = red.getItemMeta();
							redim.setDisplayName(Main.pr + " �7Du bist in Team �9Blau");							
							red.setItemMeta(redim);
							p.getInventory().setItem(0, red);
							
							 if(Main.red.contains(p)) {
								Main.red.remove(p);
								Main.blue.add(p);
								p.sendMessage(Main.pr + " �aDu bist nun in Team �9Blau");								  
							 } else {
								 Main.blue.add(p);
								 p.sendMessage(Main.pr + " �aDu bist nun in Team �9Blau");
							 } 
					} else {
						p.sendMessage(Main.pr + " �cDie Spielerzahlen sind nicht ausgeglichen");
						p.closeInventory();
					}
				} else {
					p.sendMessage(Main.pr + " �cDu bist bereits in diesem Team");
					p.closeInventory();
				}
			} else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("�cRot")) {
				if(!Main.red.contains(p)) {
					int isinred = 0;
					if(Main.blue.contains(p)){
						isinred = 1;
					}
					if(Main.red.size()+1-(Main.blue.size()-isinred)< 2) {
							p.closeInventory();		
							p.setDisplayName("�c"+ p.getName()+ "�f");
							ItemStack red = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
							ItemMeta redim = red.getItemMeta();
							redim.setDisplayName(Main.pr + " �7Du bist in Team �cRot");
							red.setItemMeta(redim);
							p.getInventory().setItem(0, red);
							 if(Main.blue.contains(p)) {
								Main.blue.remove(p);
								
								Main.red.add(p);
								p.sendMessage(Main.pr + " �aDu bist nun in Team �cRot");								  
							 } else {
								 Main.red.add(p);
								 p.sendMessage(Main.pr + " �aDu bist nun in Team �cRot");
							 } 
					} else {
						p.sendMessage(Main.pr + " �cDie Spielerzahlen sind nicht ausgeglichen");
						p.closeInventory();
					}
				} else {
					p.sendMessage(Main.pr + " �cDu bist bereits in diesem Team");
					p.closeInventory();
				}
		} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aEnderman (gekauft)") || e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aEnderman �c(nicht gekauft)")) {
    		
			p.closeInventory();
    	e.setCancelled(true);
    	if(Main.getInstance().getKitapi().getKit(UUID, 2) == 1) {
    		if(Enderman.contains(p)) {
			p.sendMessage(Main.pr + " �cDu hast dieses Kit bereits ausgew�hlt");
		} else {
    	    
    		Enderman.add(p);
    		p.sendMessage(Main.pr + " �aDu hast das Kit �6Enderman �aausgew�hlt");
    		
    		
    		if(Starter.contains(p)) {
    			Starter.remove(p);
    		}
    		if(Darkness.contains(p)) {
    			Darkness.remove(p);
    		}
    		if(Light.contains(p)) {
    			Light.remove(p);
    		}
		}
	} else if(Main.getInstance().getKitapi().getKit(UUID, 2) == 0){
		
		Inventory invender = Bukkit.createInventory(null, 27, Main.pr + " �7Enderman");
		ItemStack kaufen = new ItemStack(Material.GOLD_INGOT);
		ItemMeta imkaufen = kaufen.getItemMeta();
		imkaufen.setDisplayName("�aKit kaufen f�r �65000 �aCoins");
		
		kaufen.setItemMeta(imkaufen);
		invender.setItem(16, kaufen);
		
		ItemStack coins = new ItemStack(Material.PAPER);
		ItemMeta imcoins = coins.getItemMeta();
		imcoins.setDisplayName("�aDu hast �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �aCoins");		
		coins.setItemMeta(imcoins);
		invender.setItem(10, coins);
		
		ItemStack enderman = new ItemStack(Material.ENDER_PEARL);
		ItemMeta imenderman = enderman.getItemMeta();
		imenderman.setDisplayName("�aKit: Enderman");
		List<String> edlore = new ArrayList<String>();
		edlore.add("�7Das m�chtige Enderman Kit");
		edlore.add("�7Schaden: 1 Hertz pro Treffer");
		edlore.add("�7Specialattack: Der Spieler kann sich mit einem Schuss teleportierten");
		imenderman.setLore(edlore);
		
		
		enderman.setItemMeta(imenderman);
		invender.setItem(13, enderman);
		
		p.openInventory(invender);
		
		//12 14
	}
    		
    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aStarter (gekauft)")) {
		
    	
    	e.setCancelled(true);
    	p.closeInventory();
    	
    	if(Starter.contains(p)) {
			p.sendMessage(Main.pr + " �cDu hast dieses Kit bereits ausgew�hlt");
		} else {
    	   Starter.add(p);
    		p.sendMessage(Main.pr + " �aDu hast das Kit �6Starter �aausgew�hlt");
    		
    		if(Enderman.contains(p)) {
    			Enderman.remove(p);
    		}
    		if(Darkness.contains(p)) {
    			Darkness.remove(p);
    		}
    		if(Light.contains(p)) {
    			Light.remove(p);
    		}
		}
    	
    } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aKit kaufen f�r �65000 �aCoins")) {
    	if(e.getClickedInventory().getName().equals(Main.pr + " �7Enderman")) {
    		if(Main.getInstance().getCoinsapi().getCoins(UUID) <= 5000) {
    			p.sendMessage(Main.pr+" �cDu hast nicht gen�gend Coins um dieses Kit zu kaufen: Deine Coins �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �c| Ben�tigte Coins: �65000"); 										
    			p.closeInventory();
    			
    				} else {
    					Inventory invbest� = Bukkit.createInventory(null, 27, Main.pr + " �7Enderman");
    					ItemStack kaufen = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.GREEN.getData());
    					List<String> lore = new ArrayList<String>();
    					ItemMeta imkaufen = kaufen.getItemMeta();
    					long coinsminus = Main.getInstance().getCoinsapi().getCoins(UUID)-5000;
    					lore.add("�cCoins nach dem Kauf:�6 " +coinsminus);
    					imkaufen.setDisplayName("�aKit wirklich kaufen f�r �65000 �aCoins");
    					imkaufen.setLore(lore);				
    					kaufen.setItemMeta(imkaufen);
    					invbest�.setItem(15, kaufen);
    					
    					ItemStack coins = new ItemStack(Material.PAPER);
    					ItemMeta imcoins = coins.getItemMeta();
    					imcoins.setDisplayName("�aDu hast �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �aCoins");		
    					coins.setItemMeta(imcoins);
    					invbest�.setItem(11, coins);
    					
    					p.openInventory(invbest�);
    				
    				}
    			} else if(e.getClickedInventory().getName().equals(Main.pr + " �7Darkness")) {
    				if(Main.getInstance().getCoinsapi().getCoins(UUID) < 5000) {
    	    			p.sendMessage(Main.pr+" �cDu hast nicht gen�gend Coins um dieses Kit zu kaufen: Deine Coins �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �c| Ben�tigte Coins: �65000"); 										
    	    			p.closeInventory();
    	    			
    	    				} else {
    	    					Inventory invbest� = Bukkit.createInventory(null, 27, Main.pr + " �7Darkness");
    	    					ItemStack kaufen = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.GREEN.getData());
    	    					List<String> lore = new ArrayList<String>();
    	    					ItemMeta imkaufen = kaufen.getItemMeta();
    	    					long coinsminus = Main.getInstance().getCoinsapi().getCoins(UUID)-5000;
    	    					lore.add("�cCoins nach dem Kauf:�6 " +coinsminus);
    	    					imkaufen.setDisplayName("�aKit wirklich kaufen f�r �65000 �aCoins");
    	    					imkaufen.setLore(lore);				
    	    					kaufen.setItemMeta(imkaufen);
    	    					invbest�.setItem(15, kaufen);
    	    					
    	    					ItemStack coins = new ItemStack(Material.PAPER);
    	    					ItemMeta imcoins = coins.getItemMeta();
    	    					imcoins.setDisplayName("�aDu hast �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �aCoins");		
    	    					coins.setItemMeta(imcoins);
    	    					invbest�.setItem(11, coins);
    	    					
    	    					p.openInventory(invbest�);
    	    				}
    			} else if(e.getClickedInventory().getName().equals(Main.pr + " �7Light")) {
    				if(Main.getInstance().getCoinsapi().getCoins(UUID) < 5000) {
    	    			p.sendMessage(Main.pr+" �cDu hast nicht gen�gend Coins um dieses Kit zu kaufen: Deine Coins �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �c| Ben�tigte Coins: �65000"); 										
    	    			p.closeInventory();
    	    			
    	    				} else {
    	    					Inventory invbest� = Bukkit.createInventory(null, 27, Main.pr + " �7Light");
    	    					ItemStack kaufen = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.GREEN.getData());
    	    					List<String> lore = new ArrayList<String>();
    	    					ItemMeta imkaufen = kaufen.getItemMeta();
    	    					long coinsminus = Main.getInstance().getCoinsapi().getCoins(UUID)-5000;
    	    					lore.add("�cCoins nach dem Kauf:�6 " +coinsminus);
    	    					imkaufen.setDisplayName("�aKit wirklich kaufen f�r �65000 �aCoins");
    	    					imkaufen.setLore(lore);				
    	    					kaufen.setItemMeta(imkaufen);
    	    					invbest�.setItem(15, kaufen);
    	    					
    	    					ItemStack coins = new ItemStack(Material.PAPER);
    	    					ItemMeta imcoins = coins.getItemMeta();
    	    					imcoins.setDisplayName("�aDu hast �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �aCoins");		
    	    					coins.setItemMeta(imcoins);
    	    					invbest�.setItem(11, coins);
    	    					
    	    					p.openInventory(invbest�);
    	    				}
    			}
    	
    		} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aKit wirklich kaufen f�r �65000 �aCoins")) {
    			if(e.getClickedInventory().getName().equals(Main.pr + " �7Enderman")) {
    				if(Main.getInstance().getCoinsapi().getCoins(UUID) >= 5000) {
    					Main.getInstance().getCoinsapi().removeCoins(UUID, 5000);
    					Main.getInstance().getKitapi().addKit(UUID, 2);
    					p.closeInventory();
    					p.sendMessage(Main.pr +" �aKauf erfolgreich");
    					Enderman.add(p);
    		    		p.sendMessage(Main.pr + " �aDu hast das Kit �6Enderman �aausgew�hlt");
    		    		
    		    		if(Starter.contains(p)) {
    		    			Starter.remove(p);
    		    		}
    		    		if(Darkness.contains(p)) {
    		    			Darkness.remove(p);
    		    		}
    		    		if(Light.contains(p)) {
    		    			Light.remove(p);
    		    		}
    				
    				}
    			} else if(e.getClickedInventory().getName().equals(Main.pr + " �7Darkness")) {
    				if(Main.getInstance().getCoinsapi().getCoins(UUID) >= 5000) {
    					Main.getInstance().getCoinsapi().removeCoins(UUID, 5000);
    					Main.getInstance().getKitapi().addKit(UUID, 3);
    					p.closeInventory();
    					p.sendMessage(Main.pr +" �aKauf erfolgreich");
    					Darkness.add(p);
    	        		p.sendMessage(Main.pr + " �aDu hast das Kit �6Darkness �aausgew�hlt");
    	        		
    	        		if(Starter.contains(p)) {
    	        			Starter.remove(p);
    	        		}
    	        		if(Enderman.contains(p)) {
    	        			Enderman.remove(p);
    	        		}
    	        		if(Light.contains(p)) {
    	        			Light.remove(p);
    	        		}
    				}
    			} else if(e.getClickedInventory().getName().equals(Main.pr + " �7Light")) {
    				if(Main.getInstance().getCoinsapi().getCoins(UUID) >= 5000) {
    					Main.getInstance().getCoinsapi().removeCoins(UUID, 5000);
    					Main.getInstance().getKitapi().addKit(UUID, 4);
    					p.closeInventory();
    					p.sendMessage(Main.pr +" �aKauf erfolgreich");
    					Light.add(p);
    	        		p.sendMessage(Main.pr + " �aDu hast das Kit �6Light �aausgew�hlt");
    	        		
    	        		if(Starter.contains(p)) {
    	        			Starter.remove(p);
    	        		}
    	        		if(Enderman.contains(p)) {
    	        			Enderman.remove(p);
    	        		}
    	        		if(Darkness.contains(p)) {
    	        			Darkness.remove(p);
    	        		}
    				}
    			}
    		}  else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aDarkness (gekauft)") || e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aDarkness �c(nicht gekauft)")) {
        		
    			p.closeInventory();
        	e.setCancelled(true);
        	if(Main.getInstance().getKitapi().getKit(UUID, 3) == 1) {
        		if(Darkness.contains(p)) {
    			p.sendMessage(Main.pr + " �cDu hast dieses Kit bereits ausgew�hlt");
    		} else {
        	    
        		Darkness.add(p);
        		p.sendMessage(Main.pr + " �aDu hast das Kit �6Darkness �aausgew�hlt");
        		
        		if(Starter.contains(p)) {
        			Starter.remove(p);
        		}
        		if(Enderman.contains(p)) {
        			Enderman.remove(p);
        		}
        		if(Light.contains(p)) {
        			Light.remove(p);
        		}
    		}
    	} else if(Main.getInstance().getKitapi().getKit(UUID, 3) == 0){
    		
    		Inventory invender = Bukkit.createInventory(null, 27, Main.pr + " �7Darkness");
    		ItemStack kaufen = new ItemStack(Material.GOLD_INGOT);
    		ItemMeta imkaufen = kaufen.getItemMeta();
    		imkaufen.setDisplayName("�aKit kaufen f�r �65000 �aCoins");
    		
    		kaufen.setItemMeta(imkaufen);
    		invender.setItem(16, kaufen);
    		
    		ItemStack coins = new ItemStack(Material.PAPER);
    		ItemMeta imcoins = coins.getItemMeta();
    		imcoins.setDisplayName("�aDu hast �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �aCoins");		
    		coins.setItemMeta(imcoins);
    		invender.setItem(10, coins);
    		
    		ItemStack enderman = new ItemStack(Material.COAL_BLOCK);
    		ItemMeta imenderman = enderman.getItemMeta();
    		imenderman.setDisplayName("�aKit: Darkness");
    		List<String> dalore = new ArrayList<String>();
			dalore.add("�7Das dunkle Darkness Kit");
			dalore.add("�7Schaden: 1 Hertz pro Treffer");
			dalore.add("�7Specialattack: Gibt dem getroffenen Gegner Blindness f�r 5 Sekunden");
			imenderman.setLore(dalore);
    		
    		
    		enderman.setItemMeta(imenderman);
    		invender.setItem(13, enderman);
    		
    		p.openInventory(invender);
    		
    		//12 14
    	
        
    	}
    		} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aLight (gekauft)") || e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("�aLight �c(nicht gekauft)")) {
       			p.closeInventory();
            	e.setCancelled(true);
            	if(Main.getInstance().getKitapi().getKit(UUID, 4) == 1) {
            		if(Light.contains(p)) {
        			p.sendMessage(Main.pr + " �cDu hast dieses Kit bereits ausgew�hlt");
        		} else {
            	    
            		Light.add(p);
            		p.sendMessage(Main.pr + " �aDu hast das Kit �6Light �aausgew�hlt");
            		
            		if(Starter.contains(p)) {
            			Starter.remove(p);
            		}
            		if(Enderman.contains(p)) {
            			Enderman.remove(p);
            		}
            		if(Darkness.contains(p)) {
            			Darkness.remove(p);
            		}
        		}
        	} else if(Main.getInstance().getKitapi().getKit(UUID, 4) == 0){
        		
        		Inventory invender = Bukkit.createInventory(null, 27, Main.pr + " �7Light");
        		ItemStack kaufen = new ItemStack(Material.GOLD_INGOT);
        		ItemMeta imkaufen = kaufen.getItemMeta();
        		imkaufen.setDisplayName("�aKit kaufen f�r �65000 �aCoins");
        		
        		kaufen.setItemMeta(imkaufen);
        		invender.setItem(16, kaufen);
        		
        		ItemStack coins = new ItemStack(Material.PAPER);
        		ItemMeta imcoins = coins.getItemMeta();
        		imcoins.setDisplayName("�aDu hast �6 "+ Main.getInstance().getCoinsapi().getCoins(UUID) + " �aCoins");		
        		coins.setItemMeta(imcoins);
        		invender.setItem(10, coins);
        		
        		ItemStack enderman = new ItemStack(Material.NETHER_STAR);
        		ItemMeta imenderman = enderman.getItemMeta();
        		imenderman.setDisplayName("�aKit: Light");
        		List<String> lilore = new ArrayList<String>();
				lilore.add("�7Das blendende Light Kit");
				lilore.add("�7Schaden: 1 Hertz pro Treffer");
				lilore.add("�7Specialattack: Schiess Blitze auf Gegner in der N�he");
				imenderman.setLore(lilore);
        		
        		
        		enderman.setItemMeta(imenderman);
        		invender.setItem(13, enderman);
        		
        		p.openInventory(invender);
        		
        		//12 14
        	}
    		}
		}
	}
    	}

