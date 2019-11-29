package com.example.adminmyclub.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmyclub.Adapter.CustomAdapter;
import com.example.adminmyclub.AuthView.SetNotificationInterface;
import com.example.adminmyclub.Beans.NotificationBeans;
import com.example.adminmyclub.Model.AddAlertEvent;
import com.example.adminmyclub.Model.GetClubNotifications;
import com.example.adminmyclub.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AdminMain extends AppCompatActivity implements View.OnClickListener, SetNotificationInterface {

    RecyclerView eventView;
    LinearLayoutManager manager;
    FloatingActionButton fab;
    String club;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_mainpage);

        club = getIntent().getStringExtra("club");
        GetClubNotifications notifications = new GetClubNotifications(this);
        notifications.getAlertsFromClub(club);

        eventView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        manager = new LinearLayoutManager(this);
        eventView.setLayoutManager(manager);
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("Pos 1");
//        strings.add("Pos 2");
//        strings.add("Pos 3");
//        strings.add("Pos 4");
//        strings.add("Pos 5");
//        CustomAdapter adapter = new CustomAdapter(strings);
//        eventView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = getLayoutInflater();
        View alertView = inflater.inflate(R.layout.notification_main, null);
        final EditText title = alertView.findViewById(R.id.et_title);
        final EditText subtitle = alertView.findViewById(R.id.et_subtitle);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Add Alert");
        alert.setView(alertView);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", (dialog, which) -> {
            Toast.makeText(this, "Canceled by user", Toast.LENGTH_LONG).show();
        });
        alert.setPositiveButton("Add", (dialog, which) -> {
            String title_text = title.getText().toString();
            String subtitle_text = subtitle.getText().toString();
            if (!title_text.equals("") && !subtitle_text.equals("")) {
                NotificationBeans beans = new NotificationBeans(title_text, subtitle_text);
                AddAlertEvent event = new AddAlertEvent(this);
                event.addAlertToClub(beans, club);
                // Toast.makeText(AdminMain.this, title_text + subtitle_text, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AdminMain.this, "Field must have values", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @Override
    public void setNotificationListener(ArrayList<NotificationBeans> beansArrayList) {
        CustomAdapter adapter = new CustomAdapter(beansArrayList);
        eventView.setAdapter(adapter);
    }
}
