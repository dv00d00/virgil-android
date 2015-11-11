package com.virgilsecurity.sdk.keys.model.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andrii Nutskovskyi on 05.10.15.
 */
public class PublicKeyOperationRequest {

    @SerializedName("action_token")
    public String actionToken;
    @SerializedName("user_ids")
    public List<String> userIds;

    public PublicKeyOperationRequest(String actionToken, List<String> userIds) {
        this.actionToken = actionToken;
        this.userIds = userIds;
    }
}
