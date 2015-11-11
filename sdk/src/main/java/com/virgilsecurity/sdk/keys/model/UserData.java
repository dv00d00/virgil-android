package com.virgilsecurity.sdk.keys.model;
/**
 * Created by Andrii Nutskovskyi on 05.10.15.
 */


import com.google.gson.annotations.SerializedName;

/**
 * Represents user data object associated to the public key.
 */
public class UserData {

    /**
     * The user data identifier.
     */
    @SerializedName("id")
    public UserDataId userDataId;

    /**
     * The user data type.
     */
    public UserDataType type;

    /**
     * The user data class.
     */
    @SerializedName("class")
    public UserDataClass userDataClass;

    /**
     * The value of the user data (email, phone number, application name, etc.).
     */
    public String value;

    /**
     * Confirmed flag
     */
    @SerializedName("is_confirmed")
    public Boolean confirmed;

    public UserData(UserDataClass userDataClass, UserDataType type, String value) {
        this.value = value;
        this.type = type;
        this.userDataClass = userDataClass;
    }

    public static class UserDataId extends PublicKey.PublicKeyId {
        @SerializedName("user_data_id")
        String userDataId;

        public String getUserDataId() {
            return userDataId;
        }
    }
}
