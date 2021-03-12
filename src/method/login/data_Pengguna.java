/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.login;

/**
 *
 * @author rudi
 */
public class data_Pengguna {
    String username, password, hak_akses;
      int id_pengguna;
      
    public String getusername(){
       return username; 
    }
    
    public void setusername(String username){
        this.username=username;
    }
    
    public String getpassword(){
        return password;
    }
    
    public void setpassword(String password){
        this.password= password;
    }
    public String gethak_akses(){
        return hak_akses;
    }
    
    public void sethak_akses(String hak_akses){
        this.hak_akses= hak_akses;
    }
    
  
    public int getid_pengguna(){
        return id_pengguna;
    }
    
    public void setid_pengguna(int id_pengguna){
        this.id_pengguna=id_pengguna;
    }
    
    
}
