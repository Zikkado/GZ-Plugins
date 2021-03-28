package me.gz.economy.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import me.gz.economy.main;
import me.gz.economy.sql.CheckPlayerExists;
import me.gz.economy.sql.DinheiroSQL;
import me.gz.economy.sql.EnumSQL;
import me.gz.economy.sql.InsertPlayer;
import me.gz.economy.utils.FormatType.TypeFormat;
import me.gz.economy.utils.TopFormat.FormatTops;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Join implements Listener {
	
	
	@EventHandler
	private void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(CheckPlayerExists.CheckPlayer(p)) {
			main.bankLocal.put(p, DinheiroSQL.ValorEmBancoDeDados(p));
			main.FormatType.put(p, TypeFormat.valueOf(EnumSQL.MoneyFormat(p)));
			main.TopType.put(p, FormatTops.valueOf(EnumSQL.TopFormat(p)));
			return;
		}
		
		
		main.bankLocal.put(p, (double) main.CFG.getInt("MoneyConfigs.StartMoney"));
		main.FormatType.put(p, TypeFormat.OP);
		main.TopType.put(p, FormatTops.GUI);
		
		InsertPlayer.InsertUser(p.getUniqueId().toString(), p.getName(), main.CFG.getInt("MoneyConfigs.StartMoney"));
		
		TextComponent newP = new TextComponent("§aClique Nesta Mensagem Para Alterar a Formataçao!");
		newP.setClickEvent(new ClickEvent(Action.RUN_COMMAND, "/money format"));
		newP.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Alterar Formatação").create()));

		
		e.getPlayer().spigot().sendMessage(newP);
	}
	
	
}
