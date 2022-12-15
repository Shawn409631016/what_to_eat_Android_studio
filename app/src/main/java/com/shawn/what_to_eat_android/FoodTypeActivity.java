package com.shawn.what_to_eat_android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;


public class FoodTypeActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    List<FoodType> foodTypes = Arrays.asList(
            new FoodType("所有類別", R.drawable.all_food, "All Food"),
            new FoodType("台式料理", R.drawable.taiwan_img, "Taiwan Food"),
            new FoodType("日式料理", R.drawable.japan_img, "Japan Food"),
            new FoodType("韓式料理", R.drawable.korea_img, "Korea Food"),
            new FoodType("泰式料理", R.drawable.tai_img, "Tai Food"),
            new FoodType("西式料理", R.drawable.west_img, "West Food")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);

        recyclerView = findViewById(R.id.recyclerView);
        FoodTypeAdapter adapter = new FoodTypeAdapter(this, foodTypes, new FoodTypeAdapter.OnItemClickListener() {
            public void onItemClick(int position){
                FoodType foodType = foodTypes.get(position);
                String collectionPath = foodType.getCollectionName();

                Bundle bundle = new Bundle();
                bundle.putString("collection_path", collectionPath);

                Intent intent = new Intent();
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}

class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.ViewHolder>{
    // List of food types
    private List<FoodType> foodTypes;
    private OnItemClickListener onItemClickListener;


    // Constructor for the FoodTypeAdapter
    public FoodTypeAdapter(Context context, List<FoodType> foodTypes, OnItemClickListener onItemClickListener) {
        this.foodTypes = foodTypes;
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    // Inner class for the ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Views in the ViewHolder
        TextView foodTypeTextView;
        ImageView foodTypeImageView;

        // Constructor for the ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTypeTextView = itemView.findViewById(R.id.foodTypeTextView);
            foodTypeImageView = itemView.findViewById(R.id.foodTypeImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        FoodType foodType = foodTypes.get(position);
                        String collectionPath = foodType.getCollectionName();

                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        intent.putExtra("collection_path", collectionPath);
                        view.getContext().startActivity(intent);

                    }
                }
            });
        }
    }

    // Called when the RecyclerView needs a new ViewHolder
    @NonNull
    @Override
    public FoodTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_type_item, parent, false);

        // Create a new ViewHolder using the inflated layout
        ViewHolder viewHolder = new ViewHolder(view);

        // Return the ViewHolder
        return viewHolder;
    }

    // Called to display the data at the specified position
    @Override
    public void onBindViewHolder(@NonNull FoodTypeAdapter.ViewHolder holder, int position) {
        // Get the food type at the specified position
        FoodType foodType = foodTypes.get(position);

        // Set the name of the food type
        holder.foodTypeTextView.setText(foodType.getName());

        // Set the image for the food type
        holder.foodTypeImageView.setImageResource(foodType.getImageResId());
    }

    // Returns the number of items in the data set
    @Override
    public int getItemCount() {
        return foodTypes.size();
    }
}
