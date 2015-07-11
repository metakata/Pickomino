package Version8_stable;
import java.util.*;

import javax.swing.JOptionPane;

public class ActivePlayerActions {
	
	private PlayerWorms currentplayer;
	
	private static boolean dicerollavailable = true;
	private boolean isOnGrill = false;
	
	public void setDiceRollAvailable(boolean setboolean){
		dicerollavailable=setboolean;
	}
	
	public boolean getDiceRollAvailable(){
		return dicerollavailable;
	}
	
	public ActivePlayerActions(PlayerWorms player) {
		dicerollavailable=false;
		currentplayer=player;
	}
	
	public void performRollDice(){	
		Dice.rollDice();
		dicerollavailable=false;
	}

	public void performPlayerBunk(){
		if(currentplayer.getPlayerWormsArrayList().size()>0){
			Grill.AddWormBackToGrill(currentplayer.getPlayerWormsArrayList().get(0));
			currentplayer.RemoveBunkWormFromPlayer();
			Dice.restartAllDice();
			dicerollavailable=false;
			Dice.setBunk(false);
			Gui.setEndPlayerTurn(true);
		}
		else{
			Dice.restartAllDice();
			dicerollavailable=false;
			Dice.setBunk(false);
			Gui.setEndPlayerTurn(true);
		}

	}
	
	public void performFreezeDice(int freezedicenumber){
		//2. Move Number Grouping to FrozenDiceList
		Dice.moveDiceToFrozen(freezedicenumber);
	}
	

	public void performTakeWormFromGrill(int takewormnumber){
		//Check if player can take worm
		if(Dice.getDiceSum() >= takewormnumber){
			currentplayer.addPrizeWormToPlayer(takewormnumber);
		}
		else{
			JOptionPane.showMessageDialog(null, String.format("You do not have enough to take that worm, please select enter another worm to take"));
		}
	}
	
	public void performTakeWormFromPlayer(int prizeworm){			
		currentplayer.addPrizeWormToPlayer(prizeworm);
		dicerollavailable = false;	
	}
	
}