package de.viralvince.paintball.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import de.viralvince.paintball.main.Gamestate;
import de.viralvince.paintball.main.Main;

public class EVENTinteract implements Listener{
	public static int coolcd;
	public static int coolsek = 5;
	public static int stcool;
	public static int stcoolsek = 1;
	public static int spcool;
	public static int spcoolsek = 30;
	public static int startcool;
	public static int startcoolsek = 20;
	public static int startcool1;
	public static int startcoolsek1 = 20;
	public static ArrayList<Player> cd = new ArrayList<>();
	public static ArrayList<Player> startershot = new ArrayList<>();
	public static ArrayList<Player> map1 = new ArrayList<>();
	public static ArrayList<Player> map2 = new ArrayList<>();
	public static ArrayList<Player> map3 = new ArrayList<>();
	public static ArrayList<Player> sStarter = new ArrayList<>();
	public static ArrayList<Player> sEnderman = new ArrayList<>();
	public static ArrayList<Player> sDarknes = new ArrayList<>();
	public static ArrayList<Player> sLight = new ArrayList<>();
	@EventHandler
	
	public void onInt(PlayerInteractEvent e)  {
		Player p = e.getPlayer();
		String UUID = p.getUniqueId().toString();
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK ) {
			if(p.getItemInHand().getType() == Material.WOOL){
				if(Main.gameState == Gamestate.Lobby || Main.gameState == Gamestate.Countdown) {
					
				
				e.setCancelled(true);
				ItemStack blue = new ItemStack(Material.WOOL, 1, DyeColor.BLUE.getData());
				ItemMeta blueim = blue.getItemMeta();
				blueim.setDisplayName("§9Blau");
				List<String> bluelore = new ArrayList<String>();
				for(Player a : Bukkit.getOnlinePlayers()) {
					if(Main.blue.contains(a)) {
						bluelore.add(a.getDisplayName());
					}
				}	   																				
				blueim.setLore(bluelore);
				blue.setItemMeta(blueim);
				
				
				ItemStack red = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
				ItemMeta redim = red.getItemMeta();
				redim.setDisplayName("§cRot");
				List<String> redlore = new ArrayList<String>();
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(Main.red.contains(all)) {
						redlore.add(all.getDisplayName());
					}
				}	   																				
				blueim.setLore(redlore);
				red.setItemMeta(redim);
				
				
				Inventory inv = Bukkit.createInventory(null, 9, "§7Wähle dein Team");
				inv.setItem(2, blue);
				inv.setItem(6, red);
				
				p.openInventory(inv);
				}
				
				
			} else if(p.getItemInHand().getType() == Material.ANVIL) {
				if(Main.gameState == Gamestate.Lobby || Main.gameState == Gamestate.Countdown) {
					if(!cd.contains(p)) {
					
				p.performCommand("start");
				p.playSound(p.getLocation(), Sound.ANVIL_USE, 1, 1);
				cd.add(p);
				
				
				coolcd = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						
						coolsek--;
						if(coolsek == 0) {
							
								cd.remove(p);
							
						}
						
						
						
						}
					
				}, 0, 20*5);
				
				
				} else {
					p.sendMessage(Main.pr + " §cBitte spamme dieses Item nicht");
				}
			}
				
			} else if(p.getItemInHand().getType() == Material.CHEST) {
				if(Main.gameState == Gamestate.Lobby || Main.gameState == Gamestate.Countdown) {
					Inventory inv = Bukkit.createInventory(null, 27, Main.pr + " §7Wähle Kit");
					
					
					ItemStack Starter = new ItemStack(Material.SLIME_BALL);
					ItemMeta Starterim = Starter.getItemMeta();
					List<String> stlore = new ArrayList<String>();
					stlore.add("§7Das einfache Starter Kit");
					stlore.add("§7Schaden: Ein halbes Hertz pro Treffer");
					stlore.add("§7Specialattack: Schiesst viele kugeln für 5 sekunden");
					Starterim.setDisplayName("§aStarter (gekauft)");
					Starterim.setLore(stlore);
					Starter.setItemMeta(Starterim);
					
					inv.setItem(0, Starter);
					
					ItemStack Enderm = new ItemStack(Material.ENDER_PEARL);
					ItemMeta Endermim = Enderm.getItemMeta();
					if(Main.getInstance().getKitapi().getKit(UUID, 2) == 1) {
					Endermim.setDisplayName("§aEnderman (gekauft)");
					} else {
						Endermim.setDisplayName("§aEnderman §c(nicht gekauft)");
					}
					List<String> edlore = new ArrayList<String>();
					edlore.add("§7Das mächtige Enderman Kit");
					edlore.add("§7Schaden: 1 Hertz pro Treffer");
					edlore.add("§7Specialattack: Der Spieler kann sich mit einem Schuss teleportierten");
					Endermim.setLore(edlore);
					Enderm.setItemMeta(Endermim);
					inv.setItem(1, Enderm);
					
					ItemStack dark = new ItemStack(Material.COAL_BLOCK);
					ItemMeta darkim = dark.getItemMeta();				
					if(Main.getInstance().getKitapi().getKit(UUID, 3) == 1) {
						darkim.setDisplayName("§aDarkness (gekauft)");
						} else {
							darkim.setDisplayName("§aDarkness §c(nicht gekauft)");
						}
					List<String> dalore = new ArrayList<String>();
					dalore.add("§7Das dunkle Darkness Kit");
					dalore.add("§7Schaden: 1 Hertz pro Treffer");
					dalore.add("§7Specialattack: Gibt dem getroffenen Gegner Blindness für 5 Sekunden");
					darkim.setLore(dalore);
					dark.setItemMeta(darkim);
					inv.setItem(2, dark);
					
					ItemStack light = new ItemStack(Material.NETHER_STAR);
					ItemMeta lightim = light.getItemMeta();
					
					if(Main.getInstance().getKitapi().getKit(UUID, 4) == 1) {
						lightim.setDisplayName("§aLight (gekauft)");
						} else {
							lightim.setDisplayName("§aLight §c(nicht gekauft)");
						}
					List<String> lilore = new ArrayList<String>();
					lilore.add("§7Das blendende Light Kit");
					lilore.add("§7Schaden: 1 Hertz pro Treffer");
					lilore.add("§7Specialattack: Schiess Blitze auf Gegner in der Nähe");
					lightim.setLore(lilore);
					light.setItemMeta(lightim);
					inv.setItem(3, light);
					
					
					
					
					p.openInventory(inv);
					
					
					
				}
				
			
			} else if(p.getItemInHand().getType() == Material.BOOK_AND_QUILL) {
				e.setCancelled(true);
				p.closeInventory();
				Inventory force = Bukkit.createInventory(null, 9, Main.pr + " §aMap forcen");
				
				
				
				
				
				p.openInventory(force);
				
			} else if(p.getItemInHand().getType() == Material.BOOK) {
				e.setCancelled(true);
			}
		}
		
	
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(Main.gameState == Gamestate.Ingame) {
		Player p = e.getPlayer();
		ItemStack starter = new ItemStack(Material.STICK);		
		ItemMeta starterim = starter.getItemMeta();
		starterim.setDisplayName("§aStarter Gun");
		starter.setItemMeta(starterim);
		
		ItemStack starterr = new ItemStack(Material.ENDER_PORTAL_FRAME);		
		ItemMeta starterrim = starterr.getItemMeta();
		starterrim.setDisplayName("§aEnder Gun");
		starterr.setItemMeta(starterrim);
		
		ItemStack startert = new ItemStack(Material.BONE);		
		ItemMeta startertim = startert.getItemMeta();
		startertim.setDisplayName("§aDarkness Gun");		
		startert.setItemMeta(startertim);
		
		
		ItemStack starteru = new ItemStack(Material.NETHER_STAR);		
		ItemMeta starteruim = starteru.getItemMeta();
		starteruim.setDisplayName("§aLight Gun");
		starteru.setItemMeta(starteruim);
		if(e.getAction() == Action.RIGHT_CLICK_AIR) {
			if(e.getItem().equals(starter)) {
				if(!startershot.contains(p)) {
			
				
				final Item slime = (Item) p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.SLIME_BALL));
				p.playSound(p.getLocation(), Sound.SLIME_WALK, 1, 1);
			    ((Entity) slime).setVelocity(p.getLocation().getDirection().multiply(1.2D));
			    
			    new BukkitRunnable() {
			    	

					@Override
					public void run() {
						
						
					
						for(Entity e : slime.getLocation().getChunk().getEntities()) {
							if (!(slime.getVelocity().equals(slime.getVelocity().zero()))) { 
							if(e.getLocation().distance(slime.getLocation()) < 2.0) {
								if(e instanceof Player) {
									if(!(e == p)) {
											if(!(Main.getInstance().getRed().contains(p) && Main.getInstance().getRed().contains(e)) || (Main.getInstance().getBlue().contains(p) && Main.getInstance().getBlue().contains(e))) {

												if(((Player) e).getHealth() - 1.0 == 0.0) {
													
													Main.getInstance().killed.put((Player) e, p);
											
													
												}
												
											((Player) e).damage(1.0);
											
									
									
										}
									}
								}
								}
								
								
							}
							
						}
			            
						if (!(slime.isOnGround() == true)) { 	
						
					    p.getWorld().playEffect(slime.getLocation(), Effect.SLIME, 5);
					    
					    
						} else {
							
							this.cancel();
							slime.remove();
							
						}
					}
			    	
			    }.runTaskTimer(Main.getInstance(), 0, 1);
			    
			    
			 
			    
			    
			    startershot.add(p);
			    
			    stcool = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
					
					@Override
					public void run() {
						
						stcoolsek--;
						if(stcoolsek == 0) {
							
								startershot.remove(p);
								stcoolsek = 1;
							
						}
						
						
						}
					
				}, 20);
			    

	            
//	            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
//
//					@Override
//					public void run() {
//                    
//						for(Entity entities : p.getWorld().getEntities()) {
//							
//							if(!(entities instanceof Player)) {
//								
//							entities.remove();
//								
//							}
//						}
//						
//					}
//	            	
//	            }, 40);
				} else {
					p.sendMessage(Main.pr + " §cBitte warte einen Moment befor du diese Waffe erneut benutzt!");
				}
			} else if(e.getItem().equals(starterr)) {
				if(!startershot.contains(p)) {
					
					
					final Item slime = (Item) p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.EYE_OF_ENDER));
					p.playSound(p.getLocation(), Sound.ENDERMAN_IDLE, 1, 1);
				    ((Entity) slime).setVelocity(p.getLocation().getDirection().multiply(1.2D));
				    
				    new BukkitRunnable() {
				    	

						@Override
						public void run() {
							
							
						
							for(Entity e : slime.getLocation().getChunk().getEntities()) {
								if (!(slime.getVelocity().equals(slime.getVelocity().zero()))) { 
								if(e.getLocation().distance(slime.getLocation()) < 2.0) {
									if(e instanceof Player) {
										if(!(e == p)) {
												if(!(Main.getInstance().getRed().contains(p) && Main.getInstance().getRed().contains(e)) || (Main.getInstance().getBlue().contains(p) && Main.getInstance().getBlue().contains(e))) {
									  
												((Player) e).damage(2.0);
												
												if(((Player) e).getHealth()  == 0.0) {
													
													e.sendMessage(Main.pr + " §cDu wurdest von " + p.getDisplayName() + " §cgetötet.");
													p.sendMessage(Main.pr + " §aDu hast " + ((Player) e).getDisplayName() + " §rgetötet.");
											
													
												}
										
										
											}
										}
									}
									}
									
									
								}
								
							}
				            
							if (!(slime.isOnGround() == true)) { 	
								
							    p.getWorld().playEffect(slime.getLocation(), Effect.PORTAL, 5);
							    
							    
								} else {
									
									this.cancel();
									slime.remove();
									
								}
						}
				    	
				    }.runTaskTimer(Main.getInstance(), 0, 1);
				    
				    
				 
				    
				    
				    startershot.add(p);
				    
				    stcool = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							
							stcoolsek--;
							if(stcoolsek == 0) {
								
									startershot.remove(p);
									stcoolsek = 1;
								
							}
							
							
							}
						
					}, 20);
				    

		            
  
					} else {
						p.sendMessage(Main.pr + " §cBitte warte einen Moment bevor du diese Waffe erneut benutzt!");
					}
			}
			
			else if(e.getItem().equals(startert)) {
				if(!startershot.contains(p)) {
					
					
					final Item slime = (Item) p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.COAL));
					p.playSound(p.getLocation(), Sound.DIG_STONE, 1, 1);
				    ((Entity) slime).setVelocity(p.getLocation().getDirection().multiply(1.2D));
				    
				    new BukkitRunnable() {
				    	

						@Override
						public void run() {
							
							
						
							for(Entity e : slime.getLocation().getChunk().getEntities()) {
								if (!(slime.getVelocity().equals(slime.getVelocity().zero()))) { 
								if(e.getLocation().distance(slime.getLocation()) < 2.0) {
									if(e instanceof Player) {
										if(!(e == p)) {
												if(!(Main.getInstance().getRed().contains(p) && Main.getInstance().getRed().contains(e)) || (Main.getInstance().getBlue().contains(p) && Main.getInstance().getBlue().contains(e))) {
									  
												((Player) e).damage(2.0);
												
												if(((Player) e).getHealth()  == 0.0) {
													
													e.sendMessage(Main.pr + " §cDu wurdest von " + p.getDisplayName() + " §cgetötet.");
													p.sendMessage(Main.pr + " §aDu hast " + ((Player) e).getDisplayName() + " §rgetötet.");
											
													
												}
										
										
											}
										}
									}
									}
									
									
								}
								
							}
				            
							if (!(slime.isOnGround() == true)) { 	
								
							    p.getWorld().playEffect(slime.getLocation(), Effect.CRIT, 5);
							    
							    
								} else {
									
									this.cancel();
									slime.remove();
									
								}
						}
				    	
				    }.runTaskTimer(Main.getInstance(), 0, 1);
				    
				    
				 
				    
				    
				    startershot.add(p);
				    
				    stcool = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							
							stcoolsek--;
							if(stcoolsek == 0) {
								
									startershot.remove(p);
									stcoolsek = 1;
								
							}
							
							
							}
						
					}, 20);
				    

		            
  
					} else {
						p.sendMessage(Main.pr + " §cBitte warte einen Moment befor du diese Waffe erneut benutzt!");
					}
			} else if(e.getItem().equals(starteru)) {
				if(!startershot.contains(p)) {
					
					
					final Item slime = (Item) p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.NETHER_STAR));
					p.playSound(p.getLocation(), Sound.FIZZ, 1, 1);
				    ((Entity) slime).setVelocity(p.getLocation().getDirection().multiply(1.2D));
				    
				    new BukkitRunnable() {
				    	

						@Override
						public void run() {
							
							
						
							for(Entity e : slime.getLocation().getChunk().getEntities()) {
								if (!(slime.getVelocity().equals(slime.getVelocity().zero()))) { 
								if(e.getLocation().distance(slime.getLocation()) < 2.0) {
									if(e instanceof Player) {
										if(!(e == p)) {
												if(!(Main.getInstance().getRed().contains(p) && Main.getInstance().getRed().contains(e)) || (Main.getInstance().getBlue().contains(p) && Main.getInstance().getBlue().contains(e))) {
									  
												((Player) e).damage(2.0);
												
												if(((Player) e).getHealth()  == 0.0) {
													
													e.sendMessage(Main.pr + " §cDu wurdest von " + p.getDisplayName() + " §cgetötet.");
													p.sendMessage(Main.pr + " §aDu hast " + ((Player) e).getDisplayName() + " §rgetötet.");
											
													
												}
										
										
											}
										}
									}
									}
									
									
								}
								
							}
				            
							if (!(slime.isOnGround() == true)) { 	
								
							    p.getWorld().playEffect(slime.getLocation(), Effect.CLOUD, 5);
							    
							    
								} else {
									
									this.cancel();
									slime.remove();
									
								}
						}
				    	
				    }.runTaskTimer(Main.getInstance(), 0, 1);
				    
				    
				 
				    
				    
				    startershot.add(p);
				    
				    stcool = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), new Runnable() {
						
						@Override
						public void run() {
							
							stcoolsek--;
							if(stcoolsek == 0) {
								
									startershot.remove(p);
									stcoolsek = 1;
								
							}
							
							
							}
						
					}, 20);
				    

		            
  
					} else {
						p.sendMessage(Main.pr + " §cBitte warte einen Moment befor du diese Waffe erneut benutzt!");
					}
			}
			
		}
		}
	}
	
	@EventHandler
	public void onInt1(PlayerInteractEvent e){
	 if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
		 if(Main.gameState == Gamestate.Ingame){
			  Player p = e.getPlayer();
				  	ItemStack starters = new ItemStack(Material.WOOL);		
					ItemMeta startersim = starters.getItemMeta();
					startersim.setDisplayName("§aSpecial Attack | Starter");
					starters.setItemMeta(startersim);
					ItemStack ends = new ItemStack(Material.WOOL);		
					ItemMeta endsim = ends.getItemMeta();
					endsim.setDisplayName("§aSpecial Attack | Enderman");
					ends.setItemMeta(endsim);
					ItemStack startersr = new ItemStack(Material.WOOL);		
					ItemMeta startersrim = startersr.getItemMeta();
					startersrim.setDisplayName("§aSpecial Attack | Darkness");
					startersr.setItemMeta(startersrim);
					ItemStack starterst = new ItemStack(Material.WOOL);		
					ItemMeta starterstim = starterst.getItemMeta();
					starterstim.setDisplayName("§aSpecial Attack | Light");
					starterst.setItemMeta(starterstim);
					
					if(p.getItemInHand().equals(starters)) {
						
						if(!sStarter.contains(p)) {
							sStarter.add(p);
							final Item slime = (Item) p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.SLIME_BALL));
							startcool = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
								@Override
								public void run() {
									
									p.playSound(p.getLocation(), Sound.SLIME_WALK, 1, 1);
									((Entity) slime).setVelocity(p.getLocation().getDirection().multiply(1.2D));
									startcoolsek --;
									if(startcoolsek == 0) {
										Bukkit.getScheduler().cancelTask(startcool);
										startcoolsek = 5;
									}
									
								}
							}, 0, 5);
								new BukkitRunnable() {
							    	
	
									@Override
									public void run() {
									
									
									
										for(Entity e : slime.getLocation().getChunk().getEntities()) {
											if (!(slime.getVelocity().equals(slime.getVelocity().zero()))) { 
											if(e.getLocation().distance(slime.getLocation()) < 2.0) {
												if(e instanceof Player) {
													if(!(e == p)) {
															if(!(Main.getInstance().getRed().contains(p) && Main.getInstance().getRed().contains(e)) || (Main.getInstance().getBlue().contains(p) && Main.getInstance().getBlue().contains(e))) {
	
																if(((Player) e).getHealth() - 1.0 == 0.0) {
																	
																	Main.getInstance().killed.put((Player) e, p);															
																
																}
																
															((Player) e).damage(1.0);
															
													
													
														}
													}
												}
												}
												
												
											}
										
										}
							            
										if (!(slime.isOnGround() == true)) { 	
											startcool1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
												@Override
												public void run() {
													
													
													
													startcoolsek1 --;
													if(startcoolsek1 == 0) {
														Bukkit.getScheduler().cancelTask(startcool1);
														startcoolsek1 = 20;
													}
													p.getWorld().playEffect(slime.getLocation(), Effect.SLIME, 5);
												}
											}, 0, 5);
									    
									    
									    
										} else {
											
											this.cancel();
											slime.remove();
											
									}
									}
							    	
							    }.runTaskTimer(Main.getInstance(), 0, 1);
							    
									
									
									
								
							
							
							
							
//							final Item slime = (Item) p.getWorld().dropItem(p.getEyeLocation(), new ItemStack(Material.SLIME_BALL));
//							p.playSound(p.getLocation(), Sound.SLIME_WALK, 1, 1);
//						    ((Entity) slime).setVelocity(p.getLocation().getDirection().multiply(1.2D));
//						    
//						    new BukkitRunnable() {
//						    	
//
//								@Override
//								public void run() {
//									
//									
//								
//									for(Entity e : slime.getLocation().getChunk().getEntities()) {
//										if (!(slime.getVelocity().equals(slime.getVelocity().zero()))) { 
//										if(e.getLocation().distance(slime.getLocation()) < 2.0) {
//											if(e instanceof Player) {
//												if(!(e == p)) {
//														if(!(Main.getInstance().getRed().contains(p) && Main.getInstance().getRed().contains(e)) || (Main.getInstance().getBlue().contains(p) && Main.getInstance().getBlue().contains(e))) {
//
//															if(((Player) e).getHealth() - 1.0 == 0.0) {
//																
//																Main.getInstance().killed.put((Player) e, p);
//														
//																
//															}
//															
//														((Player) e).damage(1.0);
//														
//												
//												
//													}
//												}
//											}
//											}
//											
//											
//										}
//										
//									}
//						            
//									if (!(slime.isOnGround() == true)) { 	
//									
//								    p.getWorld().playEffect(slime.getLocation(), Effect.SLIME, 5);
//								    
//								    
//									} else {
//										
//										this.cancel();
//										slime.remove();
//										
//									}
//								}
//						    	
//						    }.runTaskTimer(Main.getInstance(), 0, 1);
//						    
//						    
//						 
//						    
//						    
//						    
//						    
//						    
							
							//___________________________________________
							spcool = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
								
								@Override
								public void run() {
									p.setLevel(spcoolsek);
									spcoolsek--;
									if(spcoolsek == 0) {
											p.setLevel(0);
											sStarter.remove(p);
											Bukkit.getScheduler().cancelTask(spcool);
											spcoolsek = 30;
											
									}
									
									
									
									}
								
							}, 0, 20);
							
						
						} else {
							p.sendMessage(Main.pr+ " §cWarte noch §6" + spcoolsek + " §cSekunden befor du die Spezialattacke erneut einsetzen kannst!");
						}
						//____________________________________________
					} else if(p.getItemInHand().equals(ends)) {
						if(!sEnderman.contains(p)) {
							
							
							
							
							sEnderman.add(p);
							
						spcool = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {

								@Override
								public void run() {
									p.setLevel(spcoolsek);
									spcoolsek--;
									if(spcoolsek == 0) {
											
											sEnderman.remove(p);
											Bukkit.getScheduler().cancelTask(spcool);
											spcoolsek = 30;
									}
									
									
									
									}
								
							}, 0, 20);
							
						
						} else {
							p.sendMessage(Main.pr+ " §cWarte noch §6" + spcoolsek + " §cSekunden befor du die Spezialattacke erneut einsetzen kannst!");
						}
				
					}
					
					

			 	}		 
		 	}		
		}
		
	}
		
		
		