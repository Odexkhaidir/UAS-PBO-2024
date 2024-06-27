# Aplikasi Laporan Kehilangan Barang

## Gambaran Umum

Aplikasi Laporan Kehilangan Barang adalah aplikasi desktop berbasis Java yang dirancang untuk mengelola laporan barang hilang dan barang yang ditemukan di area kampus. Sistem ini mengimplementasikan pola desain MVC (Model-View-Controller), memastikan pemisahan yang jelas antara berbagai hal dan memfasilitasi pemeliharaan dan skalabilitas.

## Fitur

- **Otentikasi Pengguna:** Login yang aman untuk mahasiswa dan administrator.
- **Manajemen Laporan:** Membuat, memperbarui, menghapus, dan melihat laporan barang yang hilang dan ditemukan (CRUD feature).
- **Fitur Pencarian:** Mencari laporan tertentu dengan cepat menggunakan kata kunci.
- **Role-Based Access:** Tampilan aplikasi yang berbeda untuk mahasiswa dan administrator.
- **Real-Time Updates:** Pembaruan status laporan secara otomatis beserta notifikasi laporan baru ke seluruh pengguna.

## Prasyarat

- Java Development Kit (JDK) 8 atau lebih tinggi
- Apache Netbeans
- SQLiteStudio 3 atau lebih tinggi

## Instalasi & Konfigurasi

1. **Tambah Project**
   Terdapat dua opsi:
   
   a. Clone the repository
    ```sh
    git clone https://github.com/Odexkhaidir/UAS-PBO-2024.git
    cd UAS-PBO-2024
    ```
    
   b. Atau download ZIP pada Code

3. **Buka project di Apache NetBeans**

4. **Impor Database Project ke SQLiteStudio**
    - Buka SQLiteStudio dan buat database dengan 'Add a Database'
    - Pada File, tambahkan path folder Database yang berisi `mgr_db.db` atau Browse
    - Pada Name, silahkan beri nama mgr_db

5. **Jalankan Aplikasi**
   
   Buka file `MainApp.java` dan jalankan untuk memulai aplikasi
     
