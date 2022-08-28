package com.nicomot.myquran.apipoint;

import com.nicomot.myquran.model.ModelDetailSurah;
import com.nicomot.myquran.model.ModelSurat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonEquranIdApiInterface {

    @GET("surat")
    Call<List<ModelSurat>> getModelSurat();

    @GET("surat/{nomorsurat}")
    Call<ModelDetailSurah> getDetailSurat(@Path("nomorsurat") int nomorSurat);

}
