package com.uts_33459;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<com.uts_33459.MainModel> mainModels;
    Context context;

    public MainAdapter(Context context,ArrayList<com.uts_33459.MainModel> mainModels) {
        this.context = context;
        this.mainModels = mainModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //set album image
        holder.imageAlbumCover.setImageResource(mainModels.get(position).getAlbumCover());
        //set song title
        holder.textSongTitle.setText(mainModels.get(position).getSongTitle());
        //set song singer
        holder.textSongSinger.setText(mainModels.get(position).getSongSinger());

        //set OnClickListener
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, com.uts_33459.DetailSongActivity.class);
                intent.putExtra("album cover", mainModels.get(position).getAlbumCover());
                intent.putExtra("song title", mainModels.get(position).getSongTitle());
                intent.putExtra("song singer", mainModels.get(position).getSongSinger());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { return mainModels.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAlbumCover;
        TextView textSongTitle;
        TextView textSongSinger;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAlbumCover = itemView.findViewById(R.id.image_albumCover);
            textSongTitle = itemView.findViewById(R.id.text_title);
            textSongSinger = itemView.findViewById(R.id.text_singer);
            linearLayout = itemView.findViewById(R.id.linear_layout);

            textSongTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            textSongTitle.setSelected(true);

        }
    }
}
