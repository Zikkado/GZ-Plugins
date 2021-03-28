package me.gz.economy.listeners;

import java.sql.PreparedStatement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.gz.economy.main;
import me.gz.economy.sql.SQL;

public class Quit implements Listener {
	
	@EventHandler
	private void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Save(p);
	}
	
	private static void Save(Player p)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				try {
					PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
					stmt.setDouble(1, main.bankLocal.get(p));
					stmt.setString(2,p.getName());
					stmt.execute();
					
					main.bankLocal.remove(p);
					main.FormatType.remove(p);
					main.TopType.remove(p);
					
					stmt.close();
					
				} catch (Exception e2) {
					Bukkit.getConsoleSender().sendMessage("§4Ocorreu um Erro Inesperado! Erro: " + e2);
				}
			}
		});
	}
}
