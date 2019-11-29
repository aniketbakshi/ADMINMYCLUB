package com.example.adminmyclub.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.adminmyclub.Activity.AdminMain;
import com.example.adminmyclub.AuthView.UserPassInterface;
import com.example.adminmyclub.Beans.UserPassBeans;
import com.example.adminmyclub.Model.LoginEvent;
import com.example.adminmyclub.R;

public class Login_frag extends Fragment implements UserPassInterface {
    private View view;
    private EditText emailid, password;
    private Button loginButton;
    private CheckBox show_hide_password;
    public static final String regEx = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.adminlogin, container, false);
        emailid = (EditText) view.findViewById(R.id.login_emailid);
        password = (EditText) view.findViewById(R.id.login_password);
        loginButton = (Button) view.findViewById(R.id.loginBtn);
        show_hide_password = (CheckBox) view.findViewById(R.id.show_hide_password);
        show_hide_password
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
        loginButton.setOnClickListener(view -> checkValidation());

        return view;
    }
    private void checkValidation() {
        // Get email id and password
        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {
            Toast.makeText(getActivity(), "Enter both credentials.", Toast.LENGTH_SHORT).show();
        }
        // Check if email id is valid or not
        else if (!m.find())
            Toast.makeText(view.getContext(), "Your Email Id is Invalid.", Toast.LENGTH_SHORT).show();
            // Else do login and do your stuff
        else {
            //Toast.makeText(getActivity(), "Do Login.", Toast.LENGTH_SHORT).show();
            LoginEvent event = new LoginEvent(this);
            UserPassBeans beans = new UserPassBeans(getEmailId, getPassword);
            event.loginWithEmail(beans, view);
        }

    }

    @Override
    public void onConnectionResult(String club) {
        Toast.makeText(this.getContext(), "Login success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this.getContext(), AdminMain.class);
        intent.putExtra("club", club);
        startActivity(intent);
    }
}
