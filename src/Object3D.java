public class Object3D {
	public Vector3[] vertices;
	public Edge[] edges;
	public Vector3 position;
	public Vector3 rotation;
	public Object3D(Primitive primitive, Vector3 position) {
		rotation = new Vector3(0,0,0);
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
		else if (primitive == Primitive.Dypiramid) {
			makeDipyramid();
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
		vertices = new Vector3[4];
		vertices[0] = new Vector3(0, 0, 0); // top left
		vertices[1] = new Vector3(100, 0, 0); // top right
		vertices[2] = new Vector3(100, 100, 0); // bottom right
		vertices[3] = new Vector3(0, 100, 0); // bottom left
		// create edges
		edges = new Edge[4];
		edges[0] = new Edge(vertices[0], vertices[1]); // top
		edges[1] = new Edge(vertices[1], vertices[2]); // right
		edges[2] = new Edge(vertices[2], vertices[3]); // bottom
		edges[3] = new Edge(vertices[3], vertices[0]); // left
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
		// create vertices
		vertices = new Vector3[5];
		vertices[0] = new Vector3(0, 0, 0); // top left
		vertices[1] = new Vector3(100, 0, 0); // top right
		vertices[2] = new Vector3(100, 100, 0); // bottom right
		vertices[3] = new Vector3(0, 100, 0); // bottom left
		vertices[4] = new Vector3(50, 50, 100);
		// create edges
		edges = new Edge[8];
		edges[0] = new Edge(vertices[0], vertices[1]); // top
		edges[1] = new Edge(vertices[1], vertices[2]); // right
		edges[2] = new Edge(vertices[2], vertices[3]); // bottom
		edges[3] = new Edge(vertices[3], vertices[0]); // left
		edges[4] = new Edge(vertices[4], vertices[0]);
		edges[5] = new Edge(vertices[4], vertices[1]);
		edges[6] = new Edge(vertices[4], vertices[2]);
		edges[7] = new Edge(vertices[4], vertices[3]);
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

	private void makeDipyramid() {
		vertices = new Vector3d[6];
		vertices[0] = new Vector3d(-50, -50, 0); // top left front
		vertices[1] = new Vector3d(50, -50, 0); // top right front
		vertices[2] = new Vector3d(50, 50, 0); // bottom right front
		vertices[3] = new Vector3d(-50, 50, 0); // bottom left front
		vertices[4] = new Vector3d(0, 0, 50); // top
		vertices[5] = new Vector3d(0, 0, -50); // bottom

		edges = new Edge[12];
		edges[0] = new Edge(vertices[0], vertices[1]);
		edges[1] = new Edge(vertices[1], vertices[2]);
		edges[2] = new Edge(vertices[2], vertices[3]);
		edges[3] = new Edge(vertices[3], vertices[0]);
		edges[4] = new Edge(vertices[0], vertices[4]);
		edges[5] = new Edge(vertices[1], vertices[4]);
		edges[6] = new Edge(vertices[2], vertices[4]);
		edges[7] = new Edge(vertices[3], vertices[4]);
		edges[8] = new Edge(vertices[0], vertices[5]);
		edges[9] = new Edge(vertices[1], vertices[5]);
		edges[10] = new Edge(vertices[2], vertices[5]);
		edges[11] = new Edge(vertices[3], vertices[5]);
	}
}
