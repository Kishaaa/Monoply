import java.util.ArrayList;


public interface Observer {
	public void update(int roll1, int roll2, int player1Score, int player2Score, int player1Location, int player2location, String message);
	public void initialSetUp(ArrayList<Space> x);
}
