package edu.miracostacollege.cs134.petprotector;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import edu.miracostacollege.cs134.petprotector.model.Pet;

/**
 * PetListAdapter.java - custom adapter to inflate the list view
 *
 * @author Dennis La
 * @version 1.0
 */
public class PetListAdapter extends ArrayAdapter<Pet>
{
    private Context mContext;
    private List<Pet> mPetList;
    private int mResourceId;

    /**
     * Constructor for the custom adapter
     *
     * @param context PetListActivity
     * @param resource the xml file for the list item
     * @param objects the list of pets
     */
    public PetListAdapter(Context context, int resource, List objects)
    {
        super(context, resource, objects);

        mContext = context;
        mPetList = objects;
        mResourceId = resource;
    }


    /**
     * Method to inflate each list item with data from the list of pets
     *
     * @param position the position of the list item
     * @param convertView
     * @param parent
     * @return the list item view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Pet focusedPet = mPetList.get(position);

        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout petListLinearLayout = view.findViewById(R.id.petListLinearLayout);
        ImageView petListImageView = view.findViewById(R.id.petListImageView);
        TextView petListNameTextView = view.findViewById(R.id.petListNameTextView);
        TextView petListDetailTextView = view.findViewById(R.id.petListDetailTextView);

        petListLinearLayout.setTag(focusedPet);
        petListImageView.setImageURI(focusedPet.getImageURI());
        petListNameTextView.setText(focusedPet.getName());
        petListDetailTextView.setText(focusedPet.getDetails());

        return view;
    }
}
