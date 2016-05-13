package BattleShip;

import BattleShip.BattleController;
import BattleShip.BattleModel;
import BattleShip.BattleView;

public class BattleMVC {

public static void main(String[] args){
		
		
		BattleView theView=new BattleView();
		BattleModel theModel=new BattleModel();
		new BattleController(theView,theModel);
		theView.frame.setVisible(true);
	}
	
	
}
