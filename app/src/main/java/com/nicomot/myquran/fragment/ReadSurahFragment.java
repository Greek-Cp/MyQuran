package com.nicomot.myquran.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicomot.myquran.R;
import com.nicomot.myquran.adapter.AdapterAyat;
import com.nicomot.myquran.apipoint.JsonEquranIdApiInterface;
import com.nicomot.myquran.model.ModelDetailSurah;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadSurahFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadSurahFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Retrofit mRetrofit;
    private JsonEquranIdApiInterface mJsonEquranApiInterface;
    private TextView textViewNamaSurat , textViewNamaSuratCard
            , textViewNamaSuratMain , textViewTempatTurun;
    private RecyclerView recViewAyat;

    private ImageView backBtn;
    private void retrofitInitializer(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://equran.id/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mJsonEquranApiInterface = mRetrofit.create(JsonEquranIdApiInterface.class);
    }
    public ReadSurahFragment() {
        // Required empty public constructor
    }
    MediaPlayer mediaPlayer = new MediaPlayer();
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backBtn = view.findViewById(R.id.id_btn_back);
        this.textViewNamaSurat = view.findViewById(R.id.id_tv_nama_surah_card);
        this.textViewNamaSuratCard = view.findViewById(R.id.id_tv_nama_surah_card);
        this.textViewNamaSuratMain = view.findViewById(R.id.id_tv_nama_surah_main);
        this.textViewTempatTurun = view.findViewById(R.id.id_tv_tempat_turun_jumlah_ayat_card);
        this.recViewAyat = view.findViewById(R.id.id_rec_read_surah);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_layout_base,new HomeFragment()).commit();
                if(mediaPlayer != null){
                    mediaPlayer.reset();
                }
            }
        });
        retrofitInitializer();

        Call<ModelDetailSurah> listModelDetailSurahCall = mJsonEquranApiInterface.getDetailSurat(nomorSurah);
       listModelDetailSurahCall.enqueue(new Callback<ModelDetailSurah>() {
           @Override
           public void onResponse(Call<ModelDetailSurah> call, Response<ModelDetailSurah> response) {
               System.out.println(response.body() + "SIZE");
               textViewNamaSuratMain.setText(response.body().getNamaLatin());
               textViewNamaSurat.setText(response.body().getNama());
               textViewNamaSuratCard.setText(response.body().getNama());;
               textViewTempatTurun.setText(response.body().getTempatTurun() + " | " + String.valueOf(response.body().getJumlahAyat()));
               AdapterAyat.AdapterAyatInterface adapterAyatInterface = new AdapterAyat.AdapterAyatInterface() {
                   @Override
                   public void putarAyat(int positionOfAyat) {
                       String audioUrl = response.body().getAudioUri();

                       if(mediaPlayer.isPlaying()){
                            mediaPlayer.reset();
                       }

                       try {
                           mediaPlayer.setDataSource(audioUrl);
                           mediaPlayer.prepare();
                           mediaPlayer.start();

                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }

                   @Override
                   public void shareAyat(int positionOfAyat) {

                   }

                   @Override
                   public void bookmarkAyat(int positionOfAyat) {

                   }
               };
               AdapterAyat adapterAyat = new AdapterAyat(response.body().getListAyat(),adapterAyatInterface);
               recViewAyat.setAdapter(adapterAyat);
           }

           @Override
           public void onFailure(Call<ModelDetailSurah> call, Throwable t) {

           }
       });
    }

    private int nomorSurah;

    public ReadSurahFragment(int nomorSurah) {
        this.nomorSurah = nomorSurah;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReadSurahFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReadSurahFragment newInstance(String param1, String param2) {
        ReadSurahFragment fragment = new ReadSurahFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_read_surah, container, false);
    }
}