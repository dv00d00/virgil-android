package com.virgilsecurity.sdk.keys.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 09.10.15.
 */
public class SearchPublicKeyRequest {
    String value;
    @SerializedName("request_sign_uuid")
    String sign;

    public SearchPublicKeyRequest(String value, String sign) {
        this.value = value;
        this.sign = sign;
    }

    public SearchPublicKeyRequest(String sign) {
        this.sign = sign;
    }
}
