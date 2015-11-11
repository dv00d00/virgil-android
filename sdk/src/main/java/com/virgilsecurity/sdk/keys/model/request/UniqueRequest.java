package com.virgilsecurity.sdk.keys.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */
public class UniqueRequest {
    @SerializedName("request_sign_uuid")
    String sign;

    public UniqueRequest(String sign) {
        this.sign = sign;
    }
}
