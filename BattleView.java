package BattleShip;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BattleView{

	/**
	 *  main frame of the View
	 */
	public JFrame frame = new JFrame();
	
	/**
	 *  Panel with buttons Start and Exit
	 */
	public JPanel menuPanel = new JPanel();
	/**
	 *  Panel with ShipTypes and Deploy1 Button
	 */
	public JPanel setShipsPanel = new JPanel();
	/**
	 *  Panel with ShipTypes and Deploy2 Button
	 */
	public JPanel setShipsPanelPlayer2 = new JPanel();
	/**
	 *  Panel where the first Player puts his Ships 
	 */
	public JPanel gridPlayer1Panel = new JPanel();
	/**
	 *  Panel where the second Player puts his Ships 
	 */
	public JPanel gridPlayer2Panel = new JPanel();
	/**
	 *  Panel where the second Player hit Ships 
	 */
	public JPanel gameBoardPlayer1Panel = new JPanel();
	/**
	 *  Panel where the first Player hit Ships 
	 */
	public JPanel gameBoardPlayer2Panel = new JPanel();
	/**
	 *  Panel where are the first Player Ships
	 */
	public JPanel Player1ShipsPanel = new JPanel();
	/**
	 *  Panel where are the second Player Ships
	 */
	public JPanel Player2ShipsPanel = new JPanel();
	/**
	 * Create server button
	 */
	private JButton ServerButton = new JButton("Create server");
	/**
	 * Connect to server button
	 */
	private JButton ClientButton = new JButton("Connect to server");
	/**
	 * Exit button
	 */
	private JButton ExitButton = new JButton("Exit");
	/**
	 * 4-health ship button
	 */
	private JButton Ship4 = new JButton("4");
	/**
	 * 3-health ship button
	 */
	private JButton Ship3 = new JButton("3");
	/**
	 * 2-health ship button
	 */
	private JButton Ship2 = new JButton("2");
	/**
	 * 1-health ship button
	 */
	private JButton Ship1 = new JButton("1");
	/**
	 * Deploy button of the first player
	 */
	private JButton DeployPlayer1 = new JButton("Deploy");
	/**
	 * Deploy button of the second player
	 */
	private JButton DeployPlayer2 = new JButton("Deploy");
	/**
	 * Array where the first Player puts his Ships 
	 */
	private JButton[][] gridPlayer1 = new JButton[10][10];
	/**
	 * Array where the second Player puts his Ships 
	 */
	private JButton[][] gridPlayer2 = new JButton[10][10];
	/**
	 * Array with the first Player ships
	 */
	private JButton[][] gridPlayer1Ships = new JButton[10][10];
	/**
	 * Array with the second Player ships
	 */
	private JButton[][] gridPlayer2Ships = new JButton[10][10];
	
	/**
	 * Create frame, menuPanel and setShipsPanel
	 */
	public BattleView(){
		
		frame.setLayout(new GridLayout());
		menuPanel.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960,1080);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Menu");
		frame.setResizable(false);

		ServerButton.setBounds(100, 300, 200, 70);
		menuPanel.add(ServerButton);
		ClientButton.setBounds(100, 400, 200, 70);
		menuPanel.add(ClientButton);
		ExitButton.setBounds(100, 500, 200, 70);
		menuPanel.add(ExitButton);
		frame.add(menuPanel);
		
		setShipsPanel.setLayout(null);
		Ship4.setBounds(100, 100, 200, 100);
		setShipsPanel.add(Ship4);
		Ship3.setBounds(100, 300, 200, 100);
		setShipsPanel.add(Ship3);
		Ship2.setBounds(100, 500, 200, 100);
		setShipsPanel.add(Ship2);
		Ship1.setBounds(100, 700, 200, 100);
		setShipsPanel.add(Ship1);
		DeployPlayer1.setBounds(100, 900, 200, 100);
		setShipsPanel.add(DeployPlayer1);

	}
	/**
	 * Set panel where player 1 set his ships	
	 */
	public void setGridPlayer1Panel(){
		
		gridPlayer1Panel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1[x][y] = new JButton();
				gridPlayer1Panel.add(gridPlayer1[x][y]);
			}
		
	}
	/**
	 * @return gridPlayer1Panel
	 */
	public JPanel getGridPlayer1Panel(){
		
		return gridPlayer1Panel;
		
	}
	/**
	 * Set panel where player 2 set his ships	
	 */
	public void setGridPlayer2Panel(){
		
		gridPlayer2Panel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2[x][y] = new JButton();
				gridPlayer2Panel.add(gridPlayer2[x][y]);
			}
		
	}
	/**
	 * @return gridPlayer2Panel
	 */
	public JPanel getGridPlayer2Panel(){
		
		return gridPlayer2Panel;
		
	}
	/**
	 * @return setShipsPanelPlayer2
	 */
	public JPanel getShipsPanelPlayer2(){
		
		setShipsPanelPlayer2.setLayout(null);
		Ship4.setBounds(100, 100, 200, 100);
		setShipsPanelPlayer2.add(Ship4);
		Ship3.setBounds(100, 300, 200, 100);
		setShipsPanelPlayer2.add(Ship3);
		Ship2.setBounds(100, 500, 200, 100);
		setShipsPanelPlayer2.add(Ship2);
		Ship1.setBounds(100, 700, 200, 100);
		setShipsPanelPlayer2.add(Ship1);
		DeployPlayer2.setBounds(100, 900, 200, 100);
		setShipsPanelPlayer2.add(DeployPlayer2);
		
		return setShipsPanelPlayer2;
		
	}
	/**
	 * Set panel where the second Player hit Ships 
	 */
	public void setGameBoardPlayer1Panel(){
		
		gameBoardPlayer1Panel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1[x][y] = new JButton();
				gameBoardPlayer1Panel.add(gridPlayer1[x][y]);			
			}
		
	}
	/**
	 * @return gameBoardPlayer1Panel
	 */
	public JPanel getGameBoardPlayer1Panel(){
		
		return gameBoardPlayer1Panel;
		
	}
	/**
	 * Set panel where the first Player hit Ships 
	 */
	public void setGameBoardPlayer2Panel(){
	
		gameBoardPlayer2Panel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2[x][y] = new JButton();
				gameBoardPlayer2Panel.add(gridPlayer2[x][y]);			
			}
	
	}
	/**
	 * @return gameBoardPlayer2Panel
	 */
	public JPanel getGameBoardPlayer2Panel(){
		
		return gameBoardPlayer2Panel;
		
	}
	/**
	 * Set panel where are the first Player Ships
	 */
	public void setPlayer1ShipsPanel(){
	
		Player1ShipsPanel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1Ships[x][y] = new JButton();
				Player1ShipsPanel.add(gridPlayer1Ships[x][y]);
			}
	
	}
	/**
	 * @return Player1ShipPanel
	 */
	public JPanel getPlayer1ShipsPanel(){
		
		return Player1ShipsPanel;
		
	}
	/**
	 * Set panel where are the second Player Ships
	 */
	public void setPlayer2ShipsPanel(){
	
		Player2ShipsPanel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2Ships[x][y] = new JButton();
				Player2ShipsPanel.add(gridPlayer2Ships[x][y]);
			}
	
	}
	/**
	 * @return Player2ShipsPanel
	 */
	public JPanel getPlayer2ShipsPanel(){
		
		return Player2ShipsPanel;
		
	}
	/**
	 * @param x
	 * @param y
	 * @return gridPlayer1[x][y]
	 */
	public JButton getGridPlayer1(int x, int y){
		
		return gridPlayer1[x][y];

	}
	/**
	 * @param x
	 * @param y
	 * @return gridPlayer2[x][y]
	 */
	public JButton getGridPlayer2(int x, int y){
	
		return gridPlayer2[x][y];

	}
	/**
	 * @param x
	 * @param y
	 * @return gridPlayer1Ships[x][y]
	 */
	public JButton getGridPlayer1Ships(int x, int y){
	
		return gridPlayer1Ships[x][y];

	}
	/**
	 * @param x
	 * @param y
	 * @return gridPlayer2Ships[x][y]
	 */
	public JButton getGridPlayer2Ships(int x, int y){
	
		return gridPlayer2Ships[x][y];

	}
	/**
	 * @param listenForBoardButton
	 * 							add to gridPlayer1[][] action listeners when player hit board
	 */
	public void addBoardButtonPlayer1Listener(ActionListener listenForBoardButton){
		
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1[x][y].addActionListener(listenForBoardButton);
			}
	}
	/**
	 * @param listenForBoardButton
	 * 							add to gridPlayer2[][] action listeners when player hit board
	 */
	public void addBoardButtonPlayer2Listener(ActionListener listenForBoardButton){
		
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2[x][y].addActionListener(listenForBoardButton);
			}
	}
	/**
	 * @param listenForBoardButton
	 * 							add to gridPlayer1[][] action listeners when player place ships
	 */	
	public void addPlaceButtonPlayer1Listener(ActionListener listenForPlaceButton){
			
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1[x][y].addActionListener(listenForPlaceButton);
			}
	}	
	/**
	 * @param listenForBoardButton
	 * 							add to gridPlayer2[][] action listeners when player place ships
	 */
	public void addPlaceButtonPlayer2Listener(ActionListener listenForPlaceButton){
		
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2[x][y].addActionListener(listenForPlaceButton);
			}
	}	
	/**
	 * @param listenForServer
	 * 						set actionlistener to ServerButton
	 */
	public void addServerListener(ActionListener listenForServer){
		
		ServerButton.addActionListener(listenForServer);
		
	}
	/**
	 * @param listenForServer
	 * 						set actionlistener to ClientButton
	 */
	public void addClientListener(ActionListener listenForClient){
		
		ClientButton.addActionListener(listenForClient);
		
	}
	/**
	 * @param listenForServer
	 * 						set actionlistener to ExitButton
	 */
	public void addExitListener(ActionListener listenForExit){
	
		ExitButton.addActionListener(listenForExit);
	
	}
	/**
	 * @param listenForServer
	 * 						set actionlistener to Ship4
	 */
	public void addShip4Listener(ActionListener listenForShip4){
		
		Ship4.addActionListener(listenForShip4);
		
	}
	/**
	 * @param listenForServer
	 * 						set actionlistener to Ship3
	 */
	public void addShip3Listener(ActionListener listenForShip3){
		
		Ship3.addActionListener(listenForShip3);
		
	}
	/**
	 * @param listenForServer
	 * 						set actionlistener to Ship2
	 */
	public void addShip2Listener(ActionListener listenForShip2){
	
		Ship2.addActionListener(listenForShip2);
	
	}
	/**
	 * @param listenForServer
	 * 						set actionlistener to Ship1
	 */
	public void addShip1Listener(ActionListener listenForShip1){
	
		Ship1.addActionListener(listenForShip1);
	
	}
	/**
	 * @param listenForServer
	 * 						set actionlistener to DeployPlayer1
	 */
	public void addDeployPlayer1Listener(ActionListener listenForDeploy) {
		
		DeployPlayer1.addActionListener(listenForDeploy);
		
	}
	/**
	 * @param listenForServer
	 * 						set actionlistener to DeployPlayer2
	 */
	public void addDeployPlayer2Listener(ActionListener listenForDeploy) {
		
		DeployPlayer2.addActionListener(listenForDeploy);
		
	}
	
}
