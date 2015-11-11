package com.virgilsecurity.sdk.keys.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 09.10.15.
 */
public class ConfirmUserDataRequest {
    @SerializedName("confirmation_code")
    String confirmCode;
    @SerializedName("request_sign_uuid")
    String sign;

    public ConfirmUserDataRequest(String confirmCode, String sign) {
        this.confirmCode = confirmCode;
        this.sign = sign;
    }
}
