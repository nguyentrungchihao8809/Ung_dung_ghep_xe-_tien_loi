package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "taikhoan")
public class Taikhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String SoDienThoai;
    private String Name;
    private String CanCuocCongDan;
    private String GioiTinh;
    private String Bienso;
    private String TrangThai;  

    // Constructor mặc định
    public Taikhoan() {}

    // Constructor có tham số
    public Taikhoan(Long id, String SoDienThoai, String Name, String CanCuocCongDan, String GioiTinh, String Bienso, String TrangThai) {
        this.id = id;
        this.SoDienThoai = SoDienThoai;
        this.Name = Name;
        this.CanCuocCongDan = CanCuocCongDan;
        this.GioiTinh = GioiTinh;
        this.Bienso = Bienso;
        this.TrangThai = TrangThai;

    }

    // Getter và Setter cho id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter và Setter cho username
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

     public String getSoDienThoai() {
        return SoDienThoai;
    }
    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }
    
      public String getCanCuocCongDan() {
        return CanCuocCongDan;
    }
    public void setCanCuocCongDan(String CanCuocCongDan) {
        this.CanCuocCongDan = CanCuocCongDan;
    }
     public String getBienso() {
        return Bienso;
    }
    public void setBienso(String Bienso) {
        this.Bienso = Bienso;
    }
    
      public String getGioiTinh() {
        return GioiTinh;
    }
     public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }
     public String getTrangThai() {
        return TrangThai;
    }
     public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }
}