package BattleShip;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BattleView{

	/*
	 *  main frame of the View
	 */
	public JFrame frame = new JFrame();
	
	/*
	 *  Panel with buttons Start and Exit
	 */
	public JPanel menuPanel = new JPanel();
	/*
	 *  Panel with ShipTypes and Deploy1 Button
	 */
	public JPanel setShipsPanel = new JPanel();
	/*
	 *  Panel with ShipTypes and Deploy2 Button
	 */
	public JPanel setShipsPanelPlayer2 = new JPanel();
	/*
	 *  Panel where the first Player puts his Ships 
	 */
	public JPanel gridPlayer1Panel = new JPanel();
	/*
	 *  Panel where the second Player puts his Ships 
	 */
	public JPanel gridPlayer2Panel = new JPanel();
	/*
	 *  Panel where the second Player hit Ships 
	 */
	public JPanel gameBoardPlayer1Panel = new JPanel();
	/*
	 *  Panel where the first Player hit Ships 
	 */
	public JPanel gameBoardPlayer2Panel = new JPanel();
	/*
	 *  Panel where are the first Player Ships
	 */
	public JPanel Player1ShipsPanel = new JPanel();
	/*
	 *  Panel where are the second Player Ships
	 */
	public JPanel Player2ShipsPanel = new JPanel();
	
	private JButton PlayerVSPlayerButton = new JButton("Multiplayer");
	private JButton ExitButton = new JButton("Exit");
	private JButton Ship4 = new JButton("4");
	private JButton Ship3 = new JButton("3");
	private JButton Ship2 = new JButton("2");
	private JButton Ship1 = new JButton("1");
	private JButton DeployPlayer1 = new JButton("Deploy");
	private JButton DeployPlayer2 = new JButton("Deploy");
	private JButton[][] gridPlayer1 = new JButton[10][10];;
	private JButton[][] gridPlayer2 = new JButton[10][10];
	private JButton[][] gridPlayer1Ships = new JButton[10][10];
	private JButton[][] gridPlayer2Ships = new JButton[10][10];
	
	/*
	 *  Constructor
	 */
	public BattleView(){
		
		frame.setLayout(new GridLayout());
		menuPanel.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920,1080);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Menu");
		frame.setResizable(false);

		PlayerVSPlayerButton.setBounds(100, 300, 200, 70);
		menuPanel.add(PlayerVSPlayerButton);
		ExitButton.setBounds(100, 400, 200, 70);
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

		gridPlayer1Panel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1[x][y] = new JButton();
				gridPlayer1Panel.add(gridPlayer1[x][y]);
			}

	}
	
	public void setGridPlayer2Panel(){
		
		gridPlayer2Panel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2[x][y] = new JButton();
				gridPlayer2Panel.add(gridPlayer2[x][y]);
			}
		
	}
	
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
	
	public JPanel getGridPlayer1Panel(){
		
		return gridPlayer1Panel;
		
	}
	
	public JPanel getGridPlayer2Panel(){
		
		return gridPlayer2Panel;
		
	}
	
	public JPanel getGameBoardPlayer1Panel(){
		
		return gameBoardPlayer1Panel;
		
	}
	
	public JPanel getGameBoardPlayer2Panel(){
		
		return gameBoardPlayer2Panel;
		
	}
	
	public void setGameBoardPlayer1Panel(){
		
		gameBoardPlayer1Panel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1[x][y] = new JButton();
				gameBoardPlayer1Panel.add(gridPlayer1[x][y]);			
			}
		
	}

	public void setGameBoardPlayer2Panel(){
	
		gameBoardPlayer2Panel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2[x][y] = new JButton();
				gameBoardPlayer2Panel.add(gridPlayer2[x][y]);			
			}
	
	}

	public JPanel getPlayer1ShipsPanel(){
		
		return Player1ShipsPanel;
		
	}
	
	public JPanel getPlayer2ShipsPanel(){
		
		return Player2ShipsPanel;
		
	}
	
	
	public void setPlayer1ShipsPanel(){
	
		Player1ShipsPanel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1Ships[x][y] = new JButton();
				Player1ShipsPanel.add(gridPlayer1Ships[x][y]);
			}
	
	}

	public void setPlayer2ShipsPanel(){
	
		Player2ShipsPanel.setLayout(new GridLayout(10,10));
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2Ships[x][y] = new JButton();
				Player2ShipsPanel.add(gridPlayer2Ships[x][y]);
			}
	
	}
	
	public JButton getPlayer2Ships(int x, int y){
		
		return gridPlayer2Ships[x][y];

	}
	
	public JButton getPlayer1Ships(int x, int y){
		
		return gridPlayer1Ships[x][y];

	}
	
	public JButton getGridPlayer1(int x, int y){
		
		return gridPlayer1[x][y];

	}

	public JButton getGridPlayer2(int x, int y){
	
		return gridPlayer2[x][y];

	}

	public JButton getGridPlayer1Ships(int x, int y){
	
		return gridPlayer1Ships[x][y];

	}

	public JButton getGridPlayer2Ships(int x, int y){
	
		return gridPlayer2Ships[x][y];

	}
	
	public void addBoardButtonPlayer1Listener(ActionListener BoardButtonListener){
		
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1[x][y].addActionListener(BoardButtonListener);
			}
	}
	
	public void addBoardButtonPlayer2Listener(ActionListener BoardButtonListener){
		
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2[x][y].addActionListener(BoardButtonListener);
			}
	}
		
	public void addPlaceButtonPlayer1Listener(ActionListener PlaceButtonListener){
			
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer1[x][y].addActionListener(PlaceButtonListener);
			}
	}	
	
	public void addPlaceButtonPlayer2Listener(ActionListener PlaceButtonListener){
		
		for(int y=0;y<10;y++)
			for(int x=0;x<10;x++){
				gridPlayer2[x][y].addActionListener(PlaceButtonListener);
			}
	}	
	
	public void addPlayerVSPlayerListener(ActionListener PlayerVSPlayerListener){
		
		PlayerVSPlayerButton.addActionListener(PlayerVSPlayerListener);
		
	}
	
	public void addExitListener(ActionListener ExitListener){
	
		ExitButton.addActionListener(ExitListener);
	
	}
	
	public void addShip4Listener(ActionListener listenForShip4){
		
		Ship4.addActionListener(listenForShip4);
		
	}
	
	public void addShip3Listener(ActionListener listenForShip3){
		
		Ship3.addActionListener(listenForShip3);
		
	}

	public void addShip2Listener(ActionListener listenForShip2){
	
		Ship2.addActionListener(listenForShip2);
	
	}

	public void addShip1Listener(ActionListener listenForShip1){
	
		Ship1.addActionListener(listenForShip1);
	
	}
	
	public void addDeployPlayer1Listener(ActionListener deployListener) {
		DeployPlayer1.addActionListener(deployListener);
	}
	
	public void addDeployPlayer2Listener(ActionListener deployListener) {
		DeployPlayer2.addActionListener(deployListener);
	}
	
}
