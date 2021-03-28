package me.gz.economy.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.gz.economy.main;
import me.gz.economy.commands.CommandsFunctions.FType;
import me.gz.economy.managers.GUIManager;
import me.gz.economy.sql.CheckPlayerExists;
import me.gz.economy.sql.DinheiroSQL;
import me.gz.economy.utils.FormatType;
import me.gz.economy.utils.FormatType.TypeFormat;
import me.gz.economy.utils.TopFormat;


public class MoneyCommand implements CommandExecutor {


	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
			
			if(sender instanceof Player)
			{
				Player p = (Player) sender;
				
				if(args.length == 0) {
					sender.sendMessage(main.CFG.getString("Bank").replace("{amount}", FormatType.Formatar(main.FormatType.get(p), main.bankLocal.get(p))).replace("&", "§"));
					return false;
				}
				
				if(args[0].equalsIgnoreCase("format") || args[0].equalsIgnoreCase("formatar"))
				{
					if(!hasPerm(p, "gz.economy.format"))
						return false;
					
					CreateGuiInicial(p);
					return false;
				}
				// /money arg0 arg2 arg3
				
				//Top Money
				if(args[0].equalsIgnoreCase("top")) {
					
					if(!hasPerm(p, "gz.economy.top"))
						return false;
					
					TopFormat.Formatar(main.TopType.get(p), p);
					return false;
				}
				
				//Pagar
				if(args[0].equalsIgnoreCase("pagar") || args[0].equalsIgnoreCase("pay") || args[0].equalsIgnoreCase("send") || args[0].equalsIgnoreCase("enviar"))
				{
					if(!hasPerm(p, "gz.economy.pay"))
						return false;
					
					if(args.length < 3) {
						 p.sendMessage("§cUse /money <pay> <nick> <valor> -> Modifica o Valor Bancario!");
						 return false;
					}
					CommandsFunctions.Types(FType.payMoney, p, args);
					return false;
				}
			
				
				if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("remover"))
				{
					if(!hasPerm(p, "gz.economy.remove"))
						return false;
					
					if(args.length < 3) {
						p.sendMessage("§cUse /money <remove> <nick> <valor> -> Remove Dinheiro");
						 return false;
					}
					CommandsFunctions.Types(FType.removeMoney, p, args);
					return false;
				}
				
				//Setar
				if(args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("definir") || args[0].equalsIgnoreCase("setar"))
				{
					
					if(!hasPerm(p, "gz.economy.set"))
						return false;
					
					if(args.length < 3) {
						 p.sendMessage("§cUse /money <set> <nick> <valor> -> Modifica o Valor Bancario!");
						 return false;
					}
					CommandsFunctions.Types(FType.setMoney, p, args);
					return false;
				}
				
				//Adicionar
				if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("adicionar"))
				{
					
					if(!hasPerm(p, "gz.economy.add"))
						return false;
					
					if(args.length < 3) {
						 p.sendMessage("§cUse /money <add> <nick> <valor> -> Adiciona Dinheiro");
						 return false;
					}
					CommandsFunctions.Types(FType.addMoney, p, args);
					return false;
				}
				if(args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("ajuda"))
				{
					if(!hasPerm(p, "gz.economy.help"))
						return false;
					
				    p.sendMessage("§cUse /money <set> <nick> <valor> -> Modifica o Valor Bancario!");
				    p.sendMessage("§cUse /money <add> <nick> <valor> -> Adiciona Dinheiro");
				    p.sendMessage("§cUse /money <remove> <nick> <valor> -> Remove Dinheiro");
				    p.sendMessage("§cUse /money <format> -> Muda o Formato do Money e Money Top");
				    p.sendMessage("§cUse /money <top> -> Mostra o Top Money");
					return false;
				}
				
				if(args.length == 1) {
					
				   if(!CheckPlayerExists.CheckPlayer(args[0])) {
					   sender.sendMessage("§cEste Jogador não Possui Conta bancaria");
					   return false;
					}
				   sender.sendMessage(main.CFG.getString("BankOthers").replace("{amount}", FormatType.Formatar(main.FormatType.get(p), DinheiroSQL.ValorEmBancoDeDados(args[0]))).replace("{target}", args[0]).replace("&", "§"));
				   return false;
				}
				
				sender.sendMessage("§cUse /money <nick> -> Mostra o Banco de Um Jogador");
				
				return false;
	}
			
			//Console
			
			if(args.length < 1 || args.length > 3) {
				sender.sendMessage("§cUse /money <set> <nick> <valor> -> Modifica o Valor Bancario!");
				sender.sendMessage("§cUse /money <add> <nick> <valor> -> Adiciona Dinheiro");
				sender.sendMessage("§cUse /money <remove> <nick> <valor> -> Remove Dinheiro");	
			    sender.sendMessage("§cUse /money <top> -> Mostra o Top Money");
				return false;
			}
			
			//Top Money
			if(args[0].equalsIgnoreCase("top")) {
				ConsoleAPI.Top(sender);
				return false;
			}
			
			if(args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("definir") || args[0].equalsIgnoreCase("setar")) {
				CommandsFunctions.Types(FType.setMoney, sender, args);
				
				return false;
			}
			
			if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("remover")) {
				CommandsFunctions.Types(FType.removeMoney, sender, args);
				return false;
			}
			
			if(args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("adicionar")) {
				CommandsFunctions.Types(FType.addMoney, sender, args);
				return false;
			}
			
			if(!CheckPlayerExists.CheckPlayer(args[0])) {
				sender.sendMessage("§cEste Jogador não Possui Conta bancaria");
				return false;
			}
			
			sender.sendMessage("§aBanco de §e" + args[0] + " §aValor: §e" + FormatType.Formatar(TypeFormat.Numerica, DinheiroSQL.ValorEmBancoDeDados(Bukkit.getOfflinePlayer(args[0]))));
			
		
		return false;
	}
	
	private boolean hasPerm(Player p, String perms)
	{
		if(!p.hasPermission(perms))
		{
			p.sendMessage("§cVocé não Possui Permissão para Usar Este Comando!");
			return false;
		}
		return true;	
	}
	
	private void CreateGuiInicial(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "Preferencias de Formatação");
		
        Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
	
	        @Override
	        public void run() {
	        	inv.setItem(12, GUIManager.CreateItem(Material.DIAMOND, 0, "§aFormatação do Money", true));

	        	inv.setItem(14, GUIManager.CreateItem(Material.ITEM_FRAME, 0, "§aFormatação do Money Top"));
	        			
	        	GUIManager.FillArround(Material.valueOf(main.GUI.getString("Configs.FillArround.Item")), main.GUI.getInt("Configs.FillArround.Data"), main.GUI.getString("Configs.FillArround.Name").replace("&", "§"), inv, main.GUI.getBoolean("Configs.FillArround.Enchant"));
	        			
	        	p.openInventory(inv);
		    }
	    });
		
		
	}
}
