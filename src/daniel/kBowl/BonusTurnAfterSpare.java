




package daniel.kBowl;

public class BonusTurnAfterSpare extends BonusTurn {
	
	public boolean isFinished()
	{
		return numberOfRolls==1;
	}

}
