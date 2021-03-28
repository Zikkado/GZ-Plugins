package me.gz.limpeza;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

public class ActionBar {
	
	public static void onSend(Player p, String msg) {
		if(Bukkit.getPluginManager().getPlugin("ActionBarAPI") == null)
			p.sendMessage(msg.replace("&", "§"));
		else
			ActionBarAPI.sendActionBar(p, msg.replace("&", "§"));
	}
	

}
