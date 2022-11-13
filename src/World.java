import javax.swing.*;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World extends JPanel {
	public static Camera camera;
	public List<Object3D> objects;

	public World(Camera camera) {
		// set background color
		setBackground(Color.WHITE);
		objects = new ArrayList<>();
		World.camera = camera;
		new Thread(() -> {
			while (true) {
				Update();
				try {
					Thread.sleep(100 / 60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void Update() {
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw objects
		for (Object3D object : objects) {
			// draw points
			for (Vector3d point : object.vertices) {
				Vector3d projectedPoint = camera.project(point, object.rotation, object.position, object.scale);
				g.fillOval((int) projectedPoint.x, (int) projectedPoint.y, 5, 5);
			}
			if (object.faces == null) continue;
			for (Face face : object.faces) {
				// get center vector of face
				Vector3d p1 = camera.project(face.p1, object);
				Vector3d p2 = camera.project(face.p2, object);
				Vector3d p3 = camera.project(face.p3, object);
				Vector3d V = new Vector3d(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z);
				Vector3d W = new Vector3d(p3.x - p1.x, p3.y - p1.y, p3.z - p1.z);
				Vector3d normal = new Vector3d(
						V.y * W.z - V.z * W.y,
						V.z * W.x - V.x * W.z,
						V.x * W.y - V.y * W.x
				);
				// if normal.z is positive, the face is facing the camera
				if (normal.z > 0) {
					// draw face
					g.setColor(face.color);
					int[] xPoints = {(int) p1.x, (int) p2.x, (int) p3.x};
					int[] yPoints = {(int) p1.y, (int) p2.y, (int) p3.y};
					g.fillPolygon(xPoints, yPoints, 3);
				}
			}
		}
	}
}
