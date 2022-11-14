import javax.swing.*;
public class Frame extends JFrame {
	World world;
	public Frame() {
		setTitle("Moteur3d Test");
		setSize(800, 600);
		// set fullscreen windowed
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		world = new World(new Camera());
		add(world);
	}

}
