package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "danhgia")
public class danhgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iddanhgia;

    private Long idchuyen;
    private Long nguoigui_id;
    private Long nguoinhan_id;
    private Long sosao;
    private String cmt;
    // Constructor mặc định
    public danhgia() {}
    public danhgia(Long iddanhgia, Long idchuyen, Long nguoigui_id, Long nguoinhan_id, Long sosao, String cmt) {   
        this.iddanhgia = iddanhgia;       
        this.idchuyen = idchuyen;
        this.nguoigui_id = nguoigui_id;       
        this.nguoinhan_id = nguoinhan_id;
        this.sosao = sosao;
        this.cmt = cmt;
    }
    public Long getIddanhgia() {
        return iddanhgia;
    }
    public void setIddanhgia(Long iddanhgia) {
        this.iddanhgia = iddanhgia;
    }
    public Long getIdchuyen() {
        return idchuyen;
    }
    public void setIdchuyen(Long idchuyen) {
        this.idchuyen = idchuyen;
    }
    public Long getNguoigui_id() {
        return nguoigui_id;
    }

    public void setNguoigui_id(Long nguoigui_id) {
        this.nguoigui_id = nguoigui_id;
    }
    public Long getNguoinhan_id() {
        return nguoinhan_id;
    }
    public void setNguoinhan_id(Long nguoinhan_id) {
        this.nguoinhan_id = nguoinhan_id;
    }
    public Long getSosao() {
        return sosao;
    }
    public void setSosao(Long sosao) {
        this.sosao = sosao;
    }
    public String getCmt() {
        return cmt;
    }
    public void setCmt(String cmt) {
        this.cmt = cmt;
    }  
}