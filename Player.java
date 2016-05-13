package BattleShip;

import javax.swing.JPanel;
import BattleShip.ShipType;

public class Player {

	/*
	 *  Is this Player turn to attack
	 */
	private boolean checkTurn = false;
	/*
	 *  Player ships array
	 */
	private ShipType[][] shipPlacement = new ShipType[10][10];
	/*
	 *  Player hits array
	 */
	private boolean[][] hits = new boolean[10][10];
	/*
	 *  Number of ships 
	 */
	private int shipsLeft;
	/*
	 *  Array with ShipTypes
	 */
	private ShipType[] playerShips;
	/*
	 *  Number of ships were placed
	 */
	private int shipsPlaced=0;
	/*
	 *  Current active ship of this Player
	 */
	private ShipType activeShip=new ShipType(4);
	/*
	 *  Is Player placing ship
	 */
	private boolean isPlacingShip=false;
	private int shipX=0;
	private int shipY=0;
	
	/*
	 *  Constructor
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
	
	public ShipType getShipPlacement(int x, int y){
		
		return shipPlacement[x][y];
		
	}

	public void decShipsLeft(){
		
		shipsLeft--;
		
	}
	
	public void incShipsPlaced(){
		
		shipsPlaced++;
		
	}
	
	public void setShipX(int value){
		
		shipX=value;
		
	}
	
	public void setShipY(int value){
		
		shipY=value;
		
	}
	
	public int getShipX(){
		
		return shipX;
		
	}
	
	public int getShipY(){
		
		return shipY;
		
	}
	
	public boolean getIsPlacingShip(){
		
		return isPlacingShip;
		
	}
	
	public void setIsPlacingShip(boolean is){
		
		isPlacingShip = is;
		
	}
	
	public ShipType getPlayerShips(int number){
		
		return playerShips[number];
		
	}
	
	public void setTurn(boolean turn){
		
		checkTurn=turn;
		
	}
	
	public boolean getTurn(){
		
		return checkTurn;
		
	}
	
	public void placeShip(int length, int horzStart, int vertStart, int horzEnd, int vertEnd){
		int l;
		for(l=0;l<10;l++){
			if(playerShips[l].getSize()==length && playerShips[l].isPlaced()==false){
				playerShips[l].setStartandEnd(horzStart, vertStart, horzEnd, vertEnd);
				playerShips[l].place();
				break;
			}
		}
        if(horzStart-horzEnd == 0)
        	for(int y=0;y<length;y++){
        		if(vertStart<vertEnd){
        			shipPlacement[horzStart][vertStart+y] = playerShips[l];
        		}
        		else{
        			shipPlacement[horzStart][vertEnd+y] = playerShips[l];
        		}
        	}
        else{
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
	
	public void setActiveShip(ShipType ship){
		
		activeShip = ship;
		
	}
	
	public ShipType getActiveShip(){
		
		return activeShip;
		
	}
	
	public ShipType getShip(int x, int y){
		return shipPlacement[x][y];
	}
	
	public void setHit(int x, int y, boolean is){
		
		hits[x][y]=is;
		
	}
	
	public void setHits(){
		
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++)
				hits[x][y] = (shipPlacement[x][y] != null);
		
	}
	
	public boolean getHits(int x, int y){
		
		return hits[x][y];
		
	}
	
	public int getShipsLeft(){
		
		return shipsLeft;
		
	}
	
	public int getShipsPlaced(){
		
		return shipsPlaced;
		
	}
}
