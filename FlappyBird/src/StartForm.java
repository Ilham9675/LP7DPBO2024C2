import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ImagePanel extends JPanel {
    private Image backgroundImage;

    // Konstruktor untuk kelas ImagePanel
    public ImagePanel(String fileName) {
        // Memuat gambar
        backgroundImage = new ImageIcon(getClass().getResource(fileName)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Menggambar gambar sebagai latar belakang
        g.drawImage(backgroundImage, 0, 0, this);
    }
}

// Kelas StartForm yang merupakan subclass dari JFrame
public class StartForm extends JFrame {
    // Konstruktor untuk kelas StartForm
    public StartForm() {
        // Mengatur judul untuk JFrame
        setTitle("Start Game");
        // Mengatur operasi default ketika JFrame ditutup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Mengatur ukuran JFrame
        setSize(360, 640);
        // Menempatkan JFrame di tengah layar
        setLocationRelativeTo(null);
        // Mencegah pengguna merubah ukuran JFrame
        setResizable(false);

        ImagePanel contentPane = new ImagePanel("assets/background.png");
        // Mengatur contentPane untuk JFrame
        setContentPane(contentPane);

        // Membuat tombol start
        JButton startButton = new JButton("Start Game");
        // Mengatur warna latar belakang tombol menjadi hitam
        startButton.setBackground(Color.GREEN);

        // Mengatur warna teks tombol menjadi putih
        startButton.setForeground(Color.WHITE);

        // Menghilangkan border
        startButton.setBorderPainted(false);

        // Menghilangkan efek fokus
        startButton.setFocusPainted(false);

        // Menambahkan ActionListener ke tombol start
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menutup StartForm
                dispose();

                // Memulai game FlappyBird
                App.startGame();
            }
        });

        // Mengatur LayoutManager ke null
        getContentPane().setLayout(null);

        // Menetapkan posisi dan ukuran tombol
        startButton.setBounds(130, 250, 100, 30);

        // Menambahkan tombol start ke StartForm
        getContentPane().add(startButton);
    }

    // Metode main untuk menjalankan program
    public static void main(String[] args) {
        // Menampilkan StartForm
        new StartForm().setVisible(true);
    }
}
