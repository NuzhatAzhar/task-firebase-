package com.example.task;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText login_email;
    EditText login_pass;
    Button login_btn;
    FirebaseAuth auth;
   FirebaseDatabase firebaseDatabase;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        if(user!=null){
            startActivity(new Intent(this,MainActivity.class));
        }


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=login_email.getText().toString();
                String pass=login_pass.getText().toString();

                authUser(email,pass);


            }
        });

    }

    private void authUser(final String email, final String pass) {
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(MainActivity.this, "Signup", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




    private void init() {
        login_email=findViewById(R.id.logEmail);
        login_pass=findViewById(R.id.logpass);
        login_btn=findViewById(R.id.log);
       firebaseDatabase=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();

    }

    public void signUp(View view) {

        startActivity(new Intent(MainActivity.this,Sigup.class));
    }
}
