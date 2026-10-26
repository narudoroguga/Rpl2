package id.ac.aymkepak;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    private static final Color BG = new Color(246, 247, 249);
    private static final Color NAVY = new Color(25, 35, 52);
    private static final Color ORANGE = new Color(239, 126, 45);
    private static final Color TEXT = new Color(39, 48, 63);
    private static final Color MUTED = new Color(126, 135, 148);
    private final JFrame frame = new JFrame("Kepak ERP | Ayam Kepak Madu");
    private final JPanel pages = new JPanel(new CardLayout());
    private final Map<String, JButton> navButtons = new LinkedHashMap<>();
    private String activePage = "Dashboard";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            new Main().show();
        });
    }

    private void show() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1100, 700));
        frame.setSize(1360, 850);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(buildSidebar(), BorderLayout.WEST);
        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(BG);
        right.add(buildTopbar(), BorderLayout.NORTH);
        pages.setBackground(BG);
        pages.add(dashboardPage(), "Dashboard");
        pages.add(posPage(), "POS & Pesanan");
        pages.add(kitchenPage(), "Manajemen Dapur");
        pages.add(inventoryPage(), "Inventaris");
        pages.add(financePage(), "Keuangan & Laporan");
        right.add(pages, BorderLayout.CENTER);
        frame.add(right, BorderLayout.CENTER);
        navigate("Dashboard");
        frame.setVisible(true);
    }

    private JPanel buildSidebar() {
        JPanel side = new JPanel();
        side.setPreferredSize(new Dimension(245, 0));
        side.setBackground(NAVY);
        side.setBorder(new EmptyBorder(22, 16, 18, 16));
        side.setLayout(new BoxLayout(side, BoxLayout.Y_AXIS));
        JLabel logo = new JLabel("  KEPak"); logo.setForeground(Color.WHITE); logo.setFont(new Font("SansSerif", Font.BOLD, 27));
        JLabel sub = new JLabel("  AYAM KEPAK MADU"); sub.setForeground(new Color(180, 190, 204)); sub.setFont(new Font("SansSerif", Font.BOLD, 10));
        side.add(logo); side.add(Box.createVerticalStrut(3)); side.add(sub); side.add(Box.createVerticalStrut(42));
        JLabel menu = new JLabel("MENU UTAMA"); menu.setForeground(new Color(139, 151, 169)); menu.setFont(new Font("SansSerif", Font.BOLD, 10));
        menu.setBorder(new EmptyBorder(0, 10, 12, 0)); side.add(menu);
        addNav(side, "Dashboard", "▦"); addNav(side, "POS & Pesanan", "＋"); addNav(side, "Manajemen Dapur", "♨");
        addNav(side, "Inventaris", "▤"); addNav(side, "Keuangan & Laporan", "▥");
        side.add(Box.createVerticalGlue());
        JPanel status = new JPanel(new BorderLayout(8, 4)); status.setOpaque(false); status.setBorder(new EmptyBorder(12, 8, 8, 8));
        JLabel dot = new JLabel("●"); dot.setForeground(new Color(93, 204, 142));
        JLabel online = new JLabel("Mode demo GUI"); online.setForeground(new Color(221, 227, 235)); online.setFont(new Font("SansSerif", Font.PLAIN, 12));
        status.add(dot, BorderLayout.WEST); status.add(online, BorderLayout.CENTER); side.add(status);
        JLabel version = new JLabel("  ERP UI  •  Java 17"); version.setForeground(new Color(122, 135, 153)); version.setFont(new Font("SansSerif", Font.PLAIN, 10)); side.add(version);
        return side;
    }

    private void addNav(JPanel side, String name, String glyph) {
        JButton b = new JButton(glyph + "    " + name);
        b.setHorizontalAlignment(SwingConstants.LEFT); b.setFont(new Font("SansSerif", Font.PLAIN, 13));
        b.setForeground(new Color(211, 218, 228)); b.setBackground(NAVY); b.setBorder(new EmptyBorder(13, 13, 13, 8));
        b.setFocusPainted(false); b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        b.addActionListener(e -> navigate(name)); navButtons.put(name, b); side.add(b); side.add(Box.createVerticalStrut(5));
    }

    private JPanel buildTopbar() {
        JPanel top = new JPanel(new BorderLayout()); top.setBackground(Color.WHITE); top.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 233, 238)));
        top.setBorder(new EmptyBorder(17, 28, 17, 28));
        JLabel crumb = new JLabel("OPERASIONAL  /  ERP SYSTEM"); crumb.setForeground(MUTED); crumb.setFont(new Font("SansSerif", Font.BOLD, 10));
        JLabel date = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")) + "    •    Admin Demo"); date.setForeground(new Color(90, 100, 115)); date.setFont(new Font("SansSerif", Font.PLAIN, 12));
        top.add(crumb, BorderLayout.WEST); top.add(date, BorderLayout.EAST); return top;
    }

    private void navigate(String name) {
        activePage = name; ((CardLayout) pages.getLayout()).show(pages, name);
        navButtons.forEach((key, button) -> { boolean selected = key.equals(name); button.setBackground(selected ? new Color(239, 126, 45) : NAVY); button.setForeground(Color.WHITE); });
    }

    private JPanel page(String eyebrow, String title, String description) {
        JPanel p = new JPanel(new BorderLayout(0, 20)); p.setBackground(BG); p.setBorder(new EmptyBorder(27, 30, 30, 30));
        JPanel heading = new JPanel(); heading.setOpaque(false); heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        JLabel e = new JLabel(eyebrow.toUpperCase()); e.setForeground(ORANGE); e.setFont(new Font("SansSerif", Font.BOLD, 10));
        JLabel t = new JLabel(title); t.setForeground(TEXT); t.setFont(new Font("SansSerif", Font.BOLD, 27));
        JLabel d = new JLabel(description); d.setForeground(MUTED); d.setFont(new Font("SansSerif", Font.PLAIN, 12));
        heading.add(e); heading.add(Box.createVerticalStrut(7)); heading.add(t); heading.add(Box.createVerticalStrut(5)); heading.add(d);
        p.add(heading, BorderLayout.NORTH); return p;
    }

    private JPanel contentColumn() { JPanel p = new JPanel(); p.setOpaque(false); p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS)); return p; }
    private JPanel card(String title, JComponent content) {
        JPanel p = new JPanel(new BorderLayout(0, 14)); p.setBackground(Color.WHITE); p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(231, 234, 239)), new EmptyBorder(18, 18, 18, 18)));
        JLabel h = new JLabel(title); h.setForeground(TEXT); h.setFont(new Font("SansSerif", Font.BOLD, 14)); p.add(h, BorderLayout.NORTH); p.add(content, BorderLayout.CENTER); return p;
    }
    private JPanel stat(String label, String value, String note, String symbol) {
        JPanel p = new JPanel(new BorderLayout(10, 8)); p.setBackground(Color.WHITE); p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(231,234,239)), new EmptyBorder(17,17,17,17)));
        JPanel text = new JPanel(); text.setOpaque(false); text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        JLabel l = new JLabel(label); l.setForeground(MUTED); l.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JLabel v = new JLabel(value); v.setForeground(TEXT); v.setFont(new Font("SansSerif", Font.BOLD, 23));
        JLabel n = new JLabel(note); n.setForeground(new Color(61, 159, 105)); n.setFont(new Font("SansSerif", Font.PLAIN, 10));
        text.add(l); text.add(Box.createVerticalStrut(8)); text.add(v); text.add(Box.createVerticalStrut(5)); text.add(n);
        JLabel icon = new JLabel(symbol, SwingConstants.CENTER); icon.setOpaque(true); icon.setBackground(new Color(255, 241, 230)); icon.setForeground(ORANGE); icon.setFont(new Font("SansSerif", Font.BOLD, 21)); icon.setPreferredSize(new Dimension(45,45));
        p.add(text, BorderLayout.CENTER); p.add(icon, BorderLayout.EAST); return p;
    }
    private JPanel stats(String[][] data) {
        JPanel row = new JPanel(new GridLayout(1, data.length, 14, 0)); row.setOpaque(false);
        for (String[] d : data) row.add(stat(d[0], d[1], d[2], d[3])); return row;
    }
    private JTable table(String[] columns, Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, columns) { @Override public boolean isCellEditable(int r, int c) { return false; } };
        JTable table = new JTable(model); table.setRowHeight(34); table.setFont(new Font("SansSerif", Font.PLAIN, 12)); table.setForeground(TEXT); table.setGridColor(new Color(237,239,243));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 11)); table.getTableHeader().setForeground(MUTED); table.getTableHeader().setBackground(new Color(249,250,252)); table.getTableHeader().setPreferredSize(new Dimension(0, 36));
        table.setSelectionBackground(new Color(255, 239, 225)); table.setSelectionForeground(TEXT); return table;
    }
    private JButton action(String label) { JButton b = new JButton(label); b.setFont(new Font("SansSerif", Font.BOLD, 12)); b.setForeground(Color.WHITE); b.setBackground(ORANGE); b.setFocusPainted(false); b.setBorder(new EmptyBorder(10, 15, 10, 15)); b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); return b; }
    private void demoMessage(String feature) { JOptionPane.showMessageDialog(frame, "Tombol '" + feature + "' merupakan placeholder GUI. Logika bisnis belum diaktifkan.", "Mode Demo GUI", JOptionPane.INFORMATION_MESSAGE); }

    private JPanel dashboardPage() {
        JPanel p = page("Ringkasan operasional", "Dashboard", "Pantau performa outlet Ayam Kepak Madu hari ini.");
        JPanel body = contentColumn(); body.add(stats(new String[][]{{"Omzet Hari Ini","Rp 2.450.000","↑ 12,5% dari kemarin","↗"},{"Pesanan Masuk","86 pesanan","12 sedang diproses","▣"},{"Stok Perlu Dicek","3 bahan","Perlu perhatian","!"},{"Rata-rata Transaksi","Rp 28.488","Dari transaksi hari ini","Rp"}})); body.add(Box.createVerticalStrut(18));
        JPanel lower = new JPanel(new GridLayout(1,2,16,0)); lower.setOpaque(false);
        JPanel chart = new JPanel(new BorderLayout(0,10)); chart.setOpaque(false); chart.add(new JLabel("Tren Penjualan Mingguan"),BorderLayout.NORTH);
        JPanel bars = new JPanel(new GridLayout(1,7,12,0)); bars.setBackground(Color.WHITE); bars.setBorder(new EmptyBorder(15,20,10,20));
        int[] vals={42,63,52,79,58,92,72}; String[] days={"Sen","Sel","Rab","Kam","Jum","Sab","Min"};
        for(int i=0;i<vals.length;i++){ JPanel col=new JPanel(new BorderLayout(0,8)); col.setOpaque(false); JPanel track=new JPanel(new GridBagLayout()); track.setOpaque(false); JPanel bar=new JPanel(); bar.setBackground(i==5?ORANGE:new Color(250,190,145)); bar.setPreferredSize(new Dimension(22,vals[i]*2)); track.add(bar,new GridBagConstraints(0,0,1,1,0,1,GridBagConstraints.SOUTH,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0)); col.add(track,BorderLayout.CENTER); JLabel day=new JLabel(days[i],SwingConstants.CENTER);day.setFont(new Font("SansSerif",Font.PLAIN,10));day.setForeground(MUTED);col.add(day,BorderLayout.SOUTH); bars.add(col); }
        chart.add(bars,BorderLayout.CENTER); lower.add(card("Penjualan",chart));
        JTable orders=table(new String[]{"Pesanan","Pelanggan","Status"},new Object[][]{{"#KM-1082","Walk-in","Diproses"},{"#KM-1081","Dine-in","Siap"},{"#KM-1080","Online","Baru"},{"#KM-1079","Walk-in","Selesai"}});
        lower.add(card("Pesanan Terbaru",new JScrollPane(orders))); body.add(lower); p.add(body,BorderLayout.CENTER); return p;
    }

    private JPanel posPage() {
        JPanel p=page("Point of sales","POS & Pemesanan Kasir","Pilih menu, tentukan varian, lalu tinjau keranjang pesanan.");
        JPanel body=new JPanel(new BorderLayout(16,0));body.setOpaque(false);
        JPanel left=new JPanel(new BorderLayout(0,12));left.setOpaque(false);
        JPanel categories=new JPanel(new FlowLayout(FlowLayout.LEFT,8,0));categories.setOpaque(false);for(String c:new String[]{"Semua Menu","Paket","Ala Carte","Minuman","Add-on"}){JButton b=new JButton(c);b.setFocusPainted(false);b.setBackground(c.equals("Semua Menu")?ORANGE:Color.WHITE);b.setForeground(c.equals("Semua Menu")?Color.WHITE:TEXT);categories.add(b);}
        left.add(categories,BorderLayout.NORTH);
        JPanel menuGrid=new JPanel(new GridLayout(0,2,12,12));menuGrid.setOpaque(false);
        String[][] items={{"Paket 1 Ayam + Nasi","Rp 22.000","Paket"},{"Paket 2 Ayam + Nasi","Rp 32.000","Paket"},{"Kepak Madu Original","Rp 18.000","Ala Carte"},{"Kepak Madu Spicy","Rp 19.000","Ala Carte"},{"Nasi Putih","Rp 5.000","Add-on"},{"Es Teh Manis","Rp 6.000","Minuman"}};
        for(String[] it:items){JPanel tile=new JPanel(new BorderLayout(0,10));tile.setBackground(Color.WHITE);tile.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(231,234,239)),new EmptyBorder(16,16,16,16)));JLabel emoji=new JLabel("🍗",SwingConstants.CENTER);emoji.setFont(new Font("SansSerif",Font.PLAIN,34));emoji.setOpaque(true);emoji.setBackground(new Color(255,244,235));emoji.setPreferredSize(new Dimension(0,76));tile.add(emoji,BorderLayout.NORTH);JPanel tx=new JPanel();tx.setOpaque(false);tx.setLayout(new BoxLayout(tx,BoxLayout.Y_AXIS));JLabel name=new JLabel(it[0]);name.setFont(new Font("SansSerif",Font.BOLD,13));JLabel price=new JLabel(it[1]);price.setForeground(ORANGE);price.setFont(new Font("SansSerif",Font.BOLD,12));tx.add(name);tx.add(Box.createVerticalStrut(7));tx.add(price);tile.add(tx,BorderLayout.CENTER);JButton add=action("＋ Tambah");add.addActionListener(e->demoMessage("Tambah " + it[0]));tile.add(add,BorderLayout.SOUTH);menuGrid.add(tile);}
        left.add(new JScrollPane(menuGrid),BorderLayout.CENTER);body.add(left,BorderLayout.CENTER);
        JPanel cart=new JPanel(new BorderLayout(0,14));cart.setPreferredSize(new Dimension(280,0));cart.setBackground(Color.WHITE);cart.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(231,234,239)),new EmptyBorder(18,18,18,18)));
        JLabel ct=new JLabel("Keranjang Pesanan");ct.setFont(new Font("SansSerif",Font.BOLD,16));cart.add(ct,BorderLayout.NORTH);
        JPanel details=new JPanel();details.setOpaque(false);details.setLayout(new BoxLayout(details,BoxLayout.Y_AXIS));details.add(new JLabel("Pesanan baru"));details.add(Box.createVerticalStrut(15));details.add(new JLabel("Belum ada menu dipilih"));details.add(Box.createVerticalGlue());JSeparator sep=new JSeparator();details.add(sep);details.add(Box.createVerticalStrut(12));details.add(new JLabel("Subtotal                         Rp 0"));details.add(Box.createVerticalStrut(10));details.add(new JLabel("Pajak                                  Rp 0"));details.add(Box.createVerticalStrut(15));JLabel total=new JLabel("TOTAL                            Rp 0");total.setFont(new Font("SansSerif",Font.BOLD,15));details.add(total);cart.add(details,BorderLayout.CENTER);JButton pay=action("Lanjut Pembayaran");pay.addActionListener(e->demoMessage("Pembayaran"));cart.add(pay,BorderLayout.SOUTH);body.add(cart,BorderLayout.EAST);p.add(body,BorderLayout.CENTER);return p;
    }

    private JPanel kitchenPage() {
        JPanel p=page("Kitchen display system","Manajemen Dapur & Batch Prep","Antrean FIFO, tahapan produksi, dan pemantauan waktu marinasi."); JPanel body=contentColumn();
        body.add(stats(new String[][]{{"Pesanan Baru","8","Menunggu diproses","＋"},{"Sedang Dimasak","5","Dalam antrean dapur","♨"},{"Siap Disajikan","3","Perlu diantar","✓"},{"Batch Marinasi","4 batch","Pantau shelf life","◷"}}));body.add(Box.createVerticalStrut(18));
        JPanel board=new JPanel(new GridLayout(1,3,14,0));board.setOpaque(false);
        board.add(kitchenColumn("PESANAN BARU",new String[][]{{"#KM-1085","Paket 1 Ayam + Nasi","2 item","Baru"},{"#KM-1086","Kepak Madu Spicy","1 item","Baru"},{"#KM-1087","Paket 2 Ayam + Nasi","3 item","Baru"}}));
        board.add(kitchenColumn("SEDANG DIPROSES",new String[][]{{"#KM-1082","Kepak Original","2 item","Goreng"},{"#KM-1083","Paket 1 Ayam + Nasi","1 item","Glaze"}}));
        board.add(kitchenColumn("SIAP DISAJIKAN",new String[][]{{"#KM-1080","Kepak Madu Spicy","2 item","Siap"},{"#KM-1081","Paket 1 Ayam + Nasi","1 item","Siap"}}));body.add(board);body.add(Box.createVerticalStrut(18));
        JTable batch=table(new String[]{"Kode Batch","Bahan","Mulai Marinasi","Batas Shelf Life","Status"},new Object[][]{{"BT-021","Ayam kepak","10:15","14:15","Aman"},{"BT-022","Ayam kepak","09:30","13:30","Segera"},{"BT-023","Ayam kepak","08:10","12:10","Kritis"}});body.add(card("Pemantauan Batch Marinasi",new JScrollPane(batch)));p.add(body,BorderLayout.CENTER);return p;
    }
    private JPanel kitchenColumn(String title,String[][] rows){JPanel col=new JPanel(new BorderLayout(0,12));col.setOpaque(false);JLabel h=new JLabel(title);h.setForeground(MUTED);h.setFont(new Font("SansSerif",Font.BOLD,11));col.add(h,BorderLayout.NORTH);JPanel list=new JPanel();list.setOpaque(false);list.setLayout(new BoxLayout(list,BoxLayout.Y_AXIS));for(String[] r:rows){JPanel c=new JPanel();c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));c.setBackground(Color.WHITE);c.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(231,234,239)),new EmptyBorder(13,13,13,13)));JLabel id=new JLabel(r[0]);id.setForeground(ORANGE);id.setFont(new Font("SansSerif",Font.BOLD,11));JLabel item=new JLabel(r[1]);item.setFont(new Font("SansSerif",Font.BOLD,12));JLabel qty=new JLabel(r[2]+"   •   "+r[3]);qty.setForeground(MUTED);qty.setFont(new Font("SansSerif",Font.PLAIN,10));JButton next=action("Ubah Status");next.setAlignmentX(Component.LEFT_ALIGNMENT);next.addActionListener(e->demoMessage("Status " + r[0]));c.add(id);c.add(Box.createVerticalStrut(8));c.add(item);c.add(Box.createVerticalStrut(7));c.add(qty);c.add(Box.createVerticalStrut(12));c.add(next);list.add(c);list.add(Box.createVerticalStrut(10));}col.add(list,BorderLayout.CENTER);return col;}

    private JPanel inventoryPage() {
        JPanel p=page("Kontrol persediaan","Inventaris Bahan Baku","Pantau jumlah stok, satuan, status persediaan, dan pencatatan bahan rusak.");JPanel body=contentColumn();
        body.add(stats(new String[][]{{"Jenis Bahan","12 item","Terdaftar di inventaris","▤"},{"Stok Menipis","2 item","Perlu segera dibeli","!"},{"Stok Kritis","1 item","Prioritas pembelian","⚠"},{"Wastage Hari Ini","1,2 kg","Pencatatan demo","↘"}}));body.add(Box.createVerticalStrut(18));
        JPanel controls=new JPanel(new BorderLayout());controls.setOpaque(false);JTextField search=new JTextField();search.setPreferredSize(new Dimension(260,37));search.putClientProperty("JTextField.placeholderText","Cari nama bahan...");controls.add(search,BorderLayout.WEST);JPanel buttons=new JPanel(new FlowLayout(FlowLayout.RIGHT,8,0));buttons.setOpaque(false);JButton opname=action("＋ Stock Opname");JButton wastage=action("＋ Catat Bahan Rusak");opname.addActionListener(e->demoMessage("Stock Opname"));wastage.addActionListener(e->demoMessage("Catat Bahan Rusak"));buttons.add(opname);buttons.add(wastage);controls.add(buttons,BorderLayout.EAST);body.add(controls);body.add(Box.createVerticalStrut(12));
        JTable t=table(new String[]{"Kode","Nama Bahan","Kategori","Stok Saat Ini","Stok Minimum","Status"},new Object[][]{{"BB-001","Ayam Kepak Segar","Bahan utama","18 kg","10 kg","AMAN"},{"BB-002","Madu Murni","Saus / glaze","3,5 liter","2 liter","AMAN"},{"BB-003","Minyak Goreng","Bahan pendukung","4 liter","5 liter","MENIPIS"},{"BB-004","Tepung Bumbu","Bahan pendukung","1,2 kg","3 kg","KRITIS"},{"BB-005","Wijen","Topping","2 kg","1 kg","AMAN"},{"BB-006","Saus Pedas","Saus / glaze","2,5 liter","2 liter","AMAN"}});JScrollPane sc=new JScrollPane(t);sc.setBorder(BorderFactory.createLineBorder(new Color(231,234,239)));body.add(card("Daftar Stok Bahan Baku",sc));p.add(body,BorderLayout.CENTER);search.addActionListener(e->{String q=search.getText().toLowerCase();for(int i=0;i<t.getRowCount();i++){if(t.getValueAt(i,1).toString().toLowerCase().contains(q)){t.setRowSelectionInterval(i,i);t.scrollRectToVisible(t.getCellRect(i,0,true));return;}}});return p;
    }

    private JPanel financePage() {
        JPanel p=page("Analitik & laporan","Keuangan & Laporan Penjualan","Ringkasan omzet, estimasi HPP, performa produk, dan laporan penjualan.");JPanel body=contentColumn();
        body.add(stats(new String[][]{{"Omzet Bulan Ini","Rp 48,2 jt","↑ 8,4% dari bulan lalu","↗"},{"Estimasi HPP","Rp 19,4 jt","40,2% dari omzet","Rp"},{"Laba Kotor","Rp 28,8 jt","Sebelum biaya operasional","＋"},{"Transaksi","1.284","Bulan berjalan","▣"}}));body.add(Box.createVerticalStrut(18));
        JPanel charts=new JPanel(new GridLayout(1,2,14,0));charts.setOpaque(false);
        JPanel trend=new JPanel(new BorderLayout(0,10));trend.setOpaque(false);trend.add(new JLabel("Tren Omzet Bulanan"),BorderLayout.NORTH);JPanel bars=new JPanel(new GridLayout(1,6,12,0));bars.setBackground(Color.WHITE);bars.setBorder(new EmptyBorder(20,20,12,20));int[] v={35,48,43,67,58,86};String[] m={"Mei","Jun","Jul","Agu","Sep","Okt"};for(int i=0;i<v.length;i++){JPanel c=new JPanel(new BorderLayout(0,8));c.setOpaque(false);JPanel track=new JPanel(new GridBagLayout());track.setOpaque(false);JPanel bar=new JPanel();bar.setBackground(i==5?ORANGE:new Color(250,190,145));bar.setPreferredSize(new Dimension(25,v[i]*2));track.add(bar,new GridBagConstraints(0,0,1,1,0,1,GridBagConstraints.SOUTH,GridBagConstraints.NONE,new Insets(0,0,0,0),0,0));c.add(track,BorderLayout.CENTER);JLabel label=new JLabel(m[i],SwingConstants.CENTER);label.setForeground(MUTED);label.setFont(new Font("SansSerif",Font.PLAIN,10));c.add(label,BorderLayout.SOUTH);bars.add(c);}trend.add(bars,BorderLayout.CENTER);charts.add(card("Grafik Penjualan",trend));
        JTable products=table(new String[]{"Produk","Terjual","Kontribusi"},new Object[][]{{"Kepak Madu Original","428","33,3%"},{"Paket 1 Ayam + Nasi","362","28,2%"},{"Kepak Madu Spicy","274","21,3%"},{"Paket 2 Ayam + Nasi","220","17,1%"}});charts.add(card("Produk Terlaris",new JScrollPane(products)));body.add(charts);body.add(Box.createVerticalStrut(18));
        JPanel report=new JPanel(new BorderLayout(12,0));report.setOpaque(false);JPanel labels=new JPanel();labels.setOpaque(false);labels.setLayout(new BoxLayout(labels,BoxLayout.Y_AXIS));JLabel title=new JLabel("Laporan Penjualan");title.setFont(new Font("SansSerif",Font.BOLD,14));JLabel desc=new JLabel("Pilih format untuk menyiapkan laporan laba rugi.");desc.setForeground(MUTED);desc.setFont(new Font("SansSerif",Font.PLAIN,12));labels.add(title);labels.add(Box.createVerticalStrut(5));labels.add(desc);report.add(labels,BorderLayout.WEST);JPanel acts=new JPanel(new FlowLayout(FlowLayout.RIGHT,8,0));acts.setOpaque(false);JButton excel=action("↓ Ekspor Excel");JButton pdf=action("↓ Ekspor PDF");excel.addActionListener(e->demoMessage("Ekspor Excel"));pdf.addActionListener(e->demoMessage("Ekspor PDF"));acts.add(excel);acts.add(pdf);report.add(acts,BorderLayout.EAST);body.add(card("Unduh Laporan",report));p.add(body,BorderLayout.CENTER);return p;
    }
}
