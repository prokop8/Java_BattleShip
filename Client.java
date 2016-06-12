package BattleShip;

import java.net.*;
import java.io.*;

/**
 * This class writes object into outputstream
 */
public class Client implements Runnable{  
	/**
	 * socket
	 */
	private Socket socket = null;
	/**
	 * thread
	 */
	private Thread thread = null;
	/**
	 * client
	 */
	private ClientThread client = null;
	/**
	 * server host name
	 */
	private String serverHostname = new String ("127.0.0.1");
	/**
	 * Array of the players
	 */
	private Player[] players = new Player[2];
	/**
	 * outputstream
	 */
	private ObjectOutputStream output = null;
	/**
	 * This flag controls which player is writing to the outputstream
	 */
	private boolean flag = false;
	/**
	 * To stop thread
	 */
	private volatile boolean shutdown = false;
	
	/**
	 * @param players
	 * 				set array of players
	 * @param tmp
	 * 			set flag
	 * @param value
	 * 			set socket number
	 */
	public Client(Player[] players, boolean tmp, int value){ 
		try{  
			this.players = players;
			flag=tmp;
			socket = new Socket(serverHostname, value);
			start();
		}
		catch(UnknownHostException uhe){  
			System.out.println("Host unknown"); 
		}
		catch(IOException ioe){  
			System.out.println("Unexpected exception" ); 
		}
	}
	/**
  	 * This function writes object to the outputstream
  	 * if flag==false you write players[1]
  	 * if flag==true you write players[0]
  	 */
	public void run(){  
		while (!shutdown){  
			try{
				if(flag==false){
					output.reset();
					output.writeObject(players[1]);
				}
				else{
					output.reset();
					if(players[0].getUpdateBoard() == true){
						output.writeObject(players[0]);
						players[0].setUpdateBoard(false);
						players[0].setIsFired(false);
					}
					if(players[0].getIsGameOver() == true){
						
						players[1].setIsGameOver(true);
						players[1].setUpdateBoard(true);
						//shutdown();
						//stop();
					}
				}
			}
			catch(IOException ioe){  
				System.out.println("Sending error" + ioe.getMessage());
				stop();
			}
		}
	}
	/**
     * Function which stop run
     */
	public void shutdown(){
		
		shutdown = true;
		client.shutdown();
		
	}
	/**
	 * @param tmp
	 * 			set flag
	 */
	public void setFlag(boolean tmp){
		
		flag=tmp;
		
	}
	/**
	 * @return flag
	 */
	public boolean getFlag(){
		
		return flag;
		
	}
	/**
	 * @param tmp
	 * 			set players[1]
	 */
	public void setYourPlayer(Player tmp){
		
		if(tmp.getUpdateBoard() == true){

				players[1] = tmp;
				if(players[1].getIsGameOver() == true){
					players[0].setIsGameOver(true);
					players[1].setUpdateBoard(true);
				}

		}	
	}
	/**
	 * @return players[1]
	 */
	public Player getYourPlayer(){
		   
		return players[1];
		   
	}
	/**
	 * @param tmp
	 * 			set players[0]
	 */
	public void setEnemyPlayer(Player tmp){
		
		players[0] = tmp;
		
	}
	/**
	 * @return players[0]
	 */
	public Player getEnemyPlayer(){
		   
		return players[0];
		   
	}	
	/**
	 * Function which create outputstream , clientthread and thread
	 * @throws IOException
	 */
	public void start() throws IOException{  
		
  		output = new ObjectOutputStream(socket.getOutputStream());
		if (thread == null){  
			client = new ClientThread(this, socket);
			thread = new Thread(this);                   
			thread.start();
		}
	}
	/**
	 * Function which close socket and client
	 */
	public void stop(){  
		try {
			if(socket!=null)
			socket.close();
		} catch (IOException e) {
			System.out.println("Client Error closing socket"); 
		}
		client.close();  
	}
	
}