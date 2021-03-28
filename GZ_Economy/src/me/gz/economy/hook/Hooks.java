package me.gz.economy.hook;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

import me.gz.economy.main;

public class Hooks implements Listener {
	
	@EventHandler
	private void onHookMVdWPlaceholderAPI(PluginEnableEvent e) {
		if(e.getPlugin().getName().equals("MVdWPlaceholderAPI")) {
			new MVdWPlaceholderAPI();
		}
	}
	
	@EventHandler
	private void onHookVault(PluginEnableEvent e) {
		if(e.getPlugin().getName().equals("Vault")) {
			VaultAPI.VaultHook();
		}
	}

	@EventHandler
	private void onHookLegendChat(PluginEnableEvent e) {
		if(e.getPlugin().getName().equals("Legendchat")) {
			Bukkit.getPluginManager().registerEvents(new LegendChatAPI(), main.pl);
			Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§aHook Com o LegendChat Efetuado!");
		}
	}
	
	@EventHandler
	private void onHooknChat(PluginEnableEvent e) {
		if(e.getPlugin().getName().equals("nChat")) {
			Bukkit.getPluginManager().registerEvents(new nChat(), main.pl);
			Bukkit.getConsoleSender().sendMessage("§f[§6" + main.pl.getName() + "§f] " + "§aHook Com o nChat Efetuado!");
		}
	}
}
