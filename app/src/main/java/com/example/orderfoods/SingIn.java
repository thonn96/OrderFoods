package com.example.orderfoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoods.Common.Common;
import com.example.orderfoods.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;


public class SingIn extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btnSingin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPass);

        btnSingin = (Button)findViewById(R.id.btn_SingIn);

        //init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        btnSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //get user infomation
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()){
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);

                            user.setPhone(edtPhone.getText().toString());
                            if(user.getPassword().equals(edtPassword.getText().toString())){
                                //Toast.makeText(SingIn.this, "xxxxxxxxxx", Toast.LENGTH_SHORT).show();
                                {

                                    Intent homeIntent = new Intent(SingIn.this, home.class);
                                    Common.currentUser = user;
                                    startActivity(homeIntent);


                                }
                            }
                            else{
                                Toast.makeText(SingIn.this, "Sing in Fall", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SingIn.this, "User not exits", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

}
