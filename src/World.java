import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World extends JPanel {
	public List<Object3D> objects;
	public Camera camera;

	public World(Camera camera) {
		// set background color
		setBackground(Color.WHITE);
		objects = new ArrayList<Object3D>();
		this.camera = camera;

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw objects
		for (Object3D object : objects) {
			for (Edge edge : object.edges){
				Vector3 point1Projected = camera.project(edge.p1, object.rotation);
				Vector3 point2Projected = camera.project(edge.p2, object.rotation);
				g.drawLine((int) point1Projected.x, (int) point1Projected.y, (int) point2Projected.x, (int) point2Projected.y);


			}
		}
	}
}
