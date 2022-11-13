import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.vecmath.Vector3d;

public class Camera extends Object3D {
	double focalLength = 1000;
	public Camera() {
		super(new Vector3d(0,0,0));
	}

	/**
	 * @param alpha rotation around z axis
	 * @param beta rotation around y axis
	 * @param gamma rotation around x axis
	 * @return the rotation matrix
	 */
	private double[] @NotNull [] rotationMatrix(double alpha, double beta, double gamma) {
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

	/**
	 * Multiplies a vector with a matrix
	 * @param matrix matrix to multiply with
	 * @param vector vector to multiply
	 * @return the result of the multiplication
	 */
	@Contract(pure = true)
	private double @NotNull [] matriceMultiplication(double[][] matrix, double[] vector) {
		double[] result = new double[3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				result[i] += matrix[i][j] * vector[j];
			}
		}
		return result;
	}

	/**
	 * Transform a 3D point to a 2D point
	 * @param point the point to transform
	 * @param rotation rotation of the 3d object
	 * @return transformed point
	 */
	public Vector3d project(@NotNull Vector3d point, @NotNull Vector3d rotation,@NotNull Vector3d objectPosition,
	                       @NotNull Vector3d scale) {
		double[] vector = new double[3];
		vector[0] = point.x * scale.x;
		vector[1] = point.y * scale.y;
		vector[2] = point.z * scale.z;
		double[][] matrix = rotationMatrix(Math.toRadians(rotation.z), Math.toRadians(rotation.y), Math.toRadians(rotation.x));
		double[] result = matriceMultiplication(matrix,vector);
		result[0] += objectPosition.x ;
		result[1] += objectPosition.y;
		result[2] += objectPosition.z- position.z;
		double xProjected = (focalLength * result[0]) / (result[2] + focalLength);
		double yProjected = (focalLength * result[1]) / (result[2] + focalLength);
		return new Vector3d(xProjected - position.x , yProjected - position.y, 0);
	}
	public Vector3d project(int index,Object3D object) {
		return project(object.vertices[index],object.rotation,object.position,object.scale);
	}
}
