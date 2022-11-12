import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World extends JPanel {
	public List<Object3D> objects;
	public Camera camera;

	public World(Camera camera) {
		// set background color
		setBackground(Color.BLACK);
		objects = new ArrayList<Object3D>();
		this.camera = camera;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw objects

	}
}
