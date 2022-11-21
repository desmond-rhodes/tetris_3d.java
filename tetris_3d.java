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

					private static void triangle(java.awt.Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
						g.fillPolygon(new int[] {x1, x2, x3}, new int[] {y1, y2, y3}, 3);
					}

					private void paintComponent(java.awt.Graphics2D g, int w, int h) {
						setBackground(java.awt.Color.BLACK);
						g.setColor(java.awt.Color.WHITE);
						triangle(g, 0, 0, w, 0, w, h);
						g.setColor(java.awt.Color.RED);
						triangle(g, w/2, (h-110)/2, (w+120)/2, (h+110)/2, (w-120)/2, (h+110)/2);
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
