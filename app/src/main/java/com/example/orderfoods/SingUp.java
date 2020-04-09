package com.example.orderfoods;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoods.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SingUp extends AppCompatActivity {
    MaterialEditText edtName,edtPhone,edtPassword;
    Button btn_SingUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        edtName = (MaterialEditText)findViewById(R.id.edtName);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        edtPassword = (MaterialEditText)findViewById(R.id.edtPass);

        btn_SingUp = (Button)findViewById(R.id.btn_SingUp);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");
        btn_SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    /*    final ProgressDialog mDialog = new ProgressDialog(SingUp.this);
                        mDialog.setMessage("please waiting ...");*/

                        //check user exits;
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()){
                          //  mDialog.dismiss();

                        }else{
                           // mDialog.dismiss();
                            User user = new User(edtName.getText().toString(),edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(user);

                           // Toast.makeText(SingUp.this, "Sing up success", Toast.LENGTH_SHORT).show();
                            finish();
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
