package BattleShip;

import java.io.Serializable;

public class ShipType implements Serializable{

	/**
	 * ID which is for Serializable
	 */
	private static final long serialVersionUID = 1L;
	/**
	 *  Size of this Ship
	 */
	private int Size;
	/**
	 *  Current Health of this Ship
	 */
	private int Health;
	/**
	 *  Front coordinates of this Ship
	 */
	private int vertStart, vertEnd;
	/**
	 *  Back coordinates of this Ship
	 */
	private int horzStart, horzEnd;
	/**
	 *  Is this Ship placed or not
	 */
	private boolean isPlaced = false;
	/**
	 *  Was this Ship fired or not
	 */
	private boolean wasFired = false;
	
	/**
	 *  Constructor which set size and length of the ship
	 *  @param length
	 *  			size of the ship
	 *  			health of the ship
	 */				
	public ShipType(int length){
		
		Size = length;
		Health = length;
		
	}
	
	/**
	 *  Constructor which set size and length and also coordinates of the ship
	 *  @param length
	 *  			size of the ship
	 *  			health of the ship
	 * @param horzStart
	 * 				horizontal coordinate of ship front
	 * @param vertStart
	 * 				vertical coordinate of ship front
	 * @param horzEnd
	 * 				horizontal coordinate of ship back
	 * @param vertEnd
	 * 				vertical coordinate of ship back
	 */
	public ShipType(int length, int horzStart, int vertStart, int horzEnd, int vertEnd){
		
		Size = length;
		Health = length;
		this.horzStart = horzStart;
		this.vertStart = vertStart;
		this.horzEnd = horzEnd;
		this.vertEnd = vertEnd;
		
	}
	/**
	 * @return size of this ship
	 */
	public int getSize(){
		
		return Size;
		
	}
	/**
	 * @return health of this ship
	 */
	public int getHealth(){
		
		return Health;
		
	}
	/**
	 * @return horizontal coordinate of ship front
	 */
	public int getHorzStart(){
		
		return horzStart;
		
	}
	/**
	 * @return vertical coordinate of ship front
	 */
	public int getVertStart(){
		
		return vertStart;
		
	}
	/**
	 * @return horizontal coordinate of ship back
	 */
	public int getHorzEnd(){
	
		return horzEnd;
	
	}
	/**
	 * @return vertical coordinate of ship back
	 */
	public int getVertEnd(){
	
		return vertEnd;
	
	}
	/**
	 * Function which set front and back coordinates of the ship
	 * @param horzStart
	 * 				horizontal coordinate of ship front
	 * @param vertStart
	 * 				vertical coordinate of ship front
	 * @param horzEnd
	 * 				horizontal coordinate of ship back
	 * @param vertEnd
	 * 				vertical coordinate of ship back
	 */
	public void setStartAndEnd(int horzStart, int vertStart, int horzEnd, int vertEnd){
		
		this.horzStart = horzStart;
		this.vertStart = vertStart;
		this.horzEnd = horzEnd;
		this.vertEnd = vertEnd;
		
	}
	/**
	 * Function which return isPlaced
	 * @return isPlaced
	 * 					(true if the ship is placed
	 * 					false if the ship isn't placed)
	 */
	public boolean isPlaced(){
		
		return isPlaced;
		
	}
	/**
	 * Function which return if your ship was fired
	 * @return wasFired
	 * 				 	(true if health of the ship is 0
	 * 				 	false if health of the ship isn't 0)
	 */
	public boolean wasFired(){
		
		if(Health == 0)
			wasFired = true;
		return wasFired;
		
	}
	/**
	 * Function which set isPlaced when you place the ship
	 */
	public void place(){
		
		isPlaced = true;
		
	}
	/**
	 * Function which increment health of the ship when player hit this ship
	 */
	public void hit(){
		
		Health--;
		
	}
	
	
}
