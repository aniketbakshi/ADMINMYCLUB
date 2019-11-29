package com.example.adminmyclub.Model;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.adminmyclub.AuthPresenter.UserPassInterface;
import com.example.adminmyclub.Beans.UserPassBeans;
import com.example.adminmyclub.UI.Login_frag;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginEvent implements UserPassInterface {

    private FirebaseAuth auth;
    private Login_frag loginFrag;

    public LoginEvent(Login_frag loginFrag) {
        this.loginFrag = loginFrag;
    }

    @Override
    public void loginWithEmail(UserPassBeans userPassBeans, View view) {
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(userPassBeans.getUname(), userPassBeans.getPass())
        .addOnCompleteListener(loginFrag.getActivity(), task -> {
            if (task.isSuccessful()) {
                Log.w("LoginEvent status: ", "Success");
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Admins").document(auth.getUid())
                        .get().addOnSuccessListener(documentSnapshot -> {
                            String s = (String) documentSnapshot.getData().get("club");
                            boolean b = (boolean) documentSnapshot.getData().get("isAdmin");
                            Log.w("LoginEvent status: ", s + b);
                            if (b)
                                loginFrag.onConnectionResult(s);
                });
            } else {
                Toast.makeText(loginFrag.getContext(), "Failed to connect", Toast.LENGTH_SHORT).show();
                Log.w("LoginEvent status: ", "Failed");
            }
        });
    }
}
