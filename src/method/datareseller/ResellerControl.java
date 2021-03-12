/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.datareseller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ResellerControl {
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    String sql=null;
    
    public ResellerControl(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbuku","root","");
            st=con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Koneksi database gagal, Terjadi kesalahan pada : \n"+e);
        }
    
    }
    
  public List tampil(){
      List LogReseller = new ArrayList();
      sql="select kode_reseller, nama_reseller, jk, status, no_hp, alamat from tbdatareseller order by idreseller asc";
      try {
          rs = st.executeQuery(sql);
          while (rs.next()){
              DataReseller DR = new DataReseller();
              DR.setKodereseller(rs.getString("kode_reseller"));
              DR.setNmreseller(rs.getString("nama_reseller"));
              DR.setjk(rs.getString("jk"));
              DR.setsttus(rs.getString("status"));
              DR.setnohp(rs.getString("no_hp"));
              DR.settalmt(rs.getString("alamat"));
              LogReseller.add(DR);
          }
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+e);
      } 
     return LogReseller; 
  }     
  
    public int tambahreseller(DataReseller e){
      sql ="insert into tbdatareseller ( kode_reseller, nama_reseller, jk, status, no_hp, alamat) values('"+
      e.getKodereseller()+"','"+e.getNmreseller()+"','"+e.getjk()+"','"+e.getsttus()+"','"+e.getnohp()+"','"+e.getalmt()+"')";
      int hasil=0;
      try {
          hasil=st.executeUpdate(sql);
          
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+e);
      }
      return hasil;
            
              
  }

  public int editReseller(DataReseller e){
      sql="update tbdatareseller set nama_reseller='"+e.getNmreseller()+"',jk='"+e.getjk()
              +"',status='"+e.getsttus()+"',no_hp='"+e.getnohp()+"',alamat='"+e.getalmt()+
              "'where kode_reseller='"+ e.getKodereseller()+"'";
      int hasil=0;
      try {
          hasil =st.executeUpdate(sql);
          
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+a);
      }
      return hasil;
  }
  
  
  
  public  List cariReseller(String cari){
      List logReseller = new ArrayList();
      sql="select kode_reseller, nama_reseller, jk, status, no_hp, alamat from tbdatareseller where nama_reseller "
              + "like '%"+cari+"%'";
      try {
          rs=st.executeQuery(sql);
          while(rs.next()){
              DataReseller DR = new DataReseller();
              DR.setKodereseller(rs.getString("kode_reseller"));
              DR.setNmreseller(rs.getString("nama_reseller"));
              DR.setjk(rs.getString("jk"));
              DR.setsttus(rs.getString("status"));
              DR.setnohp(rs.getString("no_hp"));
              DR.settalmt(rs.getString("alamat"));
              logReseller.add(DR);
          }
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+a);
      }
      return logReseller;
  }
  public int deleteReseller(DataReseller e){
      sql="delete from tbdatareseller where kode_reseller='"+e.getKodereseller()+"'";
      int hasil=0;
      try {
          hasil=st.executeUpdate(sql);
          
      } catch (Exception a) {
          JOptionPane.showMessageDialog(null,"Terjadi kesalahan pada : \n"+a);
      }
      return hasil;
  }
  
  public int getJumlahreseller() throws SQLException{
      sql= "select count(*) as jumlah_reseller" + " from tbdatareseller";
      rs=st.executeQuery(sql);
      int jumlah;
      while(rs.next()){
          jumlah = rs.getInt("jumlah_reseller");
          return jumlah;
      }
      return 0;
  }
  

  
}
