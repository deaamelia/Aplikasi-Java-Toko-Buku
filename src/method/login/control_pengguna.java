/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.login;


import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class control_pengguna {
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    String sql=null;
    
    public control_pengguna(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbuku","root","");
            st=con.createStatement();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada :\n"+e);
        }
    }
    
    public List CariLogin(String username, String password) {
        List logLogin= new ArrayList();
        int result;
        sql="select username, password, hak_akses from pengguna where username='" +username+ "' and password='" +password+"'";
   try{
       rs=st.executeQuery(sql);
       while(rs.next()){
           data_Pengguna dp=new data_Pengguna();
           dp.setusername(rs.getString("username"));
           dp.setpassword(rs.getString("password"));
           dp.sethak_akses(rs.getString("hak_akses"));
           logLogin.add(dp); 
       }
   } catch(Exception e){
       JOptionPane.showMessageDialog(null,"Terjadi Kesalahan Login, Pada:n"+e);
   }
return logLogin;
    }
    

   public List cariid(int id_pengguna){
       List logLogin= new ArrayList();
       int result;
       sql="select id_pengguna from pengguna where id_pengguna='"+id_pengguna+"'";
       try{
           rs=st.executeQuery(sql);
           while(rs.next()){
               data_Pengguna dp=new data_Pengguna();
               dp.setid_pengguna(rs.getInt("id_pengguna"));
               logLogin.add(dp);
                       }
       } catch(Exception e){
           JOptionPane.showMessageDialog(null, "Terjadi kesalahan pencarian id, pada"+e);
           
       }
       return logLogin;
   }
   
   public int tambah(data_Pengguna e){
       sql="insert into pengguna values('"+e.getid_pengguna()+"','"+e.getusername()+"','"+e.getpassword()+"','"+e.gethak_akses()+"')";
       int hasil=0;
       try{
           hasil=st.executeUpdate(sql);
       } catch(Exception a){
           Logger.getLogger(control_pengguna.class.getName()).log(Level.SEVERE, null, a);
       }
       return hasil;
   }
   
   public int deletepengguna(data_Pengguna e){
       sql="delete from pengguna where id_pengguna='"+e.getid_pengguna()+"'";
       int hasil=0;
       try{
           hasil=st.executeUpdate(sql);
           
       }catch (Exception a){
           Logger.getLogger(control_pengguna.class.getName()).log(Level.SEVERE, null, a);
       }
       return hasil;
   } 
   
   public List tampil() {
       List logTiket=new ArrayList();
       sql="select id_pengguna, username, password, hak_akses from pengguna order by id_pengguna asc";
       try{
           rs=st.executeQuery(sql);
           while(rs.next()){
               data_Pengguna dp= new data_Pengguna();
               dp.setid_pengguna(rs.getInt("id_pengguna"));
               dp.setusername(rs.getString("username"));
               dp.setpassword(rs.getString("password"));
               dp.sethak_akses(rs.getString("hak_akses"));
               logTiket.add(dp);
           } 
           
           
       } catch(Exception e){
           JOptionPane.showConfirmDialog(null, "Terjadi kesalahan tampil, pada:"+e);
       }
       return logTiket;
   }
}