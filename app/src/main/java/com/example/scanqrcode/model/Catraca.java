package com.example.scanqrcode.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Catraca {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("qrCode")
    @Expose
    private String qrCode;

    @SerializedName("onibus_id")
    @Expose
    private Integer onibus_id;

    @SerializedName("terminal_id")
    @Expose
    private Integer terminal_id;

    @SerializedName("tubo_id")
    @Expose
    private Integer tubo_id;

    public Catraca() {
    }

    public Catraca(Integer id, String qrCode, Integer onibus_id, Integer terminal_id, Integer tubo_id) {
        this.id = id;
        this.qrCode = qrCode;
        this.onibus_id = onibus_id;
        this.terminal_id = terminal_id;
        this.tubo_id = tubo_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public Integer getOnibus_id() {
        return onibus_id;
    }

    public void setOnibus_id(Integer onibus_id) {
        this.onibus_id = onibus_id;
    }

    public Integer getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(Integer terminal_id) {
        this.terminal_id = terminal_id;
    }

    public Integer getTubo_id() {
        return tubo_id;
    }

    public void setTubo_id(Integer tubo_id) {
        this.tubo_id = tubo_id;
    }

    @Override
    public String toString() {
        return "Catraca{" +
                "id=" + id +
                ", qrCode='" + qrCode + '\'' +
                ", onibus_id=" + onibus_id +
                ", terminal_id=" + terminal_id +
                ", tubo_id=" + tubo_id +
                '}';
    }
}
