
public class Quaternion {
    public double w;
    public double x;
    public double y;
    public double z;

    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Quaternion() {
        this(1, 0, 0, 0);
    }

    static Quaternion fromEulerAngles(Vector3 rotation) {
        return fromEulerAngles(rotation.x, rotation.y, rotation.z);
    }

    public Vector3 getEulerAngles() {
        double[] angles = new double[3];
        double sinRCosP = 2 * (w * x + y * z); // roll
        double cosRCosP = 1 - 2 * (x * x + y * y); // pitch
        angles[0] = Math.atan2(sinRCosP, cosRCosP); // yaw
        double sinP = 2 * (w * y - z * x); // pitch
        if (Math.abs(sinP) >= 1) // use 90 degrees if out of range
            angles[1] = Math.copySign(Math.PI / 2, sinP); // pitch
        else // pitch
            angles[1] = Math.asin(sinP); // pitch
        double sinYCosP = 2 * (w * z + x * y); // yaw
        double cosYCosP = 1 - 2 * (y * y + z * z); // yaw
        angles[2] = Math.atan2(sinYCosP, cosYCosP); // yaw
        return new Vector3(angles[0], angles[1], angles[2]); // yaw, pitch, roll
    }

    /**
     * @param x The X angle in radian
     * @param y The Y angle in radian
     * @param z The Z angle in radian
     * @return The quaternion
     */
    public static Quaternion fromEulerAngles(double x, double y, double z) {
        float cosX = (float) Math.cos(x / 2);
        float sinX = (float) Math.sin(x / 2);
        float cosY = (float) Math.cos(y / 2);
        float sinY = (float) Math.sin(y / 2);
        float cosZ = (float) Math.cos(z / 2);
        float sinZ = (float) Math.sin(z / 2);

        float w = cosX * cosY * cosZ + sinX * sinY * sinZ;
        float xVal = sinX * cosY * cosZ - cosX * sinY * sinZ;
        float yVal = cosX * sinY * cosZ + sinX * cosY * sinZ;
        float zVal = cosX * cosY * sinZ - sinX * sinY * cosZ;

        return new Quaternion(w, xVal, yVal, zVal);
    }
    public double[][] toRotationMatrix() {
        double[][] matrix = new double[3][3];
        matrix[0][0] = 1 - 2 * (y * y + z * z);
        matrix[0][1] = 2 * (x * y - w * z);
        matrix[0][2] = 2 * (x * z + w * y);
        matrix[1][0] = 2 * (x * y + w * z);
        matrix[1][1] = 1 - 2 * (x * x + z * z);
        matrix[1][2] = 2 * (y * z - w * x);
        matrix[2][0] = 2 * (x * z - w * y);
        matrix[2][1] = 2 * (y * z + w * x);
        matrix[2][2] = 1 - 2 * (x * x + y * y);
        return matrix;
    }

    @Override
    public String toString() {
        return "Quaternion{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
    /**
     * Multiplies a vector with a matrix
     * @param matrix matrix to multiply with
     * @param vector vector to multiply
     * @return the result of the multiplication
     */

    private double  [] matriceMultiplication(double[][] matrix, double[] vector) {
        double[] result = new double[3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }
    public Vector3 rotatePoint(Vector3 point) {
        double[] result = matriceMultiplication(toRotationMatrix(), new double[]{point.x, point.y, point.z});
        return new Vector3(result[0], result[1], result[2]);
    }

}
