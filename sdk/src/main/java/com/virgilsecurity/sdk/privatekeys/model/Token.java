package com.virgilsecurity.sdk.privatekeys.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 14.10.15.
 */
public class Token {
    @SerializedName("auth_token")
    String token;

    public String getToken() {
        return token;
    }
}
