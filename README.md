# LP7DPBO2024C2
## Janji
Saya ilham akbar NIM [2201017] mengerjakan Latihan Praktikum 7 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

## Desain Program

* Program ini dibuat menggunakan Java dan Swing untuk GUI.
* Program ini terdiri dari enam kelas utama: App, FlappyBird, Pipe, Player, StartForm, dan ImagePanel.
* Kelas App berfungsi sebagai titik masuk program dan bertugas memulai game.
* Kelas FlappyBird adalah subclass dari JPanel dan mengimplementasikan ActionListener dan KeyListener. Kelas ini berisi logika utama game.
* Kelas Pipe digunakan untuk merepresentasikan pipa dalam game. Setiap pipa memiliki posisi, ukuran, gambar, kecepatan, dan status apakah sudah dilewati oleh pemain atau belum.
* Kelas Player digunakan untuk merepresentasikan pemain dalam game. Setiap pemain memiliki posisi, ukuran, gambar, dan kecepatan.
* Kelas StartForm adalah subclass dari JFrame dan berfungsi sebagai layar awal sebelum game dimulai. Kelas ini berisi tombol “Start Game” yang, ketika ditekan, akan memulai game.
* Kelas ImagePanel adalah subclass dari JPanel dan digunakan untuk menampilkan gambar sebagai latar belakang.

## Alur Program

* Ketika program dimulai, metode main di kelas StartForm dieksekusi. Ini menampilkan layar awal yang berisi tombol “Start Game”.
* Ketika tombol “Start Game” ditekan, metode startGame di kelas App dipanggil. Metode ini membuat frame dan menambahkan objek FlappyBird ke dalam frame tersebut.
* Dalam konstruktor FlappyBird, berbagai variabel diinisialisasi, gambar dimuat, objek Player dan ArrayList untuk Pipe dibuat, dan Timer untuk loop game dan cooldown pipa dibuat.
* Label “Start Game” ditambahkan ke panel dan gameLoop dan pipesCooldown dihentikan sampai tombol spasi ditekan.
* Ketika tombol spasi ditekan, metode startGame dipanggil yang memulai gameLoop dan pipesCooldown dan menyembunyikan label “Start Game”.
* Dalam gameLoop, metode move, checkCollision, dan repaint dipanggil setiap kali timer memicu event.
* Metode move memperbarui kecepatan dan posisi pemain berdasarkan gravitasi dan memindahkan semua pipa ke kiri berdasarkan kecepatan x mereka.
* Metode checkCollision memeriksa apakah pemain menyentuh batas bawah JFrame atau menabrak pipa dan memanggil metode gameOver jika kondisi tersebut terpenuhi.
* Jika pemain melewati pipa, metode checkCollision juga menambahkan skor dan menandai pipa sebagai telah dilewati.
* Ketika game berakhir, metode gameOver dipanggil yang menghentikan gameLoop dan pipesCooldown, mereset skor, dan menampilkan label dan tombol game over.
* Jika pemain menekan ‘R’ setelah game berakhir, metode restartGame dipanggil yang merestart game.

## dokumentasi

https://github.com/Ilham9675/LP7DPBO2024C2/assets/117561201/e2df99c1-fe4f-4218-adec-1d7b7af52812
