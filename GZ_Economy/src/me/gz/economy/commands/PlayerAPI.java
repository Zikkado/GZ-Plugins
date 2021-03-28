package me.gz.economy.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.gz.economy.main;
import me.gz.economy.sql.CheckPlayerExists;
import me.gz.economy.sql.DinheiroSQL;
import me.gz.economy.sql.SQL;
import me.gz.economy.utils.FormatType;
import me.gz.economy.utils.FormatType.TypeFormat;

public class PlayerAPI {
	public static String magnata = "";
	
	@SuppressWarnings("deprecation")
	public static void pay(Player p, String target, double valor)
	{

		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				if(Bukkit.getPlayerExact(target) == null) {
					
					if(!CheckPlayerExists.CheckPlayer(p)) {
						p.sendMessage("§cEste Jogador Não Possui Conta Bancaria!");
						return;
					}
					
					
					try
					{
						PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
						stmt.setDouble(1, DinheiroSQL.ValorEmBancoDeDados(Bukkit.getOfflinePlayer(target)) + valor);
						stmt.setString(2, target);
						
						stmt.execute();
						stmt.close();
						main.bankLocal.put(p, main.bankLocal.get(p) - valor);
						p.sendMessage(main.CFG.getString("Admin.Pay").replace("{amount}", FormatType.Formatar(main.FormatType.get(p), valor)).replace("{target}", target));
						
					}
					catch (Exception e) {
						p.sendMessage("§cOcorreu um Erro Pessa Ajuda Para Alguem!");
						
					}
					return;
				}
				
				main.bankLocal.put(p, main.bankLocal.get(p) - valor);
				main.bankLocal.put(Bukkit.getPlayerExact(target), main.bankLocal.get(Bukkit.getPlayerExact(target)) + valor);
				
				
				Bukkit.getPlayerExact(target).sendMessage(main.CFG.getString("User.Pay").replace("{amount}", FormatType.Formatar(main.FormatType.get(Bukkit.getOfflinePlayer(target)), valor)).replace("{player}", p.getName()).replace("&", "§"));
				p.sendMessage(main.CFG.getString("Admin.Pay").replace("{amount}", FormatType.Formatar(main.FormatType.get(p), valor)).replace("{target}", target).replace("&", "§"));
				
			}
		});
			
			
		
	}
	
	@SuppressWarnings("deprecation")
	public static void Set(Player p, String target, double valor)
	{
		
		if(Bukkit.getPlayerExact(target) == null)
		{
			if(!CheckPlayerExists.CheckPlayer(p)) {
				p.sendMessage("§cEste Jogador Não Possui Conta Bancaria!");
				return;
			}
			
			try
			{
				PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
				stmt.setDouble(1, valor);
				stmt.setString(2, target);
				
				stmt.execute();
				stmt.close();
				p.sendMessage(main.CFG.getString("Admin.Set").replace("{amount}", FormatType.Formatar(main.FormatType.get(p), valor)).replace("{target}", target).replace("&", "§"));
			}
			catch (Exception e) {
				p.sendMessage("§cOcorreu um Erro Pessa Ajuda Para Alguem!");
			}
			return;
		}

		main.bankLocal.put(Bukkit.getPlayerExact(target), valor);
		
		Bukkit.getPlayerExact(target).sendMessage(main.CFG.getString("User.Set").replace("{amount}", FormatType.Formatar(main.FormatType.get(Bukkit.getOfflinePlayer(target)), valor)).replace("&", "§"));
		p.sendMessage(main.CFG.getString("Admin.Set").replace("{amount}", FormatType.Formatar(main.FormatType.get(p), valor)).replace("{target}", target).replace("&", "§"));
		
	}
	
	@SuppressWarnings("deprecation")
	public static void Add(Player p, String target, double valor)
	{
		if(Bukkit.getPlayerExact(target) == null) {
			
			if(!CheckPlayerExists.CheckPlayer(p)) {
				p.sendMessage("§cEste Jogador Não Possui Conta Bancaria!");
				return;
			}
			
			try
			{
				PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
				stmt.setDouble(1, DinheiroSQL.ValorEmBancoDeDados(Bukkit.getOfflinePlayer(target)) + valor);
				stmt.setString(2, target);
				
				stmt.execute();
				stmt.close();
				p.sendMessage(main.CFG.getString("Admin.Add").replace("{amount}", FormatType.Formatar(main.FormatType.get(p), valor)).replace("&", "§"));
			}
			catch (Exception e) {
				p.sendMessage("§cOcorreu um Erro Pessa Ajuda Para Alguem!");
			}
			return;
		}
		

		main.bankLocal.put(Bukkit.getPlayerExact(target), main.bankLocal.get(Bukkit.getOfflinePlayer(target)) + valor);
		
		Bukkit.getPlayerExact(target).sendMessage(main.CFG.getString("User.Add").replace("{amount}", FormatType.Formatar(main.FormatType.get(Bukkit.getOfflinePlayer(target)), valor)).replace("&", "§"));
		p.sendMessage(main.CFG.getString("Admin.Add").replace("{amount}", FormatType.Formatar(main.FormatType.get(p), valor)).replace("{target}", target).replace("&", "§"));
		
	}
	
	public static void Remove(Player p, String target, double valor)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				if(!CheckPlayerExists.CheckPlayer(target)) {
					p.sendMessage("§cEste Jogador Não Possui Conta Bancaria!");
					return;
				}
				
				if(Bukkit.getPlayerExact(target) == null) {
					
					try {
						PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET Valor = ? WHERE Nickname = ?");
						stmt.setDouble(1, DinheiroSQL.ValorEmBancoDeDados(target) - valor);
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
				
				main.bankLocal.put(Bukkit.getPlayerExact(target), main.bankLocal.get(Bukkit.getPlayerExact(target)) - valor);
				
				Bukkit.getPlayerExact(target).sendMessage(main.CFG.getString("User.Remove").replace("{amount}", FormatType.Formatar(main.FormatType.get(Bukkit.getOfflinePlayer(target)), valor)).replace("&", "§"));
				p.sendMessage(main.CFG.getString("Admin.Remove").replace("{amount}", FormatType.Formatar(TypeFormat.OP, valor)).replace("{target}", target).replace("&", "§"));
				
			}
		});		
	}
	
	public static void Top(Player p) {
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				
				try {
					int i = 1;
					
					PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_bank ORDER BY Valor DESC LIMIT 5");
					ResultSet rs = stmt.executeQuery();
					
					for (String a : main.CFG.getStringList("User.Tops.Titulo")) {
						p.sendMessage(a.replace("&", "§"));
					}
					
					while(rs.next()) {
						
						if(i == 1) {
							p.sendMessage(main.CFG.getString("User.Tops.Formato").replace("{position}", i + "°").replace("{player}", rs.getString("Nickname")).replace("{amount}", FormatType.Formatar(main.FormatType.get(p), rs.getDouble("Valor"))).replace("{magnata}", main.CFG.getString("Magnata")).replace("&", "§"));
						}
						else {
							p.sendMessage(main.CFG.getString("User.Tops.Formato").replace("{position}", i + "°").replace("{player}", rs.getString("Nickname")).replace("{amount}", FormatType.Formatar(main.FormatType.get(p), rs.getDouble("Valor"))).replace("{magnata}", "").replace("&", "§"));
						}
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
