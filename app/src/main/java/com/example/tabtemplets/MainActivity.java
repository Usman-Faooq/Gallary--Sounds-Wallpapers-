package com.example.tabtemplets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tabtemplets.Adapters.FragmentsSlidingAdapter;
import com.example.tabtemplets.RegistrationForm.RegistrationForm;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button loginbtn, regbtn;
    EditText useremail, userpassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        loginbtn = findViewById(R.id.loginbtn);
        regbtn = findViewById(R.id.regbtn);
        useremail = findViewById(R.id.user_email);
        userpassword = findViewById(R.id.user_password);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegistrationForm.class);
                startActivity(i);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = useremail.getText().toString().trim();
                String pass = userpassword.getText().toString().trim();

                if (email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(MainActivity.this, "All Fields Are Requried", Toast.LENGTH_SHORT).show();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(MainActivity.this, "Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                }else if (pass.length() < 6){
                    Toast.makeText(MainActivity.this, "At Least 6 Charater Password", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    useremail.setEnabled(false);
                    userpassword.setEnabled(false);
                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.INVISIBLE);
                                Intent i = new Intent(MainActivity.this, TabsActivity.class);
                                startActivity(i);
                                useremail.setEnabled(true);
                                userpassword.setEnabled(true);
                                useremail.setText("");
                                userpassword.setText("");
                            }else{
                                Toast.makeText(MainActivity.this, "Fail to Login!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.INVISIBLE);
                                useremail.setEnabled(true);
                                userpassword.setEnabled(true);
                            }
                        }
                    });
                }

            }
        });


    }
}