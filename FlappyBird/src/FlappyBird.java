import javax.swing.*;
import  java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// Kelas FlappyBird yang merupakan subclass dari JPanel dan mengimplementasikan ActionListener dan KeyListener
public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    // Variabel untuk lebar dan tinggi frame
    int frameWidth = 360;
    int frameHeight = 640;

    // Variabel untuk menyimpan gambar latar belakang, burung, dan pipa
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // Variabel untuk posisi awal, lebar, dan tinggi pemain
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    // Objek Player
    Player player;

    // Variabel untuk posisi awal, lebar, dan tinggi pipa
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    // ArrayList untuk menyimpan objek Pipe
    ArrayList<Pipe> pipes;

    // Timer untuk loop game dan cooldown pipa
    Timer gameLoop;
    Timer pipesCooldown;

    // Variabel untuk gravitasi dan skor
    int gravity = 1;
    int score = 0;

    // Tombol dan label untuk game over
    JButton restartButton;
    JLabel gameOverLabel;

    JLabel startLabel;
    // Konstruktor untuk kelas FlappyBird
    public FlappyBird() {
        // Mengatur ukuran dan fokus panel
        setPreferredSize(new Dimension(frameWidth,frameHeight));
        setFocusable(true);
        addKeyListener(this);

        // Memuat gambar
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        // Membuat objek Player dan ArrayList untuk Pipe
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<Pipe>();

        // Membuat Timer untuk cooldown pipa
        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Menempatkan pipa
                placePipes();
            }
        });
        pipesCooldown.start();

        // Membuat Timer untuk loop game
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
        // Mengatur LayoutManager ke null
        setLayout(null);

        // Menginisialisasi gameOverLabel dan restartButton
        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);
        gameOverLabel.setVisible(false);
        add(gameOverLabel);

        restartButton = new JButton("Restart");
        restartButton.setBackground(Color.RED);
        restartButton.setForeground(Color.WHITE);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Memulai ulang game
                restartGame();
            }
        });
        restartButton.setVisible(false);
        add(restartButton);

        setLayout(null);

        // Tambahkan kode untuk label "Mulai" di sini
        startLabel = new JLabel("Start Game");
        startLabel.setHorizontalAlignment(JLabel.CENTER);
        startLabel.setVerticalAlignment(JLabel.CENTER);
        startLabel.setFont(new Font("Arial", Font.BOLD, 24));
        startLabel.setForeground(Color.WHITE);

        startLabel.setBounds((frameWidth - 200) / 2, (frameHeight - 50) / 2, 200, 50);
        startLabel.setVisible(true);
        add(startLabel);


        // Menghentikan gameLoop dan pipesCooldown di awal
        gameLoop.stop();
        pipesCooldown.stop();
    }

    public void startGame() {
        // Memulai ulang loop game dan cooldown pipa
        gameLoop.start();
        pipesCooldown.start();

        // Sembunyikan label Mulai
        startLabel.setVisible(false);
    }



    // Metode untuk menggambar elemen game
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        drawScore(g);
    }

    // Metode untuk menggambar latar belakang, pemain, dan pipa
    private void draw(Graphics g) {
        g.drawImage(backgroundImage,0,0,frameWidth,frameHeight,null);

        g.drawImage(player.getImage(), player.getPosX(),player.getPosY() ,player.getWidth(), player.getHeight() ,null);

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipa = pipes.get(i);
            g.drawImage(pipa.getImage(),pipa.getPosX(),pipa.getPosY(),pipa.getWidth(),pipa.getHeight(),null);

        }
    }

    // Metode untuk menggambar skor
    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 40); // Ubah posisi ini sesuai kebutuhan Anda
    }

    public void move(){
        // Mengupdate kecepatan dan posisi pemain berdasarkan gravitasi
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));
        // Memindahkan semua pipa ke kiri berdasarkan kecepatan x mereka
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());
        }
    }

    public  void placePipes(){
        // Menghitung posisi y acak untuk pipa atas
        int randomPosY = (int) (pipeStartPosY - pipeHeight/4 - Math.random() * (pipeHeight/2));
        // Menentukan jarak antara pipa atas dan bawah
        int openingSpace = frameHeight/4;

        // Membuat dan menambahkan pipa atas ke daftar pipa
        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        // Membuat dan menambahkan pipa bawah ke daftar pipa
        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth,pipeHeight,lowerPipeImage);
        pipes.add(lowerPipe);
    }

    @Override
    public  void actionPerformed(ActionEvent e){
        // Memanggil metode move, checkCollision, dan repaint setiap kali timer gameLoop memicu event
        move();
        checkCollision();
        repaint();
    }

    @Override
    public  void  keyTyped(KeyEvent e){

    }

    @Override
    public  void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE && startLabel.isVisible() && !gameLoop.isRunning()){
            startGame();
        }
        // Jika pemain menekan spasi, mengubah kecepatan y pemain
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            player.setVelocityY(-10);
        }
        // Jika game sudah berakhir dan pemain menekan 'R', memulai ulang permainan
        else if (!gameLoop.isRunning() && restartButton.isVisible() && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

    }
    public void checkCollision() {
        // Jika pemain menyentuh batas bawah JFrame, memanggil metode gameOver
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            gameOver();
        }

        // Jika pemain menabrak pipa, memanggil metode gameOver
        for (Pipe pipe : pipes) {
            if (player.getPosX() < pipe.getPosX() + pipe.getWidth() &&
                    player.getPosX() + player.getWidth() > pipe.getPosX() &&
                    player.getPosY() < pipe.getPosY() + pipe.getHeight() &&
                    player.getPosY() + player.getHeight() > pipe.getPosY()) {
                gameOver();
            }

        }
        // Jika pemain melewati pipa, menambahkan skor dan menandai pipa sebagai telah dilewati
        for (int i = 0; i < pipes.size(); i += 2) { // Ubah ini untuk melompati setiap pipa kedua
            Pipe pipe = pipes.get(i);
            if (!pipe.isPassed() && player.getPosX() > pipe.getPosX() + pipe.getWidth()) {
                pipe.setPassed(true);
                score++;
            }
        }
    }

    public void gameOver() {
        // Menghentikan loop game dan cooldown pipa, mereset skor, dan menampilkan label dan tombol game over
        gameLoop.stop();
        pipesCooldown.stop();



        int labelWidth = 200;
        int labelHeight = 50;
        int buttonWidth = 100;
        int buttonHeight = 25;
        gameOverLabel.setBounds((getWidth() - labelWidth) / 2, (getHeight() - labelHeight) / 2 - buttonHeight / 2, labelWidth, labelHeight);
        restartButton.setBounds((getWidth() - buttonWidth) / 2, (getHeight() + labelHeight) / 2 - buttonHeight / 2, buttonWidth, buttonHeight);

        gameOverLabel.setVisible(true);
        restartButton.setVisible(true);
    }

    public void restartGame() {
        // Mereset posisi dan kecepatan pemain, menghapus semua pipa, mereset skor, dan menyembunyikan label dan tombol game over
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);

        // Hapus semua pipa yang ada
        pipes.clear();

        // Reset skor
        score = 0;

        // Sembunyikan label dan tombol game over
        gameOverLabel.setVisible(false);
        restartButton.setVisible(false);

        // Memulai ulang loop game dan cooldown pipa
        gameLoop.start();
        pipesCooldown.start();

    }

}
