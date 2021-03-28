package me.gz.economy.sql;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import me.gz.economy.main;

public class MySQL {

		private static String host;
	    private static int port;
	    private static String username;
	    private static String password;
	    private static String database;
	    private static int query;
	    
	    
	    public MySQL(String host, int port, String username, String password, String database) {
	    	MySQL.host = host;
	    	MySQL.port = port;
	    	MySQL.username = username;
	    	MySQL.password = password;
	    	MySQL.database = database;
	    	MySQL.query = 0;
	    }
	    
	    
	    public static void openConnection() {
	    	Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] §aConectando-se ao MySQL");
	        try {
	            query++;
	            if ((SQL.conn != null) && (!SQL.conn.isClosed()))
	                return;

	            Class.forName("com.mysql.jdbc.Driver");
	            SQL.conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
	            Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] §aConectado ao MySQL");
	            
	        } catch (ClassNotFoundException | SQLException e) {
	            query--;
	            
	            Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§cOcorreu um erro ao tentar conectar-se ao MySQL! Altarando para SQLITE");
	            Bukkit.getConsoleSender().sendMessage("§4Erro: §2" + e);
	            SQLITE.openConnection();
				SQL.CreateTable("gz_bank", "UUID TEXT, NICKNAME TEXT, FORMAT TEXT, BANK DOUBLE");
	        }
	    }
	 
	    public static void closeConnection() {
	        query--;
	        if (query <= 0) {
	            try {
	                if (SQL.conn != null && !SQL.conn.isClosed())
	                	SQL.conn.close();
	            } catch (Exception e) {
	                Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§cHouve um Erro ao Fechar a conexão!");
	            }
	        }
	    }
	    
	  
}
