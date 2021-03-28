package me.gz.economy.hook;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

import me.gz.economy.main;
import me.gz.economy.managers.SaveManager;
import me.gz.economy.sql.DinheiroSQL;
import me.gz.economy.sql.EnumSQL;
import me.gz.economy.sql.MySQL;
import me.gz.economy.sql.SQL;
import me.gz.economy.sql.SQLITE;
import me.gz.economy.utils.FormatType.TypeFormat;
import me.gz.economy.utils.TopFormat.FormatTops;
import net.milkbowl.vault.economy.Economy;

public class VaultAPI {
	
	@SuppressWarnings("unused")
	private static Economy economy;
	
	public static void VaultHook() {
		
			Bukkit.getServicesManager().register(Economy.class, new me.gz.economy.Economy(), Bukkit.getPluginManager().getPlugin("Vault"), ServicePriority.Normal);
			
			RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
			if (economyProvider != null) {
				economy = economyProvider.getProvider();
			}
			Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§aHook Com o Vault Efetuado!");
			SQLStart();
			return;
	}
	
	private static void SQLStart() {
		
		if(main.CFG.getBoolean("MySQL.Enable")) {
			  new MySQL(main.CFG.getString("MySQL.host"), main.CFG.getInt("MySQL.port"), main.CFG.getString("MySQL.user"), main.CFG.getString("MySQL.pass"), main.CFG.getString("MySQL.DB"));
			  MySQL.openConnection();
			  SQL.CreateTable("gz_bank", "ID INT PRIMARY KEY NOT NULL AUTO_INCREMENT, UUID VARCHAR(255), Nickname VARCHAR(16), MoneyFormat VARCHAR(10), TopFormat VARCHAR(5), Valor DOUBLE");
		}
		else {
			SQLITE.openConnection();
			SQL.CreateTable("gz_bank", "UUID TEXT, Nickname TEXT, MoneyFormat TEXT, TopFormat TEXT, Valor DOUBLE");
		}
		
		new SaveManager().onAutoSave();
		
	    for(Player p : Bukkit.getOnlinePlayers())  {
				
			if(p.isEmpty())
			return;
				
			main.bankLocal.put(p, DinheiroSQL.ValorEmBancoDeDados(p));
			main.FormatType.put(p, TypeFormat.valueOf(EnumSQL.MoneyFormat(p)));
			main.TopType.put(p, FormatTops.valueOf(EnumSQL.TopFormat(p)));
		}  		
	}
	
}
