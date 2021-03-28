package me.gz.economy.hook;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;

import me.gz.economy.main;
import me.gz.economy.managers.TopManager;
import me.gz.economy.utils.FormatType;

public class MVdWPlaceholderAPI {
	
	public MVdWPlaceholderAPI() {
		
		if(Bukkit.getPluginManager().getPlugin("MVdWPlaceholderAPI") != null) 
		{

			Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§aHook Com o MVdWPlaceholderAPI Efetuado!");
			
			PlaceholderAPI.registerPlaceholder(main.pl, "gzeconomy_money", new PlaceholderReplacer() {
				
				@Override
				public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
						OfflinePlayer p = e.getPlayer();
						
						
					return FormatType.Formatar(main.FormatType.get(p), main.bankLocal.get(p));
				}
			});
			
			
			
			
			PlaceholderAPI.registerPlaceholder(main.pl, "gzeconomy_magnata", new PlaceholderReplacer() {
				
				@Override
				public String onPlaceholderReplace(PlaceholderReplaceEvent e) {
	
					return new TopManager().getTopPlayer().get(0).getName();
				}
			});
			return;
		}
		
		
		Bukkit.getConsoleSender().sendMessage("§cVocê não Possui o MVdWPlaceholderAPI! Hook não Efetuada!");
	}
		
}
