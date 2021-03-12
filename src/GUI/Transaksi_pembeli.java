/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import method.databuku.DataBuku;
import method.databuku.BukuControl;
import method.penjualan.DataPenjualan;
import method.penjualan.PenjualanControl;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import method.report.reportcontrol;
import method.databeli.BeliControl;
import method.databeli.DataBeli;
import method.transaksireseller.DataTReseller;
/**
 *
 * @author rudi
 */
public class Transaksi_pembeli extends javax.swing.JFrame {
 private Connection koneksi;
    private Statement kode;
    int x=0;
    private DefaultTableModel model;
    
    PenjualanControl pc = new PenjualanControl();
    DataPenjualan dp = new DataPenjualan();
    List<DataPenjualan> listDP = new ArrayList<DataPenjualan>();
    
    BukuControl tc = new BukuControl();
    DataBuku DT = new DataBuku();
    List<DataBuku> ListBuku = new ArrayList<DataBuku>();
    
     BeliControl bc = new BeliControl();
    DataBeli db = new DataBeli();
    List<DataBeli> listdb = new ArrayList<DataBeli>();
    

    public class Transaksi2 {
    
    int jml,stok,total, totalbayar, harga,tunai,kembali, sisaStok, stokKurang, hargaKurang;
 
    int sisaStok(){
        sisaStok=stok-jml;
        return sisaStok;
    }
     int total(){
       total = harga*jml;  
        return total;
     }
     
     int totalbayar(){
         totalbayar= total+(harga*jml);
         return totalbayar;
     }
     
     int kembali(){
    
         kembali=tunai-total;
         return kembali;
     }
     
     int stokKurang(){
         stokKurang=(stok-jml)+jml;
         return stokKurang;
     }
     
     int hargaKurang(){
         hargaKurang=(total-(harga*jml));
         return hargaKurang;
     }
    
     String kembalian;
    
    String ket(){
    if(kembali<0){
        kembalian="mohon maaf, uang anda kurang";
    } 
    return kembalian;
}
    int ketHarga(){
        if(hargaKurang<0){
            total=0; 
        }
        return total;
    }
     
}
   
    Transaksi2 tr= new Transaksi2();
    
    /**
     * Creates new form Transaksi_pembeli
     */
    public Transaksi_pembeli() {
        initComponents();
        tstok.setVisible(false);
        tombolHiden();
        hiden();
        sembunyi();
        buatTablebeli();
    }

        private void tampiltanggal(){
        DateFormat tgl=new SimpleDateFormat("dd/MMMM/yyyy");
        String htgl=tgl.format(Calendar.getInstance().getTime());
        tgltransaksi.setText(htgl);
    }
    
    private void bersih(){
        
        kodebuku.setText(null);
        namapembeli.setText(null);
        namabuku.setText(null);
        hargajual.setText(null);
        jumlahbeli.setText(null);
        txttotal.setText("0");
        txttunai.setText("0");
        txtkembali.setText("0");
        
    }
    
    public void hiden(){
        nonota.setEnabled(false);
        kodebuku.setEnabled(false);
        namabuku.setEnabled(false);
        hargajual.setEnabled(false);
       
    }
    
    public void sembunyi(){
         namapembeli.setEnabled(false);
        jumlahbeli.setEnabled(false);
        tgltransaksi.setEnabled(false);
    }
    
    public void normal(){
        nonota.setEnabled(false);
        kodebuku.setEnabled(false);
        namabuku.setEnabled(true);
        hargajual.setEnabled(true);
        jumlahbeli.setEnabled(true);
        txttotal.setEnabled(true);
        txttunai.setEnabled(true);
        txtkembali.setEnabled(true);
        
    }
    
    public void tombolHiden(){
        btntambah.setEnabled(false);
        btnkurang.setEnabled(false);
        btnpilih.setEnabled(false);
        btncetak.setEnabled(false);
    }
    
    public void tombolNormal(){
         btntambah.setEnabled(true);
         btnkurang.setEnabled(true);
        btnpilih.setEnabled(true);
        btncetak.setEnabled(true);
    }
    
    private void buatNoNota(){
         listdb=bc.tampil();
         if(listdb.isEmpty()){
             nonota.setText("N-1");
         } else {
             int a=listdb.size()-1;
        int no=Integer.parseInt(listdb.get(a).getNonota().replace("N-", ""))+1;
        nonota.setText("N-"+no);
        nonota.setEnabled(false);
         }
        
    
}
    
    public void siapIsi(boolean a){
        tgltransaksi.setEnabled(a);
        tstok.setEnabled(a);
        nonota.setEnabled(a);
        namapembeli.setEnabled(a);
        kodebuku.setEnabled(a);
        namabuku.setEnabled(a);
        hargajual.setEnabled(a);
        jumlahbeli.setEnabled(a);
    }
    
    private void buatTablebeli(){
        model=new DefaultTableModel();
        model.addColumn("Kode Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total");
        jTable1.setModel(model);
        
    }
    
    private void tampilBeli(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        listDP.clear();
        listDP=pc.cariBuku(nonota.getText());
        for(int x=0; x<listDP.size(); x++){
            Object[] data=new Object[5];
            data[0]=listDP.get(x).getKdbuku();
            data[1]=listDP.get(x).getNmbuku();
            data[2]=listDP.get(x).getHrgjual();
            data[3]=listDP.get(x).getJmlbeli();
            data[4]=listDP.get(x).getTtlbayar();
            model.addRow(data);
        }
    }
        
            private void buatTableBuku(){
        model=new DefaultTableModel();
        model.addColumn("Kode Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Jenis Buku");
        model.addColumn("Jumlah Stok");
        model.addColumn("Harga Jual");
        jTable2.setModel(model);
        
    }
    
    private void tampilbuku(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        ListBuku.clear();
        ListBuku=tc.tampil();
            for(int x=0; x<ListBuku.size(); x++){
            Object[] data=new Object[5];
            data[0]=ListBuku.get(x).getKodebuku();
            data[1]=ListBuku.get(x).getNmbuku();
            data[2]=ListBuku.get(x).getjenisbuku();
            data[3]=ListBuku.get(x).getJmlstok();
            data[4]=ListBuku.get(x).getHrgjual();
           
      
            model.addRow(data);
        }
    }
    
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog2 = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        caritiket = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtkembali = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        txttunai = new javax.swing.JTextField();
        btntambah = new javax.swing.JButton();
        btnkurang = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tstok = new javax.swing.JTextField();
        namapembeli = new javax.swing.JTextField();
        tgltransaksi = new javax.swing.JTextField();
        kodebuku = new javax.swing.JTextField();
        namabuku = new javax.swing.JTextField();
        hargajual = new javax.swing.JTextField();
        jumlahbeli = new javax.swing.JTextField();
        btnpilih = new javax.swing.JButton();
        tambahtransaksi = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        nonota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jDialog2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(57, 100, 142));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("DATA BUKU");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(177, 177, 177))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(29, 29, 29))
        );

        jDialog2.getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 90));

        jPanel15.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jDialog2.getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 510, 10));

        jPanel16.setBackground(new java.awt.Color(226, 211, 196));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Cari Data Buku :");

        caritiket.setBackground(new java.awt.Color(226, 211, 196));
        caritiket.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        caritiket.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        caritiket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                caritiketKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(33, 33, 33)
                        .addComponent(caritiket, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(caritiket, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jDialog2.getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 510, 260));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(226, 211, 196));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(57, 100, 142));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 290, 20));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconNew/icons8-back-64 (2).png"))); // NOI18N
        jLabel15.setText("jLabel15");
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 80));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 90));

        jPanel4.setBackground(new java.awt.Color(57, 100, 142));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, 550));

        jPanel5.setBackground(new java.awt.Color(57, 100, 142));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 560));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 110, 500));

        jPanel6.setBackground(new java.awt.Color(57, 100, 142));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 30));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 500, 980, 100));

        jPanel9.setBackground(new java.awt.Color(57, 100, 142));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 30, 550));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 100, 520));

        jPanel12.setBackground(new java.awt.Color(226, 211, 196));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(57, 100, 142));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Total");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tunai");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Kembali");

        txtkembali.setBackground(new java.awt.Color(226, 211, 196));
        txtkembali.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtkembali.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txttotal.setBackground(new java.awt.Color(226, 211, 196));
        txttotal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txttunai.setBackground(new java.awt.Color(226, 211, 196));
        txttunai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttunai.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttunai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttunaiActionPerformed(evt);
            }
        });

        btntambah.setText("+");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btnkurang.setText("-");
        btnkurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkurangActionPerformed(evt);
            }
        });

        jButton3.setText("Bayar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(82, 82, 82)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(txttunai, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3))
                                    .addComponent(txtkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btntambah)
                        .addGap(18, 18, 18)
                        .addComponent(btnkurang)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btntambah)
                    .addComponent(btnkurang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txttotal))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttunai)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))))
        );

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 480, 340));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(48, 99, 118));
        jLabel5.setText("No Transaksi");
        jPanel12.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 23, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(48, 99, 118));
        jLabel6.setText("Nama Pembeli");
        jPanel12.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(48, 99, 118));
        jLabel7.setText("Kode Buku");
        jPanel12.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(48, 99, 118));
        jLabel8.setText("Nama Buku");
        jPanel12.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(48, 99, 118));
        jLabel9.setText("Harga Jual");
        jPanel12.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 181, -1, -1));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(48, 99, 118));
        jLabel10.setText("Jumlah Beli");
        jPanel12.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(48, 99, 118));
        jLabel11.setText("Tanggal Transaksi");
        jPanel12.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        tstok.setBackground(new java.awt.Color(226, 211, 196));
        tstok.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tstok.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.add(tstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 70, -1));

        namapembeli.setBackground(new java.awt.Color(226, 211, 196));
        namapembeli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        namapembeli.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.add(namapembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 190, 22));

        tgltransaksi.setBackground(new java.awt.Color(226, 211, 196));
        tgltransaksi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tgltransaksi.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.add(tgltransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 190, 22));

        kodebuku.setBackground(new java.awt.Color(226, 211, 196));
        kodebuku.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        kodebuku.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.add(kodebuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 120, 20));

        namabuku.setBackground(new java.awt.Color(226, 211, 196));
        namabuku.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        namabuku.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        namabuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabukuActionPerformed(evt);
            }
        });
        jPanel12.add(namabuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 190, 22));

        hargajual.setBackground(new java.awt.Color(226, 211, 196));
        hargajual.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        hargajual.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.add(hargajual, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 190, 22));

        jumlahbeli.setBackground(new java.awt.Color(226, 211, 196));
        jumlahbeli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jumlahbeli.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.add(jumlahbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 190, 22));

        btnpilih.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnpilih.setText("Pilih");
        btnpilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpilihActionPerformed(evt);
            }
        });
        jPanel12.add(btnpilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 60, 20));

        tambahtransaksi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tambahtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconDataBuku/icons8-add-24 (1).png"))); // NOI18N
        tambahtransaksi.setText("Tambah");
        tambahtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahtransaksiActionPerformed(evt);
            }
        });
        jPanel12.add(tambahtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, -1, -1));

        btncetak.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconNew/icons8-print-24 (1).png"))); // NOI18N
        btncetak.setText("Cetak Faktur");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        jPanel12.add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, -1));

        nonota.setBackground(new java.awt.Color(226, 211, 196));
        nonota.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        nonota.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.add(nonota, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 70, -1));

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, -1, -1));

        jLabel1.setBackground(new java.awt.Color(57, 100, 142));
        jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(57, 100, 142));
        jLabel1.setText("TRANSAKSI PEMBELI");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 540, 90));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconNew/icons8-add-shopping-cart-64.png"))); // NOI18N
        jLabel14.setText("jLabel14");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 70, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 600));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int baris=jTable1.getSelectedRow();
        kodebuku.setText(jTable1.getModel().getValueAt(baris, 0).toString());
        namabuku.setText(jTable1.getModel().getValueAt(baris, 1).toString());
        hargajual.setText(jTable1.getModel().getValueAt(baris, 2).toString());
        jumlahbeli.setText(jTable1.getModel().getValueAt(baris, 3).toString());
        btnkurang.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void txttunaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttunaiActionPerformed
        int jml=Integer.parseInt(jumlahbeli.getText());
        int harga=Integer.parseInt(hargajual.getText());

        DataPenjualan dp=new DataPenjualan();
        dp.setHrgjual(harga);
        dp.setJmlbeli(jml);
        dp.setTtlbayar(harga*jml);
        int total=Integer.parseInt(txttotal.getText());
        int tunai=Integer.parseInt(txttunai.getText()) ;
        int kembali=tunai-total;

        txtkembali.setText(Integer.toString(kembali));
    }//GEN-LAST:event_txttunaiActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        if(kodebuku.getText().equals("")|| nonota.getText().equals("")|| namabuku.getText().equals("")|| namapembeli.getText().equals("")|| hargajual.getText().equals("")||
            jumlahbeli.getText().equals("")||txttotal.getText().equals("")||txttunai.getText().equals("")||txtkembali.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Data harus lengkap");
        } else {
            tr.jml=Integer.parseInt(jumlahbeli.getText());
            tr.stok=Integer.parseInt(tstok.getText());

            if(tr.jml>tr.stok){
                JOptionPane.showMessageDialog(null, "Stok Buku tidak cukup");
            } else {
                listDP=pc.CekBuku(nonota.getText(), kodebuku.getText());
                if(listDP.size()>0){
                    JOptionPane.showMessageDialog(null, "Buku ini sudah anda beli, silahkan pilih yang lain");
                } else{

                    tr.harga=Integer.parseInt(hargajual.getText());
                    DataPenjualan dp=new DataPenjualan();
                    dp.setNonota(nonota.getText());
                    dp.setKdbuku(kodebuku.getText());
                    dp.setNmbuku(namabuku.getText());
                    dp.setHrgjual(tr.harga);
                    dp.setJmlbeli(tr.jml);
                    dp.setTtlbayar(tr.total());
                    if(pc.tambahPenjualan(dp)==0){
                        JOptionPane.showMessageDialog(null, "Data gagal disimpan,mohon periksa kembali");
                    } else{
                        
                        tc.updateStok(tr.sisaStok(), kodebuku.getText());
                        buatTablebeli();
                        tampilBeli();
                        tr.total=Integer.parseInt(txttotal.getText());
                        
                        txttotal.setText(Integer.toString(tr.totalbayar()));

                        JOptionPane.showMessageDialog(null, "Data Telah ditambahkan");

                        btncetak.setEnabled(true);
                        
                        kodebuku.setText(null);
                        namabuku.setText("");
                        hargajual.setText(null);
                        jumlahbeli.setText(null);
                        nonota.setEnabled(false);
                        txttunai.setText("0");
                        txtkembali.setText("0");
                    }
                }

            }
        }
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnkurangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkurangActionPerformed
        dp.setKdbuku(kodebuku.getText());
        dp.setNonota(nonota.getText());
        int pesan = JOptionPane.showConfirmDialog(null, "Data akan dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pesan==JOptionPane.YES_NO_OPTION){
            tr.jml=Integer.parseInt(jumlahbeli.getText());
            tr.stok=Integer.parseInt(tstok.getText());
            
            if(pc.deletePenjualan(dp)== 1){
                DataTReseller dt=new DataTReseller();
                tr.harga=Integer.parseInt(hargajual.getText());
                    dt.setNonota(nonota.getText());
                    dt.setKdbuku(kodebuku.getText());
                    dt.setNmbuku(namabuku.getText());
                    dt.setHrgreseller(tr.harga);
                    dt.setJmlbeli(tr.jml);
                    dt.setTtlbayar(tr.total());
                    
                    tr.total=Integer.parseInt(txttotal.getText());
                    
                        if(tr.hargaKurang()<0){
                            txttotal.setText(Integer.toString(tr.ketHarga()));
                        } else{
                            txttotal.setText(Integer.toString(tr.hargaKurang()));
                        }
                        
                    tc.updateStok(tr.stokKurang(), kodebuku.getText());    
                
                    JOptionPane.showMessageDialog(null,"Data telah dihapus");
                    
                kodebuku.setText(null);
                namabuku.setText(null);
                hargajual.setText(null);
                jumlahbeli.setText(null);
                tgltransaksi.setText(null);
                tgltransaksi.setEnabled(false);
                tstok.setEnabled(false);
                nonota.setEnabled(false);
                namapembeli.setEnabled(false);
                kodebuku.setEnabled(false);
                namabuku.setEnabled(false);
                hargajual.setEnabled(false);
                
                buatTablebeli();
                tampilBeli();

                try {
                    tampilBeli();
                } catch (Exception e) {
                    Logger.getLogger(Transaksi_pembeli.class.getName()).log(Level.SEVERE, null, e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Data gagal dihapus");
            }
        }

    }//GEN-LAST:event_btnkurangActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tr.total=Integer.parseInt(txttotal.getText());
        tr.tunai=Integer.parseInt(txttunai.getText()) ;
        
        if(tr.kembali()<0){
            txtkembali.setText(""+tr.ket());
        } else

        txtkembali.setText(""+tr.kembali());
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void namabukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namabukuActionPerformed

    private void btnpilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpilihActionPerformed
        buatTableBuku();
        tampilbuku();
        caritiket.setText(null);
        jDialog2.setVisible(true);

        jDialog2.setBounds(300, 300, 520, 350);

        caritiket.requestFocus();

    }//GEN-LAST:event_btnpilihActionPerformed

    private void tambahtransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahtransaksiActionPerformed
        if(tambahtransaksi.getText().equalsIgnoreCase("Tambah")){
            tambahtransaksi.setText("Batal");
            siapIsi(true);
            bersih();
            hiden();
            buatNoNota();
            tampiltanggal();
            namapembeli.requestFocus();
            btncetak.setEnabled(true);
            btntambah.setEnabled(true);
            btncetak.setEnabled(false);

            btnpilih.setEnabled(true);
            tgltransaksi.setEnabled(false);
        } else{
            bersih();
            siapIsi(false);
            tambahtransaksi.setText("Tambah");
            tombolNormal();
            bersih();
            txttotal.setText(null);
            txttunai.setText(null);
            txtkembali.setText(null);
            nonota.setText(null);
            tgltransaksi.setText(null);
        }
    }//GEN-LAST:event_tambahtransaksiActionPerformed

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        int baris=jTable1.getRowCount();
        if( nonota.getText().equals("")|| namapembeli.getText().equals("")|| txttotal.getText().equals("")||txttunai.getText().equals("")||txtkembali.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Lengkapi inputan pembelian");
        } else {
            DateFormat tgl=new SimpleDateFormat("yyyy/MM/dd");
            
            String htgl=tgl.format(Calendar.getInstance().getTime());

            DataBeli DB=new DataBeli();
            DB.setNonota(nonota.getText());
            DB.setTglbeli(htgl);
            DB.setnamapembeli(namapembeli.getText());
            DB.setbayar(Integer.parseInt(txttunai.getText()));
            DB.settotal(Integer.parseInt(txttotal.getText()));
            DB.setkembali(Integer.parseInt(txtkembali.getText()));
            
            if(bc.tambah(DB)==1){
                int pesan= JOptionPane.showConfirmDialog(null, "Cetak Nota", "Cetak", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(pesan==JOptionPane.YES_NO_OPTION){
                    reportcontrol rc=new reportcontrol();
                    rc.cetakNota(nonota.getText());
                } else{
                    JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
                }
                bersih();
                nonota.setText(null);
                txttotal.setText(null);
                txttunai.setText(null);
                txtkembali.setText(null);
                siapIsi(false);
                tambahtransaksi.setText("Tambah");
                btncetak.setEnabled(false);
                model.getDataVector().removeAllElements();
                model.fireTableDataChanged();
            } else{
                JOptionPane.showMessageDialog(null, "Simpan Transaksi Pembelian Gagal");
            }
        }

    }//GEN-LAST:event_btncetakActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int baris = jTable2.getSelectedRow();

        kodebuku.setText(jTable2.getModel().getValueAt(baris, 0).toString());
        namabuku.setText(jTable2.getModel().getValueAt(baris, 1).toString());
        tstok.setText(jTable2.getModel().getValueAt(baris, 3).toString());
        hargajual.setText(jTable2.getModel().getValueAt(baris, 4).toString());

        jDialog2.setVisible(false);    // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void caritiketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caritiketKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        ListBuku.clear();
        ListBuku=tc.cariBuku(caritiket.getText());
        for(int x=0; x<ListBuku.size(); x++){
            Object[] data=new Object[5];
            data[0]=ListBuku.get(x).getKodebuku();
            data[1]=ListBuku.get(x).getNmbuku();
            data[2]=ListBuku.get(x).getjenisbuku();
            data[3]=ListBuku.get(x).getJmlstok();
            data[4]=ListBuku.get(x).getHrgjual();

            model.addRow(data);
        }
    }//GEN-LAST:event_caritiketKeyReleased

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

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
            java.util.logging.Logger.getLogger(Transaksi_pembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi_pembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi_pembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi_pembeli.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi_pembeli().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnkurang;
    private javax.swing.JButton btnpilih;
    private javax.swing.JButton btntambah;
    private javax.swing.JTextField caritiket;
    private javax.swing.JTextField hargajual;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog2;
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
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jumlahbeli;
    private javax.swing.JTextField kodebuku;
    private javax.swing.JTextField namabuku;
    private javax.swing.JTextField namapembeli;
    private javax.swing.JTextField nonota;
    private javax.swing.JButton tambahtransaksi;
    private javax.swing.JTextField tgltransaksi;
    private javax.swing.JTextField tstok;
    private javax.swing.JTextField txtkembali;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttunai;
    // End of variables declaration//GEN-END:variables
}
