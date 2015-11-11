package com.virgilsecurity.sdk.privatekeys.clients;

import android.support.annotation.Nullable;
import android.util.Base64;

import com.virgilsecurity.crypto.VirgilCipher;
import com.virgilsecurity.sdk.keys.helpers.SignHelper;
import com.virgilsecurity.sdk.keys.helpers.StringHelper;
import com.virgilsecurity.sdk.keys.http.EmptyResponseCallback;
import com.virgilsecurity.sdk.keys.http.ResponseCallback;
import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.UserDataClass;
import com.virgilsecurity.sdk.keys.model.UserDataType;
import com.virgilsecurity.sdk.keys.model.request.UniqueRequest;
import com.virgilsecurity.sdk.privatekeys.http.AuthenticationException;
import com.virgilsecurity.sdk.privatekeys.http.Connection;
import com.virgilsecurity.sdk.privatekeys.http.Credentials;
import com.virgilsecurity.sdk.privatekeys.model.PrivateKey;
import com.virgilsecurity.sdk.privatekeys.model.Token;
import com.virgilsecurity.sdk.privatekeys.model.request.AddRequest;
import com.virgilsecurity.sdk.privatekeys.model.request.AuthRequest;

import java.util.UUID;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public class PrivateKeysClient {
    private Connection connection;

    /**
     * Initializes a new instance of the {@link PrivateKeysClient} class with the default implementations.
     */
    public PrivateKeysClient(Connection connection) {
        this.connection = connection;
    }

    /**
     * Gets the private key by public key ID.
     *
     * @param publicKeyId public key identifier.
     */
    public void get(UUID publicKeyId, ResponseCallback<PrivateKey> callback) {
        get(publicKeyId, connection.getCredentials().password, callback);
    }

    /**
     * Gets the private key by public key ID.
     *
     * @param publicKeyId        public key identifier.
     * @param privateKeyPassword private key password.
     */
    public void get(UUID publicKeyId, final String privateKeyPassword, final ResponseCallback<PrivateKey> callback) {
        if (connection.getAuthToken() == null) throw new AuthenticationException();
        connection.getPrivateKeysApi().get(connection.getAuthToken(), publicKeyId.toString()).enqueue(new ResponseCallback<PrivateKey>() {
            @Override
            public void onResult(@Nullable PrivateKey object) {
                VirgilCipher crypto = new VirgilCipher();
                byte[] privateKey = crypto.decryptWithPassword(StringHelper.base64ToByte(object.getPrivateKey()),
                        StringHelper.base64ToByte(privateKeyPassword));
                object.setPrivateKey(StringHelper.byteToBase64(privateKey));
                callback.onResult(object);
            }

            @Override
            public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                callback.onError(error);
            }
        });
    }

    /**
     * Adds new private key for storage.
     *
     * @param publicKeyId the public key ID
     * @param privateKey  The private key associated for this public key. It should be encrypted if account type is Normal.
     */
    public void add(UUID publicKeyId, byte[] privateKey, EmptyResponseCallback callback) {
        add(publicKeyId, privateKey, connection.getCredentials().password, callback);
    }

    /**
     * Adds new encrypted private key to Virgil Private Keys storage
     *
     * @param publicKeyId        the public key ID
     * @param privateKey         the private key associated for this public key.
     * @param privateKeyPassword the password of private key.
     */
    public void add(UUID publicKeyId, byte[] privateKey, String privateKeyPassword, EmptyResponseCallback callback) {
        if (connection.getAuthToken() == null) throw new AuthenticationException();
        VirgilCipher cipher = new VirgilCipher();
        cipher.addPasswordRecipient(Base64.decode(privateKeyPassword, Base64.NO_WRAP));
        byte[] encryptedPrivateKey = cipher.encrypt(privateKey, true);
        AddRequest body = new AddRequest(StringHelper.byteToBase64(encryptedPrivateKey), UUID.randomUUID().toString());
        connection.getPrivateKeysApi().add(connection.getAuthToken(), SignHelper.sign(privateKey, body), publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Removes the private key from service by specified public key id.
     *
     * @param publicKeyId the public key ID
     * @param privateKey  the public key ID digital signature. Verifies the possession of the private key.
     */
    public void remove(UUID publicKeyId, byte[] privateKey, EmptyResponseCallback callback) {
        if (connection.getAuthToken() == null) throw new AuthenticationException();
        UniqueRequest body = new UniqueRequest(UUID.randomUUID().toString());
        connection.getPrivateKeysApi().remove(connection.getAuthToken(), SignHelper.sign(privateKey, body), publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * User authentication.
     *
     * @param credentials the information about user.
     */
    public void authentication(Credentials credentials, ResponseCallback<Token> callback) {
        AuthRequest body = new AuthRequest(credentials.password, new UserData(UserDataClass.USER_ID, UserDataType.EMAIL_ID, credentials.userName));
        connection.getPrivateKeysApi().authentications(body).enqueue(callback);
    }
}
