package com.nicomot.myquran.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nicomot.myquran.R;
import com.nicomot.myquran.adapter.AdapterCategory;
import com.nicomot.myquran.adapter.AdapterSurah;
import com.nicomot.myquran.apipoint.JsonEquranIdApiInterface;
import com.nicomot.myquran.client.RetroFitClient;
import com.nicomot.myquran.model.ModelDetailSurah;
import com.nicomot.myquran.model.ModelSurat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters

    private RetroFitClient mRetrofit;
    private JsonEquranIdApiInterface mJsonEquranApiInterface;
    private ActivityResultLauncher<String[]> mActivityResultLauncher;
    private boolean isPermissionAccessInternetGranted = false;
    private boolean isPermissionAccessNetworkStateGranted = false;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private void permission(){
        mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                if(result.get(Manifest.permission.INTERNET) != null){
                    isPermissionAccessInternetGranted = result.get(Manifest.permission.INTERNET);
                }
                if(result.get(Manifest.permission.ACCESS_NETWORK_STATE) != null){
                    isPermissionAccessNetworkStateGranted = result.get(Manifest.permission.ACCESS_NETWORK_STATE);
                }
            }
        });
        requestPermission();
    }

    private void requestPermission(){
        isPermissionAccessInternetGranted = ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
        isPermissionAccessNetworkStateGranted = ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED;
        List<String> listUngrantedPermission = new ArrayList<>();
        if((!isPermissionAccessInternetGranted)){
            listUngrantedPermission.add(Manifest.permission.INTERNET);
        }
        if(!isPermissionAccessNetworkStateGranted){
            listUngrantedPermission.add(Manifest.permission.ACCESS_NETWORK_STATE);
        }
        mActivityResultLauncher.launch(listUngrantedPermission.toArray(new String[0]));
    }
    RecyclerView recyclerViewSurah , recyclerViewCategory;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        permission();
        retrofitInitializer();
        recyclerViewSurah = view.findViewById(R.id.id_rec_surah);
        recyclerViewCategory = view.findViewById(R.id.id_rec_category);
        List<String> listCategory = new ArrayList<>();
        listCategory.add("Surah");
        AdapterCategory adapterCategory = new AdapterCategory(listCategory);
        recyclerViewCategory.setAdapter(adapterCategory);
        getModelSurat();
    }

    private void retrofitInitializer(){
        mRetrofit = new RetroFitClient("https://equran.id/api/");
        mJsonEquranApiInterface = mRetrofit.getInstance().create(JsonEquranIdApiInterface.class);
    }
    private AdapterSurah.SurahAdapterInterface surahAdapterInterface;
    private void getModelSurat(){
        Call<List<ModelSurat>> getCallModelSurat = mJsonEquranApiInterface.getModelSurat();
        getCallModelSurat.enqueue(new Callback<List<ModelSurat>>() {
            @Override
            public void onResponse(Call<List<ModelSurat>> call, Response<List<ModelSurat>> response) {
                if(!response.isSuccessful()){
                    return;
                }
                List<ModelSurat> listModelSurat = response.body();
                surahAdapterInterface = new AdapterSurah.SurahAdapterInterface() {
                    @Override
                    public void clickSurahListener(int positionOfSurah) {
                        System.out.println("nomor = " + listModelSurat.get(positionOfSurah).getNomor());
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.id_frame_layout_base,new ReadSurahFragment(listModelSurat.get(positionOfSurah).getNomor())).commit();
                    }
                };
                AdapterSurah adapterSurah = new AdapterSurah(listModelSurat,surahAdapterInterface);
                recyclerViewSurah.setAdapter(adapterSurah);

                System.out.println("test size = " + response.body().size());
            }
        //makan dulu bg
            @Override
            public void onFailure(Call<List<ModelSurat>> call, Throwable t) {
                    System.out.println("Error " + t.getMessage());

            }
        });
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}