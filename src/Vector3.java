public class Vector3 {
	double x;
	double y;
	double z;

	public Vector3(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}

	public Vector3() {
		this(0,0,0);
	}

	public Vector3(Vector3 result) {
		this(result.x,result.y,result.z);
	}

	void cross(Vector3 v1, Vector3 v2) {
		x = v1.y * v2.z - v1.z * v2.y;
		y = v1.z * v2.x - v1.x * v2.z;
		z = v1.x * v2.y - v1.y * v2.x;
	}

	double[] toDouble() {
		return new double[]{x,y,z};
	}

	Vector3 sub(Vector3 vector3) {
		return new Vector3(x-vector3.x,y-vector3.y,z-vector3.z);
	}

	Vector3 normalize(){
		double length = Math.sqrt(x*x+y*y+z*z);
		return new Vector3(x/length,y/length,z/length);
	}

	@Override
	public String toString() {
		return "Vector3{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}

	Vector3 mul(int i) {
		return new Vector3(x*i,y*i,z*i);
	}

	Vector3 add(Vector3 v) {
		return new Vector3(x+v.x,y+v.y,z+v.z);
	}
}
