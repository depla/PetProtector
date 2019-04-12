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

public class PetListAdapter extends ArrayAdapter<Pet>
{
    private Context mContext;
    private List<Pet> mPetList;
    private int mResourceId;

    public PetListAdapter(Context context, int resource, List objects)
    {
        super(context, resource, objects);

        mContext = context;
        mPetList = objects;
        mResourceId = resource;
    }


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
