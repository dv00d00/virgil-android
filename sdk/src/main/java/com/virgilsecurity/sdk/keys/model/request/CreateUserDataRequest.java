package com.virgilsecurity.sdk.keys.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 09.10.15.
 */
public class CreateUserDataRequest {
    @SerializedName("class")
    String userDataClass;
    String type;
    String value;
    @SerializedName("request_sign_uuid")
    String sign;

    public CreateUserDataRequest(String userDataClass, String type, String value, String sign) {
        this.userDataClass = userDataClass;
        this.type = type;
        this.value = value;
        this.sign = sign;
    }
}
