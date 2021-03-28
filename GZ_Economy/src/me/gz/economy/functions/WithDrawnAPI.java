package me.gz.economy.functions;

import java.sql.PreparedStatement;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.gz.economy.main;
import me.gz.economy.api.events.PlayerWithDrawEvent;
import me.gz.economy.sql.DinheiroSQL;
import me.gz.economy.sql.SQL;

public class WithDrawnAPI {
	
	//Async Method
	public static void Sacar(OfflinePlayer p, double valor)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.getPlugin(main.class), new Runnable() {
			
			@Override
			public void run() {
				
				PlayerWithDrawEvent event = new PlayerWithDrawEvent(p, valor);
				Bukkit.getPluginManager().callEvent(event);
				
				if(Bukkit.getPlayerExact(p.getName()) == null) 
				{
					try {
						
						if(event.isCancelled()) {
							return;
						}
						
						PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
						stmt.setDouble(1, DinheiroSQL.ValorEmBancoDeDados(p) - valor);
						stmt.setString(2, p.getName());
						stmt.execute();
						
						stmt.close();
					} catch (Exception e) {
						Bukkit.getConsoleSender().sendMessage("§4Ocorreu um Erro Inesperado!");
					}
					return;
				}
				
				if(event.isCancelled()) {
					return;
				}
				
				main.bankLocal.put(p, main.bankLocal.get(p) - valor);
			}
		});
	}

	
	//Async Method
	@SuppressWarnings("deprecation")
	public static void Sacar(String name, double valor)
	{
		OfflinePlayer p = Bukkit.getOfflinePlayer(name);
		
		Bukkit.getScheduler().runTaskAsynchronously(main.getPlugin(main.class), new Runnable() {
			
			@Override
			public void run() {
				
				PlayerWithDrawEvent event = new PlayerWithDrawEvent(p, valor);
				Bukkit.getPluginManager().callEvent(event);
				
				if(Bukkit.getPlayerExact(name) == null)
				{
					try {
						
						if(event.isCancelled()) {
							return;
						}
						PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
						stmt.setDouble(1, DinheiroSQL.ValorEmBancoDeDados(p) - valor);
						stmt.setString(2, p.getName());
						stmt.execute();
						
						stmt.close();
					} catch (Exception e) {
						Bukkit.getConsoleSender().sendMessage("§4Ocorreu um Erro Inesperado!");
					}
					return;
				}
				
				if(event.isCancelled()) {
					return;
				}
				
				main.bankLocal.put(p, main.bankLocal.get(p) - valor);
			}
		});		
	}

}
