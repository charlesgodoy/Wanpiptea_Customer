package com.burning_glow.wanpipteacustomer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.burning_glow.wanpipteacustomer.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.Collections;


public class LoginScreen extends AppCompatActivity {

    private LoginButton btnLogin;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        btnLogin = findViewById(R.id.bt_fb_login);

        btnLogin.setPermissions(Arrays.asList("email"));
        mCallbackManager = CallbackManager.Factory.create();

        registerCallback();

    }

    private void registerCallback() {
        btnLogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserProfile(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("onError", error.toString());
            }
        });
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, (object, response) -> {
            Log.d("response", object.toString());
            try {
                String first_name = object.getString("first_name");
                String last_name = object.getString("last_name");
                String full_name = first_name + " " + last_name;
                Log.d("full name", full_name);

                if (response != null){
                    startActivity(new Intent(LoginScreen.this, WelcomeScreen.class));
                    finish();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Bundle param = new Bundle();
        param.putString("fields", "first_name,last_name");
        request.setParameters(param);
        request.executeAsync();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}