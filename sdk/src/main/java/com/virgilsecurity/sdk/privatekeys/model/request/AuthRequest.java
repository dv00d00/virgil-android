package com.virgilsecurity.sdk.privatekeys.model.request;

import com.google.gson.annotations.SerializedName;
import com.virgilsecurity.sdk.keys.model.UserData;

/**
 * Created by Andrii Nutskovskyi on 14.10.15.
 */
public class AuthRequest {
    String password;
    @SerializedName("user_data")
    UserData userData;

    public AuthRequest(String password, UserData userData) {
        this.password = password;
        this.userData = userData;
    }
}
