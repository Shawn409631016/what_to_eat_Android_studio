package com.shawn.what_to_eat_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Food> mFoodList;
    Food randomFood;

    TextView foodNameTextView;
    TextView foodCaloriesTextView;
    TextView foodCarbTextView;
    TextView foodProteinTextView;
    TextView appBarTitle;
    ImageView foodPhotoImageView;
    Button drawAgainButton;
    Button moreInfoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodNameTextView = findViewById(R.id.foodNameTextView);
        foodCaloriesTextView = findViewById(R.id.foodCaloriesTextView);
        foodCarbTextView = findViewById(R.id.foodCarbTextView);
        foodProteinTextView = findViewById(R.id.foodProteinTextView);
        foodPhotoImageView = findViewById(R.id.foodPhotoImageView);
        drawAgainButton = findViewById(R.id.drawAgainButton);
        moreInfoButton = findViewById(R.id.moreInfoButton);
        appBarTitle = findViewById(R.id.appBarTitle);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference foodsRef;

        String collentionPath = getIntent().getStringExtra("collection_path");
        if(collentionPath != null){
            foodsRef = db.collection(collentionPath);
        }else{
            foodsRef = db.collection("All Food");
        }

        switch(collentionPath){
            case "All Food":
                appBarTitle.setText("所有料理");
                break;
            case "Japan Food":
                appBarTitle.setText("日式料理");
                break;
            case "Korea Food":
                appBarTitle.setText("韓式料理");
                break;
            case "Tai Food":
                appBarTitle.setText("泰式料理");
                break;
            case "Taiwan Food":
                appBarTitle.setText("台式料理");
                break;
            case "West Food":
                appBarTitle.setText("西式料理");
                break;
        }

        // Retrieve all documents in the "All food" collection
        foodsRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            mFoodList = new ArrayList<>();

                            // Loop through all of the documents in the "All food" collection
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Retrieve the data for the current document and add it to the list of foods
                                mFoodList.add(document.toObject(Food.class));
                            }

                            randomFood = getRandomFood();
                            foodNameTextView.setText(randomFood.getName());
                            foodCaloriesTextView.setText("熱量: " + randomFood.getCalories());
                            foodProteinTextView.setText("蛋白質: " + randomFood.getProtein());
                            foodCarbTextView.setText("碳水化合物: " + randomFood.getCarbohydrates());
                            Glide.with(MainActivity.this)
                                    .load(randomFood.getImg())
                                    .into(foodPhotoImageView);

                        }
                    }
                });


    }

    private Food getRandomFood() {
        int randomIndex = new Random().nextInt(mFoodList.size());
        return mFoodList.get(randomIndex);
    }

    public void onMoreInfoClicked(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(randomFood.getUri()));
        startActivity(browserIntent);
    }

    public void onDrawAgainClicked(View view) {
        randomFood = getRandomFood();
        foodNameTextView.setText(randomFood.getName());
        foodCaloriesTextView.setText("熱量: " + randomFood.getCalories());
        foodProteinTextView.setText("蛋白質: " + randomFood.getProtein());
        foodCarbTextView.setText("碳水化合物: " + randomFood.getCarbohydrates());
        Glide.with(MainActivity.this)
                .load(randomFood.getImg())
                .into(foodPhotoImageView);
        Toast.makeText(MainActivity.this, "再抽一次", Toast.LENGTH_SHORT).show();
    }

}

