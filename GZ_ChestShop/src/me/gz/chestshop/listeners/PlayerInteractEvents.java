package me.gz.chestshop.listeners;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import me.gz.chestshop.sql.BuySQL;

public class PlayerInteractEvents implements Listener {
	
	@EventHandler
	private void onInteract(PlayerInteractEvent e) {
		//Player p = e.getPlayer();
		
		if(e.getClickedBlock().getState() instanceof Sign)
		{
			Sign sign = (Sign) e.getClickedBlock().getState();
			BuySQL.Comprar(e.getPlayer(), sign.getLocation());
		}
		
	}

}
