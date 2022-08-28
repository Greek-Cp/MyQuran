package com.nicomot.myquran.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicomot.myquran.R;
import com.nicomot.myquran.model.ModelAyat;
import com.nicomot.myquran.model.ModelDetailSurah;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterAyat extends RecyclerView.Adapter<AdapterAyat.ViewHolder> {

    List<ModelAyat> detailSurahList;
    AdapterAyat.AdapterAyatInterface adapterAyatInterface;

    public AdapterAyat(List<ModelAyat> detailSurahList, AdapterAyatInterface adapterAyatInterface) {
        this.detailSurahList = detailSurahList;
        this.adapterAyatInterface = adapterAyatInterface;
    }

    public interface AdapterAyatInterface{
        void putarAyat(int positionOfAyat);
        void shareAyat(int positionOfAyat);
        void bookmarkAyat(int positionOfAyat);
    }
    @Override
    public AdapterAyat.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout_surah_read,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAyat.ViewHolder holder, int position) {
        holder.textViewAyat.setText(detailSurahList.get(position).getHurufArabText());
        holder.textViewArti.setText(detailSurahList.get(position).getHurufIndonesia());
        holder.textViewNomerAyat.setText(String.valueOf(detailSurahList.get(position).getNomorAyat()));
        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterAyatInterface.putarAyat(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return detailSurahList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewNomerAyat , textViewAyat , textViewArti;
        ImageView btnShare , btnPlay , btnBookmark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomerAyat = itemView.findViewById(R.id.id_tv_nomer_ayat);
            textViewAyat = itemView.findViewById(R.id.id_tv_ayat_read_surah);
            textViewArti = itemView.findViewById(R.id.id_tv_arti_id);
            btnPlay = itemView.findViewById(R.id.id_btn_play);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
