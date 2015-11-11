package com.virgilsecurity.sdk.privatekeys.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public class ConfirmRequest {
    String token;
    @SerializedName("request_sign_uuid")
    String requestSignUuid;

    public ConfirmRequest(String token) {
        this.token = token;
        this.requestSignUuid = UUID.randomUUID().toString();
    }
}
