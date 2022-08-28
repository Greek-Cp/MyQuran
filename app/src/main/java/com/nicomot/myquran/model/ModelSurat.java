package com.nicomot.myquran.model;

import com.google.gson.annotations.SerializedName;

public class ModelSurat {
    @SerializedName("nomor")
    private int nomor;
    @SerializedName("nama")
    private String nama;
    @SerializedName("nama_latin")
    private String namaLatin;
    @SerializedName("jumlah_ayat")
    private int jumlahAyat;
    @SerializedName("tempat_turun")
    private String tempatTurun;
    @SerializedName("arti")
    private String arti;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("audio")
    private String audioUri;

    public int getNomor() {
        return nomor;
    }

    public String getNama() {
        return nama;
    }

    public String getNamaLatin() {
        return namaLatin;
    }

    public int getJumlahAyat() {
        return jumlahAyat;
    }

    public String getTempatTurun() {
        return tempatTurun;
    }

    public String getArti() {
        return arti;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getAudioUri() {
        return audioUri;
    }
}
