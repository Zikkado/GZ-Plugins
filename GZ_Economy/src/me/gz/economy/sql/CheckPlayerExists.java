package me.gz.economy.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class CheckPlayerExists {

	public static Boolean CheckPlayer(OfflinePlayer p)
	{
		try {
			PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank WHERE Nickname = ?");
			stmt.setString(1, p.getName());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	public static Boolean CheckPlayer(String name)
	{
		
		if(Bukkit.getPlayerExact(name) != null)
			return true;
		
		try {
			PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank WHERE Nickname = ?");
			stmt.setString(1, name);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return true;
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			return false;
		}
		return false;
	}
	
}
