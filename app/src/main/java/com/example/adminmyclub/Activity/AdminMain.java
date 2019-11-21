package com.example.adminmyclub.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmyclub.Adapter.CustomAdapter;
import com.example.adminmyclub.R;

import java.util.ArrayList;

public class AdminMain extends AppCompatActivity {

    RecyclerView eventView;
    LinearLayoutManager manager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_mainpage);

        eventView = findViewById(R.id.recycler_view);
        manager = new LinearLayoutManager(this);
        eventView.setLayoutManager(manager);
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Pos 1");
        strings.add("Pos 2");
        strings.add("Pos 3");
        strings.add("Pos 4");
        strings.add("Pos 5");
        CustomAdapter adapter = new CustomAdapter(strings);
        eventView.setAdapter(adapter);
    }
}
