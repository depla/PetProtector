package edu.miracostacollege.cs134.petprotector.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Pet.java - class the represents each pet
 *
 * @author Dennis La
 * @version 1.0
 *
 */
public class Pet implements Parcelable
{
    private long mId;
    private String mName;
    private String mDetails;
    private String mPhone;
    private Uri mImageURI;

    /**
     * Full constructor for the pet
     *
     * @param id id for the database
     * @param name name of pet
     * @param details pet details
     * @param phone phone number for contacting owner
     * @param imageURI the uri of the image of the pet
     */
    public Pet(long id, String name, String details, String phone, Uri imageURI) {
        mId = id;
        mName = name;
        mDetails = details;
        mPhone = phone;
        mImageURI = imageURI;
    }

    /**
     * Constructor for pet, without the id as a parameter
     *
     * @param name name of pet
     * @param details pet details
     * @param phone phone number for contacting owner
     * @param imageURI the uri of the image of the pet
     */
    public Pet(String name, String details, String phone, Uri imageURI) {
        mName = name;
        mDetails = details;
        mPhone = phone;
        mImageURI = imageURI;
    }

    /**
     * Getter for pet id
     *
     * @return the pet id
     */
    public long getId() {
        return mId;
    }

    /**
     * Getter for pet name
     *
     * @return the pet name
     */
    public String getName() {
        return mName;
    }

    /**
     * Getter for pet details
     *
     * @return the pet details
     */
    public String getDetails() {
        return mDetails;
    }

    /**
     * Getter for pet owner's phone number
     *
     * @return the pet owner's phone number
     */
    public String getPhone() {
        return mPhone;
    }

    /**
     * Getter for pet image uri
     *
     * @return the pet image uri
     */
    public Uri getImageURI() {
        return mImageURI;
    }

    /**
     * Setter for pet's name
     *
     * @param name the pet's name
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * Setter for pet's details
     *
     * @param details the pet's details
     */
    public void setDetails(String details) {
        mDetails = details;
    }

    /**
     * Setter for pet owner's phone number
     *
     * @param phone the pet owner's phone number
     */
    public void setPhone(String phone) {
        mPhone = phone;
    }

    /**
     * Setter for pet's image uri
     *
     * @param imageURI the pet's image uri
     */
    public void setImageURI(Uri imageURI) {
        mImageURI = imageURI;
    }

    /**
     * Setter for pet id
     *
     * @param id
     */
    public void setId(long id) {
        mId = id;
    }

    /**
     * Formats the phone number into (123)456-7890
     *
     * @return the formatted phone number
     */
    public String getFormatPhone()
    {
        return "(" + mPhone.substring(0, 3) + ")" + mPhone.substring(3, 6) + "-" + mPhone.substring(6);
    }

    /**
     * toString for pet
     *
     * @return the data about the pet
     */
    @Override
    public String toString() {
        return "Pet{" +
                "Id=" + mId +
                ", Name='" + mName + '\'' +
                ", Details='" + mDetails + '\'' +
                ", Phone='" + mPhone + '\'' +
                ", ImageURI=" + mImageURI +
                '}';
    }

    /**
     * Does nothing, stub
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes a pet instance variables into a parcel
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeString(mDetails);
        dest.writeString(mPhone);
        dest.writeString(mImageURI.toString());
    }

    //Mechanism to create (instantiate) a new Pet Object from a parcel
    //private constructor to create a new Pet from that Parcel

    /**
     * Creates pet out of a parcel
     * @param parcel
     */
    private Pet(Parcel parcel)
    {
        mId = parcel.readLong();
        mName = parcel.readString();
        mDetails = parcel.readString();
        mPhone = parcel.readString();
        mImageURI = Uri.parse(parcel.readString());
    }

    public static final Parcelable.Creator<Pet> CREATOR = new Creator<Pet>() {

        /**
         * Create new pet from parcel
         *
         * @param source the parcel
         * @return a new pet object
         */
        @Override
        public Pet createFromParcel(Parcel source) {
            return new Pet(source);
        }

        /**
         * Allows the creation of an array of Pet objects
         *
         * @param size the size of the array
         * @return an array of pets
         */
        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };
}
