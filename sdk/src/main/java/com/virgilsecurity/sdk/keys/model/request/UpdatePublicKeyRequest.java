package com.virgilsecurity.sdk.keys.model.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */
public class UpdatePublicKeyRequest {
    @SerializedName("public_key")
    String publicKey;
    @SerializedName("request_sign_uuid")
    String sign;
    @SerializedName("uuid_sign")
    String privateSign;

    public UpdatePublicKeyRequest(String publicKey, String sign, String privateSign) {
        this.publicKey = publicKey;
        this.sign = sign;
        this.privateSign = privateSign;
    }

    public UpdatePublicKeyRequest(String publicKey, String sign) {
        this.publicKey = publicKey;
        this.sign = sign;
    }
}
