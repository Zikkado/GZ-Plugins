package me.gz.economy.listeners;

import java.sql.PreparedStatement;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.gz.economy.main;
import me.gz.economy.managers.GUIManager;
import me.gz.economy.sql.SQL;
import me.gz.economy.utils.FormatType.TypeFormat;
import me.gz.economy.utils.TopFormat.FormatTops;

public class ClickInventory implements Listener {
	
	@EventHandler
	private void ClickGui(InventoryClickEvent e)
	{
		Player p = (Player) e.getWhoClicked();
		
		if(e.getCurrentItem() == null)
			return;
		
		if(e.getCurrentItem().getItemMeta() == null) {
			return;
		}
		
		if(e.getInventory() == null)
			return;

		if(e.getView().getTitle().equals("Preferencias de Formatação"))
		{
			if(e.getCurrentItem().getType() == Material.DIAMOND)
			{
				MoneyFormat(p);
				e.setCancelled(true);
				p.closeInventory();
			}
			
			if(e.getCurrentItem().getType() == Material.ITEM_FRAME)
			{
				TopFormat(p);
				e.setCancelled(true);
				p.closeInventory();
			}
			
			e.setCancelled(true);	
		}
			
			
			
		
	    if(e.getView().getTitle().equals("Preferencias do Money"))
	    {
	    	if(e.getCurrentItem().getType() == Material.PAPER)
			{
				
				UpdateType(p.getName(), "Numerica");
				
				main.FormatType.put(p, TypeFormat.Numerica);
				
				p.sendMessage("§aSua Formatação Foi Alterada Com Sucesso para Númerica!");
				
				e.setCancelled(true);
				p.closeInventory();
			}
			
			if(e.getCurrentItem().getType() == Material.BOOK_AND_QUILL)
			{
				
				UpdateType(p.getName(), "Formal");
				
				main.FormatType.put(p, TypeFormat.Formal);
				
				p.sendMessage("§aSua Formatação Foi Alterada Com Sucesso para Formal!");
				
				e.setCancelled(true);
				p.closeInventory();
			}
			
			if(e.getCurrentItem().getType() == Material.BOOK)
			{
				UpdateType(p.getName(), "OP");
				
				main.FormatType.put(p, TypeFormat.OP);
				
				p.sendMessage("§aSua Formatação Foi Alterada Com Sucesso OP!");
				e.setCancelled(true);
				p.closeInventory();
			}
			e.setCancelled(true);	
	    }
		
		if(e.getView().getTitle().equals("Preferencias do Money Top"))
		{
			if(e.getCurrentItem().getType() == Material.PAPER)
			{
				
				UpdateTopFormat(p.getName(), "CHAT");
				
				main.TopType.put(p, FormatTops.CHAT);
				
				p.sendMessage("§aSua Formatação Foi Alterada Com Sucesso para CHAT!");
				
				e.setCancelled(true);
				p.closeInventory();
			}
			
			if(e.getCurrentItem().getType() == Material.ITEM_FRAME)
			{
				
				UpdateTopFormat(p.getName(), "GUI");
				
				main.TopType.put(p, FormatTops.GUI);
				
				p.sendMessage("§aSua Formatação Foi Alterada Com Sucesso para GUI!");
				
				e.setCancelled(true);
				p.closeInventory();
			}
			
			
			e.setCancelled(true);	
		}
		 if(e.getView().getTitle().equals("TOP 5 MAIS RICOS"))
		   {
			 e.setCancelled(true);
		   }
		

	}
	
	private void MoneyFormat(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "Preferencias do Money");
		
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				if(main.FormatType.get(p) == TypeFormat.Numerica)
					inv.setItem(11, GUIManager.CreateItem(Material.PAPER, 0, "§aFormatação Numérica [Ativa]", true));
				else
					inv.setItem(11, GUIManager.CreateItem(Material.PAPER, 0, "§cFormatação Numérica"));
				
				if(main.FormatType.get(p) == TypeFormat.Formal)
					inv.setItem(13, GUIManager.CreateItem(Material.BOOK_AND_QUILL, 0, "§aFormatação Formal [Ativa]", true));
				else
					inv.setItem(13, GUIManager.CreateItem(Material.BOOK_AND_QUILL, 0, "§cFormatação Formal"));
				
				if(main.FormatType.get(p) == TypeFormat.OP)
					inv.setItem(15, GUIManager.CreateItem(Material.BOOK, 0, "§aFormatação OP [Ativa]", true));
				else
					inv.setItem(15, GUIManager.CreateItem(Material.BOOK, 0, "§cFormatação OP"));
				
				
				GUIManager.FillArround(Material.valueOf(main.GUI.getString("Configs.FillArround.Item")), main.GUI.getInt("Configs.FillArround.Data"), main.GUI.getString("Configs.FillArround.Name").replace("&", "§"), inv, main.GUI.getBoolean("Configs.FillArround.Enchant"));
				
				p.openInventory(inv);
			}
		});
		
		
	}
	private void TopFormat(Player p)
	{
		Inventory inv = Bukkit.createInventory(null, 9 * 3, "Preferencias do Money Top");
		
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				if(main.TopType.get(p) == FormatTops.CHAT)
					inv.setItem(12, GUIManager.CreateItem(Material.PAPER, 0, "§aFormatação CHAT [Ativa]", true));
				else
					inv.setItem(12, GUIManager.CreateItem(Material.PAPER, 0, "§cFormatação CHAT"));
				
				if(main.TopType.get(p) == FormatTops.GUI)
					inv.setItem(14, GUIManager.CreateItem(Material.ITEM_FRAME, 0, "§aFormatação GUI [Ativa]", true));
				else
					inv.setItem(14, GUIManager.CreateItem(Material.ITEM_FRAME, 0, "§cFormatação GUI"));
				
				GUIManager.FillArround(Material.valueOf(main.GUI.getString("Configs.FillArround.Item")), main.GUI.getInt("Configs.FillArround.Data"), main.GUI.getString("Configs.FillArround.Name").replace("&", "§"), inv, main.GUI.getBoolean("Configs.FillArround.Enchant"));
				
				p.openInventory(inv);
			}
		});	
	}
	
	private void UpdateTopFormat(String p, String format)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				try {
					PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET TopFormat = ? WHERE Nickname = ?");
					stmt.setString(1, format);
					stmt.setString(2, p);
					stmt.execute();
					stmt.close();
				}
				catch (Exception e) {}	
			}
		});
	}
	
	
	private void UpdateType(String p, String format)
	{
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				try
				{
					PreparedStatement stmt = SQL.conn.prepareStatement("UPDATE gz_bank SET MoneyFormat = ? WHERE Nickname = ?");
					stmt.setString(1, format);
					stmt.setString(2, p);
					stmt.execute();
					stmt.close();
				}
				catch (Exception e) {
					
				}
				
			}
		});
	}

}
