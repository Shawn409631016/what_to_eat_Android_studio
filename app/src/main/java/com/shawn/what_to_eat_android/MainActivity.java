package com.shawn.what_to_eat_android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    TextView foodNameTextView;
    TextView foodCaloriesTextView;
    TextView foodCarbTextView;
    TextView foodProteinTextView;
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

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference foodsRef = db.collection("All Food");

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

                            Food randomFood = getRandomFood();
                            foodNameTextView.setText(randomFood.getName());
                            foodCaloriesTextView.setText("熱量: " + randomFood.getCalories());
                            foodProteinTextView.setText("蛋白質: " + randomFood.get蛋白質());
                            foodCarbTextView.setText("碳水化合物: " + randomFood.get碳水化合物());
                            Glide.with(MainActivity.this)
                                    .load(randomFood.getImg())
                                    .into(foodPhotoImageView);

                        }
                    }
                });


    }

    public void onDrawAgainClicked(View view) {
        Food randomFood = getRandomFood();
        foodNameTextView.setText(randomFood.getName());
        foodCaloriesTextView.setText("熱量: " + randomFood.getCalories());
        foodProteinTextView.setText("蛋白質: " + randomFood.get蛋白質());
        foodCarbTextView.setText("碳水化合物: " + randomFood.get碳水化合物());
        Glide.with(MainActivity.this)
                .load(randomFood.getImg())
                .into(foodPhotoImageView);
    }

    private Food getRandomFood() {
        int randomIndex = new Random().nextInt(mFoodList.size());
        return mFoodList.get(randomIndex);
    }

}