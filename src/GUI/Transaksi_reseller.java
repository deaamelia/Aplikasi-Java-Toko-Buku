/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author rudi
 */


import method.databuku.DataBuku;
import method.databuku.BukuControl;
import method.penjualan.DataPenjualan;
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
import method.databelireseller.BeliResellerControl;
import method.databelireseller.DataBeliReseller;
import method.datareseller.DataReseller;
import method.datareseller.ResellerControl;
import method.transaksireseller.DataTReseller;
import method.transaksireseller.TResellerControl;

public class Transaksi_reseller extends javax.swing.JFrame {

    /**
     * Creates new form Transaksi_reseller
     */
  private Connection koneksi;
    private Statement kode;
    int x=0;
    private DefaultTableModel model;
    
    TResellerControl tc = new TResellerControl();
    DataTReseller dt = new DataTReseller();
    List<DataTReseller> listdt = new ArrayList<DataTReseller>();

    ResellerControl rc = new ResellerControl();
    DataReseller dr = new DataReseller();
    List<DataReseller> listReseller = new ArrayList<DataReseller>();    
    
    BukuControl bc = new BukuControl();
    DataBuku DB = new DataBuku();
    List<DataBuku> ListBuku = new ArrayList<DataBuku>();
    
     BeliResellerControl br = new BeliResellerControl();
    DataBeliReseller db = new DataBeliReseller();
    List<DataBeliReseller> listdb = new ArrayList<DataBeliReseller>();
    
    
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
        
    Transaksi2 tr=new Transaksi2();
    public Transaksi_reseller() {
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
        
        btnkdrslr.setText(null);
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
        btnkdrslr.setEnabled(false);
        namapembeli.setEnabled(false);
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
        pilihrslr.setEnabled(false);
    }
    
    public void tombolNormal(){
         btntambah.setEnabled(true);
         btnkurang.setEnabled(true);
        btnpilih.setEnabled(true);
        btncetak.setEnabled(true);
    }
    
    private void buatNoNota(){
         listdb=br.tampil();
         if(listdb.isEmpty()){
             nonota.setText("M-1");
         } else {
             int a=listdb.size()-1;
        int no=Integer.parseInt(listdb.get(a).getNonota().replace("M-", ""))+1;
        nonota.setText("M-"+no);
        nonota.setEnabled(false);
         }
        
    
}
    
    public void siapIsi(boolean a){
        tgltransaksi.setEnabled(a);
        tstok.setEnabled(a);
        nonota.setEnabled(a);
        btnkdrslr.setEnabled(a);
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
        listdt.clear();
        listdt=tc.cariBuku(nonota.getText());
        for(int x=0; x<listdt.size(); x++){
            Object[] data=new Object[5];
            data[0]=listdt.get(x).getKdbuku();
            data[1]=listdt.get(x).getNmbuku();
            data[2]=listdt.get(x).getHrgreseller();
            data[3]=listdt.get(x).getJmlbeli();
            data[4]=listdt.get(x).getTtlbayar();
            model.addRow(data);
        }
    }
        
            private void buatTableBuku(){
        model=new DefaultTableModel();
        model.addColumn("Kode Buku");
        model.addColumn("Nama Buku");
        model.addColumn("Jenis Buku");
        model.addColumn("Jumlah Stok");
        model.addColumn("Harga Reseller");
        jTable2.setModel(model);
        
    }
    
    private void tampilbuku(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        ListBuku.clear();
        ListBuku=bc.tampil();
            for(int x=0; x<ListBuku.size(); x++){
            Object[] data=new Object[5];
            data[0]=ListBuku.get(x).getKodebuku();
            data[1]=ListBuku.get(x).getNmbuku();
            data[2]=ListBuku.get(x).getjenisbuku();
            data[3]=ListBuku.get(x).getJmlstok();
            data[4]=ListBuku.get(x).getHrgreseller();
           
      
            model.addRow(data);
        }
    }
    
                private void buatTableReseller(){
        model=new DefaultTableModel();
        model.addColumn("Kode Reseller");
        model.addColumn("Nama Reseller");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Status");
        model.addColumn("No HP");
        model.addColumn("Alamat");
        jTable3.setModel(model);
        
    }
    
    private void tampilreseller(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        listReseller.clear();
        listReseller=rc.tampil();
            for(int x=0; x<listReseller.size(); x++){
            Object[] data=new Object[6];
            data[0]=listReseller.get(x).getKodereseller();
            data[1]=listReseller.get(x).getNmreseller();
            data[2]=listReseller.get(x).getjk();
            data[3]=listReseller.get(x).getsttus();
            data[4]=listReseller.get(x).getnohp();
            data[5]=listReseller.get(x).getalmt();
           
      
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

        jDialog1 = new javax.swing.JDialog();
        jPanel14 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        caritiket = new javax.swing.JTextField();
        jDialog2 = new javax.swing.JDialog();
        jPanel17 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        caritiket1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        nonota = new javax.swing.JTextField();
        tstok = new javax.swing.JTextField();
        btnkdrslr = new javax.swing.JTextField();
        namapembeli = new javax.swing.JTextField();
        kodebuku = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pilihrslr = new javax.swing.JButton();
        btnpilih = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        namabuku = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        hargajual = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jumlahbeli = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tgltransaksi = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        btnkurang = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txttunai = new javax.swing.JTextField();
        txtkembali = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tambahtransaksi = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(57, 100, 142));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("DATA BUKU");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(jLabel18)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel18)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jDialog1.getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 90));

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

        jDialog1.getContentPane().add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 510, 10));

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

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setText("Cari Data Buku :");

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
                        .addComponent(jLabel19)
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
                    .addComponent(jLabel19)
                    .addComponent(caritiket, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jDialog1.getContentPane().add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 510, 260));

        jDialog2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(57, 100, 142));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("DATA RESELLER");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addGap(156, 156, 156))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel20)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jDialog2.getContentPane().add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 90));

        jPanel18.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jDialog2.getContentPane().add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 520, 10));

        jPanel19.setBackground(new java.awt.Color(226, 211, 196));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("Cari Data Reseller :");

        caritiket1.setBackground(new java.awt.Color(226, 211, 196));
        caritiket1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        caritiket1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        caritiket1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                caritiket1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(31, 31, 31)
                .addComponent(caritiket1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(caritiket1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jDialog2.getContentPane().add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 520, 270));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(57, 100, 142));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(226, 211, 196));

        jPanel11.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconLogin/icons8-back-64 (1).png"))); // NOI18N
        jLabel9.setText("jLabel1");
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 90, 670));

        jPanel3.setBackground(new java.awt.Color(226, 211, 196));

        jPanel10.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 590, 560, 80));

        jPanel4.setBackground(new java.awt.Color(226, 211, 196));

        jPanel12.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 60, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 0, 90, 720));

        jPanel5.setBackground(new java.awt.Color(226, 211, 196));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(245, 160, 75));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 0, 410, 80));

        jPanel6.setBackground(new java.awt.Color(57, 100, 142));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nonota.setBackground(new java.awt.Color(226, 211, 196));
        nonota.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        nonota.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(nonota, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, 70, -1));

        tstok.setBackground(new java.awt.Color(226, 211, 196));
        tstok.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tstok.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(tstok, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, 70, -1));

        btnkdrslr.setEditable(false);
        btnkdrslr.setBackground(new java.awt.Color(226, 211, 196));
        btnkdrslr.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnkdrslr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkdrslrActionPerformed(evt);
            }
        });
        jPanel6.add(btnkdrslr, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 190, -1));

        namapembeli.setBackground(new java.awt.Color(226, 211, 196));
        namapembeli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        namapembeli.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        namapembeli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namapembeliActionPerformed(evt);
            }
        });
        jPanel6.add(namapembeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 120, 20));

        kodebuku.setBackground(new java.awt.Color(226, 211, 196));
        kodebuku.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        kodebuku.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(kodebuku, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 120, 20));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("No Transaksi");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Kode Reseller");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, 20));

        jLabel6.setBackground(new java.awt.Color(57, 100, 142));
        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Kode Buku");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, 20));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kode Buku");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, -1, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nama Reseller");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, 20));

        pilihrslr.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pilihrslr.setText("Pilih");
        pilihrslr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pilihrslrActionPerformed(evt);
            }
        });
        jPanel6.add(pilihrslr, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 60, 20));

        btnpilih.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnpilih.setText("Pilih");
        btnpilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpilihActionPerformed(evt);
            }
        });
        jPanel6.add(btnpilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 60, 20));

        jPanel7.setBackground(new java.awt.Color(57, 100, 142));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Nama Buku");

        namabuku.setBackground(new java.awt.Color(226, 211, 196));
        namabuku.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        namabuku.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        namabuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namabukuActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Harga Jual");

        hargajual.setBackground(new java.awt.Color(226, 211, 196));
        hargajual.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        hargajual.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Jumlah Beli");

        jumlahbeli.setBackground(new java.awt.Color(226, 211, 196));
        jumlahbeli.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jumlahbeli.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Tanggal Transaksi");

        tgltransaksi.setBackground(new java.awt.Color(226, 211, 196));
        tgltransaksi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tgltransaksi.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(77, 77, 77)
                        .addComponent(namabuku, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(84, 84, 84)
                        .addComponent(hargajual, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(77, 77, 77)
                        .addComponent(jumlahbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(28, 28, 28)
                        .addComponent(tgltransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(namabuku, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(hargajual, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jumlahbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(tgltransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 450, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 900, 240));

        jPanel8.setBackground(new java.awt.Color(57, 100, 142));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnkurang.setText("-");
        btnkurang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkurangActionPerformed(evt);
            }
        });
        jPanel8.add(btnkurang, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        btntambah.setText("+");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });
        jPanel8.add(btntambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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

        jPanel8.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 470, 120));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 500, 200));

        jPanel9.setBackground(new java.awt.Color(226, 211, 196));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(48, 99, 118));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(57, 100, 142));
        jLabel3.setText("Tunai");
        jPanel9.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel4.setBackground(new java.awt.Color(48, 99, 118));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(57, 100, 142));
        jLabel4.setText("Kembali");
        jPanel9.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        txttotal.setBackground(new java.awt.Color(226, 211, 196));
        txttotal.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 213, -1));

        txttunai.setBackground(new java.awt.Color(226, 211, 196));
        txttunai.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txttunai.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttunai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttunaiActionPerformed(evt);
            }
        });
        jPanel9.add(txttunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 213, -1));

        txtkembali.setBackground(new java.awt.Color(226, 211, 196));
        txtkembali.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtkembali.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel9.add(txtkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 213, -1));

        jButton3.setText("Bayar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, -1));

        jLabel2.setBackground(new java.awt.Color(48, 99, 118));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(57, 100, 142));
        jLabel2.setText("Total");
        jPanel9.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 20));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 380, 390, 200));

        jLabel1.setFont(new java.awt.Font("Lucida Handwriting", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("TRANSAKSI RESELLER");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 470, 60));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconNew/icons8-shopping-64.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 70, -1));

        tambahtransaksi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tambahtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconDataBuku/icons8-add-24 (1).png"))); // NOI18N
        tambahtransaksi.setText("Tambah");
        tambahtransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahtransaksiActionPerformed(evt);
            }
        });
        jPanel1.add(tambahtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 610, 120, 40));

        btncetak.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconNew/icons8-print-24 (1).png"))); // NOI18N
        btncetak.setText("Cetak Faktur");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });
        jPanel1.add(btncetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 610, 140, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnkdrslrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkdrslrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnkdrslrActionPerformed

    private void namapembeliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namapembeliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namapembeliActionPerformed

    private void pilihrslrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pilihrslrActionPerformed
        buatTableReseller();
        tampilreseller();
        caritiket1.setText(null);
        jDialog2.setVisible(true);

        jDialog2.setBounds(300, 300, 520, 350);

        caritiket1.requestFocus();   // TODO add your handling code here:
    }//GEN-LAST:event_pilihrslrActionPerformed

    private void btnpilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpilihActionPerformed
        buatTableBuku();
        tampilbuku();
        caritiket.setText(null);
        jDialog1.setVisible(true);

        jDialog1.setBounds(300, 300, 520, 350);

        caritiket.requestFocus();
    }//GEN-LAST:event_btnpilihActionPerformed

    private void namabukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namabukuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namabukuActionPerformed

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
                listdt=tc.Cekbuku(nonota.getText(), kodebuku.getText());
                if(listdt.size()>0){
                    JOptionPane.showMessageDialog(null, "Buku ini sudah anda beli, silahkan pilih yang lain");
                } else{

                    tr.harga=Integer.parseInt(hargajual.getText());
                    DataTReseller dt=new DataTReseller();
                    dt.setNonota(nonota.getText());
                    dt.setKdbuku(kodebuku.getText());
                    dt.setNmbuku(namabuku.getText());
                    dt.setHrgreseller(tr.harga);
                    dt.setJmlbeli(tr.jml);
                    dt.setTtlbayar(tr.total());
                    if(tc.tambahPenjualan(dt)==0){
                        JOptionPane.showMessageDialog(null, "Data gagal disimpan,mohon periksa kembali");
                    } else{
                        
                        bc.updateStok(tr.sisaStok(), kodebuku.getText());
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
        dt.setKdbuku(kodebuku.getText());
        dt.setNonota(nonota.getText());
        int pesan = JOptionPane.showConfirmDialog(null, "Data akan dihapus ?","Konfirmasi",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(pesan==JOptionPane.YES_NO_OPTION){
            tr.jml=Integer.parseInt(jumlahbeli.getText());
            tr.stok=Integer.parseInt(tstok.getText());
            if(tc.deletePenjualan(dt)== 1){
               DataTReseller dt=new DataTReseller();
                    int harga=Integer.parseInt(hargajual.getText());
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
                        
                       
                JOptionPane.showMessageDialog(null,"Data telah dihapus");
                
                bc.updateStok(tr.stokKurang(), kodebuku.getText());
                
                kodebuku.setText(null);
                namabuku.setText(null);
                hargajual.setText(null);
                jumlahbeli.setText(null);
                tgltransaksi.setText(null);
                tgltransaksi.setEnabled(false);
                tstok.setEnabled(false);
                nonota.setEnabled(false);
                btnkdrslr.setEnabled(false);
                namapembeli.setEnabled(false);
                kodebuku.setEnabled(false);
                namabuku.setEnabled(false);
                hargajual.setEnabled(false);
                
                buatTablebeli();
                tampilBeli();

                try {
                    tampilBeli();
                } catch (Exception e) {
                    Logger.getLogger(Transaksi_reseller.class.getName()).log(Level.SEVERE, null, e);
                }
            }else{
                JOptionPane.showMessageDialog(null,"Data gagal dihapus");
            }
        }

    }//GEN-LAST:event_btnkurangActionPerformed

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

        DataTReseller dt=new DataTReseller();
        dt.setHrgreseller(harga);
        dt.setJmlbeli(jml);
        dt.setTtlbayar(harga*jml);
        int total=Integer.parseInt(txttotal.getText());
        int tunai=Integer.parseInt(txttunai.getText()) ;
        int kembali=tunai-total;

        txtkembali.setText(Integer.toString(kembali));
    }//GEN-LAST:event_txttunaiActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        tr.total=Integer.parseInt(txttotal.getText());

        tr.tunai=Integer.parseInt(txttunai.getText()) ;
        if(tr.kembali()<0){
            txtkembali.setText(""+tr.ket());
        } else

        txtkembali.setText(""+tr.kembali());

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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

            pilihrslr.setEnabled(true);
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
            btnpilih.setEnabled(false);
            pilihrslr.setEnabled(false);
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

            DataBeliReseller DB=new DataBeliReseller();
            DB.setNonota(nonota.getText());
            DB.setTglbeli(htgl);
            DB.setnamareseller(namapembeli.getText());
            DB.setbayar(Integer.parseInt(txttunai.getText()));
            DB.settotal(Integer.parseInt(txttotal.getText()));
            DB.setkembali(Integer.parseInt(txtkembali.getText()));
            if(br.tambah(DB)==1){
                int pesan= JOptionPane.showConfirmDialog(null, "Cetak Nota", "Cetak", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(pesan==JOptionPane.YES_NO_OPTION){
                    reportcontrol rc=new reportcontrol();
                    rc.cetakNotaReseller(nonota.getText());
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

        jDialog1.setVisible(false);    // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseClicked

    private void caritiketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caritiketKeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        ListBuku.clear();
        ListBuku=bc.cariBuku(caritiket.getText());
        for(int x=0; x<ListBuku.size(); x++){
            Object[] data=new Object[5];
            data[0]=ListBuku.get(x).getKodebuku();
            data[1]=ListBuku.get(x).getNmbuku();
            data[2]=ListBuku.get(x).getjenisbuku();
            data[3]=ListBuku.get(x).getJmlstok();
            data[4]=ListBuku.get(x).getHrgreseller();

            model.addRow(data);
        }
    }//GEN-LAST:event_caritiketKeyReleased

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        int baris = jTable3.getSelectedRow();

        btnkdrslr.setText(jTable3.getModel().getValueAt(baris, 0).toString());
        namapembeli.setText(jTable3.getModel().getValueAt(baris, 1).toString());

        jDialog2.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jTable3MouseClicked

    private void caritiket1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caritiket1KeyReleased
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        listReseller.clear();
        listReseller=rc.cariReseller(caritiket1.getText());
        for(int x=0; x<listReseller.size(); x++){
            Object[] data=new Object[6];
            data[0]=listReseller.get(x).getKodereseller();
            data[1]=listReseller.get(x).getNmreseller();
            data[2]=listReseller.get(x).getjk();
            data[3]=listReseller.get(x).getsttus();
            data[4]=listReseller.get(x).getnohp();

            data[5]=listReseller.get(x).getalmt();

            model.addRow(data);
        }// TODO add your handling code here:
    }//GEN-LAST:event_caritiket1KeyReleased

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

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
            java.util.logging.Logger.getLogger(Transaksi_reseller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi_reseller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi_reseller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi_reseller.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi_reseller().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncetak;
    private javax.swing.JTextField btnkdrslr;
    private javax.swing.JButton btnkurang;
    private javax.swing.JButton btnpilih;
    private javax.swing.JButton btntambah;
    private javax.swing.JTextField caritiket;
    private javax.swing.JTextField caritiket1;
    private javax.swing.JTextField hargajual;
    private javax.swing.JButton jButton3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jumlahbeli;
    private javax.swing.JTextField kodebuku;
    private javax.swing.JTextField namabuku;
    private javax.swing.JTextField namapembeli;
    private javax.swing.JTextField nonota;
    private javax.swing.JButton pilihrslr;
    private javax.swing.JButton tambahtransaksi;
    private javax.swing.JTextField tgltransaksi;
    private javax.swing.JTextField tstok;
    private javax.swing.JTextField txtkembali;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttunai;
    // End of variables declaration//GEN-END:variables
}
