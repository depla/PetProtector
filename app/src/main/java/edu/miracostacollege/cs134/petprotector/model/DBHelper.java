package edu.miracostacollege.cs134.petprotector.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * DBHelper.java - class used to help manage database of pets
 *
 * @author Dennis La
 * @version 1.0
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    public static final String DATABASE_NAME = "PetProtector";
    private static final String DATABASE_TABLE = "Pets";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DETAILS = "details";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_IMAGE_URI = "image_uri";

    /**
     * Constructor for DBHelper
     *
     * @param context PetListActivity
     */
    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Creates a database table if it does not exist
     *
     * @param database
     */
    @Override
    public void onCreate (SQLiteDatabase database){
        String table = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY, "
                + FIELD_NAME + " TEXT, "
                + FIELD_DETAILS + " TEXT, "
                + FIELD_PHONE + " TEXT, "
                + FIELD_IMAGE_URI + " TEXT" + ")";
        database.execSQL (table);
    }

    /**
     * Upgrades the database
     *
     * @param database
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);
    }

    //********** DATABASE OPERATIONS:  ADD, UPDATE, EDIT, DELETE

    /**
     * Adds a pet to the database
     *
     * @param pet the pet to add
     */
    public void addPet(Pet pet) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR THE PET NAME
        values.put(FIELD_NAME, pet.getName());

        //ADD KEY-VALUE PAIR INFORMATION FOR THE PET DETAILS
        values.put(FIELD_DETAILS, pet.getDetails());

        //ADD KEY-VALUE PAIR INFORMATION FOR THE PET PHONE
        values.put(FIELD_PHONE, pet.getPhone());

        //ADD KEY-VALUE PAIR INFORMATION FOR THE PET URI
        values.put(FIELD_IMAGE_URI, pet.getImageURI().toString());

        // INSERT THE ROW IN THE TABLE
        long id = db.insert(DATABASE_TABLE, null, values);

        // UPDATE THE PET WITH THE NEWLY ASSIGNED ID FROM THE DATABASE
        pet.setId(id);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    /**
     * Retrieves all the pets from the database and puts them in a list
     *
     * @return the list of all the pets from the database
     */
    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        // A cursor is the results of a database query (what gets returned)
        Cursor cursor = database.query(
                DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_NAME, FIELD_DETAILS, FIELD_PHONE, FIELD_IMAGE_URI},
                null,
                null,
                null, null, null, null );

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()){
            do {
                String uriString = cursor.getString(4);
                Uri uri = Uri.parse(uriString);

                Pet pet =
                        new Pet(cursor.getLong(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                uri);

                petList.add(pet);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return petList;
    }

    /**
     * Deletes a given pet in the database
     *
     * @param pet the pet to be deleted
     */
    public void deletePet(Pet pet){
        SQLiteDatabase db = getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(DATABASE_TABLE, KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(pet.getId())});
        db.close();
    }

    /**
     * Deletes all the pets in the database
     */
    public void deleteAllPets()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DATABASE_TABLE, null, null);
        db.close();
    }

    /**
     * Updates a given pet in the database
     *
     * @param pet the pet to be updated
     */
    public void updatePet(Pet pet){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, pet.getName());
        values.put(FIELD_DETAILS, pet.getDetails());
        values.put(FIELD_PHONE, pet.getPhone());
        values.put(FIELD_IMAGE_URI, pet.getImageURI().toString());

        db.update(DATABASE_TABLE, values, KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(pet.getId())});
        db.close();
    }

    /**
     * Gets a pet from the database based on the id
     *
     * @param id the pet to be deleted
     * @return the pet that was searched for
     */
    public Pet getPet(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                DATABASE_TABLE,
                new String[]{KEY_FIELD_ID, FIELD_NAME, FIELD_DETAILS, FIELD_PHONE, FIELD_IMAGE_URI},
                KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        Pet pet = null;
        if (cursor != null) {
            cursor.moveToFirst();

            String uriString = cursor.getString(4);
            Uri uri = Uri.parse(uriString);

            pet = new Pet(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    uri);

            cursor.close();
        }
        db.close();
        return pet;
    }





}