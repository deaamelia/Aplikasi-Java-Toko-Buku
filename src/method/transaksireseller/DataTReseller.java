/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.transaksireseller;

/**
 *
 * @author rudi
 */
public class DataTReseller {
    private String nomor_nota, kode_buku, nama_buku;
    int harga_reseller, jumlah_beli, total_bayar;
    
    public String getNonota(){
        return nomor_nota;
    }
    
    public void setNonota(String nomor_nota){
        this.nomor_nota= nomor_nota;
    }
      public String getKdbuku(){
        return kode_buku;
    }
    
    public void setKdbuku(String kode_buku){
        this.kode_buku= kode_buku;
    }
    
      public String getNmbuku(){
        return nama_buku;
    }
    
    public void setNmbuku(String nama_buku){
        this.nama_buku= nama_buku;
    }
    
      public int getHrgreseller(){
        return harga_reseller;
    }
    
    public void setHrgreseller(int harga_reseller){
        this.harga_reseller= harga_reseller;
    }
    
         public int getJmlbeli(){
        return jumlah_beli;
    }
    
    public void setJmlbeli(int jumlah_beli){
        this.jumlah_beli= jumlah_beli;
    }
    
         public int getTtlbayar(){
        return total_bayar;
    }
    
    public void setTtlbayar(int total_bayar){
        this.total_bayar= total_bayar;
    }
    
}
