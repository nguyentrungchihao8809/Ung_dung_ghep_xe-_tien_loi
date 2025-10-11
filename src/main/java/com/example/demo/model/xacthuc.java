package com.example.demo.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;



@Entity
@Table(name = "xacthuc")
public class xacthuc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sdt;
    private String cccd;
    private String ten;
    private String gioitinh;
    private String bienso;

    public xacthuc() {}
    public xacthuc(Long id, String sdt, String cccd, String ten, String gioitinh, String bienso) {
        this.id = id;
        this.sdt = sdt;
        this.cccd = cccd;
        this.ten = ten;
        this.gioitinh = gioitinh;
        this.bienso = bienso;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    public String getCccd() {
        return cccd;
    }
    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public String getGioitinh() {
        return gioitinh;
    }
    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }
    public String getBienso() {
        return bienso;
    }
    public void setBienso(String bienso) {
        this.bienso = bienso;
    }
}
