package me.gz.economy.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.entity.Player;

public class EnumSQL {
	
	public static String MoneyFormat(Player p) {
		
		try {
			PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank WHERE Nickname = ?");
			stmt.setString(1, p.getName());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString("MoneyFormat");
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
	
	public static String TopFormat(Player p) {
		
		try {
			PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank WHERE Nickname = ?");
			stmt.setString(1, p.getName());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString("TopFormat");
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			return null;
		}
		return null;
	}
	
}
