package com.virgilsecurity.sdk.privatekeys.model;

import com.google.gson.annotations.SerializedName;

import java.security.PrivateKey;
import java.util.List;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */

/**
 * Provides an object representation of the Private Keys container.
 */
public class Container {
    /**
     * The account type which defines a method of storing private keys.
     */
    @SerializedName("container_type")
    public ContainerType type;

    /**
     * The bundle of account private keys.
     */
    public List<PrivateKey> privateKeys;
}
