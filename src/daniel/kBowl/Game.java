package daniel.kBowl;

import java.util.ArrayList;

public class Game {

	private int currentTurnIndex=0;
	private ArrayList<Turn> turns=new ArrayList<Turn>();
	private final int turnsInGame=10;
	
	
	public static void main(String[] args) {
		
		Game g=new Game();
		
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
				
		System.out.println("Full strikes - should be 300 and is:" +  g.score());
		
		g=new Game();
		g.roll(1);
		g.roll(4);
		g.roll(4);
		g.roll(5);
		g.roll(6);
		g.roll(4);
		g.roll(5);
		g.roll(5);
		g.roll(10);
		g.roll(0);
		g.roll(1);
		g.roll(7);
		g.roll(3);
		g.roll(6);
		g.roll(4);
		g.roll(10);
		g.roll(2);
		g.roll(8);
		g.roll(6);
						
		System.out.println("Example in question - should be 133 and is:" +  g.score());
		
		g=new Game();
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
		g.roll(10);
				
		System.out.println("Test too many roles - should be 300 and is:" +  g.score());
		
		g=new Game();
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
		g.roll(0);
				
		System.out.println("Test total misses - should be 0 and is:" +  g.score());
		
		g=new Game();
		g.roll(1);
		g.roll(0);
		g.roll(2);
		g.roll(0);
		g.roll(3);
		g.roll(0);
		g.roll(4);
		g.roll(0);
		g.roll(5);
		g.roll(0);
		g.roll(6);
		g.roll(0);
		g.roll(7);
		g.roll(0);
		g.roll(8);
		g.roll(0);
		g.roll(9);
		g.roll(0);
		g.roll(1);
		g.roll(0);
				
		System.out.println("Test simple game - should be 46 and is:" +  g.score());

	}
	
	public Game() {
		super();
		initTurns();
	}

	
	
	private void initTurns()
	{
		for (int i = 0; i < turnsInGame; i++) {
			turns.add(new Turn());
		}
	}
	
	public void roll(int pins)
	{
		if(currentTurnIndex<turns.size())
		{
			Turn currentTurn=turns.get(currentTurnIndex);
			currentTurn.roll(pins);
			if(currentTurn.isFinished())
			{
				currentTurnIndex++;
				boolean isLastTurn=currentTurnIndex==turnsInGame;
				
				if(isLastTurn && (currentTurn.getIsSpare()))
				{
					turns.add(new BonusTurnAfterSpare());
				}
				if(isLastTurn && currentTurn.getIsStrike())
				{
					turns.add(new BonusTurnAfterStrike());
				}
			}
			
		}
	}
	
	
	public int score()
	{
		int scoreValue=0;
		for (int i = 0; i < turnsInGame; i++) {
			Turn currentTurn=turns.get(i);
			Turn nextTurn=null;
			Turn nextNextTurn=null;
			
			if(i+1<turns.size())
			{
				nextTurn=turns.get(i+1);
			}
			if(i+2<turns.size())
			{
				nextNextTurn=turns.get(i+2);
			}
			
			scoreValue+=currentTurn.getTotalScore();
			if(currentTurn.getIsSpare() )
			{
				scoreValue+=nextTurn.getFirstTry();
			}
			
			if(currentTurn.getIsStrike() )
			{
				scoreValue+=GetStrikeExtraPoints(nextTurn,nextNextTurn);
			}
			
		}
		return scoreValue;
	}

	private int GetStrikeExtraPoints(Turn nextTurn,Turn nextNextTurn) {
		int strikeExtraPoints=0;
		
		if(nextTurn instanceof BonusTurn)
		{
			strikeExtraPoints= nextTurn.getTotalScore();
		}
		if(!(nextTurn instanceof BonusTurn) && nextTurn.getIsStrike())
		{
			strikeExtraPoints= nextTurn.getTotalScore()
					+ nextNextTurn.getFirstTry();
		}
		if(!(nextTurn instanceof BonusTurn) && !nextTurn.getIsStrike())
		{
			strikeExtraPoints= nextTurn.getTotalScore();
		}
		return strikeExtraPoints;
		
	}

}
 