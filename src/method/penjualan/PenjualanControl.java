/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.penjualan;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import java.util.logging.Logger;


/**
 *
 * @author rudi
 */
public class PenjualanControl {
     Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    String sql=null;
    
    public PenjualanControl(){

            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbuku","root","");
                st=con.createStatement();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Koneksi Database Gagal, Terjadi Kesalahan Pada : \n"+e);
            }
    }
    
    public List tampilbuku(String Nonota){
        List logpenjualan=new ArrayList();
        sql="select kode_buku, nama_buku, harga_jual,jumlah_beli, total_bayar from tbpenjualan where no_nota='"+Nonota+"'";
        try{
            rs=st.executeQuery(sql);
            
            while(rs.next()){
            DataPenjualan dp=new DataPenjualan();
            dp.setKdbuku(rs.getString("kode_buku"));
            dp.setNmbuku(rs.getString("nama_buku"));
            dp.setHrgjual(rs.getInt("harga_jual"));
            dp.setJmlbeli(rs.getInt("jumlah_beli"));
            dp.setTtlbayar(rs.getInt("total_bayar"));
            logpenjualan.add(dp);
        } 
        } catch(SQLException a){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan tampil obat, Pada : \n"+a);
        } 
        return logpenjualan;
    }
    
     public  List cariBuku(String cari){
      List logBuku = new ArrayList();
      sql="select nomor_nota, kode_buku, nama_buku, harga_jual, jumlah_beli, total_bayar from tbpenjualan where nomor_nota like'%"+cari+"%' "
              + "or nama_buku like '% "+cari+"%'";
      try {
          rs=st.executeQuery(sql);
          while(rs.next()){
              DataPenjualan DO=new DataPenjualan();
              DO.setKdbuku(rs.getString("kode_buku"));
              DO.setNmbuku(rs.getString("nama_buku"));
              DO.setHrgjual(rs.getInt("harga_jual"));
              DO.setJmlbeli(rs.getInt("jumlah_beli"));
              DO.setTtlbayar(rs.getInt("total_bayar"));
             
              logBuku.add(DO);
          }
      } catch (SQLException a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan tampil, pada : \n"+a);
      }
      return logBuku;
  }
     
     public  List CekBuku(String Nonota, String Kdbuku){
      List logDetailBeli = new ArrayList();
      sql="select nomor_nota, kode_buku, nama_buku, harga_jual, jumlah_beli, total_bayar from tbpenjualan where nomor_nota='"+Nonota+"' and kode_buku='"+Kdbuku+"'";
              
      try {
          rs=st.executeQuery(sql);
          while(rs.next()){
              DataPenjualan dp=new DataPenjualan();
              dp.setKdbuku(rs.getString("kode_buku"));
              dp.setNmbuku(rs.getString("nama_buku"));
              dp.setHrgjual(rs.getInt("harga_jual"));
              dp.setJmlbeli(rs.getInt("jumlah_beli"));
              dp.setTtlbayar(rs.getInt("total_bayar"));
             
              logDetailBeli.add(dp);
          }
      } catch (SQLException a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan tampil, pada : \n"+a);
      }
      return logDetailBeli;
  }
     
      public int tambahPenjualan(DataPenjualan e){
      sql ="insert into tbpenjualan values('"+e.getNonota()+"','"+e.getKdbuku()+"','"+e.getNmbuku()+"','"+e.getHrgjual()+"','"+e.getJmlbeli()+"','"+e.getTtlbayar()+"')";
      int hasil=0;
      try {
          hasil=st.executeUpdate(sql);
          
      } catch (SQLException a) {
          Logger.getLogger(PenjualanControl.class.getName()).log(Level.SEVERE, null, a);
      }
      return hasil;
            
              
  }
 
     public int deletePenjualan(DataPenjualan e){
      sql="delete from tbpenjualan where nomor_nota='"+e.getNonota()+"'and kode_buku='"+e.getKdbuku()+"'";
      int hasil=0;
      try {
          hasil=st.executeUpdate(sql);
          
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+a);
      }
      return hasil;
  }
     
}
