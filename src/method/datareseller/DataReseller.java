/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.datareseller;



/**
 *
 * @author ASUS
 */
public class DataReseller {
    public String kode_reseller, nama_reseller, jenis_kelamin, status, alamat, no_hp;


    public String getKodereseller(){
        return kode_reseller;
 
    }
    
    public void setKodereseller(String kode_reseller){
        this.kode_reseller = kode_reseller;
    }
    
    public String getNmreseller(){
        return nama_reseller;
    }
    
    public void setNmreseller(String nama_reseller){
        this.nama_reseller= nama_reseller;
    }
    
     public String getjk(){
        return jenis_kelamin;
    }
    
      public void setjk(String jenis_kelamin){
        this.jenis_kelamin= jenis_kelamin;
    }
    
          public String getsttus(){
        return status;
 
    }
    
    public void setsttus(String status){
        this.status = status;
    }
    
        public String getnohp(){
        return no_hp;
    }
    
    public void setnohp(String no_hp){
        this.no_hp= no_hp;
    }
    
    
    public String getalmt(){
        return alamat;
    }
    
    public void settalmt(String alamat){
        this.alamat= alamat;
    }
    
      
    
}
