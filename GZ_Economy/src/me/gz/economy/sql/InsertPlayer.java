package me.gz.economy.sql;

import java.sql.PreparedStatement;

import org.bukkit.Bukkit;

import me.gz.economy.main;


public class InsertPlayer {
	
	public static void InsertUser(String UUID, String NICK, double valor)
    {
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				try {
		    		PreparedStatement state = SQL.conn.prepareStatement("INSERT INTO gz_bank (UUID, Nickname, MoneyFormat, TopFormat, Valor) VALUES(?,?,?,?,?)");
		    		state.setString(1, UUID);
		    		state.setString(2, NICK);
		    		state.setString(3, "OP");
		    		state.setString(4, "GUI");		    		
		    		state.setDouble(5, valor);
		    		state.execute();
		    		state.close();
		    		
				} catch (Exception e) {
					Bukkit.getConsoleSender().sendMessage("§cOcorreu ao salvar os dados na Tabela Erro: §e" + e);
				}		
			}
		}); 	
    }
}
