package com.virgilsecurity.sdk.keys.helpers;

import android.util.Base64;

import com.google.gson.GsonBuilder;
import com.virgilsecurity.crypto.VirgilSigner;

/**
 * Created by Andrii Nutskovskyi on 07.10.15.
 */
public class SignHelper {

    public static String sign(byte[] privateKey, Object object) {
        return Base64.encodeToString((new VirgilSigner().sign(new GsonBuilder().disableHtmlEscaping().create().toJson(object).getBytes(), privateKey)), Base64.NO_WRAP);
    }

    public static String sign(byte[] privateKey, String string) {
        return Base64.encodeToString((new VirgilSigner().sign(string.getBytes(), privateKey)), Base64.NO_WRAP);
    }
}
