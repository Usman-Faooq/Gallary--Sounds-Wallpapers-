package com.example.tabtemplets.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tabtemplets.DataVeriables.FirebaseDataVeriables;
import com.example.tabtemplets.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FavouriteWallpaperAdapter extends FirebaseRecyclerAdapter<FirebaseDataVeriables, FavouriteWallpaperAdapter.holder> {

    public FavouriteWallpaperAdapter(@NonNull FirebaseRecyclerOptions<FirebaseDataVeriables> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull FirebaseDataVeriables model) {
        Glide.with(holder.image.getContext()).load(model.getURL()).into(holder.image);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpapercard,parent,false);
        return new holder(view);
    }

    public class holder extends RecyclerView.ViewHolder{
        ImageView image, favbtn;
        public holder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.posted_image);
            favbtn = itemView.findViewById(R.id.like_unlike);
            favbtn.setVisibility(View.GONE);
        }
    }
}
