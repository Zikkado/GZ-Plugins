package me.gz.economy.sql;

import java.io.File;
import java.sql.DriverManager;

import org.bukkit.Bukkit;

import me.gz.economy.main;

public class SQLITE {


	public static void openConnection() {

		Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] §aConectando-se ao SQLite");

		try 
		{
			if ((SQL.conn != null) && (!SQL.conn.isClosed()))
				return;

			Class.forName("org.sqlite.JDBC");
			SQL.conn = DriverManager.getConnection("jdbc:sqlite:" + new File("plugins/GZ_Economy", "database.db"));
			Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] §aConectado ao SQLite");
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao tentar conectar-se ao SQLite! Verifique e Tente Novamente!");
			Bukkit.shutdown();
		}
	}

}
