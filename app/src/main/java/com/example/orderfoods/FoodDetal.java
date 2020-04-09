package com.example.orderfoods;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Script;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.orderfoods.Model.Food;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class FoodDetal extends AppCompatActivity {

    TextView food_names,food_price, food_description;
    ImageView food_images;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btnCard;
    ElegantNumberButton numberButton;
    String foodId="";

    FirebaseDatabase database;
    DatabaseReference foods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detal);
        //init firebase
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Food");
        //init View
        numberButton = (ElegantNumberButton)findViewById(R.id.number_button);
        btnCard = (FloatingActionButton)findViewById(R.id.btn_cart);
        food_description = (TextView)findViewById(R.id.food_description);
        food_names = (TextView)findViewById(R.id.food_names);
        food_price = (TextView)findViewById(R.id.food_price);
        food_images = (ImageView)findViewById(R.id.img_food);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);

        if(getIntent() != null)
            foodId = getIntent().getStringExtra("FoodId");
        if(!foodId.isEmpty() && foodId != null){
            getDetailFoodId(foodId);
        }
    }

    private void getDetailFoodId(String foodId) {
        foods.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Food food = dataSnapshot.getValue(Food.class);
                //set image
                Picasso.get().load(food.getImage()).into(food_images);
                collapsingToolbarLayout.setTitle(food.getName());
                //  Toast.makeText(FoodDetal.this, "ccc"+food.getName(), Toast.LENGTH_SHORT).show();
                food_price.setText(food.getPrice());
                food_names.setText(food.getName());
                food_description.setText(food.getDescription());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
