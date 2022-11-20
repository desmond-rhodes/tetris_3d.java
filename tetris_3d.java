public class tetris_3d {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				javax.swing.JFrame window = new javax.swing.JFrame("Tetris 3D");
				window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				window.setSize(480, 360);
				window.setLocationRelativeTo(null);
				window.setVisible(true);
			}
		});
		System.out.printf("Hello, world!\n");
	}
}
