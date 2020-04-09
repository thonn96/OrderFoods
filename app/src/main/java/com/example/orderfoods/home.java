package com.example.orderfoods;

import android.content.Intent;
import android.os.Bundle;

import com.example.orderfoods.Common.Common;
import com.example.orderfoods.Interface.ItemClickListener;
import com.example.orderfoods.Model.Category;
import com.example.orderfoods.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
public class home extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    FirebaseDatabase database;
    DatabaseReference category;
    TextView txtFullName;
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        //init Firebase
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();


       //set Name for user
       View headerView = navigationView.getHeaderView(0);
       txtFullName = (TextView)headerView.findViewById(R.id.txtName);
       txtFullName.setText(Common.currentUser.getName());

        // load menu
        recycler_menu = (RecyclerView)findViewById(R.id.recyclerview_menu);
        recycler_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);

        loadMenu();



    }
    private void loadMenu(){
        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class,R.layout.food_item,MenuViewHolder.class,category) {
            @Override
            protected void populateViewHolder(MenuViewHolder menuViewHolder, final Category category, int i) {
                menuViewHolder.txtMenuName.setText(category.getName());

                      Picasso.get().load(category.getImage()).into(menuViewHolder.imageView);
                      Category clickItem = category;
                      menuViewHolder.setItemClickListener(new ItemClickListener() {
                          @Override
                          public void onClick(View view, int position, boolean isLongClick) {
                           //   Toast.makeText(home.this, ""+category.getName(), Toast.LENGTH_SHORT).show();
                              //get CstogeryId and send FoodList(new Acityvi)
                             Intent foodList = new Intent(home.this, FoodList.class);
                             foodList.putExtra("CatogeryId",adapter.getRef(position).getKey());
                             startActivity(foodList);

                          }
                      });

            }
        };
        recycler_menu.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
       /* NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();*/
       return true;
    }
}
