package com.example.adminmyclub.Model;

import android.util.Log;

import com.example.adminmyclub.Activity.AdminMain;
import com.example.adminmyclub.AuthPresenter.NotificationInterface;
import com.example.adminmyclub.Beans.NotificationBeans;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class GetClubNotifications implements NotificationInterface {

    private AdminMain adminMain;
    private List<NotificationBeans> beansList;

    public GetClubNotifications(AdminMain adminMain) {
        this.adminMain = adminMain;
    }

    @Override
    public void getAlertsFromClub(String club) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Clubs").document(club).collection("Notifications")
                .addSnapshotListener((listener, e) -> {
            List<DocumentSnapshot> list = listener.getDocuments();
            Log.w("notifications:", "" + list.toString());
            beansList = new ArrayList<>();
            for (DocumentSnapshot s : list) {
                NotificationBeans n = s.toObject(NotificationBeans.class);
                Log.w("notifications class:", "" + n.getSubtitle() + n.getTitle());
                beansList.add(n);
            }
            adminMain.setNotificationListener((ArrayList<NotificationBeans>) beansList);
        });
    }
}
