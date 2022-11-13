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
	}

	public Object3D(Vector3 position) {
		edges = new Edge[0];
		vertices = new Vector3[0];
		this.position = position;
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

	public void makeCube() {
		// create vertices
		vertices = new Vector3[8];
		vertices[0] = new Vector3(0, 0, 0); // top left back
		vertices[1] = new Vector3(100, 0, 0); // top right back
		vertices[2] = new Vector3(100, 100, 0); // bottom right back
		vertices[3] = new Vector3(0, 100, 0); // bottom left back
		vertices[4] = new Vector3(0, 0, 100); // top left front
		vertices[5] = new Vector3(100, 0, 100); // top right front
		vertices[6] = new Vector3(100, 100, 100); // bottom right front
		vertices[7] = new Vector3(0, 100, 100); // bottom left

		// create edges
		edges = new Edge[12];
		edges[0] = new Edge(vertices[0], vertices[1]); // top back
		edges[1] = new Edge(vertices[1], vertices[2]); // right back
		edges[2] = new Edge(vertices[2], vertices[3]); // bottom back
		edges[3] = new Edge(vertices[3], vertices[0]); // left back
		edges[4] = new Edge(vertices[0], vertices[4]); // top left
		edges[5] = new Edge(vertices[4], vertices[5]); // top front
		edges[6] = new Edge(vertices[5], vertices[1]); // top right
		edges[7] = new Edge(vertices[2], vertices[6]); // bottom right
		edges[8] = new Edge(vertices[6], vertices[7]); // bottom front
		edges[9] = new Edge(vertices[7], vertices[3]); // bottom left
		edges[10] = new Edge(vertices[6], vertices[5]); // front right
		edges[11] = new Edge(vertices[4], vertices[7]); // front left
	}

	public void makePyramid() {
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

	public void makeTriangle() {
		// create vertices
		vertices = new Vector3[3];
		vertices[0] = new Vector3(0, 0, 0); // top left
		vertices[1] = new Vector3(50, 100, 0); // top right
		vertices[2] = new Vector3(100, 0, 0); // bottom right
		// create edges
		edges = new Edge[3];
		edges[0] = new Edge(vertices[0], vertices[1]); // top
		edges[1] = new Edge(vertices[1], vertices[2]); // right
		edges[2] = new Edge(vertices[0], vertices[2]); // bottom
	}

	public void makeTetrahedron() {
		// create vertices
		vertices = new Vector3[4];
		vertices[0] = new Vector3(0, 0, 0); // base
		vertices[1] = new Vector3(50, 100, 0); // base
		vertices[2] = new Vector3(100, 0, 0); // base
		vertices[3] = new Vector3(50, 50, 100); // top
		// create edges
		edges = new Edge[6];
		edges[0] = new Edge(vertices[0], vertices[1]); // base
		edges[1] = new Edge(vertices[1], vertices[2]); // base
		edges[2] = new Edge(vertices[2], vertices[0]); // base
		edges[3] = new Edge(vertices[0], vertices[3]); // top
		edges[4] = new Edge(vertices[1], vertices[3]); // top
		edges[5] = new Edge(vertices[2], vertices[3]); // top
	}

	public  void makeDipyramid() {
		vertices = new Vector3[6];
		vertices[0] = new Vector3(50, 50, 0);
		vertices[1] = new Vector3(100, 50, 50);
		vertices[2] = new Vector3(50, 50, 100);
		vertices[3] = new Vector3(0, 50, 50);
		vertices[4] = new Vector3(50, 100, 50);
		vertices[5] = new Vector3(50, 0, 50);

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
