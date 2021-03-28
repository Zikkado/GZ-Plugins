package me.gz.chestshop.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import org.bukkit.inventory.ItemStack;

import me.gz.chestshop.main;

public class CreateShopSQL {
	
	
	public static void CreateShop(Location loc, ItemStack item, String Owner, int quantidade) {
		Bukkit.getScheduler().runTaskAsynchronously(main.pl, new Runnable() {
			
			@Override
			public void run() {
				try {
					PreparedStatement stmt = SQL.conn.prepareStatement("INSERT INTO gz_chestshop (Owner, Location, ItemStack, Quantidade) VALUES(?,?,?,?)");
					
					stmt.setString(1, Owner);
					stmt.setObject(2, loc);
					stmt.setObject(3, item);
					stmt.setInt(4, quantidade);
					
				} catch (SQLException e) {
				
					e.printStackTrace();
				}
			}
		});
	}
}
