package me.gz.limpeza;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.gz.limpeza.commands.Force;
import me.gz.limpeza.runnables.AutoDetect;
import me.gz.limpeza.runnables.Limpar;


public class main extends JavaPlugin {
	
	public static main pl;
	
	public static File config = new File("plugins/GZ_Limpezas", "settings.yml");
	public static FileConfiguration CFG = YamlConfiguration.loadConfiguration(config);
	
	
	@Override
	public void onEnable() {
		pl = this;
		load();
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Limpar(), 0, 20);
		if(CFG.getBoolean("AutoDetect"))
		Bukkit.getScheduler().runTaskTimerAsynchronously(this, new AutoDetect(), 0, 20 * 10);
		getCommand("limpar").setExecutor(new Force());
	}
	
    private void load() {
    	if(!config.exists()) {
    		saveResource("settings.yml", false);
    		
    		try {
				CFG.load(config);
			} catch (Exception e) {}
    	}
    }
}






