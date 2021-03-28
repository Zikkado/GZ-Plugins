package me.gz.limpeza.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import me.gz.limpeza.ActionBar;
import me.gz.limpeza.main;

public class Limpar implements Runnable {

	public static int i = main.CFG.getInt("Tempo");
	int quantidade = 0;
	@Override
	public void run() {
		
		if(i == 60) {
			Bukkit.broadcastMessage(main.CFG.getString("Alertas.UmMinuto").replace("&", "§"));
		}
		
		if(i == 30) {
			Bukkit.broadcastMessage(main.CFG.getString("Alertas.TrintaSegundos").replace("&", "§"));
		}
		
		if(i == 10) {
			for(Player p : Bukkit.getOnlinePlayers()) {
				ActionBar.onSend(p, main.CFG.getString("Alertas.DezSegundos").replace("&", "§"));
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1F, 1F);
			}
		}
		
		if(i <= 5 && i >= 1) {
			
			for(Player p : Bukkit.getOnlinePlayers()) {
				ActionBar.onSend(p, main.CFG.getString("Alertas.UltimosSegundos").replace("%temp%", "" + i).replace("&", "§"));
				p.playSound(p.getLocation(), Sound.CLICK, 1F, 1F);
			}
		}
		
		if(i == 0) 
		{
			for(World wd : Bukkit.getWorlds()) {
				for(Entity en : wd.getEntities()) {
					if(en.getType() == EntityType.DROPPED_ITEM) {
						
						Item item = (Item) en;
						
						for(String ig : main.CFG.getStringList("Ignorar")) 
						{
							try {
								if(item.getItemStack().getType() != Material.valueOf(ig)) {
									en.remove();
									quantidade++;
								}
							}
							catch (Exception e) {
								Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " +  "§cErro na Enum Verifique e tente Novamente!");
								en.remove();
								quantidade++;
							}
							
						}
					}
				}
			}
			
			if(quantidade == 0) 
			{
				Bukkit.broadcastMessage(main.CFG.getString("Gerais.NenhumItem").replace("&", "§"));
				if(main.CFG.getBoolean("Recompensar.enable")) {
					for(Player all : Bukkit.getOnlinePlayers()) {
						for(String cmd : main.CFG.getStringList("Recompensar.commands")) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%players%", all.getName()));
						}
						Bukkit.broadcastMessage(main.CFG.getString("Gerais.Recompensas").replace("&", "§"));
						all.playSound(all.getLocation(), Sound.LEVEL_UP, 1F, 0.5F);
					}
				}
				i = main.CFG.getInt("Tempo");
				return;
			}
			
			Bukkit.broadcastMessage(main.CFG.getString("Gerais.Limpar").replace("%quantidade%", "" + quantidade).replace("&", "§"));
			for(Player p : Bukkit.getOnlinePlayers()) {
				p.playSound(p.getLocation(), Sound.LEVEL_UP, 1F, 1F);
			}
			i = main.CFG.getInt("Tempo");
			quantidade = 0;
		}
		i--;
	}

}
