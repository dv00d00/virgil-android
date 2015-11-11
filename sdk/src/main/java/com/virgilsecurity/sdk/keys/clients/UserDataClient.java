package com.virgilsecurity.sdk.keys.clients;

import android.support.annotation.NonNull;

import com.virgilsecurity.sdk.keys.helpers.Ensure;
import com.virgilsecurity.sdk.keys.helpers.SignHelper;
import com.virgilsecurity.sdk.keys.http.Connection;
import com.virgilsecurity.sdk.keys.http.EmptyResponseCallback;
import com.virgilsecurity.sdk.keys.http.ResponseCallback;
import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.request.ConfirmUserDataRequest;
import com.virgilsecurity.sdk.keys.model.request.CreateUserDataRequest;
import com.virgilsecurity.sdk.keys.model.request.UniqueRequest;

import java.util.UUID;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */

/**
 * Provides common methods to interact with User Data resource endpoints.
 */
public class UserDataClient {
    private Connection connection;

    /**
     * Initializes a new instance of the {@link UserDataClient} class.
     *
     * @param connection the connection.
     */
    public UserDataClient(Connection connection) {
        this.connection = connection;
    }

    /**
     * Deletes the specified user data byt its identifier.
     *
     * @param userDataId  the user data identifier.
     * @param publicKeyId the public key identifier.
     * @param privateKey  the private key value. Private key is not being sent, but used to sign the HTTP request body.
     */
    public void delete(UUID userDataId, UUID publicKeyId, @NonNull byte[] privateKey, @NonNull ResponseCallback<UserData> callback) {
        Ensure.argumentNotNull(privateKey, "privateKey");
        UniqueRequest body = new UniqueRequest(UUID.randomUUID().toString());
        connection.getUserDataApi().delete(SignHelper.sign(privateKey, body), publicKeyId.toString(), userDataId.toString(), body).enqueue(callback);
    }

    /**
     * Adds the specified user data to known public key.
     *
     * @param userData    the {@link UserData} object.
     * @param publicKeyId the public key identifier.
     * @param privateKey  the private key value. Private key is not being sent, but used to sign the HTTP request body.
     */
    public void insert(@NonNull UserData userData, @NonNull UUID publicKeyId, @NonNull byte[] privateKey, @NonNull ResponseCallback<UserData> callback) {
        Ensure.argumentNotNull(privateKey, "privateKey");
        Ensure.argumentNotNull(userData, "userData");
        Ensure.userDataValid(userData);

        CreateUserDataRequest body = new CreateUserDataRequest(userData.userDataClass.toString(), userData.type.toString(), userData.value, UUID.randomUUID().toString());
        connection.getUserDataApi().create(SignHelper.sign(privateKey, body), publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Confirms the specified user data.
     * Unless confirmed user data stored on server would not show up in search requests.
     * On public key creation, public keys server will send confirmation code to the specified user id.
     *
     * @param userDataId       the user data identifier.
     * @param confirmationCode the confirmation code.
     * @param publicKeyId      the public key identifier.
     * @param privateKey       the private key valye. Private key is not being sent, but used to sign the HTTP request body.
     */
    public void confirm(UUID userDataId, String confirmationCode, UUID publicKeyId, byte[] privateKey, @NonNull EmptyResponseCallback callback) {
        Ensure.argumentNotNull(privateKey, "privateKey");
        Ensure.argumentNotNullOrEmptyString(confirmationCode, "confirmationCode");

        ConfirmUserDataRequest body = new ConfirmUserDataRequest(confirmationCode, UUID.randomUUID().toString());

        connection.getUserDataApi().confirm(SignHelper.sign(privateKey, body), publicKeyId.toString(), userDataId.toString(), body).enqueue(callback);
    }

    /**
     * Ask server to generate new confirmation code in the case when previous was lost or not delivered.
     *
     * @param userDataId  the user data identifier.
     * @param publicKeyId the public key identifier.
     * @param privateKey  the private key value. Private key is not being sent, but used to sign the HTTP request body.
     */
    public void resendConfirmation(UUID userDataId, UUID publicKeyId, byte[] privateKey, @NonNull EmptyResponseCallback callback) {

        UniqueRequest body = new UniqueRequest(UUID.randomUUID().toString());
        connection.getUserDataApi().resendConfirm(SignHelper.sign(privateKey, body), publicKeyId.toString(), userDataId.toString(), body).enqueue(callback);
    }
}
