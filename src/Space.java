// This is for holding space information which is name of the space, how much gets added or subtracted from the player's score, and if the player gets to roll again. 
public class Space 
{
	private String spaceName;
	private int scoreModifier;
	private boolean goAgain;
	
	public Space(String name, int modifier, boolean again)
	{
		//spaceNumber = number;
		spaceName = name;
		scoreModifier = modifier;
		goAgain = again;
	}
	
	public String getSpaceName()
	{
		return spaceName;
	}
	
	public int getScoreModification()
	{
		return scoreModifier;
	}
	
	public boolean getGoAgainStatus()
	{
		return goAgain;
	}
	
	public String toString()
	{
		String spaceInfo = String.format("%s, %d, %b", spaceName, scoreModifier, goAgain);
		return spaceInfo;
	}
}
