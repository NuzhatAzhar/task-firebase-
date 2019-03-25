package com.example.task;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Sigup extends AppCompatActivity {

    EditText signUp_email;
    EditText signUp_name;
    EditText signUp_pass;
    Button signUp_btn;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseUser user;
    DatabaseReference reference;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);

        init();

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=signUp_email.getText().toString();
                String pass=signUp_pass.getText().toString();
                String name=signUp_name.getText().toString();
            //  authUser(nameText,emailText,passText);

                authUser(name,email,pass);

                signUp_email.setText("");
                signUp_name.setText("");
                signUp_pass.setText("");


            }
        });
    }

    private void authUser(final String name, final String email, final String  pass) {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        user=auth.getCurrentUser();
                        String id=user.getUid();
                        saveData(id,email,name,pass);

                }

            }
        });


    }

    private void saveData(String id, String email, String name,String pass) {
        Users user=new Users(name,email,id,pass);
        reference.child(id).setValue(user);
        startActivity(new Intent(this,desboard.class));
    }


    private void init() {
        signUp_name =findViewById(R.id.signName);
        signUp_email =findViewById(R.id.signEmail);
        signUp_pass=findViewById(R.id.signpass);
        signUp_btn=findViewById(R.id.sign);


        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();

        reference=database.getReference("Users");


    }
}
