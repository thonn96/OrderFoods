package com.example.orderfoods;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfoods.Interface.ItemClickListener;
import com.example.orderfoods.Model.Category;
import com.example.orderfoods.Model.Food;
import com.example.orderfoods.ViewHolder.FoodViewHolder;
import com.example.orderfoods.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference foodList;

    RecyclerView recycler_food;
    RecyclerView.LayoutManager layoutManager;
    String catogeryId = "";
    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Food");

        recycler_food = (RecyclerView)findViewById(R.id.recyclerview_food);
        recycler_food.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_food.setLayoutManager(layoutManager);
        if(getIntent() != null)
            catogeryId = getIntent().getStringExtra("CatogeryId");
        if(!catogeryId.isEmpty() && catogeryId != null){
            loadListFood(catogeryId);
        }


    }

    private void loadListFood(String catogeryId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.menu_item, FoodViewHolder.class, foodList.orderByChild("MenuId").equalTo(catogeryId))  {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {
                foodViewHolder.txt_food_name.setText(food.getName());
                Picasso.get().load(food.getImage()).into(foodViewHolder.img_food_image);


                Food local = food;

                foodViewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                     //   Toast.makeText(FoodList.this, "zzzzzzzzzzzzzz", Toast.LENGTH_SHORT).show();
                        //start Activity
                        Intent foodDetail = new Intent(FoodList.this,FoodDetal.class);
                        foodDetail.putExtra("FoodId",adapter.getRef(position).getKey());
                        startActivity(foodDetail);
                    }
                });

            }
        };
        recycler_food.setAdapter(adapter);

    }
}
