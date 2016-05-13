package BattleShip;

import BattleShip.Player;

public class BattleModel {
	/*
	 *  Array oh two Players 
	 */
	private Player[] players = new Player[2];
		
	/*
	 *  Constructor
	 */
	public BattleModel() {
		players[0] = new Player();
		players[1] = new Player();
	}
	
	public void setCheckTurn(boolean turn1, boolean turn2){
		
		players[0].setTurn(turn1);
		players[1].setTurn(turn2);
		
	}

	public Player[] getPlayers() {
		return players;
	}
	
	public int winner(){
		
		if(players[0].getShipsLeft()==0){
			return 1;
		}
		else{
			return 0;
		}
		
	}
	
	
}
