package edu.miracostacollege.cs134.petprotector.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;


public class Pet implements Parcelable
{
    private long mId;
    private String mName;
    private String mDetails;
    private String mPhone;
    private Uri mImageURI;

    public Pet(long id, String name, String details, String phone, Uri imageURI) {
        mId = id;
        mName = name;
        mDetails = details;
        mPhone = phone;
        mImageURI = imageURI;
    }


    public Pet(String name, String details, String phone, Uri imageURI) {
        mName = name;
        mDetails = details;
        mPhone = phone;
        mImageURI = imageURI;
    }


    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getPhone() {
        return mPhone;
    }

    public Uri getImageURI() {
        return mImageURI;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setDetails(String details) {
        mDetails = details;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public void setImageURI(Uri imageURI) {
        mImageURI = imageURI;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getFormatPhone()
    {
        return "(" + mPhone.substring(0, 3) + ")" + mPhone.substring(3, 6) + "-" + mPhone.substring(6);
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

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
    private Pet(Parcel parcel)
    {
        mId = parcel.readLong();
        mName = parcel.readString();
        mDetails = parcel.readString();
        mPhone = parcel.readString();
        mImageURI = Uri.parse(parcel.readString());
    }

    public static final Parcelable.Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel source) {
            return new Pet(source);
        }

        /**
         * Allows the creation of an array of Pet objects from the JSON file.
         * This method repeatedly calls createFromParcel for every object
         * @param size
         * @return
         */
        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };
}
