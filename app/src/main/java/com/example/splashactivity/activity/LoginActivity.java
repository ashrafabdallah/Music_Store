package com.example.splashactivity.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.splashactivity.R;
import com.example.splashactivity.fragment.LoginFragment;
import com.example.splashactivity.fragment.RegisterFragment;

public class LoginActivity extends AppCompatActivity {
private MaterialRippleLayout btnLogin,btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.ripple_login);
       btnRegister=findViewById(R.id.ripple_register);
       getSupportFragmentManager().beginTransaction().replace(R.id.container,new LoginFragment()).commit();
       // button login
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getSupportFragmentManager().beginTransaction().replace(R.id.container,new LoginFragment()).commit();

           }
       });
        // button register
       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getSupportFragmentManager().beginTransaction().replace(R.id.container,new RegisterFragment()).commit();

           }
       });
    }


}
