import java.util.ArrayList;
public class GameBoard 
{ 
	private ArrayList<Space> spaces = new ArrayList<Space>();;
	
	public GameBoard()
	{
		
	}
	
	public void addSpace(Space space)
	{
		spaces.add(space);
	}
	
	public ArrayList<Space> getSpaces()
	{
		return spaces;
	}
	
	public int getNumberOfSpaces()
	{
		return spaces.size();
	}
	
	public void printSpaces()
	{
		for(int i = 0; i < spaces.size(); i++)
		{
			System.out.println(spaces.get(i).toString());
		}
	}
	
	public int getScoreModifier(int spaceNumber)
	{
		return spaces.get(spaceNumber).getScoreModification();
	}
	
	public boolean getGoAgainStatus(int spaceNumber)
	{
		return spaces.get(spaceNumber).getGoAgainStatus();
	}
	
	public String getSpaceName(int spaceNumber)
	{
		return spaces.get(spaceNumber).getSpaceName();
	}
}
