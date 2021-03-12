/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.databeli;

/**
 *
 * @author rudi
 */
public class DataBeli {
    private String no_nota, tanggal_beli, nama_pembeli;
    int total, tunai, kembali;
    
    public String getNonota(){
        return no_nota;
    }
    
    public void setNonota(String no_nota){
        this.no_nota= no_nota;
    }
      public String getTglbeli(){
        return tanggal_beli;
    }
    
    public void setTglbeli(String tanggal_beli){
        this.tanggal_beli= tanggal_beli;
    }
    
      public String getnamapembeli(){
        return nama_pembeli;
    }
    
    public void setnamapembeli(String nama_pembeli){
        this.nama_pembeli= nama_pembeli;
    }
    
      public int gettotal(){
        return total;
    }
    
    public void settotal(int total){
        this.total= total;
    }
    
         public int getbayar(){
        return tunai;
    }
    
    public void setbayar(int tunai){
        this.tunai=tunai;
        
        
    }
    
         public int getkembali(){
        return kembali;
    }
    
    public void setkembali(int kembali){
        this.kembali= kembali;
    }
    
    
}
