package me.gz.economy.api;

import java.sql.PreparedStatement;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.gz.economy.main;
import me.gz.economy.functions.DepositAPI;
import me.gz.economy.sql.SQL;
import me.gz.economy.utils.FormatType.TypeFormat;
import me.gz.economy.utils.TopFormat.FormatTops;

public class BankManagerAPI {
	
	private OfflinePlayer player;
	
	public BankManagerAPI() {
	}
	
	public void addBankAmount(OfflinePlayer player, double value) {
		DepositAPI.Depositar(this.player, value);
	}
	
	public void setTopFormat(OfflinePlayer player, FormatTops type) {
		UpdateTopFormat(player.getName(), type.toString());
	}
	
	public void setMoneyFormat(OfflinePlayer player, TypeFormat type) {
		UpdateType(player.getName(), type.toString());
	}
	
	private void UpdateTopFormat(String p, String format)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				try {
					PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET TopFormat = ? WHERE Nickname = ?");
					stmt.setString(1, format);
					stmt.setString(2, p);
					stmt.execute();
					stmt.close();
				}
				catch (Exception e) {}	
			}
		});
	}
	
	
	private void UpdateType(String p, String format)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				try
				{
					PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET MoneyFormat = ? WHERE Nickname = ?");
					stmt.setString(1, format);
					stmt.setString(2, p);
					stmt.execute();
					stmt.close();
				}
				catch (Exception e) {
					
				}
				
			}
		});
	}

}
