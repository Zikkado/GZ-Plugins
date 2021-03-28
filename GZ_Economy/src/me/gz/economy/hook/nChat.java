package me.gz.economy.hook;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.nickuc.chat.api.events.PublicMessageEvent;

import me.gz.economy.main;
import me.gz.economy.managers.TopManager;

public class nChat implements Listener {
	
	@EventHandler
	public static void onnChat(PublicMessageEvent e) {
		OfflinePlayer p = e.getSender();
		if (new TopManager().isTop(p)) {
			e.setTag("magnata", main.CFG.getString("Magnata").replace("&", "§"));
		}
	}

}
