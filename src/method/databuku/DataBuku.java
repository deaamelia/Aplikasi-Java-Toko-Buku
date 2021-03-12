/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.databuku;

/**
 *
 * @author ASUS
 */
public class DataBuku {
    public String kode_buku, nama_buku, jenis_buku, penerbit, tahun_terbit;
    public int jumlah_stok, harga_jual, harga_reseller;

    public String getKodebuku(){
        return kode_buku;
 
    }
    
    public void setKodebuku(String kode_buku){
        this.kode_buku = kode_buku;
    }
    
    public String getNmbuku(){
        return nama_buku;
    }
    
    public void setNmbuku(String nama_buku){
        this.nama_buku= nama_buku;
    }
    
     public String getjenisbuku(){
        return jenis_buku;
    }
    
      public void setjenisbuku(String jenis_buku){
        this.jenis_buku = jenis_buku;
    }
    
          public String getpenerbit(){
        return penerbit;
 
    }
    
    public void setpenerbit(String penerbit){
        this.penerbit = penerbit;
    }
    
    public String gettahunterbit(){
        return tahun_terbit;
    }
    
    public void settahunterbit(String tahun_terbit){
        this.tahun_terbit= tahun_terbit;
    }
    
      
     public int getJmlstok(){
        return jumlah_stok;
    }
    
      public void setJmlstok(int jumlah_stok){
        this.jumlah_stok = jumlah_stok;
    }
      
    public int getHrgjual(){
        return harga_jual;
    }
    
      public void setHrgjual(int harga_jual){
        this.harga_jual = harga_jual;
    }
    
      public int getHrgreseller(){
        return harga_reseller;
    }
    
      public void setHrgreseller(int harga_reseller){
        this.harga_reseller = harga_reseller;
    }
}
