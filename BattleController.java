package BattleShip;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import BattleShip.BattleModel;
import BattleShip.BattleView;
import BattleShip.ShipType;


public class BattleController {
	/**
	 * BattleView
	 */
	private BattleView theView;
	/**
	 * BattleModel
	 */
	private BattleModel theModel;
	/**
	 * UpdateMonitor
	 */
	private UpdateMonitor theMonitor;
	
	/**
	 * @param theView
	 * 				set theView
	 * @param theModel
	 * 				set theModel
	 */
	public BattleController(BattleView theView,BattleModel theModel){
		
		this.theView=theView;
		this.theModel=theModel;
		this.theView.addServerListener(new ServerListener());
		this.theView.addClientListener(new ClientListener());
		this.theView.addExitListener(new ExitListener());
		this.theView.addShip4Listener(new Ship4Player1Listener());
		this.theView.addShip3Listener(new Ship3Player1Listener());
		this.theView.addShip2Listener(new Ship2Player1Listener());
		this.theView.addShip1Listener(new Ship1Player1Listener());
		this.theView.addDeployPlayer1Listener(new DeployPlayer1Listener());
		
	}
	/**
	 * Creating server after use this button
	 * Adding setShipsPanel and gridPlayer1Panel after use this button
	 */
	class ServerListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			theModel.createStartingServer();
			theView.frame.remove(theView.menuPanel);
			theView.setGridPlayer1Panel();
			theView.frame.add(theView.setShipsPanel);
			theView.frame.add(theView.gridPlayer1Panel);
			theView.addPlaceButtonPlayer1Listener(new PlaceButtonPlayer1Listener());
			theView.frame.setTitle("Setting ships Server");
			theView.frame.revalidate();
			theView.frame.repaint();
		}
	}
	/**
	 * Creating client after use this button
	 * Adding setShipsPanelPlayer2 and gridPlayer2Panel after use this button
	 */
	class ClientListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			theModel.createStartingClient();
			theView.frame.remove(theView.menuPanel);
			theView.setGridPlayer2Panel();
			theView.addShip4Listener(new Ship4Player2Listener());
			theView.addShip3Listener(new Ship3Player2Listener());
			theView.addShip2Listener(new Ship2Player2Listener());
			theView.addShip1Listener(new Ship1Player2Listener());
			theView.addDeployPlayer2Listener(new DeployPlayer2Listener());
			theView.frame.add(theView.getShipsPanelPlayer2());
			theView.frame.add(theView.gridPlayer2Panel);
			theView.addPlaceButtonPlayer2Listener(new PlaceButtonPlayer2Listener());
			theView.frame.setTitle("Setting ships Client");
			theView.frame.revalidate();
			theView.frame.repaint();
		}
	}
	
	/**
	 * exit from the program
	 */
	class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			
			System.exit(0);
			
		}
	}
	/**
	 * Class that control sides between two players
	 */
	class UpdateMonitor implements Runnable{
		
		/**
		 * If side==true - it is your turn
		 */
		private volatile boolean side = false;
		/**
		 * To stop thread
		 */
		private volatile boolean shutdown = false;
		/**
		 * @param tmp
		 * 			set side
		 */
		public UpdateMonitor(boolean tmp){
			
			side = tmp;
			try {
				start();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		/**
		 * Function which check side and if enemy player has made a move change colors of GridPlayerShips
		 * Also this function check if this game is over
		 */
		public void run() {
			while(!shutdown){
				if(side == false){//server
					if(theModel.getPlayers()[0].getUpdateBoard() == true){
						if(theModel.getPlayers()[0].getIsGameOver() == true && theModel.getPlayers()[1].getIsGameOver()){
							gameOver(0);
						}
						if(theModel.getPlayers()[0].getIsFired() == true){
							theView.getGridPlayer1Ships(theModel.getPlayers()[0].getShipX(), theModel.getPlayers()[0].getShipY()).setBackground(Color.RED);
							for(int y=0; y<(Math.abs(theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()-theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertEnd())+3);y++)
								for(int x=0; x<(Math.abs(theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzStart()-theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzEnd())+3);x++){
									try{
										
										if(theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()==theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertEnd()){
											if(theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzStart()<theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzEnd()){
												
												if(!(theView.getGridPlayer1Ships((theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzStart()-1+x), (theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()-1+y)).getBackground()==Color.RED))
													theView.getGridPlayer1Ships((theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzStart()-1+x), (theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()-1+y)).setBackground(Color.BLUE);
											}
											else{
												
												if(!(theView.getGridPlayer1Ships((theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzEnd()-1+x), (theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()-1+y)).getBackground()==Color.RED))
													theView.getGridPlayer1Ships((theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzEnd()-1+x), (theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()-1+y)).setBackground(Color.BLUE);
											}
										}
										else{
											if(theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()<theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertEnd()){
												
												if(!(theView.getGridPlayer1Ships((theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzStart()-1+x), (theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()-1+y)).getBackground()==Color.RED))
													theView.getGridPlayer1Ships((theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzStart()-1+x), (theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertStart()-1+y)).setBackground(Color.BLUE);
											}
											else{
												
												if(!(theView.getGridPlayer1Ships((theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzEnd()-1+x), (theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertEnd()-1+y)).getBackground()==Color.RED))
													theView.getGridPlayer1Ships((theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getHorzEnd()-1+x), (theModel.getPlayers()[0].getPlayerShips(theModel.getPlayers()[0].getIndexOfShip()).getVertEnd()-1+y)).setBackground(Color.BLUE);
											}
										}
										
									}
									catch(ArrayIndexOutOfBoundsException eObj){
										
									}
								}
							theModel.getPlayers()[0].setIsFired(false);
							theModel.getPlayers()[0].setUpdateBoard(false);						
						}
						else{
							
							if(theView.getGridPlayer1Ships(theModel.getPlayers()[0].getShipX(), theModel.getPlayers()[0].getShipY()).getBackground() == Color.ORANGE){
								theView.getGridPlayer1Ships(theModel.getPlayers()[0].getShipX(), theModel.getPlayers()[0].getShipY()).setBackground(Color.RED);
							}
							else{
								theView.getGridPlayer1Ships(theModel.getPlayers()[0].getShipX(), theModel.getPlayers()[0].getShipY()).setBackground(Color.BLUE);
							}
							theModel.getPlayers()[0].setUpdateBoard(false);
							
						}
					}
				}
				else{
					if(theModel.getPlayers()[1].getUpdateBoard() == true){
						if(theModel.getPlayers()[1].getIsGameOver() == true && theModel.getPlayers()[0].getIsGameOver()){
							gameOver(1);
						}
						if(theModel.getPlayers()[1].getIsFired() == true){
							theView.getGridPlayer2Ships(theModel.getPlayers()[1].getShipX(), theModel.getPlayers()[1].getShipY()).setBackground(Color.RED);
							for(int y=0; y<(Math.abs(theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()-theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertEnd())+3);y++)
								for(int x=0; x<(Math.abs(theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzStart()-theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzEnd())+3);x++){
									try{
											
										if(theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()==theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertEnd()){
											if(theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzStart()<theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzEnd()){
												
												if(!(theView.getGridPlayer2Ships((theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzStart()-1+x), (theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()-1+y)).getBackground()==Color.RED))
													theView.getGridPlayer2Ships((theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzStart()-1+x), (theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()-1+y)).setBackground(Color.BLUE);
											}
											else{
												
												if(!(theView.getGridPlayer2Ships((theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzEnd()-1+x), (theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()-1+y)).getBackground()==Color.RED))
													theView.getGridPlayer2Ships((theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzEnd()-1+x), (theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()-1+y)).setBackground(Color.BLUE);
											}
										}
										else{
											if(theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()<theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertEnd()){
												
												if(!(theView.getGridPlayer2Ships((theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzStart()-1+x), (theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()-1+y)).getBackground()==Color.RED))
													theView.getGridPlayer2Ships((theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzStart()-1+x), (theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertStart()-1+y)).setBackground(Color.BLUE);
											}
											else{
												
												if(!(theView.getGridPlayer2Ships((theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzEnd()-1+x), (theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertEnd()-1+y)).getBackground()==Color.RED))
													theView.getGridPlayer2Ships((theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getHorzEnd()-1+x), (theModel.getPlayers()[1].getPlayerShips(theModel.getPlayers()[1].getIndexOfShip()).getVertEnd()-1+y)).setBackground(Color.BLUE);
											}
										}
										
									}
									catch(ArrayIndexOutOfBoundsException eObj){
										
									}
								}
							theModel.getPlayers()[1].setIsFired(false);
							theModel.getPlayers()[1].setUpdateBoard(false);
			
						}
						else{
							
							if(theView.getGridPlayer2Ships(theModel.getPlayers()[1].getShipX(), theModel.getPlayers()[1].getShipY()).getBackground() == Color.ORANGE){
								theView.getGridPlayer2Ships(theModel.getPlayers()[1].getShipX(), theModel.getPlayers()[1].getShipY()).setBackground(Color.RED);
							}
							else{
								theView.getGridPlayer2Ships(theModel.getPlayers()[1].getShipX(), theModel.getPlayers()[1].getShipY()).setBackground(Color.BLUE);
							}
							theModel.getPlayers()[1].setUpdateBoard(false);
							
						}
					}
				}
			}
		}
		/**
		 * Stop run
		 */
		public void shutdown(){
			
			shutdown = true;
			
		}
		/**
		 * Creating thread
		 * @throws IOException
		 */
		public void start() throws IOException{  
			
				Thread thread = new Thread(this);                   
				thread.start();
				
		}

	}
	/**
	 * Function which color not damaged ships if you win
	 * Also write if you win/lose and close the game
	 * @param tmp
	 * 			set side - if tmp = 0 it is server
	 * 					   if tmp = 1 it is client
	 */
	public void gameOver(int tmp) {
		
		for (int y=0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				if(theModel.getPlayers()[1].getShipsLeft() == 0){
					if (theModel.getPlayers()[0].getHits(x, y) == true && theView.getGridPlayer1(x, y).getBackground() != Color.RED) {
						theView.getGridPlayer1(x, y).setBackground(Color.GREEN);
					}
				}
				else{
					if (theModel.getPlayers()[1].getHits(x, y) == true && theView.getGridPlayer2(x, y).getBackground() != Color.RED) {
						theView.getGridPlayer2(x, y).setBackground(Color.GREEN);
					}
				}
			}
		}
		if(tmp==0){//server
			theMonitor.shutdown();
			if(theModel.getPlayers()[0].getShipsLeft() == 0){
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "The game is over! You lose. Good bye!");
				theModel.getMainServer().shutdown();
				theModel.getMainServer().stop();
				System.exit(0);
			}
			else{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "The game is over! You win. Good bye!");
				theModel.getMainServer().shutdown();
				theModel.getMainServer().stop();
				System.exit(0);			
			}
		}
		else{//client
			theMonitor.shutdown();
			if(theModel.getPlayers()[0].getShipsLeft() == 0){
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "The game is over! You win. Good bye!");
				theModel.getMainClient().shutdown();
				theModel.getMainClient().stop();
				System.exit(0);
			}
			else{
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "The game is over! You lose. Good bye!");
				theModel.getMainClient().shutdown();
				theModel.getMainClient().stop();
				System.exit(0);			
			}
		}
	}
	/** 
	 * When player 1 place his ships he activate this actionlistener
	 * This class checks if player has free ships and free ships of that type
	 * Also class checks the validity of placing ships
	 */
	class PlaceButtonPlayer1Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			boolean checkShip=false;
			for(int i=0;i<10;i++){
				if(theModel.getPlayers()[0].getActiveShip().getSize()==theModel.getPlayers()[0].getPlayerShips(i).getSize() && theModel.getPlayers()[0].getPlayerShips(i).isPlaced()==false){
					checkShip=true;
				}
			}
			if(checkShip==false){
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Sorry you have no ships of that type.");
			}
			if(theModel.getPlayers()[0].getIsPlacingShip()==false && checkShip==true){	
				Object source = (JButton) e.getSource();
				for (int y = 0; y < 10; y++) {
					for (int x = 0; x < 10; x++) {
						if (source == theView.getGridPlayer1(x, y)) {
							theModel.getPlayers()[0].setIsPlacingShip(true);
							theModel.getPlayers()[0].setShipX(x);
							theModel.getPlayers()[0].setShipY(y);
						}
					}
				}
				boolean flag1 = false;
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {		
						try{
							if(theModel.getPlayers()[0].getShipPlacement(theModel.getPlayers()[0].getShipX()-1+x, theModel.getPlayers()[0].getShipY()-1+y)!=null){
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Sorry the ship can't be placed here, this position is already occupied");
								theModel.getPlayers()[0].setIsPlacingShip(false);
								theModel.getPlayers()[0].setShipX(0);
								theModel.getPlayers()[0].setShipY(0);
								flag1=true;
								break;
							}
						}
						catch(ArrayIndexOutOfBoundsException eObj){
							
						}
					}
					if(flag1==true)
						break;
				}
				if(theModel.getPlayers()[0].getIsPlacingShip()==true){
					
					theView.getGridPlayer1(theModel.getPlayers()[0].getShipX(),theModel.getPlayers()[0].getShipY()).setBackground(Color.ORANGE);
					
				}
				if(theModel.getPlayers()[0].getActiveShip().getSize()==1 && theModel.getPlayers()[0].getIsPlacingShip()==true){
					theModel.getPlayers()[0].placeShip(theModel.getPlayers()[0].getActiveShip().getSize(),theModel.getPlayers()[0].getShipX(),theModel.getPlayers()[0].getShipY(),theModel.getPlayers()[0].getShipX(),theModel.getPlayers()[0].getShipY());
					theView.getGridPlayer1(theModel.getPlayers()[0].getShipX(), theModel.getPlayers()[0].getShipY()).setBackground(Color.ORANGE);
					theModel.getPlayers()[0].incShipsPlaced();
					if (theModel.getPlayers()[0].getShipsPlaced() >= 10) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,"All your ships have been placed.");
					}
					theModel.getPlayers()[0].setIsPlacingShip(false);
					theModel.getPlayers()[0].setShipX(0);
					theModel.getPlayers()[0].setShipY(0);
				}
			}
			else if(theModel.getPlayers()[0].getIsPlacingShip()==true && checkShip==true){
				Object source = (JButton) e.getSource();
				int i = 0, j = 0;
				for (int y = 0; y < 10; y++) {
					for (int x = 0; x < 10; x++) {
						if (source == theView.getGridPlayer1(x, y)) {
							i = x;
							j = y;
						}
					}
				}
				boolean flag = false;
				for(int y=0; y<(Math.abs(theModel.getPlayers()[0].getShipY()-j)+3);y++){
					for(int x=0; x<(Math.abs(theModel.getPlayers()[0].getShipX()-i)+3);x++){
						try{
							if(theModel.getPlayers()[0].getShipY()==j){ //horizontal
								if(theModel.getPlayers()[0].getShipX()<i){
									
									if(theModel.getPlayers()[0].getShipPlacement(theModel.getPlayers()[0].getShipX()-1+x, theModel.getPlayers()[0].getShipY()-1+y)!=null){
										flag=true;
										break;
									}
								}
								else{
									if(theModel.getPlayers()[0].getShipPlacement(i-1+x, theModel.getPlayers()[0].getShipY()-1+y)!=null){
										flag=true;
										break;
									}
								}
							}
							else{ // vertical
								if(theModel.getPlayers()[0].getShipY()<j){
									if(theModel.getPlayers()[0].getShipPlacement(theModel.getPlayers()[0].getShipX()-1+x, theModel.getPlayers()[0].getShipY()-1+y)!=null){
										flag=true;
										break;
									}
								}
								else{
									if(theModel.getPlayers()[0].getShipPlacement(theModel.getPlayers()[0].getShipX()-1+x, j-1+y)!=null){
										flag=true;
										break;
									}
								}
							}
						}
						catch(ArrayIndexOutOfBoundsException eObj){
							
						}
					}
					if(flag==true)
						break;
				}
				if(flag==true){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Sorry the ship can't be placed here, this position is already occupied");
					theModel.getPlayers()[0].setIsPlacingShip(false);
					theView.getGridPlayer1(theModel.getPlayers()[0].getShipX(),theModel.getPlayers()[0].getShipY()).setBackground(null);
					theModel.getPlayers()[0].setShipX(0);
					theModel.getPlayers()[0].setShipY(0);
				}
				else if(theModel.getPlayers()[0].getShipX()-i!=0 && theModel.getPlayers()[0].getShipY()-j!=0){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Sorry, you can't put the ship because the position is incorrect!");
					theModel.getPlayers()[0].setIsPlacingShip(false);
					theView.getGridPlayer1(theModel.getPlayers()[0].getShipX(),theModel.getPlayers()[0].getShipY()).setBackground(null);
					theModel.getPlayers()[0].setShipX(0);
					theModel.getPlayers()[0].setShipY(0);
				}
				else if(Math.abs(theModel.getPlayers()[0].getShipY()-j)+1!=theModel.getPlayers()[0].getActiveShip().getSize() && Math.abs(theModel.getPlayers()[0].getShipX()-i)+1!=theModel.getPlayers()[0].getActiveShip().getSize()){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,"Sorry, you can't put the ship because the lenght is incorrect!");
					theModel.getPlayers()[0].setIsPlacingShip(false);
					theView.getGridPlayer1(theModel.getPlayers()[0].getShipX(),theModel.getPlayers()[0].getShipY()).setBackground(null);
					theModel.getPlayers()[0].setShipX(0);
					theModel.getPlayers()[0].setShipY(0);
				}
				else{
					theModel.getPlayers()[0].placeShip(theModel.getPlayers()[0].getActiveShip().getSize(),theModel.getPlayers()[0].getShipX(),theModel.getPlayers()[0].getShipY(),i,j);
					if(theModel.getPlayers()[0].getShipX()-i==0){
						for(int y=0;y<theModel.getPlayers()[0].getActiveShip().getSize();y++){
							if(theModel.getPlayers()[0].getShipY()<j)
								theView.getGridPlayer1(theModel.getPlayers()[0].getShipX(), theModel.getPlayers()[0].getShipY()+y).setBackground(Color.ORANGE);
							else
								theView.getGridPlayer1(theModel.getPlayers()[0].getShipX(), j+y).setBackground(Color.ORANGE);
						}
					}
					else{
						for(int x=0;x<theModel.getPlayers()[0].getActiveShip().getSize();x++){
							if(theModel.getPlayers()[0].getShipX()<i)
								theView.getGridPlayer1(theModel.getPlayers()[0].getShipX()+x, theModel.getPlayers()[0].getShipY()).setBackground(Color.ORANGE);
							else
								theView.getGridPlayer1(i+x, theModel.getPlayers()[0].getShipY()).setBackground(Color.ORANGE);
						}		
					}
					theModel.getPlayers()[0].incShipsPlaced();
					if (theModel.getPlayers()[0].getShipsPlaced() >= 10) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,"All your ships have been placed.");
					}
					theModel.getPlayers()[0].setIsPlacingShip(false);
					theModel.getPlayers()[0].setShipX(0);
					theModel.getPlayers()[0].setShipY(0);
				}
			}	
		}
	}
	/**
	 * When player 2 hit board
	 */
	class BoardButtonPlayer1Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
				Object source = (JButton) e.getSource();
				int i = 0, j = 0;
				for (int y = 0; y < 10; y++) {
					for (int x = 0; x < 10; x++) {
						if (source == theView.getGridPlayer1(x, y)) {
							i = x;
							j = y;
						}
					}
				}
				if(theModel.getPlayers()[0].getHits(i, j) && theModel.getPlayers()[1].getTurn() && theView.getGridPlayer1(i,j).getBackground()!=Color.RED && theView.getGridPlayer1(i,j).getBackground()!=Color.GRAY){
					int index;
					theView.getGridPlayer1(i,j).setBackground(Color.RED);
					theModel.getPlayers()[0].setActiveShip(theModel.getPlayers()[0].getShipPlacement(i, j));
					for(index=0;index<10;index++){
						if((theModel.getPlayers()[0].getActiveShip().getSize()==theModel.getPlayers()[0].getPlayerShips(index).getSize()) && (theModel.getPlayers()[0].getActiveShip().getVertStart()==theModel.getPlayers()[0].getPlayerShips(index).getVertStart()) && (theModel.getPlayers()[0].getActiveShip().getHorzStart()==theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()))
							break;
					}
					theModel.getPlayers()[0].getPlayerShips(index).hit();
					if(theModel.getPlayers()[0].getPlayerShips(index).wasFired()){
						theModel.getPlayers()[0].setIndexOfShip(index);
						theModel.getPlayers()[0].setIsFired(true);
						for(int y=0; y<(Math.abs(theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-theModel.getPlayers()[0].getPlayerShips(index).getVertEnd())+3);y++)
							for(int x=0; x<(Math.abs(theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()-theModel.getPlayers()[0].getPlayerShips(index).getHorzEnd())+3);x++){
								try{
									if(theModel.getPlayers()[0].getPlayerShips(index).getVertStart()==theModel.getPlayers()[0].getPlayerShips(index).getVertEnd()){
										if(theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()<theModel.getPlayers()[0].getPlayerShips(index).getHorzEnd()){
											theModel.getPlayers()[0].setHit(theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()-1+x, theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y, false);
											if(!(theView.getGridPlayer1((theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()-1+x), (theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y)).getBackground()==Color.RED))
												theView.getGridPlayer1((theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()-1+x), (theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y)).setBackground(Color.GRAY);
										}
										else{
											theModel.getPlayers()[0].setHit(theModel.getPlayers()[0].getPlayerShips(index).getHorzEnd()-1+x, theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y, false);
											if(!(theView.getGridPlayer1((theModel.getPlayers()[0].getPlayerShips(index).getHorzEnd()-1+x), (theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y)).getBackground()==Color.RED))
												theView.getGridPlayer1((theModel.getPlayers()[0].getPlayerShips(index).getHorzEnd()-1+x), (theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y)).setBackground(Color.GRAY);
										}
									}
									else{
										if(theModel.getPlayers()[0].getPlayerShips(index).getVertStart()<theModel.getPlayers()[0].getPlayerShips(index).getVertEnd()){
											theModel.getPlayers()[0].setHit(theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()-1+x, theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y, false);
											if(!(theView.getGridPlayer1((theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()-1+x), (theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y)).getBackground()==Color.RED))
												theView.getGridPlayer1((theModel.getPlayers()[0].getPlayerShips(index).getHorzStart()-1+x), (theModel.getPlayers()[0].getPlayerShips(index).getVertStart()-1+y)).setBackground(Color.GRAY);
										}
										else{
											theModel.getPlayers()[0].setHit(theModel.getPlayers()[0].getPlayerShips(index).getHorzEnd()-1+x, theModel.getPlayers()[0].getPlayerShips(index).getVertEnd()-1+y, false);
											if(!(theView.getGridPlayer1((theModel.getPlayers()[0].getPlayerShips(index).getHorzEnd()-1+x), (theModel.getPlayers()[0].getPlayerShips(index).getVertEnd()-1+y)).getBackground()==Color.RED))
												theView.getGridPlayer1((theModel.getPlayers()[0].getPlayerShips(index).getHorzEnd()-1+x), (theModel.getPlayers()[0].getPlayerShips(index).getVertEnd()-1+y)).setBackground(Color.GRAY);
										}
									}
								}
								catch(ArrayIndexOutOfBoundsException eObj){
									
								}
							}
						theModel.getPlayers()[0].decShipsLeft();
					}
					if(theModel.getPlayers()[0].getShipsLeft()==0){
						theModel.getPlayers()[0].setIsGameOver(true);
					}
					theModel.getPlayers()[0].setUpdateBoard(true);
					theModel.getPlayers()[0].setShipX(i);
					theModel.getPlayers()[0].setShipY(j);
					theModel.setCheckTurn(false, true);
					theModel.getPlayers()[0].setHit(i, j, false);
				}
				else if(theView.getGridPlayer1(i,j).getBackground()==Color.RED || theView.getGridPlayer1(i,j).getBackground()==Color.GRAY){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "You have atacked this field");
				}
				else if(theModel.getPlayers()[1].getTurn()==false){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "It is not your turn");		
				}
				else{
					theModel.getPlayers()[0].setUpdateBoard(true);
					theModel.getPlayers()[0].setShipX(i);
					theModel.getPlayers()[0].setShipY(j);
					theModel.setCheckTurn(true, false);
					theView.getGridPlayer1(i,j).setBackground(Color.GRAY);
				}
		}	
	}	
	/**
	 * When player 1 hit board
	 */
	class BoardButtonPlayer2Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
				Object source = (JButton) e.getSource();
				int i = 0, j = 0;
				for (int y = 0; y < 10; y++) {
					for (int x = 0; x < 10; x++) {
						if (source == theView.getGridPlayer2(x, y)) {
							i = x;
							j = y;
						}
					}
				}
				if(theModel.getPlayers()[1].getHits(i, j) && theModel.getPlayers()[0].getTurn() && theView.getGridPlayer2(i,j).getBackground()!=Color.RED && theView.getGridPlayer2(i,j).getBackground()!=Color.GRAY){
					int index;
					theView.getGridPlayer2(i,j).setBackground(Color.RED);
					theModel.getPlayers()[1].setActiveShip(theModel.getPlayers()[1].getShipPlacement(i, j));
					for(index=0;index<10;index++){
						if((theModel.getPlayers()[1].getActiveShip().getSize()==theModel.getPlayers()[1].getPlayerShips(index).getSize()) && (theModel.getPlayers()[1].getActiveShip().getVertStart()==theModel.getPlayers()[1].getPlayerShips(index).getVertStart()) && (theModel.getPlayers()[1].getActiveShip().getHorzStart()==theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()))
							break;
					}
					theModel.getPlayers()[1].getPlayerShips(index).hit();
					if(theModel.getPlayers()[1].getPlayerShips(index).wasFired()){
						theModel.getPlayers()[1].setIndexOfShip(index);
						theModel.getPlayers()[1].setIsFired(true);
						for(int y=0; y<(Math.abs(theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-theModel.getPlayers()[1].getPlayerShips(index).getVertEnd())+3);y++)
							for(int x=0; x<(Math.abs(theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()-theModel.getPlayers()[1].getPlayerShips(index).getHorzEnd())+3);x++){
								try{
									if(theModel.getPlayers()[1].getPlayerShips(index).getVertStart()==theModel.getPlayers()[1].getPlayerShips(index).getVertEnd()){
										if(theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()<theModel.getPlayers()[1].getPlayerShips(index).getHorzEnd()){
											theModel.getPlayers()[1].setHit(theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()-1+x, theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y, false);
											if(!(theView.getGridPlayer2((theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()-1+x), (theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y)).getBackground()==Color.RED))
												theView.getGridPlayer2((theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()-1+x), (theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y)).setBackground(Color.GRAY);
										}
										else{
											theModel.getPlayers()[1].setHit(theModel.getPlayers()[1].getPlayerShips(index).getHorzEnd()-1+x, theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y, false);
											if(!(theView.getGridPlayer2((theModel.getPlayers()[1].getPlayerShips(index).getHorzEnd()-1+x), (theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y)).getBackground()==Color.RED))
												theView.getGridPlayer2((theModel.getPlayers()[1].getPlayerShips(index).getHorzEnd()-1+x), (theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y)).setBackground(Color.GRAY);
										}
									}
									else{
										if(theModel.getPlayers()[1].getPlayerShips(index).getVertStart()<theModel.getPlayers()[1].getPlayerShips(index).getVertEnd()){
											theModel.getPlayers()[1].setHit(theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()-1+x, theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y, false);
											if(!(theView.getGridPlayer2((theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()-1+x), (theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y)).getBackground()==Color.RED))
												theView.getGridPlayer2((theModel.getPlayers()[1].getPlayerShips(index).getHorzStart()-1+x), (theModel.getPlayers()[1].getPlayerShips(index).getVertStart()-1+y)).setBackground(Color.GRAY);
										}
										else{
											theModel.getPlayers()[1].setHit(theModel.getPlayers()[1].getPlayerShips(index).getHorzEnd()-1+x, theModel.getPlayers()[1].getPlayerShips(index).getVertEnd()-1+y, false);
											if(!(theView.getGridPlayer2((theModel.getPlayers()[1].getPlayerShips(index).getHorzEnd()-1+x), (theModel.getPlayers()[1].getPlayerShips(index).getVertEnd()-1+y)).getBackground()==Color.RED))
												theView.getGridPlayer2((theModel.getPlayers()[1].getPlayerShips(index).getHorzEnd()-1+x), (theModel.getPlayers()[1].getPlayerShips(index).getVertEnd()-1+y)).setBackground(Color.GRAY);
										}
									}
								}
								catch(ArrayIndexOutOfBoundsException eObj){
									
								}
							}
						theModel.getPlayers()[1].decShipsLeft();
					}
					if(theModel.getPlayers()[1].getShipsLeft()==0){
						theModel.getPlayers()[1].setIsGameOver(true);
					}
					theModel.getPlayers()[1].setUpdateBoard(true);
					theModel.getPlayers()[1].setShipX(i);
					theModel.getPlayers()[1].setShipY(j);
					theModel.setCheckTurn(true, false);
					theModel.getPlayers()[1].setHit(i, j, false);
				}
				else if(theView.getGridPlayer2(i,j).getBackground()==Color.RED || theView.getGridPlayer2(i,j).getBackground()==Color.GRAY){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "You have atacked this field");
				}
				else if(theModel.getPlayers()[0].getTurn()==false){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "It is not your turn");		
				}
				else{
					theModel.getPlayers()[1].setUpdateBoard(true);
					theModel.getPlayers()[1].setShipX(i);
					theModel.getPlayers()[1].setShipY(j);
					theModel.setCheckTurn(false, true);
					theView.getGridPlayer2(i,j).setBackground(Color.GRAY);
				}
		}	
	}	
	/** 
	 * When player 2 place his ships he activate this actionlistener
	 * This class checks if player has free ships and free ships of that type
	 * Also class checks the validity of placing ships
	 */
	class PlaceButtonPlayer2Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e){
			boolean checkShip=false;
			for(int i=0;i<10;i++){
				if(theModel.getPlayers()[1].getActiveShip().getSize()==theModel.getPlayers()[1].getPlayerShips(i).getSize() && theModel.getPlayers()[1].getPlayerShips(i).isPlaced()==false){
					checkShip=true;
				}
			}
			if(checkShip==false){
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Sorry you have no ships of that type.");
			}
			if(theModel.getPlayers()[1].getIsPlacingShip()==false && checkShip==true){	
				Object source = (JButton) e.getSource();
				for (int y = 0; y < 10; y++) {
					for (int x = 0; x < 10; x++) {
						if (source == theView.getGridPlayer2(x, y)) {
							theModel.getPlayers()[1].setIsPlacingShip(true);
							theModel.getPlayers()[1].setShipX(x);
							theModel.getPlayers()[1].setShipY(y);
						}
					}
				}
				boolean flag1 = false;
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {		
						try{
							if(theModel.getPlayers()[1].getShipPlacement(theModel.getPlayers()[1].getShipX()-1+x, theModel.getPlayers()[1].getShipY()-1+y)!=null){
								JFrame frame = new JFrame();
								JOptionPane.showMessageDialog(frame, "Sorry the ship can't be placed here, this position is already occupied");
								theModel.getPlayers()[1].setIsPlacingShip(false);
								theModel.getPlayers()[1].setShipX(0);
								theModel.getPlayers()[1].setShipY(0);
								flag1=true;
								break;
							}
						}
						catch(ArrayIndexOutOfBoundsException eObj){
							
						}
					}
					if(flag1==true)
						break;
				}
				if(theModel.getPlayers()[1].getIsPlacingShip()==true){
					
					theView.getGridPlayer2(theModel.getPlayers()[1].getShipX(),theModel.getPlayers()[1].getShipY()).setBackground(Color.ORANGE);
					
				}
				if(theModel.getPlayers()[1].getActiveShip().getSize()==1 && theModel.getPlayers()[1].getIsPlacingShip()==true){
					theModel.getPlayers()[1].placeShip(theModel.getPlayers()[1].getActiveShip().getSize(),theModel.getPlayers()[1].getShipX(),theModel.getPlayers()[1].getShipY(),theModel.getPlayers()[1].getShipX(),theModel.getPlayers()[1].getShipY());
					theView.getGridPlayer2(theModel.getPlayers()[1].getShipX(), theModel.getPlayers()[1].getShipY()).setBackground(Color.ORANGE);
					theModel.getPlayers()[1].incShipsPlaced();
					if (theModel.getPlayers()[1].getShipsPlaced() >= 10) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,"All your ships have been placed.");
					}
					theModel.getPlayers()[1].setIsPlacingShip(false);
					theModel.getPlayers()[1].setShipX(0);
					theModel.getPlayers()[1].setShipY(0);
				}
			}
			else if(theModel.getPlayers()[1].getIsPlacingShip()==true && checkShip==true){
				Object source = (JButton) e.getSource();
				int i = 0, j = 0;
				for (int y = 0; y < 10; y++) {
					for (int x = 0; x < 10; x++) {
						if (source == theView.getGridPlayer2(x, y)) {
							i = x;
							j = y;
						}
					}
				}
				boolean flag = false;
				for(int y=0; y<(Math.abs(theModel.getPlayers()[1].getShipY()-j)+3);y++){
					for(int x=0; x<(Math.abs(theModel.getPlayers()[1].getShipX()-i)+3);x++){
						try{
							if(theModel.getPlayers()[1].getShipY()==j){ //horizontal
								if(theModel.getPlayers()[1].getShipX()<i){
									
									if(theModel.getPlayers()[1].getShipPlacement(theModel.getPlayers()[1].getShipX()-1+x, theModel.getPlayers()[1].getShipY()-1+y)!=null){
										flag=true;
										break;
									}
								}
								else{
									if(theModel.getPlayers()[1].getShipPlacement(i-1+x, theModel.getPlayers()[1].getShipY()-1+y)!=null){
										flag=true;
										break;
									}
								}
							}
							else{ // vertical
								if(theModel.getPlayers()[1].getShipY()<j){
									if(theModel.getPlayers()[1].getShipPlacement(theModel.getPlayers()[1].getShipX()-1+x, theModel.getPlayers()[1].getShipY()-1+y)!=null){
										flag=true;
										break;
									}
								}
								else{
									if(theModel.getPlayers()[1].getShipPlacement(theModel.getPlayers()[1].getShipX()-1+x, j-1+y)!=null){
										flag=true;
										break;
									}
								}
							}
						}
						catch(ArrayIndexOutOfBoundsException eObj){
							
						}
					}
					if(flag==true)
						break;
				}
				if(flag==true){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Sorry the ship can't be placed here, this position is already occupied");
					theModel.getPlayers()[1].setIsPlacingShip(false);
					theView.getGridPlayer2(theModel.getPlayers()[1].getShipX(),theModel.getPlayers()[1].getShipY()).setBackground(null);
					theModel.getPlayers()[1].setShipX(0);
					theModel.getPlayers()[1].setShipY(0);
				}
				else if(theModel.getPlayers()[1].getShipX()-i!=0 && theModel.getPlayers()[1].getShipY()-j!=0){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Sorry, you can't put the ship because the position is incorrect!");
					theModel.getPlayers()[1].setIsPlacingShip(false);
					theView.getGridPlayer2(theModel.getPlayers()[1].getShipX(),theModel.getPlayers()[1].getShipY()).setBackground(null);
					theModel.getPlayers()[1].setShipX(0);
					theModel.getPlayers()[1].setShipY(0);
				}
				else if(Math.abs(theModel.getPlayers()[1].getShipY()-j)+1!=theModel.getPlayers()[1].getActiveShip().getSize() && Math.abs(theModel.getPlayers()[1].getShipX()-i)+1!=theModel.getPlayers()[1].getActiveShip().getSize()){
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame,"Sorry, you can't put the ship because the lenght is incorrect!");
					theModel.getPlayers()[1].setIsPlacingShip(false);
					theView.getGridPlayer2(theModel.getPlayers()[1].getShipX(),theModel.getPlayers()[1].getShipY()).setBackground(null);
					theModel.getPlayers()[1].setShipX(0);
					theModel.getPlayers()[1].setShipY(0);
				}
				else{
					theModel.getPlayers()[1].placeShip(theModel.getPlayers()[1].getActiveShip().getSize(),theModel.getPlayers()[1].getShipX(),theModel.getPlayers()[1].getShipY(),i,j);
					if(theModel.getPlayers()[1].getShipX()-i==0){
						for(int y=0;y<theModel.getPlayers()[1].getActiveShip().getSize();y++){
							if(theModel.getPlayers()[1].getShipY()<j)
								theView.getGridPlayer2(theModel.getPlayers()[1].getShipX(), theModel.getPlayers()[1].getShipY()+y).setBackground(Color.ORANGE);
							else
								theView.getGridPlayer2(theModel.getPlayers()[1].getShipX(), j+y).setBackground(Color.ORANGE);
						}
					}
					else{
						for(int x=0;x<theModel.getPlayers()[1].getActiveShip().getSize();x++){
							if(theModel.getPlayers()[1].getShipX()<i)
								theView.getGridPlayer2(theModel.getPlayers()[1].getShipX()+x, theModel.getPlayers()[1].getShipY()).setBackground(Color.ORANGE);
							else
								theView.getGridPlayer2(i+x, theModel.getPlayers()[1].getShipY()).setBackground(Color.ORANGE);
						}		
					}
					theModel.getPlayers()[1].incShipsPlaced();
					if (theModel.getPlayers()[1].getShipsPlaced() >= 10) {
						JFrame frame = new JFrame();
						JOptionPane.showMessageDialog(frame,"All your ships have been placed.");
					}
					theModel.getPlayers()[1].setIsPlacingShip(false);
					theModel.getPlayers()[1].setShipX(0);
					theModel.getPlayers()[1].setShipY(0);
				}
			}	
		}
	}
	/**
	 * Set ship4 as active ship of first player
	 */
	private class Ship4Player1Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			ShipType ship = new ShipType(4);
			theModel.getPlayers()[0].setActiveShip(ship);
		}
		
	}
	/**
	 * Set ship3 as active ship of first player
	 */
	private class Ship3Player1Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			ShipType ship = new ShipType(3);
			theModel.getPlayers()[0].setActiveShip(ship);
			
		}
		
	}
	/**
	 * Set ship2 as active ship of first player
	 */
	private class Ship2Player1Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			ShipType ship = new ShipType(2);
			theModel.getPlayers()[0].setActiveShip(ship);
			
		}
		
	}
	/**
	 * Set ship1 as active ship of first player
	 */
	private class Ship1Player1Listener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
		
			ShipType ship = new ShipType(1);
			theModel.getPlayers()[0].setActiveShip(ship);
			
		}
	
	}
	/**
	 * Set ship4 as active ship of second player
	 */
	private class Ship4Player2Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			ShipType ship = new ShipType(4);
			theModel.getPlayers()[1].setActiveShip(ship);
			
		}
		
	}
	/**
	 * Set ship3 as active ship of second player
	 */
	private class Ship3Player2Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			ShipType ship = new ShipType(3);
			theModel.getPlayers()[1].setActiveShip(ship);
			
		}
		
	}
	/**
	 * Set ship2 as active ship of second player
	 */
	private class Ship2Player2Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			ShipType ship = new ShipType(2);
			theModel.getPlayers()[1].setActiveShip(ship);
			
		}
		
	}
	/**
	 * Set ship1 as active ship of second player
	 */
	private class Ship1Player2Listener implements ActionListener{
	
		public void actionPerformed(ActionEvent e){
		
			ShipType ship = new ShipType(1);
			theModel.getPlayers()[1].setActiveShip(ship);
			
		}
	
	}
	/**
	 * if all ships have been placed:
	 * Closing startingserver and starting mainserver and updatemonitor
	 * Adding GameBoardPlayer2Panel and Player1ShipsPanel
	 */
	private class DeployPlayer1Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			if(theModel.getPlayers()[0].getShipsPlaced()==10 && theModel.getPlayers()[1].getShipsPlaced()==10)
			{
				theModel.getStartingServer().shutdown();
				theModel.getStartingServer().stop();
				theModel.setCheckTurn(true, false);
				theModel.createMainServer();
				theModel.getPlayers()[0].setHits();
				theModel.getPlayers()[1].setHits();
				theView.setGameBoardPlayer2Panel();
				theView.addBoardButtonPlayer2Listener(new BoardButtonPlayer2Listener());
				theView.setPlayer1ShipsPanel();
				for(int y=0;y<10;y++)
					for(int x=0;x<10;x++){
						if(theModel.getPlayers()[0].getHits(x, y))
							theView.getGridPlayer1Ships(x, y).setBackground(Color.ORANGE);
						else
							theView.getGridPlayer1Ships(x, y).setBackground(Color.GRAY);
					}
				theView.frame.remove(theView.setShipsPanel);
				theView.frame.remove(theView.getGridPlayer1Panel());
				theView.frame.add(theView.getGameBoardPlayer2Panel());
				theView.frame.add(theView.getPlayer1ShipsPanel());
				theView.frame.setTitle("Battleship Server");
				theMonitor = new UpdateMonitor(false);
				theView.frame.revalidate();
				theView.frame.repaint();
			}
			else if(theModel.getPlayers()[1].getShipsPlaced()!=10 && theModel.getPlayers()[0].getShipsPlaced()==10){
				
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Wait for your opponent!");
				
			}
			else{
				
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "You have free ships!");
				
			}
			
		}
		
	}
	/**
	 * if all ships have been placed:
	 * Closing startingclient and starting mainclient and updatemonitor
	 * Adding GameBoardPlayer1Panel and Player2ShipsPanel
	 */	
	private class DeployPlayer2Listener implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			if(theModel.getPlayers()[1].getShipsPlaced()==10 && theModel.getPlayers()[0].getShipsPlaced()==10)
			{
				theModel.getStartingClient().shutdown();
				theModel.getStartingClient().stop();
				theModel.setCheckTurn(true, false);
				theModel.createMainClient();
				theModel.getPlayers()[0].setHits();
				theModel.getPlayers()[1].setHits();
				theView.setGameBoardPlayer1Panel();
				theView.addBoardButtonPlayer1Listener(new BoardButtonPlayer1Listener());
				theView.setPlayer2ShipsPanel();
				for(int y=0;y<10;y++)
					for(int x=0;x<10;x++){
						if(theModel.getPlayers()[1].getHits(x, y))
							theView.getGridPlayer2Ships(x, y).setBackground(Color.ORANGE);
						else
							theView.getGridPlayer2Ships(x, y).setBackground(Color.GRAY);
					}
				theView.frame.remove(theView.setShipsPanelPlayer2);
				theView.frame.remove(theView.getGridPlayer2Panel());
				theView.frame.add(theView.getGameBoardPlayer1Panel());
				theView.frame.add(theView.getPlayer2ShipsPanel());
				theView.frame.setTitle("Battleship Client");
				theMonitor = new UpdateMonitor(true);
				theView.frame.revalidate();
				theView.frame.repaint();

			}
			else if(theModel.getPlayers()[0].getShipsPlaced()!=10 && theModel.getPlayers()[1].getShipsPlaced()==10){
				
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "Wait for your opponent!");
				
			}
			else{
				
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "You have free ships!");
	
			}
			
		}
		
	}
		
}
	

