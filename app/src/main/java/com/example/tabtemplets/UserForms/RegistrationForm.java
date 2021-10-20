package com.example.tabtemplets.UserForms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tabtemplets.R;
import com.example.tabtemplets.DataVeriables.UserData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationForm extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText reg_name, reg_email, reg_password;
    Button new_regbtn;
    ProgressBar progressBar;

    TextView demoui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);

        mAuth = FirebaseAuth.getInstance();
        reg_name = findViewById(R.id.new_reg_name);
        reg_email = findViewById(R.id.new_reg_email);
        reg_password = findViewById(R.id.new_reg_password);
        new_regbtn = findViewById(R.id.new_reg_btn);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        demoui = findViewById(R.id.newuidemo);
        demoui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationForm.this, UpdateForm.class);
                startActivity(i);
            }
        });

        new_regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = reg_name.getText().toString().trim();
                String email = reg_email.getText().toString().trim();
                String pass = reg_password.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(RegistrationForm.this, "All Fields Are Requried", Toast.LENGTH_SHORT).show();
                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(RegistrationForm.this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                }else if (pass.length() < 6){
                    Toast.makeText(RegistrationForm.this, "At Least 6 Charater in Password", Toast.LENGTH_SHORT).show();
                }else{
                    progressBar.setVisibility(View.VISIBLE);
                    reg_name.setEnabled(false);
                    reg_email.setEnabled(false);
                    reg_password.setEnabled(false);
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        UserData data = new UserData(name, email, pass);
                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegistrationForm.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                                                    reg_name.setText("");
                                                    reg_email.setText("");
                                                    reg_password.setText("");
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                } else {
                                                    Toast.makeText(RegistrationForm.this, "Fail To Register", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    reg_name.setEnabled(true);
                                                    reg_email.setEnabled(true);
                                                    reg_password.setEnabled(true);
                                                }
                                            }
                                        });
                                    }else {
                                        Toast.makeText(RegistrationForm.this, "Something Went Worng", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.INVISIBLE);
                                        reg_name.setEnabled(true);
                                        reg_email.setEnabled(true);
                                        reg_password.setEnabled(true);
                                    }
                                }
                            });
                }
            }
        });

    }
}