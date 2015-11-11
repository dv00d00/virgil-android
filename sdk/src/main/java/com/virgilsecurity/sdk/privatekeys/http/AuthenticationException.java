package com.virgilsecurity.sdk.privatekeys.http;

/**
 * Created by Andrii Nutskovskyi on 15.10.15.
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super("First, you have to  authenticate!");
    }

    public AuthenticationException(String detailMessage) {
        super(detailMessage);
    }
}
