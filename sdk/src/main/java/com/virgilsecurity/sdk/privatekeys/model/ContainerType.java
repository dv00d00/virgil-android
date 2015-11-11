package com.virgilsecurity.sdk.privatekeys.model;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Specifies the type of the Private Keys account entity.
 */
public enum ContainerType {
    @SerializedName("easy")
    EASY,
    @SerializedName("normal")
    NORMAL
}
