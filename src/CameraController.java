import java.awt.event.*;

public class CameraController implements KeyListener, MouseMotionListener {
	public Camera camera;

	private Vector3 lastMousePosition = new Vector3();
	public double sensitivity = 0.001;
	public int speed = 1;
	private Vector3 direction = new Vector3();
	public CameraController(Camera camera) {
		this.camera = camera;
		World.updateFunctions.add(() -> {
			// update camera position
			Vector3 forward = camera.getForward();
			Vector3 right = camera.getRight();
			if (direction.x > 0) {
				camera.setPosition(camera.position.add(forward.mul((int) (speed* World.deltaTime))));
			}
			if (direction.x < 0) {
				camera.setPosition(camera.position.add(forward.mul((int) (-speed * World.deltaTime))));
			}
			if (direction.y > 0) {
				camera.setPosition(camera.position.add(right.mul((int) (speed * World.deltaTime))));
			}
			if (direction.y < 0) {
				camera.setPosition(camera.position.add(right.mul((int) (-speed * World.deltaTime))));
			}
		});
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// add to the direction of the camera
		if (e.getKeyChar() == 'w') {
			direction.x = 1;
		}
		if (e.getKeyChar() == 's') {
			direction.x = -1;
		}
		if (e.getKeyChar() == 'a') {
			direction.y = -1;
		}
		if (e.getKeyChar() == 'd') {
			direction.y = 1;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// unused
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// remove from the direction of the camera
		if (e.getKeyChar() == 'w') {
			direction.x = 0;
		}
		if (e.getKeyChar() == 's') {
			direction.x = 0;
		}
		if (e.getKeyChar() == 'a') {
			direction.y = 0;
		}
		if (e.getKeyChar() == 'd') {
			direction.y = 0;
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// add to the rotation of the camera
		Vector3 rotation = camera.rotation.getEulerAngles();
		Vector3 screenPosition = new Vector3(e.getX() - (double) World.screenWidth / 2, -e.getY() + (double) World.screenHeight / 2, 0);
		Vector3 diff = screenPosition.sub(lastMousePosition).normalize();
		rotation.x -= diff.y * sensitivity;
		rotation.y -= diff.x * sensitivity;
		camera.rotation = Quaternion.fromEulerAngles(rotation);
		lastMousePosition = new Vector3(screenPosition);
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// unused
	}
}
