package com.example.orderfoods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSingIn,btnSingUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSingIn = (Button)findViewById(R.id.btn_SingIn);
        btnSingUp = (Button)findViewById(R.id.btn_SingUp);

        btnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singIn = new Intent(MainActivity.this, SingIn.class);
                startActivity(singIn);

            }
        });

        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent singUp = new Intent(MainActivity.this, SingUp.class);
                startActivity(singUp);


            }
        });

    }
}
