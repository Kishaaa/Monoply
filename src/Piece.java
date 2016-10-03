import java.awt.Point;

import javax.swing.ImageIcon;

//this is the player's piece that holds location and item
public class Piece 
{
	//private ImageIcon item;
	private int position;
	
	//constructor and set piece to space Go by 0,10
	public Piece()
	{
		//this.item = item;
		position = 0;
	}
	
//	public void setPieceItem(ImageIcon item)
//	{
//		this.item = item;
//	}
//	
//	public ImageIcon getItem()
//	{
//		return item;
//	}
	public int getLocation()
	{
		return position;
	}
	
	public void updateLocation(int update)
	{
		//position = (location + update) % numberOfSpaces ;
		
		position = update;
	}
}
