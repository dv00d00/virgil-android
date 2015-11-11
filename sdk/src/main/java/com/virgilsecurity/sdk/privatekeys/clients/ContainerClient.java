package com.virgilsecurity.sdk.privatekeys.clients;

import android.support.annotation.NonNull;

import com.virgilsecurity.sdk.keys.helpers.SignHelper;
import com.virgilsecurity.sdk.keys.http.EmptyResponseCallback;
import com.virgilsecurity.sdk.keys.http.ResponseCallback;
import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.UserDataClass;
import com.virgilsecurity.sdk.keys.model.UserDataType;
import com.virgilsecurity.sdk.keys.model.request.UniqueRequest;
import com.virgilsecurity.sdk.privatekeys.http.AuthenticationException;
import com.virgilsecurity.sdk.privatekeys.http.Connection;
import com.virgilsecurity.sdk.privatekeys.model.Container;
import com.virgilsecurity.sdk.privatekeys.model.ContainerType;
import com.virgilsecurity.sdk.privatekeys.model.request.ConfirmRequest;
import com.virgilsecurity.sdk.privatekeys.model.request.InitializeRequest;
import com.virgilsecurity.sdk.privatekeys.model.request.ResetPasswordRequest;

import java.util.UUID;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */

/**
 * Provides common methods to interact with Account (of Private Keys API) resource endpoints.
 */
public class ContainerClient {
    private Connection connection;

    /**
     * Initializes a new instance of the {@link ContainerClient} class.
     */
    public ContainerClient(Connection connection) {
        this.connection = connection;
    }

    /**
     * Initializes an container for private keys storage. It is important to have public key published on Virgil Keys service.
     *
     * @param containerType the type of private keys container.
     * @param publicKeyId   the unique identifier of public key in Virgil Keys service.
     * @param privateKey    the private key is a part of public key which was published on Virgil Keys service.
     * @param password      this password will be used to authenticate access to the container keys.
     */
    public void initialize(ContainerType containerType, UUID publicKeyId, byte[] privateKey, String password, @NonNull EmptyResponseCallback callback) {
        InitializeRequest body = new InitializeRequest(containerType, password, UUID.randomUUID().toString());
        connection.getContainerApi().initialize(SignHelper.sign(privateKey, body), publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Gets the type of private keys container by public key identifier from Virgil Keys service.
     *
     * @param publicKeyId the unique identifier of public key in Virgil Keys service.
     */
    public void getContainerType(UUID publicKeyId, @NonNull ResponseCallback<Container> callback) {
        if (connection.getAuthToken() == null) throw new AuthenticationException();
        connection.getContainerApi().getContainerType(connection.getAuthToken(), publicKeyId.toString()).enqueue(callback);
    }

    /**
     * Removes account from Private Keys Service.
     *
     * @param publicKeyId the public key ID.
     * @param privateKey  the public key ID digital signature. Verifies the possession of the private key.
     */
    public void remove(UUID publicKeyId, byte[] privateKey, @NonNull EmptyResponseCallback callback) {
        if (connection.getAuthToken() == null) throw new AuthenticationException();
        UniqueRequest body = new UniqueRequest(UUID.randomUUID().toString());
        connection.getContainerApi().remove(connection.getAuthToken(), SignHelper.sign(privateKey, body), publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Resets the account password.
     *
     * @param email       the User's email
     * @param newPassword new Private Keys account password.
     */
    public void resetPassword(String email, String newPassword, @NonNull EmptyResponseCallback callback) {
        ResetPasswordRequest body = new ResetPasswordRequest(new UserData(UserDataClass.USER_ID, UserDataType.EMAIL_ID, email), newPassword);
        connection.getContainerApi().resetPassword(body).enqueue(callback);
    }

    /**
     * Verifies the Private Keys account password reset.
     *
     * @param token the confirmation token.
     */
    public void confirm(String token, @NonNull EmptyResponseCallback callback) {
        //TODO: 500 error response (check);
        connection.getContainerApi().confirm(new ConfirmRequest(token)).enqueue(callback);
    }
}
