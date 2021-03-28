package me.gz.economy.sql;

import java.sql.Connection;
import java.sql.Statement;

import org.bukkit.Bukkit;

public class SQL {

	public static Connection conn = null;

	public static void CreateTable(String table, String value) {
		try {
			if ((conn != null) && (!conn.isClosed())) {
				Statement state = conn.createStatement();
				state.executeUpdate("CREATE TABLE IF NOT EXISTS " + table + " (" + value + ")");
			}
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cOcorreu um erro ao Criar a Tabela: §e" + e);
		}
	}
	
	public static void closeConnection() {
		try {
			if (SQL.conn != null && !SQL.conn.isClosed())
				SQL.conn.close();
		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("§cHouve um Erro ao Fechar a conexão!");
		}
	}

}
