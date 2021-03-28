package me.gz.chestshop.hooks;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;

import me.gz.chestshop.sql.SQL;
import net.milkbowl.vault.economy.Economy;

public class VaultAPI implements Listener {
	
	public static Economy economy = null;
	
	public VaultAPI() {
		
		new SQL().openConnection();
		
		 RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
	       if (rsp == null) {
	           return;
	       }
	      economy = rsp.getProvider();
	}
	
	public static void initVault() {
		try {
			new VaultAPI();
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cErro");
		}
	}
	
	
	
}
