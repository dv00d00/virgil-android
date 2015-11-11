package com.virgilsecurity.sdk.privatekeys.model.request;

import com.google.gson.annotations.SerializedName;
import com.virgilsecurity.sdk.privatekeys.model.ContainerType;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public class InitializeRequest {
    @SerializedName("container_type")
    ContainerType containerType;
    String password;
    @SerializedName("request_sign_uuid")
    String requestSignUuid;

    public InitializeRequest(ContainerType containerType, String password, String requestSignUuid) {
        this.containerType = containerType;
        this.password = password;
        this.requestSignUuid = requestSignUuid;
    }
}
