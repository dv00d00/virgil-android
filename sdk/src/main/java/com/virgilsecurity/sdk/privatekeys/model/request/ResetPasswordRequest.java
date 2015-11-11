package com.virgilsecurity.sdk.privatekeys.model.request;

import com.google.gson.annotations.SerializedName;
import com.virgilsecurity.sdk.keys.model.UserData;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public class ResetPasswordRequest {
    @SerializedName("user_data")
    UserData userData;
    @SerializedName("new_password")
    String newPassword;

    public ResetPasswordRequest(UserData userData, String newPAssword) {
        this.userData = userData;
        this.newPassword = newPAssword;
    }
}
