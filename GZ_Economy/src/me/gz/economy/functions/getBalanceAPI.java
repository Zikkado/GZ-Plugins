package me.gz.economy.functions;


import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.gz.economy.main;
import me.gz.economy.sql.DinheiroSQL;

public class getBalanceAPI {
	
	public static double getBalance(OfflinePlayer p) {
		if(Bukkit.getPlayer(p.getName()) != null) {
			return main.bankLocal.get(p);
		}
		return DinheiroSQL.ValorEmBancoDeDados(p);
	}
	
	@SuppressWarnings("deprecation")
	public static double getBalance(String name) {
		if(Bukkit.getPlayerExact(name) != null) {
			return main.bankLocal.get(Bukkit.getOfflinePlayer(name));
		}
		return DinheiroSQL.ValorEmBancoDeDados(name);
	}
	
	


}
