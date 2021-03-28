package me.gz.economy.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.gz.economy.main;
import me.gz.economy.sql.CheckPlayerExists;
import me.gz.economy.sql.DinheiroSQL;
import me.gz.economy.sql.SQL;
import me.gz.economy.utils.FormatType;
import me.gz.economy.utils.FormatType.TypeFormat;

public class ConsoleAPI {
	
	@SuppressWarnings("deprecation")
	public static void Set(CommandSender p, String target, double valor)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				
				if(!CheckPlayerExists.CheckPlayer(target)) {
					p.sendMessage("§cEste Jogador Não Possui Conta Bancaria!");
					return;
				}
				
				if(Bukkit.getPlayerExact(target) == null)
				{
					
					try {
						PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
						stmt.setDouble(1, valor);
						stmt.setString(2, target);
						
						stmt.execute();
						stmt.close();
						p.sendMessage(main.CFG.getString("Admin.Add").replace("{amount}", FormatType.Formatar(TypeFormat.OP, valor)).replace("&", "§"));
					}
					catch (Exception e) {
						p.sendMessage("§cOcorreu um Erro Pessa Ajuda Para Alguem!");
					}
					return;
				}
				
				main.bankLocal.put(Bukkit.getPlayerExact(target), valor);
				
				Bukkit.getPlayerExact(target).sendMessage(main.CFG.getString("User.Set").replace("{amount}", FormatType.Formatar(main.FormatType.get(Bukkit.getOfflinePlayer(target)), valor)).replace("&", "§"));
				p.sendMessage(main.CFG.getString("Admin.Set").replace("{amount}", FormatType.Formatar(TypeFormat.OP, valor)).replace("{target}", target).replace("&", "§"));
				
			}
		});		
	}
	
	@SuppressWarnings("deprecation")
	public static void Add(CommandSender p, String target, double valor)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
		
			@Override
			public void run() {
				
				if(!CheckPlayerExists.CheckPlayer(target)) {
					p.sendMessage("§cEste Jogador Não Possui Conta Bancaria!");
					return;
				}
				
				if(Bukkit.getPlayerExact(target) == null)
				{
					
					
					try {
						PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
						stmt.setDouble(1, DinheiroSQL.ValorEmBancoDeDados(Bukkit.getOfflinePlayer(target)) + valor);
						stmt.setString(2, target);
						
						stmt.execute();
						stmt.close();
						p.sendMessage(main.CFG.getString("Admin.Add").replace("{amount}", FormatType.Formatar(TypeFormat.OP, valor)).replace("&", "§"));
					}
					catch (Exception e) {
						p.sendMessage("§cOcorreu um Erro Pessa Ajuda Para Alguem!");
					}
					return;
				}
				
				main.bankLocal.put(Bukkit.getPlayerExact(target), main.bankLocal.get(Bukkit.getPlayerExact(target)) + valor);
				
				Bukkit.getPlayerExact(target).sendMessage(main.CFG.getString("User.Add").replace("{amount}", FormatType.Formatar(main.FormatType.get(Bukkit.getOfflinePlayer(target)), valor)).replace("&", "§"));
				p.sendMessage(main.CFG.getString("Admin.Add").replace("{amount}", FormatType.Formatar(TypeFormat.OP, valor)).replace("{target}", target).replace("&", "§"));
				
			}
		});		
    }
	
	public static void Remove(CommandSender p, String target, double valor)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				if(!CheckPlayerExists.CheckPlayer(target)) {
					p.sendMessage("§cEste Jogador Não Possui Conta Bancaria!");
					return;
				}
				
				if(Bukkit.getPlayerExact(target) == null)
				{
					if(DinheiroSQL.ValorEmBancoDeDados(target) <= valor) {
						p.sendMessage("§cA Conta não pode Fica Negativa");
						return;
					}
					
					try {
						PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
						stmt.setDouble(1, DinheiroSQL.ValorEmBancoDeDados(target) - valor);
						stmt.setString(2, target);
						
						stmt.execute();
						stmt.close();
						p.sendMessage(main.CFG.getString("Admin.Remove").replace("{amount}", FormatType.Formatar(TypeFormat.OP, valor)).replace("&", "§"));
					}
					catch (Exception e) {
						p.sendMessage("§cOcorreu um Erro Pessa Ajuda Para Alguem!");
					}
					return;
				}
				
				if(main.bankLocal.get(Bukkit.getPlayerExact(target)) <= valor) {
					p.sendMessage("§cA Conta não pode Fica Negativa");
					return;
				}
				
				main.bankLocal.put(Bukkit.getPlayerExact(target), main.bankLocal.get(Bukkit.getPlayerExact(target)) - valor);
				
				Bukkit.getPlayerExact(target).sendMessage(main.CFG.getString("User.Remove").replace("{amount}", FormatType.Formatar(main.FormatType.get(Bukkit.getOfflinePlayer(target)), valor)).replace("&", "§"));
				p.sendMessage(main.CFG.getString("Admin.Remove").replace("{amount}", FormatType.Formatar(TypeFormat.OP, valor)).replace("{target}", target).replace("&", "§"));
				
			}
		});		
	}
	
	public static void Top(CommandSender p) {
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				
				try {
					int i = 1;
					
					PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank ORDER BY Valor DESC LIMIT 3");
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						
						if(i == 1) {
							p.sendMessage(main.CFG.getString("User.Tops.Formato").replace("{position}", i + "°").replace("{player}", rs.getString("Nickname")).replace("{amount}", FormatType.Formatar(TypeFormat.Numerica, rs.getDouble("Valor"))).replace("{magnata}", main.CFG.getString("Magnata")).replace("&", "§"));
						}
						
						p.sendMessage(main.CFG.getString("User.Tops.Formato").replace("&", "§").replace("{position}", i + "°").replace("{player}", rs.getString("Nickname")).replace("{amount}", FormatType.Formatar(TypeFormat.Numerica, rs.getDouble("Valor"))).replace("{magnata}", ""));
						i++;
					}
					stmt.close();
					rs.close();
				} 
				catch (SQLException e) {}
			}
		});
	}
}
