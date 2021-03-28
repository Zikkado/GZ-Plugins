package me.gz.economy.api.events;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerDepositEvent extends Event implements Cancellable {
	
	private static final HandlerList handlers = new HandlerList();

	private OfflinePlayer player;
	private double amount;
	private boolean cancelado = false;
	
	public PlayerDepositEvent(OfflinePlayer p, double amount) {
		this.amount = amount;
		this.player	= p;
	}
	
	public OfflinePlayer getOfflinePlayer() {
		return this.player;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancelado;
	}

	@Override
	public void setCancelled(boolean arg0) {
		this.cancelado = arg0;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
	    return handlers;
	}

}
