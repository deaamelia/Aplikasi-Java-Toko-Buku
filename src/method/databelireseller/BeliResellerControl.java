/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.databelireseller;

import method.databeli.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import method.databuku.DataBuku;

/**
 *
 * @author rudi
 */
public class BeliResellerControl {
     Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    String sql=null;
    
    public BeliResellerControl(){
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
      sql="select idbeli, no_nota, tanggal_beli, nama_reseller, total, tunai,kembali from tbtambahtreseller order by idbeli asc";
      try {
          rs = st.executeQuery(sql);
          while (rs.next()){
              DataBeliReseller db = new DataBeliReseller();
              db.setNonota(rs.getString("no_nota"));
              db.setTglbeli(rs.getString("tanggal_beli"));
              db.setnamareseller(rs.getString("nama_reseller"));
              db.settotal(rs.getInt("total"));
              db.setbayar(rs.getInt("tunai"));
              db.setkembali(rs.getInt("kembali"));
              LogBuku.add(db);
          }
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+e);
      } 
     return LogBuku; 
  }     
  
  public int tambah(DataBeliReseller e){
      sql ="insert into tbtambahtreseller (no_nota, tanggal_beli, nama_reseller, total, tunai,kembali) values('"+
      e.getNonota()+"','"+e.getTglbeli()+"','"+e.getnamareseller()+"','"+e.gettotal()+"','"+e.getbayar()+"','"+e.getkembali()+"')";
      int hasil=0;
      try {
          hasil=st.executeUpdate(sql);
          
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+e);
      }
      return hasil;
            
              
  }
  
  
     public  List CekBuku(String Nonota, String Kdbuku){
      List logCekBuku = new ArrayList();
      sql="select no_nota, tanggal_beli, nama_reseller, total, tunai, kembali from tbtambahtreseller where no_nota='"+Nonota+"'";
              
      try {
          rs=st.executeQuery(sql);
          while(rs.next()){
              DataBeliReseller db=new DataBeliReseller();
              db.setNonota(rs.getString("no_nota"));
              db.setTglbeli(rs.getString("tanggal_beli"));
              db.setnamareseller(rs.getString("nama_reseller"));
              db.settotal(rs.getInt("total"));
              db.setbayar(rs.getInt("tunai"));
              db.setkembali(rs.getInt("kembali"));
             
              logCekBuku.add(db);
          }
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan tampil, pada : \n"+a);
      }
      return logCekBuku;
  }
}

 
