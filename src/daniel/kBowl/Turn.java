package daniel.kBowl;

public class Turn {
	private int[] tries=new int[2];
	private int numberOfRolls=0;
	private int totalScore=0;
	private boolean isSpare;
	private boolean isStrike=false;
	private boolean isBonus=false;
	private boolean isSpareBonus=false;
	

	public void roll(int pins) {
		tries[numberOfRolls]=pins;
		numberOfRolls++;
		totalScore+=pins;
		
		if(totalScore==10 && numberOfRolls==1)
		{
			isStrike=true;
		}
		
		if(totalScore==10 && numberOfRolls==2)
		{
			isSpare=true;
		}
		
		if(totalScore==10  && !isBonus)
		{
			numberOfRolls=tries.length;
		}
	}
	
	public boolean isFinished()
	{
		return numberOfRolls==tries.length || (isSpareBonus && numberOfRolls==1);
	}
	
	public int getFirstTry()
	{
		return tries[0];
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public boolean getIsSpare()
	{
		return isSpare;
	}
	
	public boolean getIsStrike()
	{
		return isStrike;
	}
	
	public boolean getIsBonus() {
		return isBonus;
	}
	
	public void setIsBonus() {
		isBonus=true;
	}

	public void setIsSpareBonus() {
		isSpareBonus=true;
	}
}
