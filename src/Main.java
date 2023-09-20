

public class Main {
	public static void main(String[] args) {
		// run Frame
		Frame frame = new Frame();
		Object3D object3D = new Object3D(Primitive.Tetrahedron, new Vector3(200, 200, 0));
		Object3D cube = new Object3D(Primitive.Cube, new Vector3(0, 0, 0));
		World.objects.add(object3D);
		World.objects.add(cube);
		frame.setVisible(true);


		CameraController ctrl = new CameraController(World.camera);
		frame.addKeyListener(ctrl);
		frame.addMouseMotionListener(ctrl);
	}
}
