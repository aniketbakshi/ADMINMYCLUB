package com.example.adminmyclub.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmyclub.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<ClubEvents> {

    private ArrayList<String> list;

    public CustomAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ClubEvents onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_page, parent, false);
        return new ClubEvents(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubEvents holder, int position) {
        holder.subtitle.setText("Sample text " + position + " " + list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
