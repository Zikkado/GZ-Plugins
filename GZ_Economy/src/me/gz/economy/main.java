package me.gz.economy;

import java.io.File;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.gz.economy.commands.MoneyCommand;
import me.gz.economy.hook.Hooks;
import me.gz.economy.listeners.ClickInventory;
import me.gz.economy.listeners.Join;
import me.gz.economy.listeners.Quit;
import me.gz.economy.managers.SaveManager;
import me.gz.economy.sql.MySQL;
import me.gz.economy.sql.SQL;
import me.gz.economy.utils.FormatType.TypeFormat;
import me.gz.economy.utils.TopFormat.FormatTops;

public class main extends JavaPlugin {
	
	public static main pl;
	
	public static Map<OfflinePlayer, Double> bankLocal = new HashMap<>();
	public static Map<OfflinePlayer, TypeFormat> FormatType = new HashMap<>();
	public static Map<OfflinePlayer, FormatTops> TopType = new HashMap<>();
	
	public static File settings = new File("plugins/GZ_Economy", "settings.yml");
	public static FileConfiguration CFG = YamlConfiguration.loadConfiguration(settings);
	
	public static File Np = new File("plugins/GZ_Economy", "NPC.yml");
	public static FileConfiguration NPC = YamlConfiguration.loadConfiguration(Np);
	
	public static File Gui = new File("plugins/GZ_Economy", "gui.yml");
	public static FileConfiguration GUI = YamlConfiguration.loadConfiguration(Gui);
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("§2" + me.gz.economy.utils.FormatType.Formatar(TypeFormat.OP, 1.000));
		pl = this;
		Register();
		FilesCheck();
	}
	
	private void Register() {
		Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§eTentando Efetuar A Hook Com o Vault");
		Bukkit.getPluginManager().registerEvents(new Hooks(), this);
		Bukkit.getPluginManager().registerEvents(new Join(), this);
		Bukkit.getPluginManager().registerEvents(new ClickInventory(), this);
		Bukkit.getPluginManager().registerEvents(new Quit(), this);
		getCommand("money").setExecutor(new MoneyCommand());
	}
	
	@Override
	public void onDisable() {
		new SaveManager().onSave();
		SQL.closeConnection();
	}
	
	
	private void FilesCheck()
	{
		if(!settings.exists()) {
			saveResource("settings.yml", false);
			try {
				CFG.load(settings);
			} catch (Exception e) {}
		}
		
		if(!Gui.exists()) {
			saveResource("gui.yml", false);
			try {
				GUI.load(Gui);
			} catch (Exception e) {}
		}
	}
	
	
	
	


}
