package com.example.splashactivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splashactivity.R;
import com.example.splashactivity.activity.PlayActivity;
import com.example.splashactivity.model.Music;
import com.example.splashactivity.service.MyMusicService;
import com.example.splashactivity.util.SharedUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicCard> {
    private final Context context;
    private List<Music> list;

    public MusicAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public MusicCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MusicCard(LayoutInflater.from(context).inflate(R.layout.music_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MusicCard holder, int position) {
        Music music = list.get(position);
        holder.titleView.setText(music.getName());
        holder.signerView.setText(music.getSinger());
        Picasso.get().load(music.getImg_url()).into(holder.posterView);
        String playingSong = SharedUtil.getPlayingSong(context);
        Log.i("onBindViewHolder: ",music.getName().equals(playingSong)+"");
        if(music.getName().equals(playingSong)){
            holder.isPlaying.setVisibility(View.VISIBLE);
            holder.playView.setImageResource(R.drawable.ic_stop_home);

        }else{
            holder.isPlaying.setVisibility(View.GONE);
            holder.playView.setImageResource(R.drawable.ic_play);


        }


    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public void setData(List<Music> list) {
        this.list = list;
    }

    class MusicCard extends RecyclerView.ViewHolder{
        ImageView posterView,playView;
        TextView titleView,signerView,isPlaying;

        public MusicCard(View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.textView3);
            posterView = itemView.findViewById(R.id.imageView2);
            signerView = itemView.findViewById(R.id.textView4);
            playView = itemView.findViewById(R.id.imageView3);
            isPlaying = itemView.findViewById(R.id.textView5);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open new activity
                    Intent i = new Intent(context, PlayActivity.class);
                    i.putExtra("music",list.get(getAdapterPosition()));
                    context.startActivity(i);
                }
            });
            playView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, MyMusicService.class);
                    Music music = list.get(getAdapterPosition());
                    if(!SharedUtil.getInShared(context)) {
                        i.putExtra("btn", "play");
                        i.putExtra("music", music.getSound_url());
                        context.startService(i);
                        SharedUtil.saveInShared(context,true);
                        SharedUtil.saveSongShared(context,music.getName());
                        playView.setImageResource(R.drawable.ic_stop_home);
                        isPlaying.setVisibility(View.VISIBLE);

                    }else{
                        context.stopService(i);
                        SharedUtil.saveInShared(context,false);
                        SharedUtil.saveSongShared(context,"");
                        playView.setImageResource(R.drawable.ic_play);
                        isPlaying.setVisibility(View.GONE);
                    }

                }
            });
        }
    }
}

