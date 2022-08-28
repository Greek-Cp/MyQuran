package com.nicomot.myquran.model;

import com.google.gson.annotations.SerializedName;

public class ModelAyat {
    @SerializedName("id")
    private int idAyat;
    @SerializedName("surah")
    private int idSurah;
    @SerializedName("nomor")
    private int nomorAyat;
    @SerializedName("ar")
    private String hurufArabText;
    @SerializedName("tr")
    private String hurufTranslateText;
    @SerializedName("idn")
    private String hurufIndonesia;

    public int getIdAyat() {
        return idAyat;
    }

    public int getIdSurah() {
        return idSurah;
    }

    public int getNomorAyat() {
        return nomorAyat;
    }

    public String getHurufArabText() {
        return hurufArabText;
    }

    public String getHurufTranslateText() {
        return hurufTranslateText;
    }

    public String getHurufIndonesia() {
        return hurufIndonesia;
    }
}
