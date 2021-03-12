/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method.report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rudi
 */
public class reportcontrol {
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    String sql=null;
    
    public reportcontrol(){

            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbbuku","root","");
                st=con.createStatement();
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Koneksi Database Gagal, Terjadi Kesalahan Pada : \n"+e);
            }
    }
            
    public void cetakSeluruhBuku(){
        try{
            String sumber;
            String tujuan;
            sumber="src/Report/DataBuku.jrxml";
            tujuan="src/Report/DataBuku.html";
            Map parms= new HashMap();
            JasperReport jr=JasperCompileManager.compileReport(sumber);
            JasperPrint jp=JasperFillManager.fillReport(jr, parms, con);
            JasperExportManager.exportReportToHtmlFile(jp, tujuan);
            JasperViewer.viewReport(jp, false);
        } catch (Exception xe){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada"+xe);
        }
    }
    
    public void cetakPerSatuan(String jenis_buku){
        try{
             String sumber;
            String tujuan;
            sumber="src/Report/BukuPerSatuan.jrxml";
            tujuan="src/Report/BukuPerSatuan.html";
            Map parms= new HashMap();
            parms.put("jenisbuku", jenis_buku);
            JasperReport jr=JasperCompileManager.compileReport(sumber);
            JasperPrint jp=JasperFillManager.fillReport(jr, parms, con);
            JasperExportManager.exportReportToHtmlFile(jp, tujuan);
            JasperViewer.viewReport(jp, false);
            
        } catch (Exception xe){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada"+xe);
        }
    }
    
    public void cetakNota(String nonota){
        try{
             String sumber;
            String tujuan;
            sumber="src/Report/Faktur.jrxml";
            tujuan="src/Report/Faktur.html";
            Map parms= new HashMap();
            
            parms.put("nomornota", nonota);
            JasperReport jr=JasperCompileManager.compileReport(sumber);
            JasperPrint jp=JasperFillManager.fillReport(jr, parms, con);
            JasperExportManager.exportReportToHtmlFile(jp, tujuan);
            JasperViewer.viewReport(jp, false);
            
        } catch (Exception xe){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada"+xe);
        }
    }
    
    
     public void cetakTPenjualan(){
        try{
             String sumber;
            String tujuan;
            sumber="src/Report/TransaksiPenjualan.jrxml";
            tujuan="src/Report/TransaksiPenjualan.html";
            Map parms= new HashMap();
            
            JasperReport jr=JasperCompileManager.compileReport(sumber);
            JasperPrint jp=JasperFillManager.fillReport(jr, parms, con);
            JasperExportManager.exportReportToHtmlFile(jp, tujuan);
            JasperViewer.viewReport(jp, false);
            
        } catch (Exception xe){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada"+xe);
        }
    }
     
       public void cetakTPembelian(){
        try{
             String sumber;
            String tujuan;
            sumber="src/Report/TransaksiPembelian.jrxml";
            tujuan="src/Report/TransaksiPembelian.html";
            Map parms= new HashMap();
            
            JasperReport jr=JasperCompileManager.compileReport(sumber);
            JasperPrint jp=JasperFillManager.fillReport(jr, parms, con);
            JasperExportManager.exportReportToHtmlFile(jp, tujuan);
            JasperViewer.viewReport(jp, false);
            
        } catch (Exception xe){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada"+xe);
        }
    }
    
            public void cetakNotaReseller(String nonota){
        try{
             String sumber;
            String tujuan;
            sumber="src/Report/FakturReseller.jrxml";
            tujuan="src/Report/FakturReseller.html";
            Map parms= new HashMap();
            
            parms.put("nomornota", nonota);
            JasperReport jr=JasperCompileManager.compileReport(sumber);
            JasperPrint jp=JasperFillManager.fillReport(jr, parms, con);
            JasperExportManager.exportReportToHtmlFile(jp, tujuan);
            JasperViewer.viewReport(jp, false);
            
        } catch (Exception xe){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada"+xe);
        }
    }

     public void cetakTRPenjualan(){
        try{
             String sumber;
            String tujuan;
            sumber="src/Report/TransaksiPenjualanReseller.jrxml";
            tujuan="src/Report/TransaksiPenjualanReseller.html";
            Map parms= new HashMap();
            
            JasperReport jr=JasperCompileManager.compileReport(sumber);
            JasperPrint jp=JasperFillManager.fillReport(jr, parms, con);
            JasperExportManager.exportReportToHtmlFile(jp, tujuan);
            JasperViewer.viewReport(jp, false);
            
        } catch (Exception xe){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada"+xe);
        }
    }
     
       public void cetakTRPembelian(){
        try{
             String sumber;
            String tujuan;
            sumber="src/Report/Transaksi_PembelianReseller.jrxml";
            tujuan="src/Report/Transaksi_PembelianReseller.html";
            Map parms= new HashMap();
            
            JasperReport jr=JasperCompileManager.compileReport(sumber);
            JasperPrint jp=JasperFillManager.fillReport(jr, parms, con);
            JasperExportManager.exportReportToHtmlFile(jp, tujuan);
            JasperViewer.viewReport(jp, false);
            
        } catch (Exception xe){
            JOptionPane.showMessageDialog(null,"Koneksi Database Gagal, Terjadi Kesalahan Pada"+xe);
        }
    }
}


