package com.example.splashactivity.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.splashactivity.R;
import com.example.splashactivity.activity.Home_Activity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
private MaterialRippleLayout btnLogin;
private EditText email_filed,password_filled;
private FirebaseAuth mAuth;
private ProgressBar progressBar;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth=FirebaseAuth.getInstance();
       View v= inflater.inflate(R.layout.fragment_login, container, false);
       btnLogin=v.findViewById(R.id.ripple_sign_in);
       email_filed=v.findViewById(R.id.editText);
       password_filled=v.findViewById(R.id.editText2);
       progressBar=v.findViewById(R.id.progressBar);
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email=email_filed.getText().toString().trim();
               String password=password_filled.getText().toString().trim();
               if(!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches()&&!TextUtils.isEmpty(password))
               {
                   progressBar.setVisibility(View.VISIBLE);
                   loginuser(email,password);
               }
               else {
                   email_filed.setError("Please enter email");
                   email_filed.requestFocus();
                   password_filled.setError("Please enter password");
                   password_filled.requestFocus();
               }




           }
       });
       return v;
    }

    private void loginuser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                progressBar.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getActivity(), Home_Activity.class));

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
