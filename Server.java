package BattleShip;

import java.net.*;
import java.io.*;

/**
 * This class writes object into outputstream
 */
public class Server implements Runnable{  
	/**
	 * Client
	 */
	private ServerThread client = null;
	/**
	 * ServerSocket
	 */
	private ServerSocket server = null;
	/**
	 * Thread
	 */
	private Thread thread = null;
	/**
	 * Array of two players
	 */
	private Player[] players = new Player[2];
	/**
	 * This flag controls which player is writing to the outputstream
	 */
	private boolean flag = false;
	/**
	 * OutputStram
	 */
	private ObjectOutputStream output = null;
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
	 * 			set serversocket
	 */
	public Server(Player[] players, boolean tmp, int value){  
		try{ 
			this.players = players;
			flag=tmp;
			server = new ServerSocket(value); 
			addThread(server.accept());
			start(); 
			
		}
		catch(IOException ioe){  
			System.out.println("Can not bind to port "); 
		}
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
	 * 			set players[0]
	 */
	public void setYourPlayer(Player tmp){		
		
		if(tmp.getUpdateBoard() == true){
			
			players[0] = tmp;
			if(players[0].getIsGameOver() == true){
				players[1].setIsGameOver(true);
				players[0].setUpdateBoard(true);
			}

		}		
	}
	/**
	 * @return players[0]
	 */
	public Player getYourPlayer(){
		   
		return players[0];
	   
	}
	/**
	 * @param tmp
	 * 			set players[1]
	 */
	public void setEnemyPlayer(Player tmp){
		
		players[1] = tmp;
		
	}
	/**
	 * @return players[1]
	 */
	public Player getEnemyPlayer(){
	   
		return players[1];
	   
	}
	/**
  	 * This function writes object to the outputstream
  	 * if flag==false you write players[0]
  	 * if flag==true you write players[1]
  	 */
	public void run(){  
		while (!shutdown){  
			try{
				if(flag==false){
					output.reset();
					output.writeObject(players[0]);
				}
				else{
					output.reset();
					if(players[1].getUpdateBoard() == true){
						
						output.writeObject(players[1]);
						players[1].setUpdateBoard(false);
						players[1].setIsFired(false);	
						
					}
					if(players[1].getIsGameOver() == true){
						
						players[0].setIsGameOver(true);
						players[0].setUpdateBoard(true);
						
					}
				}
			}
			catch(IOException ioe){  
				System.out.println("Sending accept error");
				stop();
			}
		}
	}
	/**
	 * Create and start thread
	 */
	public void start(){  
		if(thread == null){  
			thread = new Thread(this); 
			thread.start();
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
	 * Function which close server , thread , client
	 */
	public void stop(){
		try{  
			if(server!=null)
				server.close();
		}
		catch(IOException ioe){  
			System.out.println("Server Error closing server"); 
		}
		try{
			if(client!=null)
				client.close();
		}
		catch(IOException ioe){  
			System.out.println("Server Error closing serverthread"); 
		}
		
	}
	/**
	 * Function which create outputstream and serverthread
	 * @param socket
	 * @throws IOException
	 */
	private void addThread(Socket socket) throws IOException{  
		System.out.println("Client accepted");
		output = new ObjectOutputStream(socket.getOutputStream()); 
		client = new ServerThread(this, socket);
		try{  
			client.open(); 
			client.start();  
		}
		catch(IOException ioe){  
			System.out.println("Error opening thread"); 
		}  
	}
}