package com.virgilsecurity.sdk.keys.http;

/**
 * Created by Andrii Nutskovskyi on 12.10.15.
 */
public interface Headers {
    String REQUEST_SIGN_HEADER = "X-VIRGIL-REQUEST-SIGN";
    String REQUEST_SIGN_PUBLIC_KEY_ID_HEADER = "X-VIRGIL-REQUEST-SIGN-PK-ID";
    String REQUEST_APPLICATION_TOKEN = "X-VIRGIL-APPLICATION-TOKEN";
    String REQUEST_AUTHENTICATION_HEADER = "X-VIRGIL-AUTHENTICATION";
}
