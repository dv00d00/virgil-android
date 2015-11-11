package com.virgilsecurity.sdk.keys.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrii Nutskovskyi on 05.10.15.
 */
public class PublicKeyExtended extends PublicKey {
    @SerializedName("user_data")
    List<UserData> userData;

    public List<UserData> getUserData() {
        return userData;
    }
}
