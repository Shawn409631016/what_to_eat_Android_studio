package com.shawn.what_to_eat_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Food> mFoodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView foodNameTextView = findViewById(R.id.foodNameTextView);
        TextView foodCaloriesTextView = findViewById(R.id.foodCaloriesTextView);
        TextView foodCarbTextView = findViewById(R.id.foodCarbTextView);
        TextView foodProteinTextView = findViewById(R.id.foodProteinTextView);
        ImageView foodPhotoImageView = findViewById(R.id.foodPhotoImageView);
        Button drawAgainButton = findViewById(R.id.drawAgainButton);
        Button moreInfoButton = findViewById(R.id.moreInfoButton);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference foodsRef = db.collection("All food");

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

                        }
                    }
                });

    }
}