package me.gz.chestshop.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import me.gz.chestshop.main;

public class SQL {
	
	public static Connection conn = null;
	
	public SQL() {
		
	}
	
	public void openConnection() {
		if(main.CFG.getBoolean("MySQL.Enable")) {
			MySQL(main.CFG.getString("MySQL.Host"), 0, main.CFG.getString("MySQL.Username"), main.CFG.getString("MySQL.Password"), main.CFG.getString("MySQL.Database"));
			return;
		}
		SQLite();
		
	}
	
	public void MySQL(String host, int port, String username, String password, String database) {
		Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] §aConectando-se ao MySQL");
        try {
            if ((conn != null) && (!conn.isClosed()))
                return;

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
            Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] §aConectado ao MySQL");
            CreateTable("gz_chestshop", "Owner VARCHAR(16), Location OBJECT, ItemStack OBJECT, Quantidade INTEGER(64)");
            
        } catch (ClassNotFoundException | SQLException e) {
           
            Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§cOcorreu um erro ao tentar conectar-se ao MySQL! §eAltarando para SQLITE");
            SQLite();
        }
	}
	
	
	public void SQLite() {

		Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] §aConectando-se ao SQLite");

			try 
			{
				if ((conn != null) && (!conn.isClosed()))
					return;

				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:" + new File("plugins/GZ_ChestShop", "shops.db"));
				Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] §aConectado ao SQLite");
				CreateTable("gz_chestshop", "Owner TEXT, Location OBJECT, ItemStack OBJECT, Quantidade INTEGER(64)");
			} catch (Exception e) {
				Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao tentar conectar-se ao SQLite! Verifique e Tente Novamente!");
				Bukkit.shutdown();
			}
	}
	
	public void CreateTable(String table, String value) {
		try {
			if ((conn != null) && (!conn.isClosed())) {
				Statement state = conn.createStatement();
				state.executeUpdate("CREATE TABLE IF NOT EXISTS " + table + " (" + value + ")");
			}
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao Criar a Tabela: §e" + e);
		}
	}

}
