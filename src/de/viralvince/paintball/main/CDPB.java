package de.viralvince.paintball.main;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;

import de.viralvince.paintball.listeners.EVENTinventoryclick;





public class CDPB {
	
	
	public static boolean isIdling = false;
	public static int lobbycd;
	public static int lobbysec = 60;
	private static boolean lobbycdb = false;
	public static int IdleID;
	private static boolean ingcdb = false;
	public static int ingcd;
	public static int ingsec = 10;
	
	
	public CDPB(Main main) {
	}
	
	
	
	public static void ingCD(Player p) {
		
		if(ingcdb == false) {
			ingcdb = true;
		
			ingcd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

				@Override
				public void run() {
					
					ingsec--;
					
					for(Player a : Bukkit.getOnlinePlayers()) {
					a.setLevel(ingsec);
					}
					if(ingsec % 1 == 0) {
						
						
						for(Player a : Main.playing) {
						
							a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
							a.sendTitle("§6 " +Integer.toString(ingsec), "");
						}
						
						
						if(ingsec == 0) {
							
							Bukkit.getScheduler().cancelTask(ingcd);
							Main.gameState = Gamestate.Ingame;
						}
					}
					
				}
				
			}, 0, 20);
		}
		
	}
	
	public static void spielBeginn(Player p) {
		Integer kit = 0;
		String UUID = p.getUniqueId().toString();
		if(EVENTinventoryclick.Starter.contains(p)) {
			kit = 1;
		} else if(EVENTinventoryclick.Enderman.contains(p)) {
			kit = 2;
		} else if(EVENTinventoryclick.Darkness.contains(p)) {
			kit = 3;
		} else if(EVENTinventoryclick.Light.contains(p)) {
			kit = 4;
		}
		
		 if(kit == 0) {
			if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 1) {
				EVENTinventoryclick.Starter.add(p);
				kit = 1;
			}
			else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 2) {
				EVENTinventoryclick.Enderman.add(p);
				kit = 2;
			}
			else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 3) {
				EVENTinventoryclick.Darkness.add(p);
				kit = 3;
			}
			else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 4) {
				EVENTinventoryclick.Light.add(p);
				kit = 4;
			}
			if(kit != 0) {
				Main.getInstance().getKitapi().setDefaultKit(UUID, kit);
				}
		}
//		if(!EVENTinventoryclick.Starter.contains(p) && !EVENTinventoryclick.Darkness.contains(p) && !EVENTinventoryclick.Enderman.contains(p) && !EVENTinventoryclick.Light.contains(p)) {
//			if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 1) {
//				EVENTinventoryclick.Starter.add(p);
//			} else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 2) {
//				EVENTinventoryclick.Enderman.add(p);
//			} else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 3) {
//				EVENTinventoryclick.Darkness.add(p);
//			} else if(Main.getInstance().getKitapi().getDefaultKit(UUID) == 4) {
//				EVENTinventoryclick.Light.add(p);
//			}
//			
//		}
		
		if(!Main.red.contains(p) && !Main.blue.contains(p)) {
			if(Main.red.size() > Main.blue.size()) {
				Main.blue.add(p);
				p.setDisplayName("§9"+ p.getName()+ "§f");
			} else if(Main.red.size() < Main.blue.size()) {
				Main.red.add(p);
				p.setDisplayName("§c"+ p.getName()+ "§f");
			} else if(Main.red.size() == Main.blue.size()) {								
				Main.red.add(p);													
			}
			
		}
			
			
			if(kit == 1) {
				ItemStack starter = new ItemStack(Material.STICK);		
				ItemMeta starterim = starter.getItemMeta();
				starterim.setDisplayName("§aStarter Gun");
				starter.setItemMeta(starterim);
				p.getInventory().setItem(0, starter);
				ItemStack starters = new ItemStack(Material.WOOL);		
				ItemMeta startersim = starters.getItemMeta();
				startersim.setDisplayName("§aSpecial Attack | Starter");
				starters.setItemMeta(startersim);
				p.getInventory().setItem(8, starters);
				
				
			}		
			else if(kit == 2) {
				ItemStack starter = new ItemStack(Material.ENDER_PORTAL_FRAME);		
				ItemMeta starterim = starter.getItemMeta();
				starterim.setDisplayName("§aEnder Gun");
				starter.setItemMeta(starterim);
				p.getInventory().setItem(0, starter);
				ItemStack starters = new ItemStack(Material.WOOL);		
				ItemMeta startersim = starters.getItemMeta();
				startersim.setDisplayName("§aSpecial Attack | Enderman");
				starters.setItemMeta(startersim);
				p.getInventory().setItem(8, starters);
				
			}	
			else if(kit == 3) {
				ItemStack starter = new ItemStack(Material.BONE);		
				ItemMeta starterim = starter.getItemMeta();
				starterim.setDisplayName("§aDarkness Gun");
				starter.setItemMeta(starterim);
				p.getInventory().setItem(0, starter);
				ItemStack starters = new ItemStack(Material.WOOL);		
				ItemMeta startersim = starters.getItemMeta();
				startersim.setDisplayName("§aSpecial Attack | Darkness");
				starters.setItemMeta(startersim);
				p.getInventory().setItem(8, starters);
				
			} else if(kit == 4) {
				ItemStack starter = new ItemStack(Material.NETHER_STAR);		
				ItemMeta starterim = starter.getItemMeta();
				starterim.setDisplayName("§aLight Gun");
				starter.setItemMeta(starterim);
				p.getInventory().setItem(0, starter);
				ItemStack starters = new ItemStack(Material.WOOL);		
				ItemMeta startersim = starters.getItemMeta();
				startersim.setDisplayName("§aSpecial Attack | Light");
				starters.setItemMeta(startersim);
				p.getInventory().setItem(8, starters);
				
				p.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				ItemStack rboots = new ItemStack(Material.LEATHER_BOOTS, 1, DyeColor.RED.getData());
				ItemStack rleggins = new ItemStack(Material.LEATHER_LEGGINGS, 1, DyeColor.RED.getData());
				ItemStack rchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.RED.getData());
				ItemStack rhelm = new ItemStack(Material.LEATHER_HELMET, 1, DyeColor.RED.getData());
				
				ItemStack bboots = new ItemStack(Material.LEATHER_BOOTS, 1, DyeColor.BLUE.getData());
				ItemStack bleggins = new ItemStack(Material.LEATHER_LEGGINGS, 1, DyeColor.BLUE.getData());
				ItemStack bchest = new ItemStack(Material.LEATHER_CHESTPLATE, 1, DyeColor.BLUE.getData());
				ItemStack bhelm = new ItemStack(Material.LEATHER_HELMET, 1, DyeColor.BLUE.getData());
				
				if(Main.red.contains(p)) {
					p.getInventory().setHelmet(rhelm);
					p.getInventory().setChestplate(rchest);
					p.getInventory().setLeggings(rleggins);
					p.getInventory().setBoots(rboots);
				} else if(Main.blue.contains(p)) {
					p.getInventory().setHelmet(bhelm);
					p.getInventory().setChestplate(bchest);
					p.getInventory().setLeggings(bleggins);
					p.getInventory().setBoots(bboots);
				}
				
			
		}
		
		
		File file = new File("plugins/paintball/config.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		if(Main.red.contains(p)) {
			
			String s = cfg.getString("Red.Welt");
			
			double x =	cfg.getDouble("Red.X");
			double y =	cfg.getDouble("Red.Y");
			double z =	cfg.getDouble("Red.Z");
			
			float yaw = (float) cfg.getDouble("Red.Yaw");
			float pitch = (float) cfg.getDouble("Red.Pitch");
			
			Location loc = new Location(Bukkit.getWorld(s), x, y, z);
			
			loc.setYaw(yaw);
			loc.setPitch(pitch);
			
			p.teleport(loc);
			
			
		
			
		} else if(Main.blue.contains(p)) {
		
			String s = cfg.getString("Blue.Welt");
			
			double x =	cfg.getDouble("Blue.X");
			double y =	cfg.getDouble("Blue.Y");
			double z =	cfg.getDouble("Blue.Z");
			
			float yaw = (float) cfg.getDouble("Blue.Yaw");
			float pitch = (float) cfg.getDouble("Blue.Pitch");
			
			Location loc = new Location(Bukkit.getWorld(s), x, y, z);
			
			loc.setYaw(yaw);
			loc.setPitch(pitch);
			
			p.teleport(loc);
			
			
		
			
		}
	
	}
	
	public static void LobbyCD(Player p) {
		
			if(lobbycdb == false) {
				lobbycdb = true;
				
				lobbycd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

					
					
					@Override
					public void run() {
						
						lobbysec--;
					
						for(Player a : Bukkit.getOnlinePlayers()) {
						a.setLevel(lobbysec);
						}
						if(lobbysec % 10 == 0 && lobbysec > 5) {
							

							Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in §6" + lobbysec +" §aSekunden");
							for(Player a : Main.playing) {
								a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
								
							}
							
						} else if(lobbysec <= 5) {
						
							Bukkit.broadcastMessage(Main.pr + " §aDas Spiel startet in §6" + lobbysec +" §aSekunden");
						for(Player a : Main.playing) {
							a.playSound(a.getLocation(), Sound.LAVA_POP, 1, 1);
							
						}
						}
							if(lobbysec == 0) {
								p.getInventory().clear();
								
								
								
								
								
								Bukkit.getScheduler().cancelTask(lobbycd);
								ingCD(p);
	
								Main.gameState = Gamestate.INGCD;
								for(Player a : Bukkit.getOnlinePlayers()) {
									
									spielBeginn(a);
									
								}
							}
						
						
					}
					
				}, 0, 20);
			}
			
	}
	

	
//	public void idle() {
//	isIdling = true;
//	
//	IdleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
//		
//		@Override
//		public void run() {
//			
//			int missing = cfg.getInt("Minplayers") - Main.playing.size();
//			if(missing == 0){
//				if(isIdling) {
//					isIdling = false;
//					Bukkit.getScheduler().cancelTask(IdleID);
//					
//				}
//			}
//			
//			Bukkit.broadcastMessage(Main.pr + " §7Zum Spielstart fehlen noch §c" +missing+ " §7Spieler");
//			
//		}
//	}, 0, 20*15);
//	}
}
