
public class Camera extends Object3D {
	double focalLength = 1000;
	public Camera() {
		super(new Vector3(0,0,0));
	}
	private double[][] rotationMatrix(double alpha, double beta, double gamma) {
		double[][] matrix = new double[3][3];
		matrix[0][0] = Math.cos(alpha)*Math.cos(beta);
		matrix[0][1] = Math.cos(alpha)*Math.sin(beta)*Math.sin(gamma)-Math.sin(alpha)*Math.cos(gamma);
		matrix[0][2] = Math.cos(alpha)*Math.sin(beta)*Math.cos(gamma)+Math.sin(alpha)*Math.sin(gamma);
		matrix[1][0] = Math.sin(alpha)*Math.cos(beta);
		matrix[1][1] = Math.sin(alpha)*Math.sin(beta)*Math.sin(gamma)+Math.cos(alpha)*Math.cos(gamma);
		matrix[1][2] = Math.sin(alpha)*Math.sin(beta)*Math.cos(gamma)-Math.cos(alpha)*Math.sin(gamma);
		matrix[2][0] = -Math.sin(beta);
		matrix[2][1] = Math.cos(beta)*Math.sin(gamma);
		matrix[2][2] = Math.cos(beta)*Math.cos(gamma);
		return matrix;
	}
	private double[] matriceMultiplication(double[][] matrix, double[] vector) {
		double[] result = new double[3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result[i] += matrix[i][j] * vector[j];
			}
		}
		return result;
	}
	public Vector3 project(Vector3 point,Vector3 rotation) {
		double[] vector = new double[3];
		vector[0] = point.x;
		vector[1] = point.y;
		vector[2] = point.z;
		double[][] matrix = rotationMatrix(Math.toRadians(rotation.z), Math.toRadians(rotation.y), Math.toRadians(rotation.x));
		double[] result = matriceMultiplication(matrix,vector);
		double xProjected = (focalLength * result[0]) / (result[2] + focalLength);
		double yProjected = (focalLength * result[1]) / (result[2] + focalLength);
		return new Vector3(xProjected - position.x, yProjected - position.y, -position.z);
	}
}
