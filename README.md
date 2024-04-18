# LP7DPBO2024C2
## Janji
Saya ilham akbar NIM [2201017] mengerjakan Latihan Praktikum 7 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

## Desain Program
* Program ini dibuat menggunakan Java dan Swing untuk GUI.
* Program ini terdiri dari empat kelas utama: App, FlappyBird, Pipe, dan Player.
* Kelas App berfungsi sebagai titik masuk program dan bertugas membuat frame untuk game.
* Kelas FlappyBird adalah subclass dari JPanel dan mengimplementasikan ActionListener dan KeyListener. Kelas ini berisi logika utama game.
* Kelas Pipe digunakan untuk merepresentasikan pipa dalam game. Setiap pipa memiliki posisi, ukuran, gambar, kecepatan, dan status apakah sudah dilewati oleh pemain atau belum.
* Kelas Player digunakan untuk merepresentasikan pemain dalam game. Setiap pemain memiliki posisi, ukuran, gambar, dan kecepatan.


## Alur Program
* Ketika program dimulai, kelas App membuat frame dan menambahkan objek FlappyBird ke dalam frame tersebut.
* Dalam konstruktor FlappyBird, berbagai variabel diinisialisasi, gambar dimuat, objek Player dan ArrayList untuk Pipe dibuat, dan Timer untuk loop game dan cooldown pipa dibuat.
* Tombol “Mulai” ditambahkan ke panel dan gameLoop dan pipesCooldown dihentikan sampai tombol “Mulai” ditekan.
* Ketika tombol “Mulai” ditekan, metode startGame dipanggil yang memulai gameLoop dan pipesCooldown dan menyembunyikan tombol “Mulai”.
* Dalam gameLoop, metode move, checkCollision, dan repaint dipanggil setiap kali timer memicu event.
* Metode move memperbarui kecepatan dan posisi pemain berdasarkan gravitasi dan memindahkan semua pipa ke kiri berdasarkan kecepatan x mereka.
* Metode checkCollision memeriksa apakah pemain menyentuh batas bawah JFrame atau menabrak pipa dan memanggil metode gameOver jika kondisi tersebut terpenuhi.
* Jika pemain melewati pipa, metode checkCollision juga menambahkan skor dan menandai pipa sebagai telah dilewati.
* Ketika game berakhir, metode gameOver dipanggil yang menghentikan gameLoop dan pipesCooldown, mereset skor, dan menampilkan label dan tombol game over.
* Jika pemain menekan ‘R’ setelah game berakhir, metode restartGame dipanggil yang merestart game.

## dokumentasi

https://github.com/Ilham9675/LP7DPBO2024C2/assets/117561201/309196c4-b22c-4df9-9c4d-9e89d4d7b61a