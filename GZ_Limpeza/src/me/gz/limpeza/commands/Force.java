package me.gz.limpeza.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.gz.limpeza.main;
import me.gz.limpeza.runnables.Limpar;

public class Force implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		
		if(!sender.hasPermission("gz.limpeza.use")) {
			sender.sendMessage(main.CFG.getString("Gerais.SemPerm").replace("&", "§"));		
			return false;
		}	
		
		if(Limpar.i <= 10) {
			sender.sendMessage(main.CFG.getString("Gerais.Force.UltimosSegundos").replace("&", "§"));
			return false;
		}
		
		sender.sendMessage(main.CFG.getString("Gerais.Force.Sender").replace("&", "§"));
		Bukkit.broadcastMessage(main.CFG.getString("Gerais.Force.Players").replace("&", "§"));
		Limpar.i = 5;		
		return false;
	}

}
