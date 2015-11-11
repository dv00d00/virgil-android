package com.virgilsecurity.sdk.keys.model.request;

import com.google.gson.annotations.SerializedName;
import com.virgilsecurity.sdk.keys.model.UserData;

import java.util.List;

/**
 * Created by Andrii Nutskovskyi on 06.10.15.
 */
public class CreatePublicKeyRequest {
    @SerializedName("public_key")
    String publicKey;
    @SerializedName("user_data")
    List<UserData> userDataList;
    @SerializedName("request_sign_uuid")
    String sign;

    public CreatePublicKeyRequest(String publicKey, List<UserData> userDataList, String sign) {
        this.publicKey = publicKey;
        this.userDataList = userDataList;
        this.sign = sign;
    }
}
