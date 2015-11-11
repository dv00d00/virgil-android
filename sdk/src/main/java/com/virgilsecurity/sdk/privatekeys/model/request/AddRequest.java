package com.virgilsecurity.sdk.privatekeys.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public class AddRequest {
    @SerializedName("private_key")
    String privateKey;
    @SerializedName("request_sign_uuid")
    String requestSignUuid;

    public AddRequest(String privateKey, String requestSignUuid) {
        this.privateKey = privateKey;
        this.requestSignUuid = requestSignUuid;
    }
}
