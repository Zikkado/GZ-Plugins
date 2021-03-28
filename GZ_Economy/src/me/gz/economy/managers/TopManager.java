package me.gz.economy.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.gz.economy.main;
import me.gz.economy.sql.SQL;

public class TopManager {

	private List<OfflinePlayer> topsList = new ArrayList<>();
	private List<Double> topListMoney = new ArrayList<>();
	

	public TopManager() {

	}

	@SuppressWarnings("deprecation")
	public List<OfflinePlayer> getUpdatedTops() {
		List<OfflinePlayer> tops = new ArrayList<>();
		tops.clear();

		try {
			PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank ORDER BY Valor DESC LIMIT 5");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				tops.add(Bukkit.getOfflinePlayer(rs.getString("Nickname")));
			}
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tops;
	}

	@SuppressWarnings("deprecation")
	public List<OfflinePlayer> getTopPlayer() {

		if (this.topsList.isEmpty())
		{
			try {
				PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank ORDER BY Valor DESC LIMIT 5");
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					this.topsList.add(Bukkit.getOfflinePlayer(rs.getString("Nickname")));
					this.topListMoney.add(rs.getDouble("Valor"));
				}
				stmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.topsList;
	}

	@SuppressWarnings("deprecation")
	public void updateTops() {
		
		topsList.clear();
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				topsList.clear();

				try {
					PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank ORDER BY Valor DESC LIMIT 5");
					ResultSet rs = stmt.executeQuery();
					while (rs.next()) {
						topsList.add(Bukkit.getOfflinePlayer(rs.getString("Nickname")));
						topListMoney.add(rs.getDouble("Valor"));
					}
					stmt.close();
					
				} catch (Exception e) {
					
				}
			}
		});
		
	}

	public String tagByPlayer(OfflinePlayer p) {
		return isTop(p) ? main.CFG.getString("Magnata").replace("&", "§") : "";
	}

	public Boolean isTop(OfflinePlayer p) {
		if (getTopPlayer().get(0) == p)
			return true;
		return false;
	}
	
	public Double getMoneyByPosition(int i) {
		return topListMoney.get(i);
	}

	public OfflinePlayer getPlayerByPosition(int i) {
		return getTopPlayer().get(i);
	}

}
