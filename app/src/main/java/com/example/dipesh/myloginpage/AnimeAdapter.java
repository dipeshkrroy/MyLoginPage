package com.example.dipesh.myloginpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by dipesh on 16-12-2017.
 */

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.MyViewHolder> {
    List<Animes> animeList;
    Context mcontex;

    public AnimeAdapter(List<Animes> animeList, Context mcontex) {
        this.animeList = animeList;
        this.mcontex = mcontex;
    }

    @Override
    public AnimeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontex);
        View view=inflater.inflate(R.layout.customadapeter,null);
        return new MyViewHolder(view,mcontex);
    }

    @Override
    public void onBindViewHolder(AnimeAdapter.MyViewHolder holder, int position) {
        Animes item=animeList.get(position);
        holder.title.setText(item.getAnimeName());
        holder.episode.setText("Episode: "+item.getNoofepisode());
        holder.year.setText("Year: "+item.getAnimeyear());
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,episode,year;
        Context ctx;
        public MyViewHolder(View itemView , final Context ctx) {
            super(itemView);
            this.ctx=ctx;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Animes anime= animeList.get(position);
                    Toast.makeText(ctx, anime.getAnimeName(), Toast.LENGTH_SHORT).show();
                }
            });
            title=(TextView) itemView.findViewById(R.id.postertitle);
            episode=(TextView) itemView.findViewById(R.id.posterepisode);
            year=(TextView) itemView.findViewById(R.id.posteryear);
        }

    }
}
