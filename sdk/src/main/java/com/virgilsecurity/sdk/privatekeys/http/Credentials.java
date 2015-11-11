package com.virgilsecurity.sdk.privatekeys.http;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */

/**
 * A connection credentials for Private Keys API access.
 */
public class Credentials {
    public String userName;
    public String password;

    public Credentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
