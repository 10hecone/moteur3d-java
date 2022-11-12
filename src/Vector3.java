public class Vector3 {
	public double x, y, z;

	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Vector3() {
		this(0, 0, 0);
	}

	public Vector3(Vector3 v) {
		this(v.x, v.y, v.z);
	}

	public Vector3 add(Vector3 v) {
		return new Vector3(x + v.x, y + v.y, z + v.z);
	}

	public Vector3 sub(Vector3 v) {
		return new Vector3(x - v.x, y - v.y, z - v.z);
	}

	public Vector3 mul(double d) {
		return new Vector3(x * d, y * d, z * d);
	}

	public Vector3 mul(Vector3 v) {
		return new Vector3(x * v.x, y * v.y, z * v.z);
	}

	public Vector3 div(double d) {
		return new Vector3(x / d, y / d, z / d);
	}

	public Vector3 div(Vector3 v) {
		return new Vector3(x / v.x, y / v.y, z / v.z);
	}

	public double dot(Vector3 v) {
		return x * v.x + y * v.y + z * v.z;
	}

	public Vector3 cross(Vector3 v) {
		return new Vector3(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

}
