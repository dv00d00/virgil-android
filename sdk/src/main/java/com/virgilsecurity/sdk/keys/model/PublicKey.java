package com.virgilsecurity.sdk.keys.model;
/**
 * Created by Andrii Nutskovskyi on 05.10.15.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Represent public key
 */
public class PublicKey {
    /**
     * The public key identifier.
     */
    @SerializedName("id")
    public PublicKeyId publicKeyId;

    /**
     * The public key.
     */
    @SerializedName("public_key")
    public String publicKey;

    /**
     * Gets the public key identifier.
     */
    public PublicKeyId getPublicKeyId() {
        return publicKeyId;
    }

    /**
     * Get the public key base64 representation.
     */
    public String getPublicKey() {
        return publicKey;
    }

    public static class PublicKeyId {
        @SerializedName("account_id")
        String accountId;
        @SerializedName("public_key_id")
        String publicKeyId;

        public String getAccountId() {
            return accountId;
        }

        public String getPublicKeyId() {
            return publicKeyId;
        }
    }
}
