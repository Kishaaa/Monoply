import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;


public class Main 
{
	public static void main(String[] args)
	{
		
		View view = new View();
		Model model = new Model("SpacesTestEnter.txt");
		Controller controller = new Controller();
		
		controller.setModelInterface(model);
		model.setObserver(view);
		
		JFrame frame = new JFrame("Board Game");
		frame.setSize(1920, 1080);
		frame.add(view, BorderLayout.CENTER);
		frame.add(controller, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		
	}
}
