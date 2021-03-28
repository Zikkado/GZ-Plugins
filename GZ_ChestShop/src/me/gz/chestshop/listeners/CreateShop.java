package me.gz.chestshop.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import me.gz.chestshop.sql.CreateShopSQL;

public class CreateShop implements Listener {

	@EventHandler
	private void onCreate(SignChangeEvent e) {
		Player p = e.getPlayer();
		
		if(e.getLine(0).equalsIgnoreCase("AdminShop"))
		{
			CreateShopSQL.CreateShop(e.getBlock().getLocation(), p.getInventory().getItem(4), "AdminShop", p.getItemInHand().getAmount());
		}
		
	}
	
	
}
