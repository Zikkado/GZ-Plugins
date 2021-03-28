package me.gz.economy.api.events;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.gz.economy.functions.getBalanceAPI;

public class PlayerPayEvent extends Event implements Cancellable {
	
	
	private static final HandlerList handlers = new HandlerList();
	
	private Player player;
	private OfflinePlayer target;
	private double amount;
	private boolean cancelled = false;
	
	public PlayerPayEvent(Player player, OfflinePlayer target, double amount) {
		this.player = player;
		this.target = target;
		this.amount = amount;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Double getBalancePlayer() {
		return getBalanceAPI.getBalance(this.player);
	}
	
	public OfflinePlayer getTarget() {
		return this.target;
	}
	
	public Double getBalanceTarget() {
		return getBalanceAPI.getBalance(this.target);
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean args) {
		this.cancelled = args;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
