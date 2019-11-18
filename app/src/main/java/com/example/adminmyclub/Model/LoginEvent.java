package com.example.adminmyclub.Model;

import android.util.Log;
import android.view.View;

import com.example.adminmyclub.AuthPresenter.UserPassInterface;
import com.example.adminmyclub.Beans.UserPassBeans;
import com.example.adminmyclub.UI.Login_frag;
import com.google.firebase.auth.FirebaseAuth;

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
                //TODO(Update UI)
                loginFrag.onConnectionResult(true);
            } else {
                Log.w("LoginEvent status: ", "Failed");
            }
        });
    }
}
