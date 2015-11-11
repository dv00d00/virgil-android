package com.virgilsecurity.sdk.keys.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */
public class ConfirmRequest {
    @SerializedName("action_token")
    String token;
    @SerializedName("confirmation_codes")
    List<String> confirmationCodes;

    public ConfirmRequest(String token, List<String> confirmationCodes) {
        this.token = token;
        this.confirmationCodes = confirmationCodes;
    }
}
