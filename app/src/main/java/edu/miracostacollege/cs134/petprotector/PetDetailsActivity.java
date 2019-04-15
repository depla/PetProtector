package edu.miracostacollege.cs134.petprotector;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import edu.miracostacollege.cs134.petprotector.model.Pet;

/**
 * PetDetailsActivity.java - Shows more details about a selected pet
 *
 * @author Dennis La
 * @version 1.0
 *
 */
public class PetDetailsActivity extends AppCompatActivity {

    /**
     * Connects the views to the activity and update them with the pet data that came from
     * the intent from PetListActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        //connect the views to the activity
        ImageView petDetailsImageView = findViewById(R.id.petDetailsImageView);
        TextView petDetailsNameTextView = findViewById(R.id.petDetailsNameTextView);
        TextView petDetailsDetailTextView = findViewById(R.id.petDetailsDetailTextView);
        TextView petDetailsPhoneTextView = findViewById(R.id.petDetailsPhoneTextView);

        Intent detailsIntent = getIntent();
        //Retrieve a Pet object from the intent
        Pet pet = detailsIntent.getParcelableExtra("SelectedPet");

        petDetailsImageView.setImageURI(pet.getImageURI());
        petDetailsNameTextView.setText(pet.getName());
        petDetailsDetailTextView.setText(pet.getDetails());
        petDetailsPhoneTextView.setText(pet.getFormatPhone());


    }
}
