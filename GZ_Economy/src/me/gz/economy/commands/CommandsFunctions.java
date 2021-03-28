package me.gz.economy.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.gz.economy.main;
import me.gz.economy.api.events.MoneyAddEvent;
import me.gz.economy.api.events.MoneyRemoveEvent;
import me.gz.economy.api.events.PlayerPayEvent;
import me.gz.economy.functions.WithDrawnAPI;

public class CommandsFunctions {

	public static enum FType {
		setMoney,
		addMoney,
		removeMoney,
		payMoney;
	}

	
	@SuppressWarnings("deprecation")
	public static void Types(FType type, Player p, String[] args)
	{
		switch (type) {
		case setMoney:
		{
			if(!hasPerm(p, "gz.economy.set")) {
				return;
			}
			
			if(args.length < 3 || args.length > 3) {
				p.sendMessage("§cUse /money set <player> <amount - Para Setar Um Valor ao Jogador");	
				return;
			}
			
			if(!StringUtils.isNumeric(args[2])) {
				p.sendMessage("§cIsto não é um Valor Válido!");
				return;
			}
			
			PlayerAPI.Set(p, args[1], Double.parseDouble(args[2]));
			return;
		}
		
		case addMoney:
		{
			
			if(!hasPerm(p, "gz.economy.add")) {
				return;
			}
			
			if(args.length < 3 || args.length > 3)
			{
				p.sendMessage("§cUse /money <add:adicionar> - Para Adicionar Dinheiro ao Jogador!");
				return;
			}
			
			if(!StringUtils.isNumeric(args[2])) {
				p.sendMessage("Isto não é um Valor Válido!");
				return;
			}
			
			MoneyAddEvent event = new MoneyAddEvent(p, Bukkit.getOfflinePlayer(args[1]), Double.parseDouble(args[2]));
			Bukkit.getPluginManager().callEvent(event);
			
			if(event.isCancelled()) {
				return;
			}
			
			PlayerAPI.Add(p, args[1], Double.parseDouble(args[2]));
			return;
		}
		
		case payMoney:
		{
			if(!hasPerm(p, "gz.economy.pay")) {
				return;
			}
			
			if(args.length < 3 || args.length > 3) {
				p.sendMessage("§cUse /money pay <player> <amount> - Para Enviar Dinheiro a um Jogador o Banco do Jogador!");
				return;
			}
			
			if(!StringUtils.isNumeric(args[2])) {
				p.sendMessage("§cIsto não é um Valor Válido!");
				return;
			}
			
			
			if(main.bankLocal.get(p) < Double.parseDouble(args[2]) || main.bankLocal.get(p) <= 0) {
				p.sendMessage("§cVocê não tem Dinheiro Suficiente!");
				
				return;
			}
			
			if(Bukkit.getOfflinePlayer(args[1]) == p) {
				p.sendMessage("§cVocê não Pode Enviar Dinheiro para Você Mesmo!");
				return;
			}
			
			PlayerPayEvent eventHandler = new PlayerPayEvent(p, Bukkit.getOfflinePlayer(args[1]), Double.parseDouble(args[2]));	
			Bukkit.getPluginManager().callEvent(eventHandler);
			if(eventHandler.isCancelled()) {
				return;
			}
			
			PlayerAPI.pay(p, args[1], Double.parseDouble(args[2]));	
			return;
		}
		
		case removeMoney:
		{
			if(!hasPerm(p, "gz.economy.remove")) {
				return;
			}
			
			if(args.length < 3 || args.length > 3) {
				p.sendMessage("§cUse /money remove <player> <amount> - Para Remover Dinheiro do Banco do Jogador!");
				return;
			}
			
			if(!StringUtils.isNumeric(args[2])) {
				p.sendMessage("§cIsto não é um Valor Válido!");
				return;
			}
			
			MoneyRemoveEvent event = new MoneyRemoveEvent(p, Bukkit.getOfflinePlayer(args[1]), Double.parseDouble(args[2]));
			Bukkit.getPluginManager().callEvent(event);

			if(event.isCancelled())
				return;
			
			WithDrawnAPI.Sacar(args[1], Double.parseDouble(args[2]));
			return;
		}
		
		
		default:
			break;
		}
	
	}

	
	public static void Types(FType type, CommandSender p, String[] args)
	{
		switch (type) {
		case setMoney:
		{
			
			if(args.length < 3 || args.length > 3) {
				p.sendMessage("§cUse /money set <player> <amount - Para Setar Um Valor ao Jogador");	
				return;
			}
			
			if(!StringUtils.isNumeric(args[2])) {
				p.sendMessage("§cIsto não é um Valor Válido!");
				return;
			}
			
			ConsoleAPI.Set(p, args[1], Double.parseDouble(args[2]));
			return;
		}
		
		case addMoney:
		{
			if(args.length < 3 || args.length > 3)
			{
				p.sendMessage("§cUse /money <add:adicionar> - Para Adicionar Dinheiro ao Jogador!");
				return;
			}
			
			if(!StringUtils.isNumeric(args[2])) {
				p.sendMessage("Isto não é um Valor Válido!");
				return;
			}
			
			ConsoleAPI.Add(p, args[1], Double.parseDouble(args[2]));
			return;
		}
		
		case removeMoney:
		{
			
			if(args.length < 3 || args.length > 3) {
				p.sendMessage("§cUse /money remove <player> <amount> - Para Remover Dinheiro do Banco do Jogador!");
				return;
			}
			
			if(!StringUtils.isNumeric(args[2])) {
				p.sendMessage("§cIsto não é um Valor Válido!");
				return;
			}

			ConsoleAPI.Remove(p, args[1], Double.parseDouble(args[2]));
			
			return;
		}
		
		default:
			break;
		}
	
	}
	
	private static boolean hasPerm(Player p, String perms) {
		if(!p.hasPermission(perms))
		{
			p.sendMessage("§cVocé não Possui Permissão para Usar Este Comando!");
			return false;
		}
		return true;	
	}
}
