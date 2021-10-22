package com.example.tabtemplets.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tabtemplets.Adapters.MusicAdapter;
import com.example.tabtemplets.DataVeriables.FirebaseDataVeriables;
import com.example.tabtemplets.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class SoundFragment extends Fragment {

    RecyclerView recyclerView;
    MusicAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sound_frag, container, false);

        recyclerView = view.findViewById(R.id.soundrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<FirebaseDataVeriables> options =
                new FirebaseRecyclerOptions.Builder<FirebaseDataVeriables>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("sounds"), FirebaseDataVeriables.class).build();

        adapter = new MusicAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
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