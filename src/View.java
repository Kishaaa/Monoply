import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
//command a command i

public class View extends JPanel implements Observer
{
	JLayeredPane totalGUI;
	int squareSize;
	int rollFromModel1;
	int rollFromModel2;
	int player1Location; 
	int player2Location; 
	JPanel mainPanel;
	JPanel upperMiddle;
	JPanel overlap;
	JPanel lowerMiddle;
	private int player1Score;
	private int player2Score;
	private ArrayList<Point> points;
	private String message;
	private Dice dice;
	private JLabel player1ScoreLabel;
	private JLabel player2ScoreLabel;
	private JLabel messageLabel;
	private InnerPanel innerPanel;
	GridBagConstraints c;
	private Icon pieceOne;
	private Icon pieceTwo;
	private JLabel piece1;
	private JLabel piece2;


	public View()
	{

		super();
		player1Score = 0;
		player2Score = 0;
		message = "<html>Have a good game! <br> Roll Button in Upper Right. <br><br>";
		points = new ArrayList<Point>();
		rollFromModel1 = 2;
		rollFromModel2 = 4;
		
		setOpaque(true);
		



	}

	public void initialSetUp(ArrayList<Space> arrayOfSpaces)
	{
		ArrayList<Space> spaces = arrayOfSpaces;
		totalGUI = new JLayeredPane();
		totalGUI.setLayout(new OverlayLayout(totalGUI));

		totalGUI.setBackground(new Color(0, 0, 50));
		totalGUI.setOpaque(true);
		this.add(totalGUI);
		squareSize = 64;
		totalGUI.setPreferredSize(new Dimension(squareSize * 11 + 10, squareSize * 11 + 10));
		int size = spaces.size();
		if((size % 4) != 0)
		{
			size = size + Math.abs((size % 4) - 4);
		}
		int spacesPerSide = size / 4;

		// colors for the board spaces
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.cyan);
		colors.add(Color.red);
		colors.add(Color.orange);
		colors.add(Color.pink);
		colors.add(new Color(50, 150, 255));
		colors.add(Color.green);
		colors.add(Color.magenta);
		colors.add(Color.yellow);
		colors.add(Color.lightGray);

		mainPanel = new JPanel(new GridBagLayout());
		c = new GridBagConstraints();
		
		overlap = new JPanel(new GridBagLayout());

	//Draw the board
		int x = spacesPerSide;
		int y = spacesPerSide; 

		int colorIndex = 0;
		int k = 0;

		// bottom row
		while(x >= 0 && y == spacesPerSide)
		{
			JPanel panel = createSquareJPanel(colors.get(colorIndex), squareSize);
			colorIndex = (colorIndex + 1) % colors.size();
			c.gridx = x;
			c.gridy = y;
			mainPanel.add(panel, c);
			points.add(new Point(x,y));
			
			JPanel emptyPanel = createSquareJPanel(null, squareSize);
			emptyPanel.setOpaque(false);
			overlap.add(emptyPanel, c);
			
			
			String[] spaceName = spaces.get(k).getSpaceName().split(" ");
			for(int i = 0; i < spaceName.length; i++)
			{
				JLabel label = new JLabel(spaceName[i]);
				label.setFont(new Font("Serif", Font.PLAIN, 12));
				panel.add(label);
			}
			JLabel score = new JLabel("      " + Integer.toString(spaces.get(k).getScoreModification()) + "      ");
			score.setFont(new Font("Serif", Font.PLAIN, 12));
			panel.add(score);
			if(spaces.get(k).getGoAgainStatus())
			{
				JLabel goAgain = new JLabel("Go Again");
				goAgain.setFont(new Font("Serif", Font.PLAIN, 12));
				panel.add(goAgain);
			}
			k++;
			x--;

		}
		x = x + 1;
		y = y - 1;

		// left side
		while(x == 0 && y >= 0)
		{
			JPanel panel = createSquareJPanel(colors.get(colorIndex), squareSize);
			colorIndex = (colorIndex + 1) % colors.size();
			c.gridx = x;
			c.gridy = y;
			mainPanel.add(panel, c);
			points.add(new Point(x,y));
			
			
			JPanel emptyPanel = createSquareJPanel(null, squareSize);
			emptyPanel.setOpaque(false);
			overlap.add(emptyPanel, c);
			
			String[] spaceName = spaces.get(k).getSpaceName().split(" ");
			for(int i = 0; i < spaceName.length; i++)
			{
				JLabel label = new JLabel(spaceName[i]);
				label.setFont(new Font("Serif", Font.PLAIN, 12));
				panel.add(label);
			}
			JLabel score = new JLabel("     " + Integer.toString(spaces.get(k).getScoreModification()) + "     ");
			score.setFont(new Font("Serif", Font.PLAIN, 12));
			panel.add(score);	
			if(spaces.get(k).getGoAgainStatus())
			{
				JLabel goAgain = new JLabel("Go Again");
				goAgain.setFont(new Font("Serif", Font.PLAIN, 12));
				panel.add(goAgain);
			}
			k++;
			y--;
		}
		x = x + 1;
		y = y + 1;

		// top row
		while(x <= spacesPerSide && y == 0)
		{
			JPanel panel = createSquareJPanel(colors.get(colorIndex), squareSize);
			colorIndex = (colorIndex + 1) % colors.size();
			c.gridx = x;
			c.gridy = y;
			mainPanel.add(panel, c);
			points.add(new Point(x,y));
			
			JPanel emptyPanel = createSquareJPanel(null, squareSize);
			emptyPanel.setOpaque(false);
			overlap.add(emptyPanel, c);
			
			
			String[] spaceName = spaces.get(k).getSpaceName().split(" ");
			for(int i = 0; i < spaceName.length; i++)
			{
				JLabel label = new JLabel(spaceName[i]);
				label.setFont(new Font("Serif", Font.PLAIN, 12));
				panel.add(label);
			}
			JLabel score = new JLabel("     " + Integer.toString(spaces.get(k).getScoreModification()) + "     ");
			score.setFont(new Font("Serif", Font.PLAIN, 12));
			panel.add(score);
			if(spaces.get(k).getGoAgainStatus())
			{
				JLabel goAgain = new JLabel("Go Again");
				goAgain.setFont(new Font("Serif", Font.PLAIN, 12));
				panel.add(goAgain);
			}
			k++;
			x++;
		}
		x = x - 1;
		y = y + 1;

		// right side
		while(x == spacesPerSide && y < spacesPerSide && k < spaces.size())
		{
			JPanel panel = createSquareJPanel(colors.get(colorIndex), squareSize);
			colorIndex = (colorIndex + 1) % colors.size();
			c.gridx = x;
			c.gridy = y;
			//panel.setOpaque(false);
			mainPanel.add(panel, c);
			points.add(new Point(x,y));
			
			
			JPanel emptyPanel = createSquareJPanel(null, squareSize);
			emptyPanel.setOpaque(false);
			overlap.add(emptyPanel, c);
			
			
			String[] spaceName = spaces.get(k).getSpaceName().split(" ");
			for(int i = 0; i < spaceName.length; i++)
			{
				JLabel label = new JLabel(spaceName[i]);
				label.setFont(new Font("Serif", Font.PLAIN, 12));
				panel.add(label);
			}
			JLabel score = new JLabel("     " + Integer.toString(spaces.get(k).getScoreModification()) + "     ");
			score.setFont(new Font("Serif", Font.PLAIN, 12));
			panel.add(score);	
			if(spaces.get(k).getGoAgainStatus())
			{
				JLabel goAgain = new JLabel("Go Again");
				goAgain.setFont(new Font("Serif", Font.PLAIN, 12));
				panel.add(goAgain);
			}
			k++;
			y++;
		}
		y = y - 1;

		//this is the JPanel in the middle that contains the dice
		upperMiddle = new JPanel(); //createSquareJPanel(null, squareSize * 3);
		upperMiddle.setLayout(new BoxLayout(upperMiddle, BoxLayout.Y_AXIS));

		c.gridx = 4;
		c.gridy = 2;
		c.gridheight = 3;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.CENTER;

		mainPanel.add(upperMiddle, c);

		innerPanel = new InnerPanel(180);
		upperMiddle.add(innerPanel);
//Color
		innerPanel.setBackground(new Color(0, 0, 50));
		

		
		lowerMiddle = new JPanel();
		lowerMiddle.setLayout(new BoxLayout(lowerMiddle, BoxLayout.Y_AXIS));
		c.gridx = 4;
		c.gridy = 4;
		c.gridheight = 4;
		c.gridwidth = 8;
		c.anchor = GridBagConstraints.WEST;
		
		mainPanel.add(lowerMiddle, c);
		
		messageLabel = new JLabel(message);
		messageLabel.setForeground(Color.white);
		messageLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		player1ScoreLabel = new JLabel("Player 1's score: " + player1Score + " ");
		player1ScoreLabel.setForeground(Color.white);
		player1ScoreLabel.setFont(new Font("Serif", Font.PLAIN, 18));

		player2ScoreLabel = new JLabel("Player 2's score: " + player2Score);
		player2ScoreLabel.setForeground(Color.white);
		player2ScoreLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		
		JLabel messageLabelEmpty = new JLabel(" ");
		
		lowerMiddle.add(messageLabel);
		lowerMiddle.add(messageLabelEmpty);
		lowerMiddle.add(player1ScoreLabel);
		lowerMiddle.add(player2ScoreLabel);
//Color
		lowerMiddle.setBackground(new Color(0,0,50));
		

		c.gridheight = 1;
		c.gridwidth = 1;

		pieceOne = new ImageIcon("1.png");
		piece1 = new JLabel(pieceOne);
		c.anchor = GridBagConstraints.NORTHWEST;
		

		
		c.gridx = 10;
		c.gridy = 10;
		
		
		pieceTwo = new ImageIcon("2.png");
		piece2 = new JLabel(pieceTwo);
		
		overlap.add(piece1, c);
		c.anchor = GridBagConstraints.SOUTHEAST;
		overlap.add(piece2, c);
		totalGUI.add(overlap, new Integer(1));
		overlap.setOpaque(false);

		mainPanel.setOpaque(false);
		//adding the mainPanel to the totalGUI
		totalGUI.add(mainPanel, new Integer(0));



	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		innerPanel.repaint();

		messageLabel.setText(message);
		player1ScoreLabel.setText("Player 1's score: " + player1Score + " ");
		player2ScoreLabel.setText("Player 2's score: " + player2Score + " ");
		this.setBackground(new Color(40, 125, 150));
		
		Point point = points.get(player1Location);
		int xCoordinate = (int)point.getX();
		int yCoordinate = (int)point.getY();
		
		c.gridheight = 1;
		c.gridwidth = 1;


		c.anchor = GridBagConstraints.NORTHEAST;
		
		
		
		c.gridx = xCoordinate;
		c.gridy = yCoordinate;
		
		overlap.add(piece1, c);
		
		
		point = points.get(player2Location);
		xCoordinate = (int)point.getX();
		yCoordinate = (int)point.getY();
		c.gridx = xCoordinate;
		c.gridy = yCoordinate;
		c.anchor = GridBagConstraints.SOUTHWEST;
		overlap.add(piece2, c);
		
		

		
	}
	

	private JPanel createSquareJPanel(Color color, int size)
	{
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(color);
		tempPanel.setMinimumSize(new Dimension(size, size));
		tempPanel.setMaximumSize(new Dimension(size, size));
		tempPanel.setPreferredSize(new Dimension(size, size));
		return tempPanel;
	}

	private class Dice extends JComponent
	{
		Rectangle rect1;
		int x;
		int y;
		int width;
		int height;
		int roll;
		int placementRatio = 5;
		int fillRatio = 7;

		private Dice(int x, int y, int w, int h, Graphics g)
		{

			roll = rollFromModel1;
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
			rect1 = new Rectangle(x, y, width, height);

			Graphics2D g2 = (Graphics2D) g;

			for(int i = 0; i < 2; i++)
			{
				g2.setColor(Color.WHITE);
				g2.fill(rect1);
				g2.setColor(Color.BLACK);
				if(roll == 1)
				{
					g2.fillOval(x + 2*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 2*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
				}
				if(roll == 2)
				{
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + (height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 3*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
				}
				if(roll == 3)
				{
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + (height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 2*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 2*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 3*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
				}
				if(roll == 4)
				{
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + (height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + (height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 3*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 3*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
				}
				if(roll == 5)
				{
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + (height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + (height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 2*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 2*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 3*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 3*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);

				}
				if(roll == 6)
				{
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + (height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + (height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 2*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 2*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + (width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 3*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
					g2.fillOval(x + 3*(width/placementRatio) + (width/placementRatio - width/fillRatio)/2, y + 3*(height/placementRatio) + (height/placementRatio - height/fillRatio)/2, width/fillRatio, height/fillRatio);
				}
				x = x + width + 10;
				rect1 = new Rectangle(x, y, width, height);
				roll = rollFromModel2;
			}

			x = x - 2*width - 2*10;
		}
	}

	private class InnerPanel extends JPanel
	{
		private InnerPanel(int size)
		{
			super();
			setMinimumSize(new Dimension(size, size));
			setMaximumSize(new Dimension(size, size));
			setPreferredSize(new Dimension(size, size));

		}

		public void paintComponent(Graphics g)
		{
			dice = new Dice(0, 0, 80, 80, g);
		}
	}

	@Override
	public void update(int roll1, int roll2, int player1Score, int player2Score, int player1Location, int player2Location, String message) 
	{

		rollFromModel1 = roll1;
		rollFromModel2 = roll2;

		this.player1Score = player1Score;
		this.player2Score = player2Score;
		
		this.player1Location = player1Location; 
		this.player2Location = player2Location; 
		this.message = message;
		this.repaint();

	}
}
