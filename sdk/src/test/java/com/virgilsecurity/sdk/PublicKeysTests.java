package com.virgilsecurity.sdk;

import android.support.annotation.Nullable;

import com.vilgil.keys.BuildConfig;
import com.virgilsecurity.sdk.keys.clients.PublicKeysClient;
import com.virgilsecurity.sdk.keys.http.Connection;
import com.virgilsecurity.sdk.keys.http.ResponseCallback;
import com.virgilsecurity.sdk.keys.model.PublicKey;
import com.virgilsecurity.sdk.keys.model.PublicKeyExtended;
import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.UserDataClass;
import com.virgilsecurity.sdk.keys.model.UserDataType;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Andrii Nutskovskyi on 15.10.15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PublicKeysTests {
    Connection connection = new Connection("72ec86432dc166106289d0b51a879371", URI.create("https://keys-stg.virgilsecurity.com"));
    PublicKeysClient publicKeysClient = new PublicKeysClient(connection);

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_When_PublicKeyIsNull() {
        //VirgilKeyPair keyPair = new VirgilKeyPair();
        final UserData userData = new UserData(UserDataClass.USER_ID, UserDataType.EMAIL_ID, UUID.randomUUID().toString() + "@mailinator.com");
        ArrayList<UserData> list = new ArrayList<>();
        list.add(userData);
        publicKeysClient.create(null, null, list, new ResponseCallback<PublicKeyExtended>() {
            @Override
            public void onResult(@Nullable PublicKeyExtended object) {
                publicKeysClient.search(userData.value, new ResponseCallback<PublicKey>() {
                    @Override
                    public void onResult(@Nullable PublicKey object) {
                        Assert.assertNotNull(object);
                    }

                    @Override
                    public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                        Assert.fail(error.getMessage());
                    }
                });
            }

            @Override
            public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                Assert.fail(error.getMessage());
            }
        });
    }
    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowException_If_SearchUserDataValueArgumentIsNull(){
        publicKeysClient.search(null, new ResponseCallback<PublicKey>() {
            @Override
            public void onResult(@Nullable PublicKey object) {

            }

            @Override
            public void onError(com.virgilsecurity.sdk.keys.http.Error error) {

            }
        });
    }

}
