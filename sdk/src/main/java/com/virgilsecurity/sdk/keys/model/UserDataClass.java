package com.virgilsecurity.sdk.keys.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andrii Nutskovskyi on 05.10.15.
 */
public enum UserDataClass {
    @SerializedName("unknown")
    UNKNOWN(0),
    @SerializedName("user_id")
    USER_ID(1),
    @SerializedName("user_info")
    USER_INFO(2);

    private final int value;

    UserDataClass(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        switch (this){
            case USER_ID:
                return "user_id";
            case USER_INFO:
                return "user_info";
            default:
                throw new EnumConstantNotPresentException(UserDataClass.class, "class");
        }
    }
}
