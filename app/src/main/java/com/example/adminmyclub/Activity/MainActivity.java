package com.example.adminmyclub.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.adminmyclub.R;
import com.example.adminmyclub.UI.Login_frag;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, new Login_frag())
                    .commit();
        }
    }
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, new Login_frag())
                .commit();
    }
}
