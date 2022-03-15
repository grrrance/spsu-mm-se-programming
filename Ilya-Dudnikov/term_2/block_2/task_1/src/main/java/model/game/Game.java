package model.game;

import model.player.AbstractPlayer;
import model.service.account_manager.AccountManager;
import model.service.bet_pool.BetPool;

import java.util.ArrayList;

public abstract class Game {
	protected BetPool betPool;
	protected ArrayList<AbstractPlayer> playerList;
	protected AccountManager accountManager;

	public Game(String poolId, AccountManager accountManager) {
		betPool = new BetPool(poolId);
		playerList = new ArrayList<>();
		this.accountManager = accountManager;
	}
}
