package me.gz.economy;

import java.util.List;

import org.bukkit.OfflinePlayer;

import me.gz.economy.functions.DepositAPI;
import me.gz.economy.functions.WithDrawnAPI;
import me.gz.economy.functions.getBalanceAPI;
import me.gz.economy.utils.FormatType;
import me.gz.economy.utils.FormatType.TypeFormat;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class Economy implements net.milkbowl.vault.economy.Economy {

	@Override
	public EconomyResponse bankBalance(String arg0) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public boolean createPlayerAccount(String name) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer p) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(String name, String worlds) {
		return true;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer p, String world) {
		return true;
	}

	@Override
	public String currencyNamePlural() {
		return main.CFG.getString("MoneyConfigs.Plural");
	}

	@Override
	public String currencyNameSingular() {
		return main.CFG.getString("MoneyConfigs.Singular");
	}

	@Override
	public EconomyResponse deleteBank(String name) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse depositPlayer(String name, double amount) {
		DepositAPI.Depositar(name, amount);
		return new EconomyResponse(amount, 0D, ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer p, double amount) {
		DepositAPI.Depositar(p, amount);
		return new EconomyResponse(amount, 0D, ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse depositPlayer(String name, String wrd, double amount) {
		DepositAPI.Depositar(name, amount);
		return new EconomyResponse(amount, 0D, ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer p, String wrd, double amount) {
		DepositAPI.Depositar(p, amount);
		return new EconomyResponse(amount, 0D, ResponseType.SUCCESS, "");
	}

	@Override
	public String format(double amount) {
		return FormatType.Formatar(TypeFormat.OP, amount);
	}

	@Override
	public int fractionalDigits() {
		return 0;
	}

	@Override
	public double getBalance(String name) {
		return getBalanceAPI.getBalance(name);
	}

	@Override
	public double getBalance(OfflinePlayer p) {	
		return getBalanceAPI.getBalance(p);
	}

	@Override
	public double getBalance(String name, String world) {
		return getBalanceAPI.getBalance(name);
	}

	@Override
	public double getBalance(OfflinePlayer p, String world) {
		return getBalanceAPI.getBalance(p);
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public String getName() {
		return "GZ_Economy";
	}

	@Override
	public boolean has(String name, double amount) {
		return getBalance(name) >= amount;
	}

	@Override
	public boolean has(OfflinePlayer p, double amount) {
		return getBalance(p) >= amount;
	}

	@Override
	public boolean has(String name, String arg1, double amount) {
		return getBalance(name) >= amount;
	}

	@Override
	public boolean has(OfflinePlayer p, String arg1, double amount) {
		return getBalance(p) >= amount;
	}

	@Override
	public boolean hasAccount(String name) {
		return true;
	}

	@Override
	public boolean hasAccount(OfflinePlayer p) {
		return true;
	}

	@Override
	public boolean hasAccount(String name, String arg1) {
		return true;
	}

	@Override
	public boolean hasAccount(OfflinePlayer name, String arg1) {
		return true;
	}

	@Override
	public boolean hasBankSupport() {
		return true;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		return new EconomyResponse(0D, 0D, ResponseType.NOT_IMPLEMENTED, "GZ_Economy não tem Suporte a Bancos");
	}

	@Override
	public boolean isEnabled() {
		return main.pl.isEnabled();
	}

	@Override
	public EconomyResponse withdrawPlayer(String name, double amount) {
		WithDrawnAPI.Sacar(name, amount);
		return new EconomyResponse(amount, 0D, ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer p, double amount) {
		WithDrawnAPI.Sacar(p, amount);
		return new EconomyResponse(amount, 0D, ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse withdrawPlayer(String name, String world, double amount) {
		return withdrawPlayer(name, amount);
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer p, String world, double amount) {
		return withdrawPlayer(p, amount);

	}


}