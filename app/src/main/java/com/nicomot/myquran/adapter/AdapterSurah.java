package com.nicomot.myquran.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicomot.myquran.R;
import com.nicomot.myquran.model.ModelSurat;

import java.util.List;

public class AdapterSurah extends RecyclerView.Adapter<AdapterSurah.ViewHolder> {

    List<ModelSurat> listSurat;

    AdapterSurah.SurahAdapterInterface adapterInterfaceListener;

    public AdapterSurah(List<ModelSurat> listSurat, SurahAdapterInterface adapterInterfaceListener) {
        this.listSurat = listSurat;
        this.adapterInterfaceListener = adapterInterfaceListener;
    }

    public interface SurahAdapterInterface{
        void clickSurahListener(int positionOfSurah);
    }
    @Override
    public AdapterSurah.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout_surah,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSurah.ViewHolder holder, int position) {
        holder.textViewNomor.setText(String.valueOf(listSurat.get(position).getNomor()));
        holder.textViewNamaSuratArab.setText(listSurat.get(position).getNama());
        String tempatTurun = listSurat.get(position).getTempatTurun();
        String jumlahAyat = String.valueOf(listSurat.get(position).getJumlahAyat());
        holder.textViewTempatTurunDanJumlahAyat.setText(tempatTurun + "*" + jumlahAyat);
        holder.textViewNamaSurat.setText(listSurat.get(position).getNamaLatin());;

    }

    @Override
    public int getItemCount() {
        return listSurat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewNomor , textViewNamaSurat , textViewTempatTurunDanJumlahAyat , textViewNamaSuratArab;
        Button btnShare , btnPlay , btnBookmark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomor = itemView.findViewById(R.id.id_tv_nomor);
            textViewNamaSurat = itemView.findViewById(R.id.id_tv_nama_surat);
            textViewTempatTurunDanJumlahAyat = itemView.findViewById(R.id.id_tv_tempat_turun_jumlah_ayat);
            textViewNamaSuratArab = itemView.findViewById(R.id.id_tv_nama);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            adapterInterfaceListener.clickSurahListener(getAdapterPosition());
        }
    }
}
