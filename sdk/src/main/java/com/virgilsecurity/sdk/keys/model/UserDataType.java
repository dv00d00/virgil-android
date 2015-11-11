package com.virgilsecurity.sdk.keys.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 05.10.15.
 */
public enum UserDataType {
    @SerializedName("unknown")
    UNKNOWN(0),
    @SerializedName("email")
    EMAIL_ID(1),
    @SerializedName("domain")
    DOMAIN_ID(2),
    @SerializedName("application")
    APPLICATION_ID(3),
    FIRST_NAME_INFO(10000),
    LAST_NAME_INFO(10001);

    private final int value;

    UserDataType(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        switch (this){
            case EMAIL_ID:
                return  "email";
            case DOMAIN_ID:
                return "domain";
            case APPLICATION_ID:
                return "application";
            default:
                throw new EnumConstantNotPresentException(UserDataType.class, "type");
        }
    }
}
