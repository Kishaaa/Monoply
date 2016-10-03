import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class Model implements ModelInterface
{
	private GameBoard gameBoard;
	private int winningScore;
	private Observer observerView;
	private Dice dice;
	private int firstRoll;
	private int secondRoll;
	private int total;
	private Player currentPlayer;
	private Scanner reader;
	private File file;
	private Player player1;
	private Player player2;
	private boolean continueGame;

	public Model(String fileName)
	{

		gameBoard = new GameBoard();
		firstRoll = 0;
		secondRoll = 0;
		total = 0;
		player1 = new Player("Player 1", new Piece());
		player2 = new Player("Player 2", new Piece());
		currentPlayer = player1;
		dice  = new Dice();
		continueGame = true;



		try
		{
			file = new File(fileName);
			reader = new Scanner(file);
			//reader.useDelimiter(", ");


			//read file

			winningScore = reader.nextInt();
			reader.nextLine();
			//prints out the spaces		
			System.out.println("The Score needed to win is: " + winningScore);
			System.out.println();

			System.out.println("Each Space With It's Corresponding Information:");

			while (reader.hasNextLine()) 
			{
				String[] spaceInfo = reader.nextLine().split(", ");
				String name = spaceInfo[0];
				int scoreModifier = Integer.parseInt(spaceInfo[1]);
				boolean goAgain = Boolean.parseBoolean(spaceInfo[2]);
				Space space = new Space(name, scoreModifier, goAgain);
				gameBoard.addSpace(space);
			}

			gameBoard.printSpaces();
		}
		catch (Exception e)
		{
			System.out.println("File could not be located. Make sure it is next to .jar file");
			reader.close();
			System.exit(1);
		}


	}

	public void changeCurrentPlayer(){
		if(currentPlayer == player1){
			currentPlayer = player2;
		}

		else currentPlayer = player1;
	}

	public void setObserver(Observer newObserver)
	{
		observerView = newObserver;
		observerView.initialSetUp(gameBoard.getSpaces());
	}



	public void update()
	{

		if(continueGame)
		{
			firstRoll = dice.rollDice();
			secondRoll = dice.rollDice();

			total = firstRoll + secondRoll;
			int position = currentPlayer.getPiece().getLocation();
			currentPlayer.getPiece().updateLocation((position + total) % gameBoard.getNumberOfSpaces());
			position = currentPlayer.getPiece().getLocation();
			currentPlayer.updateScore(gameBoard.getScoreModifier(position));

			boolean goAgain = gameBoard.getGoAgainStatus(position);


			if(currentPlayer.getScore() >= winningScore)
			{
				observerView.update(firstRoll, secondRoll, player1.getScore(), player2.getScore(), player1.getPiece().getLocation(), player2.getPiece().getLocation(), currentPlayer.getName() + " Wins!");
				continueGame = false;
			}


			else if(goAgain == true)
			{
				observerView.update(firstRoll, secondRoll, player1.getScore(), player2.getScore(), player1.getPiece().getLocation(), player2.getPiece().getLocation(), currentPlayer.getName() + " Roll Again");
			}

			else
			{
				this.changeCurrentPlayer();
				observerView.update(firstRoll, secondRoll, player1.getScore(), player2.getScore(), player1.getPiece().getLocation(), player2.getPiece().getLocation(), currentPlayer.getName() + "'s turn!");
			}
		}
	}
}
