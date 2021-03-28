package me.gz.economy.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import me.gz.economy.utils.FormatType;
import me.gz.economy.main;

public class GUIManager {
	
	public static ItemStack CreateItem(Material m, int id, String nome)
	{
		ItemStack item = new ItemStack(m, 1, (short)id);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome.replace("&", "§"));
		
		item.setItemMeta(meta);
		return item;
	}
	public static ItemStack CreateItem(Material m, int id, String nome, boolean enchant)
	{
		ItemStack item = new ItemStack(m, 1, (short)id);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome.replace("&", "§"));
		if(enchant) {
			meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack CreateItem(Material m, int id, String nome, List<String> lore)
	{
		ItemStack item = new ItemStack(m, 1, (short)id);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome.replace("&", "§"));
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack CreateItem(Material m, int id, String nome, List<String> lore, boolean enchant)
	{
		ItemStack item = new ItemStack(m, 1, (short)id);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome.replace("&", "§"));
		if(enchant) {
			meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
			meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack CreateSkull(String ItemName, String owner)
	{
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ItemName.replace("&", "§"));
		meta.setOwner(owner);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack CreateSkull(String ItemName, String owner, List<String> lore) {	
		List<String> l = new ArrayList<>();
		
		for(String a : lore) {
			l.add(a.replace("{player}", owner));
		}
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ItemName.replace("&", "§"));
		meta.setOwner(owner);
		
		meta.setLore(l);
		item.setItemMeta(meta);
		l.clear();
		return item;
	}
 
	@SuppressWarnings("deprecation")
	public static ItemStack CreateSkull(Player p, int i, String owner, List<String> lore) {
		
		List<String> l = new ArrayList<>();
		for(String a : lore) {
			l.add(a.replace("{magnata}", new TopManager().tagByPlayer(Bukkit.getOfflinePlayer(owner))).replace("{player}", owner).replace("{amount}", FormatType.Formatar(main.FormatType.get(p), new TopManager().getMoneyByPosition(i))).replace("&", "§"));
		}
		
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2" + i + "° §7" + owner.replace("&", "§"));
		meta.setOwner(owner);
		
		meta.setLore(l);
		item.setItemMeta(meta);
		l.clear();
		return item;
	}
	
	
	public static void FillArround(Material m, int ID, String name, Inventory inv, boolean Enchant) {
		for(int s = 0; s < inv.getSize(); s++)  {
            if(inv.getItem(s) == null) 
            {
            	inv.setItem(s, CreateItem(m, ID, name, Enchant));
            }
        }
	}
	
	public static void FillArroundRandom(Material m, String name, Inventory inv, boolean Enchant) {
		for(int s = 0; s < inv.getSize(); s++)  {
            if(inv.getItem(s) == null) 
            {
            	inv.setItem(s, CreateItem(m, new Random().nextInt(13), name, Enchant));
            }
        }
	}
	
}
