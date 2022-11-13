import javax.swing.*;
public class Frame extends JFrame {
	World world;
	public Frame() {
		setTitle("Moteur3d Test");
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		world = new World(new Camera());
		add(world);
	}

}
