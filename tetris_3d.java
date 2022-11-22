public class tetris_3d {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				javax.swing.JFrame window = new javax.swing.JFrame("Tetris 3D");
				javax.swing.JPanel panel = new javax.swing.JPanel() {
					public void paintComponent(java.awt.Graphics g) {
						super.paintComponent(g);
						paintComponent((java.awt.Graphics2D) g, getWidth(), getHeight());
					}

					private void paintComponent(java.awt.Graphics2D g, int w, int h) {
						setBackground(java.awt.Color.BLACK);
						(new triangle(0, 0, 0, w, 0, 0, w, h, 0, java.awt.Color.WHITE)).draw(g);
						(new triangle(w/2, (h-110)/2, 0, (w+120)/2, (h+110)/2, 0, (w-120)/2, (h+110)/2, 0, java.awt.Color.RED)).draw(g);
					}
				};
				window.add(panel);
				window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				window.setSize(480, 360);
				window.setLocationRelativeTo(null);
				window.setVisible(true);
			}
		});
		System.out.printf("Hello, world!\n");
	}
}

class triangle {
	public int[] x;
	public int[] y;
	public double z;
	public java.awt.Color color;

	public triangle(
		float x1, float y1, float z1,
		float x2, float y2, float z2,
		float x3, float y3, float z3,
		java.awt.Color color
	) {
		x = new int[] {(int) x1, (int) x2, (int) x3};
		y = new int[] {(int) y1, (int) y2, (int) y3};
		z = ((double) z1 + (double) z2 + (double) z3) / 3.0;
		this.color = color;
	}

	public triangle(
		float x1, float y1, float z1,
		float x2, float y2, float z2,
		float x3, float y3, float z3,
		float r, float g, float b, float a
	) {
		this(x1, y1, z1, x2, y2, z2, x3, y3, z3, new java.awt.Color(r, g, b, a));
	}

	public triangle(
		float x1, float y1, float z1,
		float x2, float y2, float z2,
		float x3, float y3, float z3,
		float r, float g, float b
	) {
		this(x1, y1, z1, x2, y2, z2, x3, y3, z3, r, g, b, 1.0f);
	}

	public triangle(vec4 v1, vec4 v2, vec4 v3, java.awt.Color c) {
		this(v1.x(), v1.y(), v1.z(), v2.x(), v2.y(), v2.z(), v3.x(), v2.y(), v3.z(), c);
	}

	public triangle(vec4 v1, vec4 v2, vec4 v3, float r, float g, float b, float a) {
		this(v1, v2, v3, new java.awt.Color(r, g, b, a));
	}

	public triangle(vec4 v1, vec4 v2, vec4 v3, float r, float g, float b) {
		this(v1, v2, v3, r, g, b, 1.0f);
	}

	public triangle(vec4 v1, vec4 v2, vec4 v3, vec4 c) {
		this(v1, v2, v3, c.r(), c.g(), c.b(), c.a());
	}

	public void draw(java.awt.Graphics2D g) {
		g.setColor(color);
		g.fillPolygon(x, y, 3);
	}
}

class vec4 {
	private float[] e;

	public vec4(float e1, float e2, float e3, float e4) { e = new float[] {e1, e2, e3, e4}; }
	public vec4(float e1, float e2, float e3) { this(e1, e2, e3, 1.0f); }
	public vec4(float e1, float e2) { this(e1, e2, 0.0f, 1.0f); }
	public vec4(float e1) { this(e1, 0.0f, 0.0f, 1.0f); }
	public vec4() { this(0.0f, 0.0f, 0.0f, 1.0f); }

	public vec4 clone() { return new vec4(e[0], e[1], e[2], e[3]); }

	public float x() { return e[0]; }
	public float y() { return e[1]; }
	public float z() { return e[2]; }
	public float w() { return e[3]; }

	public vec4 x(float x) { e[0] = x; return this; }
	public vec4 y(float y) { e[1] = y; return this; }
	public vec4 z(float z) { e[2] = z; return this; }
	public vec4 w(float w) { e[3] = w; return this; }

	public float r() { return e[0]; }
	public float g() { return e[1]; }
	public float b() { return e[2]; }
	public float a() { return e[3]; }

	public vec4 r(float r) { e[0] = r; return this; }
	public vec4 g(float g) { e[1] = g; return this; }
	public vec4 b(float b) { e[2] = b; return this; }
	public vec4 a(float a) { e[3] = a; return this; }

	public vec4 add(vec4 v) {
		return new vec4(e[0]+v.e[0], e[1]+v.e[1], e[2]+v.e[2], e[3]+v.e[3]);
	}
	public vec4 subtract(vec4 v) {
		return new vec4(e[0]-v.e[0], e[1]-v.e[1], e[2]-v.e[2], e[3]-v.e[3]);
	}
	public vec4 multiply(float c) {
		return new vec4(e[0]*c, e[1]*c, e[2]*c, e[3]*c);
	}
	public vec4 divide(float c) {
		return new vec4(e[0]/c, e[1]/c, e[2]/c, e[3]/c);
	}

	public float dot(vec4 v) {
		return e[0]*v.e[0] + e[1]*v.e[1] + e[2]*v.e[2] + e[3]*v.e[3];
	}
	public float magnitude() {
		return (float) java.lang.Math.sqrt(dot(this));
	}
	public vec4 normalize() {
		return divide(magnitude());
	}
}

class mat4 {
	private float[] e;

	public mat4(
		float  e1, float  e2, float  e3, float  e4,
		float  e5, float  e6, float  e7, float  e8,
		float  e9, float e10, float e11, float e12,
		float e13, float e14, float e15, float e16
	) {
		e = new float[] {
			  e1,   e2,   e3,   e4,
			  e5,   e6,   e7,   e8,
			  e9,  e10,  e11,  e12,
			 e13,  e14,  e15,  e16
		};
	}

	public mat4(
		float  e1, float  e2, float  e3,
		float  e5, float  e6, float  e7,
		float  e9, float e10, float e11
	) {
		this(
			  e1,   e2,   e3, 0.0f,
			  e5,   e6,   e7, 0.0f,
			  e9,  e10,  e11, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f
		);
	}

	public mat4(
		float  e1, float  e2,
		float  e5, float  e6
	) {
		this(
			  e1,   e2, 0.0f, 0.0f,
			  e5,   e6, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f
		);
	}

	public mat4(
		float  e1
	) {
		this(
			  e1, 0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f
		);
	}

	public mat4() {
		this(
			1.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 1.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 1.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 1.0f
		);
	}

	public mat4 clone() {
		return new mat4(
			e[ 0], e[ 1], e[ 2], e[ 3],
			e[ 4], e[ 5], e[ 6], e[ 7],
			e[ 8], e[ 9], e[10], e[11],
			e[12], e[13], e[14], e[15]
		);
	}

	public mat4 transpose() {
		return new mat4(
			e[ 0], e[ 4], e[ 8], e[12],
			e[ 1], e[ 5], e[ 9], e[13],
			e[ 2], e[ 6], e[10], e[14],
			e[ 3], e[ 7], e[11], e[15]
		);
	}

	public float e(int i, int j) {
		return e[i*4+j];
	}

	public mat4 e(int i, int j, float c) {
		e[i*4+j] = c;
		return this;
	}

	public mat4 add(mat4 m) {
		return new mat4(
			e[ 0]+m.e[ 0], e[ 1]+m.e[ 1], e[ 2]+m.e[ 2], e[ 3]+m.e[ 3],
			e[ 4]+m.e[ 4], e[ 5]+m.e[ 5], e[ 6]+m.e[ 6], e[ 7]+m.e[ 7],
			e[ 8]+m.e[ 8], e[ 9]+m.e[ 9], e[10]+m.e[10], e[11]+m.e[11],
			e[12]+m.e[12], e[13]+m.e[13], e[14]+m.e[14], e[15]+m.e[15]
		);
	}

	public mat4 subtract(mat4 m) {
		return new mat4(
			e[ 0]-m.e[ 0], e[ 1]-m.e[ 1], e[ 2]-m.e[ 2], e[ 3]-m.e[ 3],
			e[ 4]-m.e[ 4], e[ 5]-m.e[ 5], e[ 6]-m.e[ 6], e[ 7]-m.e[ 7],
			e[ 8]-m.e[ 8], e[ 9]-m.e[ 9], e[10]-m.e[10], e[11]-m.e[11],
			e[12]-m.e[12], e[13]-m.e[13], e[14]-m.e[14], e[15]-m.e[15]
		);
	}

	public mat4 multiply(float c) {
		return new mat4(
			e[ 0]*c, e[ 1]*c, e[ 2]*c, e[ 3]*c,
			e[ 4]*c, e[ 5]*c, e[ 6]*c, e[ 7]*c,
			e[ 8]*c, e[ 9]*c, e[10]*c, e[11]*c,
			e[12]*c, e[13]*c, e[14]*c, e[15]*c
		);
	}

	public mat4 divide(float c) {
		return new mat4(
			e[ 0]/c, e[ 1]/c, e[ 2]/c, e[ 3]/c,
			e[ 4]/c, e[ 5]/c, e[ 6]/c, e[ 7]/c,
			e[ 8]/c, e[ 9]/c, e[10]/c, e[11]/c,
			e[12]/c, e[13]/c, e[14]/c, e[15]/c
		);
	}

	public vec4 multiply(vec4 v) {
		return new vec4(
			e[ 0]*v.x() + e[ 1]*v.y() + e[ 2]*v.z() + e[ 3]*v.w(),
			e[ 4]*v.x() + e[ 5]*v.y() + e[ 6]*v.z() + e[ 7]*v.w(),
			e[ 8]*v.x() + e[ 9]*v.y() + e[10]*v.z() + e[11]*v.w(),
			e[12]*v.x() + e[13]*v.y() + e[14]*v.z() + e[15]*v.w()
		);
	}

	public mat4 multiply(mat4 m) {
		return new mat4(
			e[ 0]*m.e[ 0] + e[ 1]*m.e[ 4] + e[ 2]*m.e[ 8] + e[ 3]*m.e[12],
			e[ 0]*m.e[ 1] + e[ 1]*m.e[ 5] + e[ 2]*m.e[ 9] + e[ 3]*m.e[13],
			e[ 0]*m.e[ 2] + e[ 1]*m.e[ 6] + e[ 2]*m.e[10] + e[ 3]*m.e[14],
			e[ 0]*m.e[ 3] + e[ 1]*m.e[ 7] + e[ 2]*m.e[11] + e[ 3]*m.e[15],

			e[ 4]*m.e[ 0] + e[ 5]*m.e[ 4] + e[ 6]*m.e[ 8] + e[ 7]*m.e[12],
			e[ 4]*m.e[ 1] + e[ 5]*m.e[ 5] + e[ 6]*m.e[ 9] + e[ 7]*m.e[13],
			e[ 4]*m.e[ 2] + e[ 5]*m.e[ 6] + e[ 6]*m.e[10] + e[ 7]*m.e[14],
			e[ 4]*m.e[ 3] + e[ 5]*m.e[ 7] + e[ 6]*m.e[11] + e[ 7]*m.e[15],

			e[ 8]*m.e[ 0] + e[ 9]*m.e[ 4] + e[10]*m.e[ 8] + e[11]*m.e[12],
			e[ 8]*m.e[ 1] + e[ 9]*m.e[ 5] + e[10]*m.e[ 9] + e[11]*m.e[13],
			e[ 8]*m.e[ 2] + e[ 9]*m.e[ 6] + e[10]*m.e[10] + e[11]*m.e[14],
			e[ 8]*m.e[ 3] + e[ 9]*m.e[ 7] + e[10]*m.e[11] + e[11]*m.e[15],

			e[12]*m.e[ 0] + e[13]*m.e[ 4] + e[14]*m.e[ 8] + e[15]*m.e[12],
			e[12]*m.e[ 1] + e[13]*m.e[ 5] + e[14]*m.e[ 9] + e[15]*m.e[13],
			e[12]*m.e[ 2] + e[13]*m.e[ 6] + e[14]*m.e[10] + e[15]*m.e[14],
			e[12]*m.e[ 3] + e[13]*m.e[ 7] + e[14]*m.e[11] + e[15]*m.e[15]
		);
	}

	private float minor(int i, int j) {
		float[] m = new float[9];
		for (int k = 0, l = 0; k < 16; ++k)
			if (k != i*4 && k != i*4+1 && k != i*4+2 && k != i*4+3 && k != j && k != 4+j && k != 8+j && k != 12+j)
				m[l++] = e[k];
		return m[0]*(m[4]*m[8]-m[5]*m[7]) - m[1]*(m[3]*m[8]-m[5]*m[6]) + m[2]*(m[3]*m[7]-m[4]*m[6]);
	}

	public float determinant() {
		return e[0]*minor(0, 0) - e[1]*minor(0, 1) + e[2]*minor(0, 2) - e[3]*minor(0, 3);
	}

	public mat4 inverse() {
		float det = determinant();
		return new mat4(
			 minor(0, 0)/det, -minor(1, 0)/det,  minor(2, 0)/det, -minor(3, 0)/det,
			-minor(0, 1)/det,  minor(1, 1)/det, -minor(2, 1)/det,  minor(3, 1)/det,
			 minor(0, 2)/det, -minor(1, 2)/det,  minor(2, 2)/det, -minor(3, 2)/det,
			-minor(0, 3)/det,  minor(1, 3)/det, -minor(2, 3)/det,  minor(3, 3)/det
		);
	}
}
