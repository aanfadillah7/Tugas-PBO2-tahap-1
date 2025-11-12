/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crud;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.sql.ResultSetMetaData;
/**
 *
 * @author User
 */
    public class crud {
    private Connection Koneksidb;
    private String username="root";
    private String password="";
    private String dbname="db_hukum";
    private String urlKoneksi="jdbc:mysql://localhost/"+dbname;
    public boolean duplikasi=false;
    public String CEK_NAMA_PELANGGAN, CEK_ALAMAT_PELANGGAN, CEK_EMAIL_PELANGGAN, CEK_TELP_PELANGGAN = null;
    public String CEK_NAMA_KONSULTAN, CEK_ALAMAT_KONSULTAN, CEK_EMAIL_KONSULTAN, CEK_TELP_KONSULTAN = null;
    public String CEK_ID_JADWAL, CEK_NAMA_LAYANAN, CEK_NIP_KONSULTAN_LYN = null;
    public String CEK_ID_LAYANAN_PEM, CEK_ID_PELANGGAN_PEM, CEK_NAMA_PEMBAYARAN, CEK_REPORT_PEMBAYARAN = null;

    
    public crud(){
        try {
            Driver dbdriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(dbdriver);
            Koneksidb=DriverManager.getConnection(urlKoneksi,username,password);
            System.out.print("Database Terkoneksi");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.toString());
        }
    }
   
//    CRUD PELANGGAN
    public void simpanPelanggan02(String ID_Pelanggan, String Nama, String Alamat, String Email, String Telp){
        try {
            String sqlsimpan="INSERT INTO Pelanggan (ID_Pelanggan, Nama, Alamat, Email, Telp) VALUES (?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM Pelanggan WHERE ID_Pelanggan = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, ID_Pelanggan);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Pelanggan sudah terdaftar");
                this.duplikasi = true;
                this.CEK_NAMA_PELANGGAN = data.getString("Nama");
                this.CEK_ALAMAT_PELANGGAN = data.getString("Alamat");
                this.CEK_EMAIL_PELANGGAN = data.getString("Email");
                this.CEK_TELP_PELANGGAN = data.getString("Telp");
            } else {
                this.duplikasi = false;
                this.CEK_NAMA_PELANGGAN = null;
                this.CEK_ALAMAT_PELANGGAN = null;
                this.CEK_EMAIL_PELANGGAN = null;
                this.CEK_TELP_PELANGGAN = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, ID_Pelanggan);
                perintah.setString(2, Nama);
                perintah.setString(3, Alamat);
                perintah.setString(4, Email);
                perintah.setString(5, Telp);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data pelanggan berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahPelanggan(String ID_Pelanggan, String Nama, String Alamat, String Email, String Telp){
        try {
            String sqlubah="UPDATE Pelanggan SET Nama = ?, Alamat = ?, Email = ?, Telp = ? WHERE ID_Pelanggan = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, Nama);
            perintah.setString(2, Alamat);
            perintah.setString(3, Email);
            perintah.setString(4, Telp);
            perintah.setString(5, ID_Pelanggan); // ID sebagai parameter terakhir
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data pelanggan berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusPelanggan(String ID_Pelanggan){
        try {
            String sqlhapus="DELETE FROM Pelanggan WHERE ID_Pelanggan = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, ID_Pelanggan);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data pelanggan berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataPelanggan(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID Pelanggan");
            modeltabel.addColumn("Nama");
            modeltabel.addColumn("Alamat");
            modeltabel.addColumn("Email");
            modeltabel.addColumn("Telp");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
            // Sesuai pola, dibiarkan kosong atau tambahkan printStackTrace
            // e.printStackTrace();
        }
    }
    
    //    CRUD KONSULTAN
    public void simpanKonsultan02(String NIP_Konsultan, String Telp, String Email, String Alamat, String Nama){
        try {
            String sqlsimpan="INSERT INTO Konsultan (NIP_Konsultan, Telp, Email, Alamat, Nama) VALUES (?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM Konsultan WHERE NIP_Konsultan = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, NIP_Konsultan);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "NIP Konsultan sudah terdaftar");
                this.duplikasi = true;
                this.CEK_TELP_KONSULTAN = data.getString("Telp");
                this.CEK_EMAIL_KONSULTAN = data.getString("Email");
                this.CEK_ALAMAT_KONSULTAN = data.getString("Alamat");
                this.CEK_NAMA_KONSULTAN = data.getString("Nama");
            } else {
                this.duplikasi = false;
                this.CEK_TELP_KONSULTAN = null;
                this.CEK_EMAIL_KONSULTAN = null;
                this.CEK_ALAMAT_KONSULTAN = null;
                this.CEK_NAMA_KONSULTAN = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, NIP_Konsultan);
                perintah.setString(2, Telp);
                perintah.setString(3, Email);
                perintah.setString(4, Alamat);
                perintah.setString(5, Nama);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data konsultan berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahKonsultan(String NIP_Konsultan, String Telp, String Email, String Alamat, String Nama){
        try {
            String sqlubah="UPDATE Konsultan SET Telp = ?, Email = ?, Alamat = ?, Nama = ? WHERE NIP_Konsultan = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, Telp);
            perintah.setString(2, Email);
            perintah.setString(3, Alamat);
            perintah.setString(4, Nama);
            perintah.setString(5, NIP_Konsultan); // ID sebagai parameter terakhir
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data konsultan berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusKonsultan(String NIP_Konsultan){
        try {
            String sqlhapus="DELETE FROM Konsultan WHERE NIP_Konsultan = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, NIP_Konsultan);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data konsultan berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataKonsultan(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("NIP Konsultan");
            modeltabel.addColumn("Telp");
            modeltabel.addColumn("Email");
            modeltabel.addColumn("Alamat");
            modeltabel.addColumn("Nama");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
    
//    CRUD PURCHASE ORDER
    public void simpanLayanan02(String ID_Layanan, String ID_Jadwal, String Nama, String NIP_konsultan){
        try {
            String sqlsimpan="INSERT INTO Konsultasi_dan_Layanan_Hukum (ID_Layanan, ID_Jadwal, Nama, NIP_konsultan) VALUES (?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM Konsultasi_dan_Layanan_Hukum WHERE ID_Layanan = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, ID_Layanan);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Layanan sudah terdaftar");
                this.duplikasi = true;
                this.CEK_ID_JADWAL = data.getString("ID_Jadwal");
                this.CEK_NAMA_LAYANAN = data.getString("Nama");
                this.CEK_NIP_KONSULTAN_LYN = data.getString("NIP_konsultan");
            } else {
                this.duplikasi = false;
                this.CEK_ID_JADWAL = null;
                this.CEK_NAMA_LAYANAN = null;
                this.CEK_NIP_KONSULTAN_LYN = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, ID_Layanan);
                perintah.setString(2, ID_Jadwal);
                perintah.setString(3, Nama);
                perintah.setString(4, NIP_konsultan);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data layanan berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahLayanan(String ID_Layanan, String ID_Jadwal, String Nama, String NIP_konsultan){
        try {
            String sqlubah="UPDATE Konsultasi_dan_Layanan_Hukum SET ID_Jadwal = ?, Nama = ?, NIP_konsultan = ? WHERE ID_Layanan = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, ID_Jadwal);
            perintah.setString(2, Nama);
            perintah.setString(3, NIP_konsultan);
            perintah.setString(4, ID_Layanan); // ID sebagai parameter terakhir
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data layanan berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusLayanan(String ID_Layanan){
        try {
            String sqlhapus="DELETE FROM Konsultasi_dan_Layanan_Hukum WHERE ID_Layanan = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, ID_Layanan);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data layanan berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataLayanan(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID Layanan");
            modeltabel.addColumn("ID Jadwal");
            modeltabel.addColumn("Nama");
            modeltabel.addColumn("NIP Konsultan");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
    
//    CRUD INVOICE
    public void simpanPembayaran02(String ID_Pembayaran, String ID_Layanan, String ID_Pelanggan, String Nama, String Report_Pembayaran){
        try {
            String sqlsimpan="INSERT INTO Pembayaran (ID_Pembayaran, ID_Layanan, ID_Pelanggan, Nama, Report_Pembayaran) VALUES (?, ?, ?, ?, ?)";
            String sqlcari= "SELECT*FROM Pembayaran WHERE ID_Pembayaran = ?";
            
            PreparedStatement cari = Koneksidb.prepareStatement(sqlcari);
            cari.setString(1, ID_Pembayaran);
            ResultSet data = cari.executeQuery();
            
            if (data.next()){
                JOptionPane.showMessageDialog(null, "ID Pembayaran sudah terdaftar");
                this.duplikasi = true;
                this.CEK_ID_LAYANAN_PEM = data.getString("ID_Layanan");
                this.CEK_ID_PELANGGAN_PEM = data.getString("ID_Pelanggan");
                this.CEK_NAMA_PEMBAYARAN = data.getString("Nama");
                this.CEK_REPORT_PEMBAYARAN = data.getString("Report_Pembayaran");
            } else {
                this.duplikasi = false;
                this.CEK_ID_LAYANAN_PEM = null;
                this.CEK_ID_PELANGGAN_PEM = null;
                this.CEK_NAMA_PEMBAYARAN = null;
                this.CEK_REPORT_PEMBAYARAN = null;
                
                PreparedStatement perintah = Koneksidb.prepareStatement(sqlsimpan);
                perintah.setString(1, ID_Pembayaran);
                perintah.setString(2, ID_Layanan);
                perintah.setString(3, ID_Pelanggan);
                perintah.setString(4, Nama);
                perintah.setString(5, Report_Pembayaran);
                perintah.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data pembayaran berhasil disimpan");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void ubahPembayaran(String ID_Pembayaran, String ID_Layanan, String ID_Pelanggan, String Nama, String Report_Pembayaran){
        try {
            String sqlubah="UPDATE Pembayaran SET ID_Layanan = ?, ID_Pelanggan = ?, Nama = ?, Report_Pembayaran = ? WHERE ID_Pembayaran = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlubah);
            perintah.setString(1, ID_Layanan);
            perintah.setString(2, ID_Pelanggan);
            perintah.setString(3, Nama);
            perintah.setString(4, Report_Pembayaran);
            perintah.setString(5, ID_Pembayaran); // ID sebagai parameter terakhir
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data pembayaran berhasil diubah");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void hapusPembayaran(String ID_Pembayaran){
        try {
            String sqlhapus="DELETE FROM Pembayaran WHERE ID_Pembayaran = ?";
            PreparedStatement perintah = Koneksidb.prepareStatement(sqlhapus);
            perintah.setString(1, ID_Pembayaran);
            perintah.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data pembayaran berhasil dihapus");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void tampilDataPembayaran(JTable komponentabel, String SQL){
        try {
            PreparedStatement perintah = Koneksidb.prepareStatement(SQL);
            ResultSet data = perintah.executeQuery();
            ResultSetMetaData meta = data.getMetaData();
            int jumlahkolom = meta.getColumnCount();
            DefaultTableModel modeltabel = new DefaultTableModel();
            
            modeltabel.addColumn("ID Pembayaran");
            modeltabel.addColumn("ID Layanan");
            modeltabel.addColumn("ID Pelanggan");
            modeltabel.addColumn("Nama");
            modeltabel.addColumn("Report Pembayaran");
            
            while(data.next()){
                Object[] row = new Object[jumlahkolom];
                for(int i=1; i<=jumlahkolom; i++){
                    row[i-1]=data.getObject(i);
                }
                modeltabel.addRow(row);
            }
            komponentabel.setModel(modeltabel);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
    }
    
