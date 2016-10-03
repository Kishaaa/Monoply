import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Controller extends JPanel implements ActionListener
{

	ModelInterface modelInterface;
	int numberOfPlayersHere;



	//public Controller(JButton rollButton)
	public Controller()
	{

		super();
		JButton rollDice = new JButton(new ImageIcon("Up.png"));
		rollDice.setBackground(null);
		//rollDice.setBackground(new Color(120, 50, 50));
		//rollDice.setForeground(new Color(120, 100, 100));
		rollDice.setFont(new Font("Serif", Font.PLAIN, 50));
		rollDice.setPreferredSize(new Dimension(98, 98));
		rollDice.setOpaque(false);
		rollDice.setContentAreaFilled(false);
		rollDice.setBorderPainted(false);
		this.add(rollDice);
		rollDice.addActionListener(this);
		this.setBackground(new Color(40, 125, 150));

		rollDice.addMouseListener(new MouseAdapter() {
			  public void mousePressed(MouseEvent e) {
			   rollDice.setIcon(new ImageIcon("Down.png"));
			  }

			  public void mouseReleased(MouseEvent e) {
			   rollDice.setIcon(new ImageIcon("Up.png"));
			  }
		}
		);

	}


	public void actionPerformed(ActionEvent e)
	{	
		
		modelInterface.update();

	}
	
	public void setModelInterface(ModelInterface newInterface){
		modelInterface = newInterface;
	}

	//	private void notifyModels()
	//	{
	////		for(ModelInterface model: models)
	////		{
	////			model.update(firstRoll, secondRoll);
	////		}
	//		modelInterface.update();
	//	}
}
