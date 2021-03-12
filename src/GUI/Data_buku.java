/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import method.databuku.DataBuku;
import method.databuku.BukuControl;
import javax.swing.JOptionPane;

/**
 *
 * @author rudi
 */
public class Data_buku extends javax.swing.JFrame {
int x = 0;
private DefaultTableModel model;
    BukuControl bc = new BukuControl();
    DataBuku DB = new DataBuku();
    List<DataBuku> ListBuku = new ArrayList<DataBuku>();
    /**
     * Creates new form Data_buku
     */
    public Data_buku() throws SQLException {
        initComponents();
        buatTable();
        showTable();
        bersih();
        siapIsi(false);
        tombolNormal();
        tampilx();
    }

  private void buatTable(){
        model = new DefaultTableModel();
        model.addColumn("Kode Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Jenis Buku");
        model.addColumn("Penerbit");
        model.addColumn("Tahun Terbit");
        model.addColumn("Stok");
        model.addColumn("Harga Jual");
        model.addColumn("Harga Reseller");
        Tabel1.setModel(model);
        
    }
    
    private void showTable(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        ListBuku.clear();
        ListBuku=bc.tampil();
        for (x=0; x<ListBuku.size(); x++){
            Object[] data=new Object[8];
            data[0]=ListBuku.get(x).getKodebuku();
            data[1]=ListBuku.get(x).getNmbuku();
            data[2]=ListBuku.get(x).getjenisbuku();
            data[3]=ListBuku.get(x).getpenerbit();
            data[4]=ListBuku.get(x).gettahunterbit();
            data[5]=ListBuku.get(x).getJmlstok();
            data[6]=ListBuku.get(x).getHrgjual();
            data[7]=ListBuku.get(x).getHrgreseller();
            model.addRow(data);
        
     }
    }
    
    private void buatKdBarang(){
        ListBuku=bc.tampil();
        int a=ListBuku.size()-1;
        int no=Integer.parseInt(ListBuku.get(a).getKodebuku().replace("A-",""))+1;
        kdbuku.setText("A-"+no);
        kdbuku.setEnabled(false);
    }
    
    private void siapIsi(boolean a){
        kdbuku.setEnabled(a);
        nmbuku.setEnabled(a);
        cmbjenis.setEnabled(a);
        penerbit.setEnabled(a);
        thnterbit.setEnabled(a);
        tstok.setEnabled(a);
        hrgjual.setEnabled(a);
        hrgreseller.setEnabled(a);
    }

    private void bersih(){
        kdbuku.setText("");
        nmbuku.setText("");
        cmbjenis.setSelectedItem("Pilih");
        penerbit.setText("");
        thnterbit.setText("");
        tstok.setText("");
        hrgjual.setText("");
        hrgreseller.setText("");
    }
    
    private void tombolNormal(){
        btnsimpan.setEnabled(false);
        btnedit.setEnabled(false);
        btnhapus.setEnabled(false);
        
    }
    
    private void tombolKembali(){
        btnsimpan.setEnabled(true);
        btnhapus.setEnabled(true);
    }
    
    public void tampilx()throws SQLException{
        int jmltiket=bc.getJumlahbuku();
        txtjml.setText(String.valueOf(jmltiket));
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        thnterbit = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbjenis = new javax.swing.JComboBox<>();
        penerbit = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nmbuku = new javax.swing.JTextField();
        hrgreseller = new javax.swing.JTextField();
        kdbuku = new javax.swing.JTextField();
        hrgjual = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tstok = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabel1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtjml = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(57, 100, 142));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 740, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, 820, 80));

        jPanel3.setBackground(new java.awt.Color(57, 100, 142));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, -1, 320));

        jPanel4.setBackground(new java.awt.Color(226, 211, 196));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconLogin/icons8-back-64 (1).png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 60, -1));

        jLabel2.setFont(new java.awt.Font("Vijaya", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(57, 100, 142));
        jLabel2.setText("Data Buku");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconDataBuku/icons8-book-shelf-64 (1).png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 70, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 80));

        jPanel5.setBackground(new java.awt.Color(226, 211, 196));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(226, 211, 196));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        thnterbit.setBackground(new java.awt.Color(226, 211, 196));
        thnterbit.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        thnterbit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(thnterbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 240, -1));

        jLabel9.setBackground(new java.awt.Color(48, 99, 118));
        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(48, 99, 118));
        jLabel9.setText("Harga Reseller");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 40));

        jLabel14.setBackground(new java.awt.Color(48, 99, 118));
        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(48, 99, 118));
        jLabel14.setText("Jenis Buku");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel7.setBackground(new java.awt.Color(48, 99, 118));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(48, 99, 118));
        jLabel7.setText("Tahun Terbit");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        jLabel4.setBackground(new java.awt.Color(48, 99, 118));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(48, 99, 118));
        jLabel4.setText("Kode Buku");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        cmbjenis.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih", "Novel", "Biografi", "Buku Religi", "Komik", "Buku Pengetahuan" }));
        cmbjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbjenisActionPerformed(evt);
            }
        });
        jPanel6.add(cmbjenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 182, -1));

        penerbit.setBackground(new java.awt.Color(226, 211, 196));
        penerbit.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        penerbit.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(penerbit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 240, -1));

        jLabel5.setBackground(new java.awt.Color(48, 99, 118));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(48, 99, 118));
        jLabel5.setText("Nama Buku");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel15.setBackground(new java.awt.Color(48, 99, 118));
        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(48, 99, 118));
        jLabel15.setText("Penerbit");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel8.setBackground(new java.awt.Color(48, 99, 118));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(48, 99, 118));
        jLabel8.setText("Harga Jual");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        nmbuku.setBackground(new java.awt.Color(226, 211, 196));
        nmbuku.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        nmbuku.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(nmbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 240, -1));

        hrgreseller.setBackground(new java.awt.Color(226, 211, 196));
        hrgreseller.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        hrgreseller.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(hrgreseller, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 240, -1));

        kdbuku.setBackground(new java.awt.Color(226, 211, 196));
        kdbuku.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        kdbuku.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(kdbuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 240, -1));

        hrgjual.setBackground(new java.awt.Color(226, 211, 196));
        hrgjual.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        hrgjual.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(hrgjual, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 240, -1));

        jLabel6.setBackground(new java.awt.Color(48, 99, 118));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(48, 99, 118));
        jLabel6.setText("Jumlah Stok Buku");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        tstok.setBackground(new java.awt.Color(226, 211, 196));
        tstok.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tstok.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(tstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 240, -1));

        jPanel11.setBackground(new java.awt.Color(226, 211, 196));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnsimpan.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconLogin/icons8-save-24 (1).png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });
        jPanel11.add(btnsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btntambah.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btntambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconDataBuku/icons8-add-24 (1).png"))); // NOI18N
        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        jPanel11.add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        btnedit.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconDataBuku/icons8-edit-24 (1).png"))); // NOI18N
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });
        jPanel11.add(btnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        btnhapus.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconLogin/icons8-delete-bin-24 (1).png"))); // NOI18N
        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });
        jPanel11.add(btnhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, -1, -1));

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 420, 50));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 440, 390));

        jPanel9.setBackground(new java.awt.Color(226, 211, 196));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabel1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabel1);

        jPanel9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 680, 230));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(48, 99, 118));
        jLabel10.setText("Cari Berdasarkan");
        jPanel9.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, -1, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(48, 99, 118));
        jLabel11.setText("Nama Buku");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        tcari.setBackground(new java.awt.Color(226, 211, 196));
        tcari.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tcariKeyTyped(evt);
            }
        });
        jPanel9.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 320, 260, 24));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(48, 99, 118));
        jLabel12.setText("Jumlah Buku");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        txtjml.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        txtjml.setForeground(new java.awt.Color(245, 160, 75));
        txtjml.setText("100");
        jPanel9.add(txtjml, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 90, 80));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(48, 99, 118));
        jLabel13.setText("Jenis Buku");
        jPanel9.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 710, 390));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1180, 430));

        jPanel7.setBackground(new java.awt.Color(57, 100, 142));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 20, 440));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 80, 100, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cmbjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbjenisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbjenisActionPerformed

    private void Tabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel1MouseClicked
        int baris = Tabel1.getSelectedRow();

        kdbuku.setText(Tabel1.getModel().getValueAt(baris, 0).toString());
        nmbuku.setText(Tabel1.getModel().getValueAt(baris, 1).toString());
        cmbjenis.setSelectedItem(Tabel1.getModel().getValueAt(baris, 2).toString());
        penerbit.setText(Tabel1.getModel().getValueAt(baris, 3).toString());
        thnterbit.setText(Tabel1.getModel().getValueAt(baris, 4).toString());
        tstok.setText(Tabel1.getModel().getValueAt(baris, 5).toString());
        hrgjual.setText(Tabel1.getModel().getValueAt(baris, 6).toString());
        hrgreseller.setText(Tabel1.getModel().getValueAt(baris, 7).toString());
        siapIsi(false);
        btnhapus.setEnabled(true);
        btnedit.setEnabled(true);
    }//GEN-LAST:event_Tabel1MouseClicked

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
        String cari=null;
        cari = tcari.getText();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();;
        ListBuku.clear();
        ListBuku=bc.cariBuku(cari);
        for(x=0; x<ListBuku.size(); x++){
            Object[] data=new Object[8];
            data[0]=ListBuku.get(x).getKodebuku();
            data[1]=ListBuku.get(x).getNmbuku();
            data[2]=ListBuku.get(x).getjenisbuku();
            data[3]=ListBuku.get(x).getpenerbit();
            data[4]=ListBuku.get(x).gettahunterbit();
            data[5]=ListBuku.get(x).getJmlstok();
            data[6]=ListBuku.get(x).getHrgjual();
            data[7]=ListBuku.get(x).getHrgreseller();
            model.addRow(data);

        }
    }//GEN-LAST:event_tcariKeyReleased

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
    if (nmbuku.getText().isEmpty() || cmbjenis.getSelectedItem().equals("") ||  penerbit.getText().isEmpty() ||  thnterbit.getText().isEmpty() ||
            tstok.getText().isEmpty() || hrgjual.getText().isEmpty() || hrgreseller.getText().isEmpty()){
            javax.swing.JOptionPane.showMessageDialog(null,"Data Harus Lengkap","Aplikasi Buku", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }else{
            DB.setKodebuku(kdbuku.getText());
            DB.setNmbuku(nmbuku.getText());
            DB.setjenisbuku(cmbjenis.getSelectedItem().toString());
             DB.setpenerbit(penerbit.getText());
            DB.settahunterbit(thnterbit.getText());
            DB.setJmlstok(Integer.parseInt(tstok.getText()));
            DB.setHrgjual(Integer.parseInt(hrgjual.getText()));
            DB.setHrgreseller(Integer.parseInt(hrgreseller.getText()));
            if(btntambah.getText().equalsIgnoreCase("Batal")){
                if(bc.tambahbuku(DB)==1){
                    javax.swing.JOptionPane.showMessageDialog(null,"Data telah disimpan","Aplikasi Buku", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    buatTable();
                    showTable();
                    try {
                        tampilx();
                    } catch (SQLException ex) {
                        Logger.getLogger(Data_buku.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }else{
                    javax.swing.JOptionPane.showMessageDialog(null,"Data gagal disimpan","Aplikasi Buku", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(btnedit.getText().equalsIgnoreCase("Batal")){
                if(bc.editBuku(DB)==1){
                    javax.swing.JOptionPane.showMessageDialog(null,"Data telah dirubah","Aplikasi Buku", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    buatTable();
                    showTable();

                }else{
                    javax.swing.JOptionPane.showMessageDialog(null,"Data gagal dirubah","Aplikasi Buku", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
            }
            bersih();
            siapIsi(false);
            btntambah.setText("Tambah");
            btnedit.setText("Edit");
            tombolNormal();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
     if (btntambah.getText().equalsIgnoreCase("Tambah")){
            btntambah.setText("Batal");
            bersih();
            siapIsi(true);
            buatKdBarang();
            nmbuku.requestFocus();
            tombolKembali();
        }else{
            btntambah.setText("Tambah");
            bersih();
            siapIsi(false);
            tombolNormal();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btntambahActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
    if (btnedit.getText().equalsIgnoreCase("Edit")){
            btnedit.setText("Batal");
            siapIsi(true);
            kdbuku.setEditable(false);
            nmbuku.requestFocus();
            btntambah.setEnabled(false);
            btnsimpan.setEnabled(true);
            btnhapus.setEnabled(false);
            btnedit.setEnabled(true);
        }else{
            btnedit.setText("Edit");
            bersih();
            siapIsi(false);
            tombolNormal();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btneditActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
    DB.setKodebuku(kdbuku.getText());
        int pesan = JOptionPane.showConfirmDialog(null, "Data akan dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
         if(pesan==JOptionPane.YES_NO_OPTION){
        if(bc.deleteBuku(DB)==1){
            JOptionPane.showMessageDialog(null,"Data telah dihapus");
            bersih();
            siapIsi(false);
            tombolNormal();
            buatTable();
            showTable();
            try {
                tampilx();
            } catch (SQLException ex) {
                Logger.getLogger(Data_buku.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Data gagal dihapus");
        }
         }        // TODO add your handling code here:
    }//GEN-LAST:event_btnhapusActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
                  // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void tcariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Data_buku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Data_buku().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Data_buku.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel1;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JComboBox<String> cmbjenis;
    private javax.swing.JTextField hrgjual;
    private javax.swing.JTextField hrgreseller;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kdbuku;
    private javax.swing.JTextField nmbuku;
    private javax.swing.JTextField penerbit;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField thnterbit;
    private javax.swing.JTextField tstok;
    private javax.swing.JLabel txtjml;
    // End of variables declaration//GEN-END:variables
}
