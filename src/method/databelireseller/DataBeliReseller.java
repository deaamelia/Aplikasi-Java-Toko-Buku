/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.databelireseller;

import method.databeli.*;

/**
 *
 * @author rudi
 */
public class DataBeliReseller {
    private String no_nota, tanggal_beli, nama_reseller;
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
    
      public String getnamareseller(){
        return nama_reseller;
    }
    
    public void setnamareseller(String nama_reseller){
        this.nama_reseller= nama_reseller;
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
