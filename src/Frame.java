import javax.swing.*;
import java.awt.*;
import java.sql.Time;

public class Frame extends JFrame {
	static JLabel fpsCounter = new JLabel("FPS: 0");
	public Frame() {
		setTitle("Moteur3d Test");
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		World world = new World(new Camera());
		add(world);
		Object3D object = new Object3D(Primitive.Pyramid, new Vector3(150, 100, 0));
		world.objects.add(object);
		Object3D object2 = new Object3D(Primitive.Tetrahedron, new Vector3(300, 100, 0));
		world.objects.add(object2);
		Object3D object3 = new Object3D(Primitive.Cube, new Vector3(450, 100, 0));
		world.objects.add(object3);
		// add slider to ajust focal length
		JSlider slider = new JSlider(0, 1000, 500);
		slider.addChangeListener(e -> {
			world.camera.focalLength = slider.getValue();
			repaint();
		});
		add(slider, BorderLayout.SOUTH);
		// add slider to move camera
		JSlider slider2 = new JSlider(-500, 500, 0);
		slider2.addChangeListener(e -> {
			world.camera.position.x = slider2.getValue();
			repaint();
		});
		add(slider2, BorderLayout.NORTH);
		// vertical slider to move camera on y axis
		JSlider slider3 = new JSlider(-500, 500, 0);
		slider3.addChangeListener(e -> {
			world.camera.position.y = slider3.getValue();
			repaint();
		});
		add(slider3, BorderLayout.WEST);
		// set slider vertical
		slider3.setOrientation(JSlider.VERTICAL);
	// vertical slider to move camera on z axis
		JSlider slider4 = new JSlider(-500, 500, 0);
		slider4.addChangeListener(e -> {
			world.camera.position.z = slider4.getValue();
			repaint();
		});
		add(slider4, BorderLayout.EAST);
		// set slider vertical
		slider4.setOrientation(JSlider.VERTICAL);
		// add fps counter
		add(fpsCounter, BorderLayout.SOUTH);
		new Thread(() -> {
				rotate(world, object);
		}).start();
		new Thread(() -> {
			rotate(world, object2);
		}).start();
		rotate(world, object3);
	}

	private static void rotate(World world, Object3D object) {
		while (true) {
			Time time = new Time(System.currentTimeMillis());
			object.rotation.x += 0.01;
			object.rotation.y += 0.01;
			object.rotation.z += 0.01;
			world.repaint();
			try {
				Thread.sleep(1000/600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fpsCounter.setText("FPS: " + 1000 / (System.currentTimeMillis() - time.getTime()));
		}
	}
}
