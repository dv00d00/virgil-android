package com.virgilsecurity.sdk.privatekeys.model;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public class PrivateKey {
    @SerializedName("public_key_id")
    public UUID publicKeyId;
    @SerializedName("private_key")
    public String privateKey;

    public UUID getPublicKeyId() {
        return publicKeyId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
