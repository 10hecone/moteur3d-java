public class Camera extends Object3D {
	double focalLength = 1000;
	Vector3 correctedPosition;
	public Camera() {
		super(new Vector3(0,0,0));
		// corrected position is the position - screen width/2, screen height/2, focalLength
		correctedPosition = position.sub(new Vector3((double) World.screenWidth /2, (double) World.screenHeight /2,focalLength));
	}

	public void setPosition(Vector3 position) {
		this.position = position;
		correctedPosition = position.sub(new Vector3((double) World.screenWidth /2, (double) World.screenHeight /2,focalLength));
	}



	/**
	 * Transform a 3D point to a 2D point
	 * @param point the point to transform
	 * @param orientation rotation of the 3d object
	 * @return transformed point
	 */
	public Vector3 project(Vector3 point, Quaternion orientation, Vector3 objectPosition) {
		Vector3 pointPosition = orientation.rotatePoint(point);
		pointPosition.x += objectPosition.x;
		pointPosition.y += objectPosition.y;
		pointPosition.z += objectPosition.z - correctedPosition.z;
		// Assuming rotation is represented as a Quaternion
		Vector3 rotatedPoint = rotation.rotatePoint(pointPosition);

		double xProjected = (focalLength * rotatedPoint.x) / (rotatedPoint.z + focalLength);
		double yProjected = (focalLength * rotatedPoint.y) / (rotatedPoint.z + focalLength);
		return new Vector3(xProjected - correctedPosition.x, yProjected - correctedPosition.y, 0);
	}
	public Vector3 project(int index, Object3D object) {
		return project(object.vertices[index],object.rotation,object.position);
	}
}
