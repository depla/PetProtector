package edu.miracostacollege.cs134.petprotector;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView petImageView;

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
