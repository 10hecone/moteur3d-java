import javax.vecmath.Vector3d;
import java.awt.*;

public class Object3D {
	public Vector3d[] vertices;
	public Vector3d position;
	public Vector3d rotation;
	public Vector3d scale;
	public Face[] faces;
	public Object3D(Primitive primitive, Vector3d position) {
		rotation = new Vector3d(0,0,0);
		this.position = position;
		if (primitive == Primitive.Plane) {
			makePlane();
		}
		else if (primitive == Primitive.Cube) {
			makeCube();
		}
		else if (primitive == Primitive.Pyramid) {
			makePyramid();
		}
		else if (primitive == Primitive.Triangle) {
			makeTriangle();
		}
		else if (primitive == Primitive.Tetrahedron) {
			makeTetrahedron();
		}
		scale = new Vector3d(1, 1, 1);
		// replace null faces with empty faces
		for (int i = 0; i < faces.length; i++) {
			if (faces[i] == null) {
				faces[i] = new Face(0, 0, 0);
			}
			faces[i].object = this;
		}
	}

	public Object3D(Vector3d position) {
		vertices = new Vector3d[0];
		this.position = position;
		rotation = new Vector3d(0, 0, 0);
	}

	private void makePlane() {
		// create vertices
		vertices = new Vector3d[4];
		vertices[0] = new Vector3d(-50, -50, 0); // top left
		vertices[1] = new Vector3d(50, -50, 0); // top right
		vertices[2] = new Vector3d(50, 50, 0); // bottom right
		vertices[3] = new Vector3d(-50, 50, 0); // bottom left
		// create faces
		faces = new Face[2];
		faces[0] = new Face(3, 0, 1);
		faces[1] = new Face(1, 2, 3);
	}

	private void makeCube() {
		// create vertices
		vertices = new Vector3d[8];
		vertices[0] = new Vector3d(-50, -50, -50); // top left front
		vertices[1] = new Vector3d(50, -50, -50); // top right front
		vertices[2] = new Vector3d(50, 50, -50); // bottom right front
		vertices[3] = new Vector3d(-50, 50, -50); // bottom left front
		vertices[4] = new Vector3d(-50, -50, 50); // top left back
		vertices[5] = new Vector3d(50, -50, 50); // top right back
		vertices[6] = new Vector3d(50, 50, 50); // bottom right back
		vertices[7] = new Vector3d(-50, 50, 50); // bottom left back

		// create faces
		faces = new Face[12];
		faces[0] = new Face(0, 1, 2);
		faces[0].color = Color.RED;
		faces[1] = new Face(0, 2, 3);
		faces[1].color = Color.RED;
		faces[2] = new Face(1, 5, 6);
		faces[2].color = Color.GREEN;
		faces[3] = new Face(1, 6, 2);
		faces[3].color = Color.GREEN;
		faces[4] = new Face(5, 4, 7);
		faces[4].color = Color.BLUE;
		faces[5] = new Face(5, 7, 6);
		faces[5].color = Color.BLUE;
		faces[6] = new Face(4, 0, 3);
		faces[6].color = Color.YELLOW;
		faces[7] = new Face(4, 3, 7);
		faces[7].color = Color.YELLOW;
		faces[8] = new Face(3, 2, 6);
		faces[8].color = Color.CYAN;
		faces[9] = new Face(3, 6, 7);
		faces[9].color = Color.CYAN;
		faces[10] = new Face(4, 5, 1);
		faces[10].color = Color.MAGENTA;
		faces[11] = new Face(4, 1, 0);
		faces[11].color = Color.MAGENTA;
	}

	private void makePyramid() {
		vertices = new Vector3d[5];
		vertices[0] = new Vector3d(0, 50, 0); // top
		vertices[1] = new Vector3d(-50, -50, -50); // front left
		vertices[2] = new Vector3d(50, -50, -50); // front right
		vertices[3] = new Vector3d(50, -50, 50); // back right
		vertices[4] = new Vector3d(-50, -50, 50); // back left

		faces = new Face[6];
		faces[0] = new Face(0, 1, 2);
		faces[0].color = Color.red;
		faces[1] = new Face(0, 2, 3);
		faces[1].color = Color.green;
		faces[2] = new Face(0, 3, 4);
		faces[2].color = Color.blue;
		faces[3] = new Face(0, 4, 1);
		faces[3].color = Color.yellow;
		faces[4] = new Face(1, 4, 3);
		faces[4].color = Color.orange;
		faces[5] = new Face(1, 3, 2);
		faces[5].color = Color.pink;
	}

	private void makeTriangle() {
		// create vertices
		vertices = new Vector3d[3];
		vertices[0] = new Vector3d(-50, -50, -50); // top left
		vertices[1] = new Vector3d(50, -50, -50); // top right
		vertices[2] = new Vector3d(0, 50, -50); // bottom

		// create faces
		faces = new Face[1];
		faces[0] = new Face(0, 1, 2);
		faces[0].color = Color.RED;
	}

	private void makeTetrahedron() {
		// create vertices
		vertices = new Vector3d[4];
		vertices[0] = new Vector3d(-50, -50, -50); // top left
		vertices[1] = new Vector3d(50, -50, -50); // top right
		vertices[2] = new Vector3d(0, -50, 50); // bottom
		vertices[3] = new Vector3d(0, 50, 0); // top

		// create faces
		faces = new Face[4];
		faces[0] = new Face(0, 1, 3);
		faces[0].color = Color.red;
		faces[1] = new Face(1, 2, 3);
		faces[1].color = Color.green;
		faces[2] = new Face(2, 0, 3);
		faces[2].color = Color.blue;
		faces[3] = new Face(0, 2, 1);
		faces[3].color = Color.yellow;
	}
}
