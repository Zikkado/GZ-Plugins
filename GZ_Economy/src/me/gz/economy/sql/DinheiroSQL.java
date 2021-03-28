package me.gz.economy.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.OfflinePlayer;

public class DinheiroSQL {
	
     public static double ValorEmBancoDeDados(OfflinePlayer p) {
		
		try {
			PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank WHERE Nickname = ? LIMIT 1");
			stmt.setString(1, p.getName());
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getDouble("Valor");
			}
			rs.close();
			stmt.close();
		} 
		catch (SQLException e) {
			return 0;
		}
		return 0;
		
	}
     
     public static double ValorEmBancoDeDados(String p) {
 		
		try {
			PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank WHERE Nickname = ? LIMIT 1");
			stmt.setString(1, p);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getDouble("Valor");
			}
			rs.close();
			stmt.close();
			
		} 
		catch (SQLException e) {
			return 0;
		}
		return 0;
		
	}
     

     public static void UpdateSQL(OfflinePlayer p, double valor) {
    	 
    	 try {
    		 PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
    		 stmt.setDouble(1, valor);
    		 stmt.setString(2, p.getName());
    		 stmt.execute();
    		 
    	 } 
    	 catch (SQLException e) {
    		 
    	 }
  
     }
     
     
     

}
