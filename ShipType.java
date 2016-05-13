package BattleShip;

public class ShipType {

	/*
	 *  Size of this Ship
	 */
	private int Size;
	/*
	 *  Current Health of this Ship
	 */
	private int Health;
	/*
	 *  Front coordinates of this Ship
	 */
	private int vertStart, vertEnd;
	/*
	 *  Back coordinates of this Ship
	 */
	private int horzStart, horzEnd;
	/*
	 *  Is this Ship placed or not
	 */
	private boolean isPlaced = false;
	/*
	 *  Was this Ship fired or not
	 */
	private boolean wasFired = false;
	
	/*
	 *  Constructor
	 */
	public ShipType(int length){
		
		Size = length;
		Health = length;
		
	}
	
	/*
	 *  Constructor
	 */
	public ShipType(int length, int horzStart, int vertStart, int horzEnd, int vertEnd){
		
		Size = length;
		Health = length;
		this.horzStart = horzStart;
		this.vertStart = vertStart;
		this.horzEnd = horzEnd;
		this.vertEnd = vertEnd;
		
	}
	
	public int getSize(){
		
		return Size;
		
	}
	
	public int getHealth(){
		
		return Health;
		
	}
	
	public int getHorzStart(){
		
		return horzStart;
		
	}
	
	public int getVertStart(){
		
		return vertStart;
		
	}
	public int getHorzEnd(){
	
		return horzEnd;
	
	}
	public int getVertEnd(){
	
		return vertEnd;
	
	}
	
	public void setStartandEnd(int horzStart, int vertStart, int horzEnd, int vertEnd){
		
		this.horzStart = horzStart;
		this.vertStart = vertStart;
		this.horzEnd = horzEnd;
		this.vertEnd = vertEnd;
		
	}
	
	public boolean isPlaced(){
		
		return isPlaced;
		
	}
	
	public boolean wasFired(){
		
		if(Health == 0)
			wasFired = true;
		return wasFired;
		
	}
	
	public void place(){
		
		isPlaced = true;
		
	}
	
	public void hit(){
		
		Health--;
		
	}
	
	
}
