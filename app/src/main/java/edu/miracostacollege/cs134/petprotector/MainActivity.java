package edu.miracostacollege.cs134.petprotector;

/**
 * PetProtector - Lets user add an image from photos or gallery to an image view.
 *
 * Dennis La
 * CS134
 *
 */

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView petImageView;
    public static final int RESULT_LOAD_IMAGE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect pet image view to the layout
        petImageView = findViewById(R.id.petImageView);

        //setImageUri on the petImageView
        petImageView.setImageURI(getUriToResource(this, R.drawable.none));
    }

    public void selectPetImage(View v)
    {
        //1. make a list of permissions, empty at first
        //2. as user grants them add each permission to the list

        List<String> permsList = new ArrayList<>();

        int permReqCode = 100;
        int hasCameraPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        //check to see if camera permission is denied
        //if denied, add it to the list of permissions requested
        if(hasCameraPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.CAMERA);
        }

        int hasReadExternalPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(hasReadExternalPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

                                                                                    //strings, could make an array of strings to loop through
        int hasWriteExternalPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(hasWriteExternalPerm == PackageManager.PERMISSION_DENIED)
        {
            permsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        //now that weve build the list, lets ask the user
        if(permsList.size() > 0)
        {
            //convert the list into an array
            String[] perms = new String[permsList.size()];

            permsList.toArray(perms);

            //make the request to the user (Backwards compatible)
            ActivityCompat.requestPermissions(this, perms, permReqCode);
        }

        //after requesting permissions, find out which ones the user granted
        if(hasCameraPerm == PackageManager.PERMISSION_GRANTED &&
            hasReadExternalPerm == PackageManager.PERMISSION_GRANTED &&
            hasWriteExternalPerm == PackageManager.PERMISSION_GRANTED)
        {
            //open the Gallery!
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
        }
        else
        {
            //toast informing user need permissions
            Toast.makeText(this, R.string.need_perms, Toast.LENGTH_SHORT).show();
        }
    }

    //override onActivityResult to find out what the user picked


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //if(resultCode == RESULT_LOAD_IMAGE) result code would be -1 but RESULT_LOAD_IMAGE is 200

        if(resultCode == -1) //changed RESULT_LOAD_IMAGE to -1
        {
            Uri uri = data.getData();

            petImageView.setImageURI(uri);
        }
    }

    private static Uri getUriToResource(Context context, int id)
    {
        Resources res = context.getResources();
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + res.getResourcePackageName(id) + "/"
                + res.getResourceTypeName(id) + "/"
                + res.getResourceEntryName(id);

        //"android.resource://edu.miracostacollege.cs134.petprotector/drawable/none"

        return Uri.parse(uri);
    }
}
