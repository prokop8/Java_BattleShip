package BattleShip;

import java.io.Serializable;

import BattleShip.ShipType;

public class Player implements Serializable{

	/**
	 * ID which is for Serializable
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  Is this Player turn to attack
	 */
	private boolean checkTurn = false;
	/**
	 *  Player ships array
	 */
	private ShipType[][] shipPlacement = new ShipType[10][10];
	/**
	 *  Player hits array
	 */
	private boolean[][] hits = new boolean[10][10];
	/**
	 *  Number of ships 
	 */
	private int shipsLeft;
	/**
	 *  Array with ShipTypes
	 */
	private ShipType[] playerShips;
	/**
	 *  Number of ships were placed
	 */
	private int shipsPlaced=0;
	/**
	 *  Current active ship of this Player
	 */
	private ShipType activeShip=new ShipType(4);
	/**
	 *  Is Player placing ship
	 */
	private boolean isPlacingShip=false;
	/**
	 * 	X coordinate of attack
	 */
	private int shipX=0;
	/**
	 * 	Y coordinate of attack
	 */
	private int shipY=0;
	/**
	 * 	Can we update our board after enemy turn
	 */
	private boolean updateBoard = false;
	/**
	 * 	Was our ship fired
	 */
	private boolean isFired = false;
	/**
	 * 	Index of fired ship
	 */
	private int indexOfShip = 0;
	/**
	 * 	Is this game over
	 */
	private volatile boolean isGameOver = false;
	/**
	 *  Constructor which set shipsLeft to 10 and create playerShips array with 10 ships
	 */
	public Player(){
		
		shipsLeft = 10;
		playerShips = new ShipType[10];		
		playerShips[0] = new ShipType(4);
		playerShips[1] = new ShipType(3);
		playerShips[2] = new ShipType(3);
		playerShips[3] = new ShipType(2);
		playerShips[4] = new ShipType(2);
		playerShips[5] = new ShipType(2);
		playerShips[6] = new ShipType(1);
		playerShips[7] = new ShipType(1);
		playerShips[8] = new ShipType(1);
		playerShips[9] = new ShipType(1);		
				
	}
	/**
	 * @param x
	 * 			coordinate x
	 * @param y
	 * 			coordinate y
	 * @return shipType in this cell
	 */
	public ShipType getShipPlacement(int x, int y){
		
		return shipPlacement[x][y];
		
	}
	/**
	 * Function which decrement shipsLeft
	 */
	public void decShipsLeft(){
		
		shipsLeft--;
		
	}
	/**
	 * Function which increment shipsPlaced
	 */
	public void incShipsPlaced(){
		
		shipsPlaced++;
		
	}
	/**
	 * Function that set shipX 
	 * @param x
	 * 			coordinate x
	 */
	public void setShipX(int x){
		
		shipX=x;
		
	}
	/**
	 * @return actual coordinate shipX
	 */
	public int getShipX(){
		
		return shipX;
		
	}
	/**
	 * @param actual coordinate y
	 */
	public void setShipY(int value){
		
		shipY=value;
		
	}
	/**
	 * @return actual coordinate shipY
	 */
	public int getShipY(){
		
		return shipY;
		
	}
	/**
	 * Function that set isFired when enemy fired our ship
	 * @param isFired
	 * 				(true if the ship was fired
	 * 				false if the ship wasn't fired)			
	 */
	public void setIsFired(boolean isFired){
		
		this.isFired = isFired;
		
	}
	/**
	 * Function that get isFired
	 * @return isFired
	 * 					(true if enemy fired our ship
	 * 					false if enemy didn't fire our ship)
	 */
	public boolean getIsFired(){
		
		return isFired;
		
	}
	/**
	 * Function that set index of fired ship in playerShips
	 * @param indexOfShip
	 * 					index of fired ship
	 */
	public void setIndexOfShip(int indexOfShip){
		
		this.indexOfShip = indexOfShip;
		
	}
	/** 
	 * @return index of fired ship 
	 */
	public int getIndexOfShip(){
		
		return indexOfShip;
		
	}
	/**
	 * Function that set isGameOver
	 * @param isGameOver
	 * 					true if the game is over
	 * 					false if the game isn't over
	 * 
	 */
	public void setIsGameOver(boolean isGameOver){
		
		this.isGameOver = isGameOver;
		
	}
	/**
	 * Function that return isGameOver
	 * @return isGameOver
	 * 					(true if the game is over
	 * 					false if the game isn't over)
	 */
	public boolean getIsGameOver(){
		
		return isGameOver;
		
	}
	/**
	 * Function which set isPlacingShip when you put beginning of the ship
	 * @param isPlacingShip
	 * 						true when you put beginning of the ship
	 * 						false when you end placing the ship
	 */
	public void setIsPlacingShip(boolean isPlacingShip){
		
		this.isPlacingShip = isPlacingShip;
		
	}
	/**
	 * Function which return isPlacingShip
	 * @param isPlacingShip
	 * 						true if you placing the ship
	 * 						false if you end placing the ship
	 */
	public boolean getIsPlacingShip(){
		
		return isPlacingShip;
		
	}
	/**
	 * @param number
	 * @return ship from the array of shipTypes
	 */
	public ShipType getPlayerShips(int number){
		
		return playerShips[number];
		
	}
	/**
	 * Function which set player turn
	 * @param turn
	 * 			true if it is your turn
	 * 			false if is is enemy player turn
	 */
	public void setTurn(boolean turn){
		
		checkTurn=turn;
		
	}
	/**
	 * Function which return player turn
	 * @return checkTurn
	 * 					(true if it is your turn
	 * 					false if it is enemy player turn)
	 */
	public boolean getTurn(){
		
		return checkTurn;
		
	}
	/**
	 * Function that place the ship with that length and set shipPlacement array with ships from playerShips
	 * @param length
	 * 				length of the ship
	 * @param horzStart
	 * 				horizontal coordinate of ship front
	 * @param vertStart
	 * 				vertical coordinate of ship front
	 * @param horzEnd
	 * 				horizontal coordinate of ship back
	 * @param vertEnd
	 * 				vertical coordinate of ship back
	 */
	public void placeShip(int length, int horzStart, int vertStart, int horzEnd, int vertEnd){
		
		int l;
		for(l=0;l<10;l++){
			if(playerShips[l].getSize()==length && playerShips[l].isPlaced()==false){//finding ship with that length and didn't place
				playerShips[l].setStartAndEnd(horzStart, vertStart, horzEnd, vertEnd);
				playerShips[l].place();
				break;
			}
		}
        if(horzStart-horzEnd == 0)// vertical
        	for(int y=0;y<length;y++){
        		if(vertStart<vertEnd){
        			shipPlacement[horzStart][vertStart+y] = playerShips[l];
        		}
        		else{
        			shipPlacement[horzStart][vertEnd+y] = playerShips[l];
        		}
        	}
        else{// horizontal
        	for(int x=0;x<length;x++){
        		if(horzStart<horzEnd){
        			shipPlacement[horzStart+x][vertStart] = playerShips[l];
        		}
        		else{
        			shipPlacement[horzEnd+x][vertStart] = playerShips[l];
        		}
        	}
        }      	
	}
	/**
	 * Function which set updateBoard 
	 * @param updateBoard
	 * 					true if player have attacked enemy player board
	 * 					false if player haven't attacked enemy player board
	 */
	public void setUpdateBoard(boolean updateBoard){
		
		this.updateBoard = updateBoard;
		
	}
	/**
	 * Function which return updateBoard 
	 * @return updateBoard
	 * 					(true if player have attacked enemy player board
	 * 					false if player haven't attacked enemy player board)
	 */
	public boolean getUpdateBoard(){
		
		return updateBoard;
		
	}
	/**
	 * Function which set active ship when player starts placing ships
	 * @param ship
	 */
	public void setActiveShip(ShipType ship){
		
		activeShip = ship;
		
	}
	/**
	 * @return active ship of this player
	 */
	public ShipType getActiveShip(){
		
		return activeShip;
		
	}
	/**
	 * @param x
	 * 			coordinate x
	 * @param y
	 * 			coordinate y
	 * @return ship from shipPlacement array
	 */
	public ShipType getShip(int x, int y){
		
		return shipPlacement[x][y];
		
	}
	/**
	 * Function which set hit in hit array
	 * @param x
	 * 			coordinate x
	 * @param y
	 * 			coordinate y
	 * @param hit
	 * 			true if there is a ship
	 * 			false if there isn't a ship
	 */
	public void setHit(int x, int y, boolean hit){
		
		hits[x][y]=hit;
		
	}
	/**
	 * Function which set hits after players put their ships 
	 */
	public void setHits(){
		
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++)
				hits[x][y] = (shipPlacement[x][y] != null);
		
	}
	/**
	 * Function which get hit from hit array
	 * @param x
	 * 			coordinate x
	 * @param y
	 * 			coordinate y
	 * @return hits[x][y]
	 * 					(true if there is a ship 
	 * 					false if there isn't a ship) 
	 */
	public boolean getHits(int x, int y){
		
		return hits[x][y];
		
	}
	/**
	 * @return shipsLeft
	 */
	public int getShipsLeft(){
		
		return shipsLeft;
		
	}
	/**
	 * @return shipsPlaced
	 */
	public int getShipsPlaced(){
		
		return shipsPlaced;
		
	}
}
