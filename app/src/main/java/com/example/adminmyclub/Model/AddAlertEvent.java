package com.example.adminmyclub.Model;

import android.util.Log;

import com.example.adminmyclub.Activity.AdminMain;
import com.example.adminmyclub.AuthPresenter.AlertInterface;
import com.example.adminmyclub.Beans.NotificationBeans;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddAlertEvent implements AlertInterface {

    private AdminMain adminMain;
    public AddAlertEvent (AdminMain adminMain) {
        this.adminMain = adminMain;
    }

    @Override
    public void addAlertToClub(NotificationBeans beans, String club) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Clubs").document(club)
                .collection("Notifications")
                .document()
                .set(beans).addOnSuccessListener(adminMain, ref -> {
                Log.w("AddAlertEvent", "Ref " + ref);
        });
    }
}
