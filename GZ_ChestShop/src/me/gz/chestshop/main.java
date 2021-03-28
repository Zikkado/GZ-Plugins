package me.gz.chestshop;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.plugin.java.JavaPlugin;

import me.gz.chestshop.hooks.VaultAPI;
import me.gz.chestshop.listeners.CreateShop;
import me.gz.chestshop.listeners.PlayerInteractEvents;

public class main extends JavaPlugin {
	
	public static main pl;
	
	private static File file = new File("plugins/GZ_ChestShop", "settings.yml");
	public static FileConfiguration CFG = YamlConfiguration.loadConfiguration(file);
	
	
	
	@Override
	public void onEnable() {
		pl = this;
		Bukkit.getPluginManager().registerEvents(new PlayerInteractEvents(), pl);
		Bukkit.getPluginManager().registerEvents(new CreateShop(), pl);
		ConfigStart();
		new VaultAPI();
		
	}
	
	private void ConfigStart() {
		if(file.exists())
			return;
		
		try {
			saveResource("settings.yml", false);
			CFG.load(file);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
