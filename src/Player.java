// This holds the player's name, score, and piece.
public class Player 
{
	private String name;
	private int score;
	private Piece piece;
	
	public Player(String playerName, Piece playerPiece)
	{
		name = playerName;
		score = 0;
		piece = playerPiece;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void updateScore(int scoreAddition)
	{
		score = score + scoreAddition;
	}
	public Piece getPiece()
	{
		return piece;
	}
}
