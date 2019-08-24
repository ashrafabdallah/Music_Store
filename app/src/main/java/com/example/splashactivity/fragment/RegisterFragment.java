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
public class RegisterFragment extends Fragment {
    private MaterialRippleLayout btnregister;
    private EditText email_filed,password_filled,password_filled2;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth=FirebaseAuth.getInstance();

        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_register, container, false);
        btnregister=v.findViewById(R.id.ripple_sign_up);
        email_filed=v.findViewById(R.id.editText);
        password_filled=v.findViewById(R.id.editText2);
        password_filled2=v.findViewById(R.id.editText3);
        progressBar=v.findViewById(R.id.progressBar);
btnregister.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String email=email_filed.getText().toString().trim();
        String password=password_filled.getText().toString().trim();
        String password2=password_filled2.getText().toString().trim();
        if(!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches()&&!TextUtils.isEmpty(password)&&password.equals(password2))
        {
            progressBar.setVisibility(View.VISIBLE);
            createuser(email,password);
        }
        else {
            email_filed.setError("Please enter email");
            email_filed.requestFocus();
            password_filled.setError("Please enter password");
            password_filled.requestFocus();
            password_filled2.setError("Please enter password2");
            password_filled2.requestFocus();
        }
    }
});
       return v;
    }

    private void createuser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
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
