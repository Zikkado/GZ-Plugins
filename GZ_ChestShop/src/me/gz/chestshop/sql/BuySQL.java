package me.gz.chestshop.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.gz.chestshop.hooks.VaultAPI;

public class BuySQL {
	
	
	@SuppressWarnings("deprecation")
	public static void Comprar(Player p, Location loc) {
		if(VaultAPI.economy.getBalance(p.getName()) < 1000)
		{
			p.sendMessage("Você não Possui Dinheiro Suficiente!");
			return;
		}
		
			try {
				PreparedStatement stmt = SQL.conn.prepareStatement("SELECT * FROM gz_chestshop WHERE Location = ?");
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					for(int i = 0; i < rs.getInt("Quantidade"); i++)
						p.getInventory().addItem((ItemStack[]) rs.getObject("ItemStack"));
					
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	}

}
