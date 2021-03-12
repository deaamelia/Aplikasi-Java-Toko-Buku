/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.databuku;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import method.penjualan.PenjualanControl;
/**
 *
 * @author ASUS
 */
public class BukuControl {
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    String sql=null;
    
    public BukuControl(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbuku","root","");
            st=con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi database gagal, Terjadi kesalahan pada : \n"+e);
        }
    
    }
    
  public List tampil(){
      List LogBuku = new ArrayList();
      sql="select kode_buku, nama_buku, jenis_buku, penerbit, tahun_terbit, jumlah_stok, harga_jual, harga_reseller from tbdatabuku order by idbuku asc";
      try {
          rs = st.executeQuery(sql);
          while (rs.next()){
              DataBuku Dt = new DataBuku();
              Dt.setKodebuku(rs.getString("kode_buku"));
              Dt.setNmbuku(rs.getString("nama_buku"));
              Dt.setjenisbuku(rs.getString("jenis_buku"));
              Dt.setpenerbit(rs.getString("penerbit"));
              Dt.settahunterbit(rs.getString("tahun_terbit"));
              Dt.setJmlstok(rs.getInt("jumlah_stok"));
              Dt.setHrgjual(rs.getInt("harga_jual"));
              Dt.setHrgreseller(rs.getInt("harga_reseller"));
              LogBuku.add(Dt);
          }
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+e);
      } 
     return LogBuku; 
  }     
  
  public int tambahbuku(DataBuku e){
      sql ="insert into tbdatabuku (kode_buku, nama_buku, jenis_buku, penerbit, tahun_terbit, jumlah_stok, harga_jual, harga_reseller) values('"+
      e.getKodebuku()+"','"+e.getNmbuku()+"','"+e.getjenisbuku()+"','"+e.getpenerbit()+"','"+e.gettahunterbit()+"','"+e.getJmlstok()+"','"+e.getHrgjual()+"','"+e.getHrgreseller()+"')";
      int hasil=0;
      try {
          hasil=st.executeUpdate(sql);
          
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+e);
      }
      return hasil;
            
              
  }

  public int editBuku(DataBuku e){
      sql="update tbdatabuku set nama_buku='"+e.getNmbuku()+"',jenis_buku='"+e.getjenisbuku()
              +"',penerbit='"+e.getpenerbit()+"',tahun_terbit='"+e.gettahunterbit()+"',jumlah_stok='"+e.getJmlstok()+"',harga_jual='"+e.getHrgjual()+"',harga_reseller='"+e.getHrgreseller()+
              "'where kode_buku='"+ e.getKodebuku()+"'";
      int hasil=0;
      try {
          hasil =st.executeUpdate(sql);
          
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+a);
      }
      return hasil;
  }
  
  
  
  public  List cariBuku(String cari){
      List logBuku = new ArrayList();
      sql="select kode_buku, nama_buku, jenis_buku, penerbit, tahun_terbit, jumlah_stok, harga_jual, harga_reseller from tbdatabuku where nama_buku "
              + "like '%"+cari+"%'";
      try {
          rs=st.executeQuery(sql);
          while(rs.next()){
                DataBuku Dt = new DataBuku();
              Dt.setKodebuku(rs.getString("kode_buku"));
              Dt.setNmbuku(rs.getString("nama_buku"));
              Dt.setjenisbuku(rs.getString("jenis_buku"));
              Dt.setpenerbit(rs.getString("penerbit"));
              Dt.settahunterbit(rs.getString("tahun_terbit"));
              Dt.setJmlstok(rs.getInt("jumlah_stok"));
              Dt.setHrgjual(rs.getInt("harga_jual"));
              Dt.setHrgreseller(rs.getInt("harga_reseller"));
              logBuku.add(Dt);
          }
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+a);
      }
      return logBuku;
  }
  public int deleteBuku(DataBuku e){
      sql="delete from tbdatabuku where kode_buku='"+e.getKodebuku()+"'";
      int hasil=0;
      try {
          hasil=st.executeUpdate(sql);
          
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+a);
      }
      return hasil;
  }
  
  public int getJumlahbuku() throws SQLException{
      sql= "select count(*) as jumlah_buku" + " from tbdatabuku";
      rs=st.executeQuery(sql);
      int jumlah;
      while(rs.next()){
          jumlah = rs.getInt("jumlah_buku");
          return jumlah;
      }
      return 0;
  }
  
  public List tampilSatuan(){
      List logTiket=new ArrayList();
      sql="select distinct(jenis_buku) from tbdatabuku order by idbuku asc";
      try{
          rs=st.executeQuery(sql);
          while(rs.next()){
              DataBuku dt=new DataBuku();
              dt.setjenisbuku(rs.getString("jenis_buku"));
              logTiket.add(dt);
          }
      } catch (Exception e){
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+e);
          
      } return logTiket;
  }

    public int updateStok(int sisaStok, String kodebuku) {
        
        sql="update tbdatabuku set jumlah_stok='"+sisaStok+"' where kode_buku='"+kodebuku+"'";
      int hasil=0;
      try {
          hasil =st.executeUpdate(sql);
          
      } catch (Exception a) {
          
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+a);
        
      } 
       return hasil;
    } 
}
