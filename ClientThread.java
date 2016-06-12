package BattleShip;

import java.net.*;
import java.io.*;

/**
 * This class reads inputstream
 */
public class ClientThread extends Thread{  
	/**
	 * Socket
	 */
	private Socket socket = null;
	/**
	 * client
	 */
	private Client client = null;
	/**
	 * Inputstream
	 */
	private ObjectInputStream input = null;
	/**
	 * To stop thread
	 */
	private volatile boolean shutdown = false;
	/**
	 * @param _client
	 * 				set client
	 * @param _socket
	 * 				set socket
	 */
	public ClientThread(Client _client, Socket _socket){  
		client = _client;
		socket = _socket;
		open();  
		start();
	}
	/**
	 * Create inputstream
	 */
	public void open(){  
		try{  
			input = new ObjectInputStream(socket.getInputStream());
		}
		catch(IOException ioe){  
			System.out.println("Error getting input stream ");
			client.stop();
		}
	}
	/**
	 * Function which stop run
	 */
	public void shutdown(){
		
		shutdown = true;
		
	}
	/**
	 * Function which close socket
	 */
	public void close(){ 
		try {
			if(socket!=null)
			socket.close();
		} catch (IOException e) {
			System.out.println("Error closing socket"); 
		}
	}
	/**
	 * Function which read object from inputstream and write it inti player
	 */
	public void run(){  
		while (!shutdown){ 
			try{  
				if(client.getFlag()==false){
  					client.setEnemyPlayer((Player) input.readObject());
  				}
  				else{
  					client.setYourPlayer((Player) input.readObject());
  				}
			}
			catch(IOException | ClassNotFoundException ioe){  
				System.out.println("Listening error ");
				client.stop();
			}
		}
	}
}

