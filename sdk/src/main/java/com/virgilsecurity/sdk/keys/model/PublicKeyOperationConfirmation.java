package com.virgilsecurity.sdk.keys.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii Nutskovskyi on 05.10.15.
 */
public class PublicKeyOperationConfirmation {
    public String actionToken;
    public List<String> confirmationCodes;

    public PublicKeyOperationConfirmation(String actionToken, List<String> confirmationCodes) {
        this.actionToken = actionToken;
        this.confirmationCodes = confirmationCodes;
    }

    public PublicKeyOperationConfirmation(String actionToken, String confirmationCode) {
        this.actionToken = actionToken;
        ArrayList<String> confirmationList = new ArrayList<>();
        confirmationList.add(confirmationCode);
        this.confirmationCodes = confirmationList;
    }

    public String getActionToken() {
        return actionToken;
    }

    public List<String> getConfirmationCodes() {
        return confirmationCodes;
    }
}
