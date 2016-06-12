package BattleShip;

import java.net.*;

import java.io.*;

/**
 * This class reads inputstream
 */
public class ServerThread extends Thread{  
	/**
	 * Server
	 */
	private Server server = null;
	/**
	 * Socket
	 */
	private Socket socket = null;
	/**
	 * InputStream
	 */
	private ObjectInputStream input = null;
	/**
	 * To stop thread
	 */
	private volatile boolean shutdown = false;
	/**
	 * @param _server
	 * 				set server
	 * @param _socket
	 * 				set socket
	 */
  	public ServerThread(Server _server, Socket _socket){  
  		
  		super();
  		server = _server;
  		socket = _socket;

  	}
  	/**
  	 * This function stops running
  	 */
  	public void shutdown(){
		
		shutdown = true;
		
	}
  	/**
  	 * This function reads object from the inputstream and write it into player
  	 */
  	public void run(){ 
  		System.out.println("Server Thread running.");
  		while (!shutdown){  
  			try{ 
  				if(server.getFlag()==false){
  					server.setEnemyPlayer((Player) input.readObject());
  				}
  				else{
  					server.setYourPlayer((Player) input.readObject());
  				}
  			}
  			catch(IOException | ClassNotFoundException ioe){  
				System.out.println("Listening error " + ioe.getMessage());
				server.stop();
			}
  		}
  	}
  	/**
  	 * This function creates inputstream
  	 * @throws IOException
  	 */
  	public void open() throws IOException{  
  		
  		input = new ObjectInputStream(socket.getInputStream());
  		
  	}
  	/**
  	 * This function closes the socket
  	 * @throws IOException
  	 */
  	public void close() throws IOException{ 
  		if(socket != null)    
  			socket.close();

  	}
}