package me.gz.economy.api.events;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MoneyRemoveEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private OfflinePlayer target;
	private double amount;
	private boolean cancelado = false;
	
	
	public MoneyRemoveEvent(Player player, OfflinePlayer target, double amount) {
		this.player = player;
		this.target = target;
		this.amount = amount;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelado;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.cancelado = arg0;
	}
	public double getAmount() {
		return this.amount;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public OfflinePlayer getTarget() {
		return this.target;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
	    return handlers;
	}
}
