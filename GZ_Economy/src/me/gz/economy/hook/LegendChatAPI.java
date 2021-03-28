package me.gz.economy.hook;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import me.gz.economy.main;
import me.gz.economy.managers.TopManager;

public class LegendChatAPI implements Listener {

	@EventHandler
	private void onChat(ChatMessageEvent e) {
		OfflinePlayer p = e.getSender();

		if (e.getTags().contains("magnata") && new TopManager().isTop(p)) {
			e.setTagValue("magnata", main.CFG.getString("Magnata").replace("&", "§"));
		}
	}

}
