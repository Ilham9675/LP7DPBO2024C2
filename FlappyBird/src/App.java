import javax.swing.*;

public class App {
    // Metode untuk memulai game
    public static void startGame() {
        // Membuat sebuah JFrame baru
        JFrame frame = new JFrame("Flappy Bird");
        // Mengatur operasi default ketika JFrame ditutup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Mengatur ukuran JFrame
        frame.setSize(360,640);
        // Menempatkan JFrame di tengah layar
        frame.setLocationRelativeTo(null);
        // Mencegah pengguna merubah ukuran JFrame
        frame.setResizable(false);

        // Membuat objek JPanel baru
        FlappyBird flappyBird = new FlappyBird();
        // Menambahkan objek JPanel ke JFrame
        frame.add(flappyBird);
        // Menyesuaikan ukuran JFrame agar sesuai dengan ukuran JPanel
        frame.pack();
        // Meminta fokus input untuk JPanel
        flappyBird.requestFocus();
        // Menampilkan JFrame
        frame.setVisible(true);
    }
}
