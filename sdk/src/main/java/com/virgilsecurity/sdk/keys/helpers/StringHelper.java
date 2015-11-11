package com.virgilsecurity.sdk.keys.helpers;

import android.util.Base64;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public class StringHelper {

    public static byte[] base64ToByte(String base64){
        return Base64.decode(base64, Base64.NO_WRAP);
    }

    public static String byteToBase64(byte[] bytes){
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }
}
