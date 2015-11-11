package com.virgilsecurity.sdk.keys.helpers;

import android.text.TextUtils;

import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.UserDataType;

/**
 * Created by Andrii Nutskovskyi on 06.10.15.
 */
public class Ensure {

    public static void argumentNotNull(Object value, String name) {
        if (value != null) return;

        throw new IllegalArgumentException(name);
    }

    public static void argumentNotNullOrEmptyString(String value, String name) {
        argumentNotNull(value, name);
        if (!TextUtils.isEmpty(value)) return;

        throw new IllegalArgumentException("String can not be empty: name");
    }

    public static void userDataTypeIsUserId(UserDataType dataType, String name) {
        if ((dataType.getValue() > 9999 || dataType.getValue() == 0))
            throw new IllegalArgumentException("Invalid enum argument: " + name + " - " + dataType.getValue());
    }

    public static void userDataTypeIsNotUnknown(UserDataType dataType, String name)
    {
        if (dataType == UserDataType.UNKNOWN)
        {
            throw new IllegalArgumentException("Invalid enum argument: " + name + " - " + dataType.getValue());
        }
    }

    public static void userDataValid(UserData userData)
    {
        userDataTypeIsNotUnknown(userData.type, "userData.type");
        argumentNotNullOrEmptyString(userData.value, "userData.value");
    }
}
