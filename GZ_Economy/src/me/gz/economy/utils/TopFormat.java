package me.gz.economy.utils;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.gz.economy.main;
import me.gz.economy.commands.PlayerAPI;
import me.gz.economy.managers.GUIManager;
import me.gz.economy.managers.TopManager;



public class TopFormat {
	
	public static enum FormatTops {
		GUI,
		CHAT;
	}
	
	public static void Formatar(FormatTops Type, Player p)
	{
	
		switch (Type) 
		{
		case GUI: GUI2(p); break;
		
		case CHAT: PlayerAPI.Top(p);  break;
		
		default: break;
		}
	}
	
	private static void GUI2(Player p) {
		

		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				Inventory inv = Bukkit.createInventory(null, 3 * 9, "TOP 5 MAIS RICOS");
				TopManager top = new TopManager();
				int gui = 11;
				int pos = 1;
				for(int i = 0; i < top.getTopPlayer().size(); i++) {
					inv.setItem(gui, GUIManager.CreateSkull(p, i, top.getPlayerByPosition(i).getName(), main.GUI.getStringList("Configs.MoneyTop.Lore")));
					gui++;
					pos++;
				}
				
				GUIManager.FillArroundRandom(Material.STAINED_GLASS_PANE, "§6§kkkkkkkk", inv, false);
				p.openInventory(inv);
			}
		});
		
		
		
	}
	
}
