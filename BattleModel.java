package BattleShip;

import BattleShip.Player;

public class BattleModel {
	/**
	 *	Array of two Players 
	 */
	private Player[] players = new Player[2];
	/**
	 *	Starting server which works until you push Deploy button
	 */
	private Server StartingServer;
	/**
	 *	Starting client which works until you push Deploy button
	 */
	private Client StartingClient;
	/**
	 *	Main server of the game which works after you pushed Deploy button
	 */
	private Server MainServer;
	/**
	 *	Main client of the game which works after you pushed Deploy button
	 */
	private Client MainClient;
	/**
	 *  Constructor which create two players
	 */
	public BattleModel() {
		
		players[0] = new Player();
		players[1] = new Player();
		
	}
	/**
	 * Setting players turns
	 * @param turn1 
	 * 				set player 1 turns
	 * 
	 * @param turn2 
	 * 				set player 2 turns
	 */
	public void setCheckTurn(boolean turn1, boolean turn2){
		
		players[0].setTurn(turn1);
		players[1].setTurn(turn2);
		
	}
	/**
	 * Setting player 1
	 * @param player 
	 * 				set player 1
	 */
	public void setPlayer1(Player player){
		
		players[0]=player;
		
	}
	/**
	 * Setting player 2
	 * @param player 
	 * 				set player 2
	 */
	public void setPlayer2(Player player){
		
		players[1]=player;
		
	}
	/**
	 * Function that create starting server
	 */
	public void createStartingServer(){
		
		StartingServer = new Server(players, false, 10007);
		
	}
	/**
	 * Function that create starting client
	 */
	public void createStartingClient(){
		
		StartingClient = new Client(players, false, 10007);
		
	}
	/**
	 * Function that create main server
	 */
	public void createMainServer(){
	
		MainServer = new Server(players, true, 10008);
	
	}
	/**
	 * Function that create main client
	 */
	public void createMainClient(){
	
		MainClient = new Client(players, true, 10008);
	
	}
	/**
	 * @return your starting client
	 */
	public Client getStartingClient(){
		
		return StartingClient;
		
	}
	/**
	 * @return your starting server
	 */
	public Server getStartingServer(){
		
		return StartingServer;
		
	}
	/**
	 * @return your main client
	 */
	public Client getMainClient(){
	
		return MainClient;
	
	}
	/**
	 * @return your main server
	 */
	public Server getMainServer(){
		
		return MainServer;
		
	}
	/**
	 * @return array of two players
	 */
	public Player[] getPlayers() {
		
		return players;
		
	}
}
