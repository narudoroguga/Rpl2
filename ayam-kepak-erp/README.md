# Kepak ERP GUI — Java 17

Prototype GUI desktop untuk kebutuhan praktikum RPL, dibuat dengan Java Swing dan dapat dibuka sebagai proyek Maven di Apache NetBeans.

## Modul navigasi
- Dashboard
- POS & Pemesanan Kasir
- Manajemen Dapur & Batch Prep
- Inventaris Bahan Baku
- Keuangan & Laporan Penjualan

## Menjalankan di Apache NetBeans
1. Pastikan JDK 17 terpasang.
2. Buka Apache NetBeans.
3. Pilih **File → Open Project**, lalu pilih folder `ayam-kepak-erp` (folder yang berisi `pom.xml`).
4. Klik kanan proyek → **Run**.

Alternatif terminal dari folder proyek:
```bash
mvn clean package
java -cp target/classes id.ac.aymkepak.Main
```

## Catatan
Ini hanya prototype GUI. Data pada tabel dan kartu adalah contoh statis. Tombol aksi menampilkan pesan placeholder; belum ada database, autentikasi, transaksi nyata, printer, ekspor file, atau sinkronisasi real-time.
