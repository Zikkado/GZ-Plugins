package me.gz.economy.managers;

import java.sql.PreparedStatement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.gz.economy.main;
import me.gz.economy.sql.DinheiroSQL;
import me.gz.economy.sql.SQL;


public class SaveManager {
	
	public SaveManager() {
		
	}

	public void onAutoSave()
	{
		
		Bukkit.getScheduler().runTaskTimerAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				try {
					
					if(Bukkit.getOnlinePlayers().isEmpty())
						return;
					
					
				    for(Player p : Bukkit.getOnlinePlayers()) {
					
						PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
						stmt.setDouble(1, main.bankLocal.get(p));
						stmt.setString(2, p.getName());
						stmt.execute();
						stmt.close();
				    }
				}
				catch (Exception e) {}
				new TopManager().updateTops();
			}
		}, 0, 20 * 10);
		
	}
	
	public void onSave() {
		Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§aSalvando Informações para o Banco de Dados");
		try {	
			for(Player p : Bukkit.getOnlinePlayers()) {
				
				if(p == null)
					return;
				
				DinheiroSQL.UpdateSQL(p, main.bankLocal.get(p));
			}
			
		} catch (Exception e) {}
	}
}
