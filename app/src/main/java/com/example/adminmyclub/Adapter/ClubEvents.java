package com.example.adminmyclub.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmyclub.R;

public class ClubEvents extends RecyclerView.ViewHolder {
    TextView subtitle;
    TextView title;
    public ClubEvents(@NonNull View itemView) {
        super(itemView);
        this.subtitle = itemView.findViewById(R.id.tv_subtitle);
        this.title = itemView.findViewById(R.id.tv_title);
    }
}
