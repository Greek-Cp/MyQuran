package com.nicomot.myquran.adapter;

import android.animation.ObjectAnimator;
import android.os.Handler;
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
        holder.textViewLatin.setText(detailSurahList.get(position).getHurufTranslateText());
        holder.textViewNomerAyat.setText(String.valueOf(detailSurahList.get(position).getNomorAyat()));
        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterAyatInterface.putarAyat(holder.getAdapterPosition());
            }
        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterAyatInterface.shareAyat(holder.getAdapterPosition());
                ObjectAnimator objectAnimatorShrinkX = ObjectAnimator.ofFloat(holder.btnShare,"scaleX",1.5f).setDuration(1000);
                ObjectAnimator objectAnimatorShrinkY = ObjectAnimator.ofFloat(holder.btnShare,"scaleY",1.5f)
                        .setDuration(1000);
                objectAnimatorShrinkX.start();
                objectAnimatorShrinkY.start();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        objectAnimatorShrinkX.setFloatValues(1.0f);
                        objectAnimatorShrinkX.start();
                        objectAnimatorShrinkY.setFloatValues(1.0f);
                        objectAnimatorShrinkY.start();
                    }
                },1000);

            }
        });
    }

    @Override
    public int getItemCount() {
        return detailSurahList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewNomerAyat , textViewAyat , textViewArti , textViewLatin;
        ImageView btnShare , btnPlay , btnBookmark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomerAyat = itemView.findViewById(R.id.id_tv_nomer_ayat);
            textViewAyat = itemView.findViewById(R.id.id_tv_ayat_read_surah);
            textViewArti = itemView.findViewById(R.id.id_tv_arti_id);
            btnPlay = itemView.findViewById(R.id.id_btn_play);
            btnShare = itemView.findViewById(R.id.id_btn_share);
            textViewLatin = itemView.findViewById(R.id.id_tv_arti_latin);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
