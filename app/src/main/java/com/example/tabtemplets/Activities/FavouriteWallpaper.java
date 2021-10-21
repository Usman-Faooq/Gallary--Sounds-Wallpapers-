package com.example.tabtemplets.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tabtemplets.Adapters.FavouriteWallpaperAdapter;
import com.example.tabtemplets.Adapters.WallpaperAdapter;
import com.example.tabtemplets.DataVeriables.FirebaseDataVeriables;
import com.example.tabtemplets.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FavouriteWallpaper extends AppCompatActivity {

    RecyclerView recyclerView;
    FavouriteWallpaperAdapter adapter;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_wallpaper);

        recyclerView = findViewById(R.id.fav_wallpaper);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuser = user.getUid();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users").child(currentuser).child("WallpaperFavourite");

        FirebaseRecyclerOptions<FirebaseDataVeriables> options =
                new FirebaseRecyclerOptions.Builder<FirebaseDataVeriables>()
                        .setQuery(reference, FirebaseDataVeriables.class).build();

        adapter = new FavouriteWallpaperAdapter(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}