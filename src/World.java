import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World extends JPanel {
	public static Camera camera;
	static int screenHeight;
	static int screenWidth;
	public static List<Object3D> objects;
	// list of functions to be called every frame
	public static List<Runnable> updateFunctions = new ArrayList<>();
	public static double deltaTime = 100.0 / 60.0;
	public World(Camera camera) {
		// set background color
		setBackground(Color.WHITE);
		objects = new ArrayList<>();
		World.camera = camera;
		new Thread(() -> {
			while (true) {
				update();
				try {
					Thread.sleep((long) deltaTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void update() {
		// update all objects
		for (Runnable r : updateFunctions) {
            r.run();
        }
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// draw objects
		for (Object3D object : objects) {
			// draw points
			for (Vector3 point : object.vertices) {
				Vector3 projectedPoint = camera.project(point, object.rotation, object.position);
				g.fillOval((int) projectedPoint.x-2, (int) projectedPoint.y-2, 5, 5);
			}
			if (object.faces == null) continue;
			for (Face face : object.faces) {
				// get center vector of face
				Vector3 p1 = camera.project(face.p1, object);
				Vector3 p2 = camera.project(face.p2, object);
				Vector3 p3 = camera.project(face.p3, object);
				Vector3 normal = new Vector3();
				normal.cross(new Vector3(p2.x - p1.x, p2.y - p1.y, p2.z - p1.z), new Vector3(p3.x - p1.x, p3.y - p1.y, p3.z - p1.z));
				// if normal.z is positive, the face is facing the camera
				if (normal.z > 0) {
					// draw face
					g.setColor(face.color);
					int[] xPoints = {(int) Math.round(p1.x), (int)Math.round(p2.x), (int) Math.round(p3.x)};
					int[] yPoints = {(int) Math.round(p1.y), (int)Math.round(p2.y), (int) Math.round(p3.y)};
					g.fillPolygon(xPoints, yPoints, 3);
				}
			}
		}
	}
}
