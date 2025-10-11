package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "thongtinchuyendriver")
public class thongtinchuyendriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddriver;

    private String Name;
    private String Loaixe;
    private String Bienso;
    private String Diemdi;
    private String Diemden;
    private String Thoigiandriver;

    // Constructor mặc định
    public thongtinchuyendriver() {}

    // Constructor có tham số
    public thongtinchuyendriver(Long iddriver, String Name, String Loaixe, String Bienso, 
                                String Diemdi, String Diemden, String Thoigiandriver) {
        this.iddriver = iddriver;
        this.Name = Name;
        this.Loaixe = Loaixe;
        this.Bienso = Bienso;
        this.Diemdi = Diemdi;
        this.Diemden = Diemden;
        this.Thoigiandriver = Thoigiandriver;
    }

    // Getter và Setter cho iddriver
    public Long getIddriver() {
        return iddriver;
    }

    public void setIddriver(Long iddriver) {
        this.iddriver = iddriver;
    }

    // Getter và Setter cho Name
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    // Getter và Setter cho Loaixe
    public String getLoaixe() {
        return Loaixe;
    }

    public void setLoaixe(String Loaixe) {
        this.Loaixe = Loaixe;
    }

    // Getter và Setter cho Bienso
    public String getBienso() {
        return Bienso;
    }

    public void setBienso(String Bienso) {
        this.Bienso = Bienso;
    }

    // Getter và Setter cho Diemdi
    public String getDiemdi() {
        return Diemdi;
    }

    public void setDiemdi(String Diemdi) {
        this.Diemdi = Diemdi;
    }

    // Getter và Setter cho Diemden
    public String getDiemden() {
        return Diemden;
    }

    public void setDiemden(String Diemden) {
        this.Diemden = Diemden;
    }

    // Getter và Setter cho Thoigiandriver
    public String getThoigiandriver() {
        return Thoigiandriver;
    }

    public void setThoigiandriver(String Thoigiandriver) {
        this.Thoigiandriver = Thoigiandriver;
    }
}
