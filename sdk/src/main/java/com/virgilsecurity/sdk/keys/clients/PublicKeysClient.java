package com.virgilsecurity.sdk.keys.clients;

import android.support.annotation.NonNull;
import android.util.Base64;

import com.virgilsecurity.sdk.keys.helpers.Ensure;
import com.virgilsecurity.sdk.keys.helpers.SignHelper;
import com.virgilsecurity.sdk.keys.http.Connection;
import com.virgilsecurity.sdk.keys.http.EmptyResponseCallback;
import com.virgilsecurity.sdk.keys.http.ResponseCallback;
import com.virgilsecurity.sdk.keys.model.PublicKey;
import com.virgilsecurity.sdk.keys.model.PublicKeyExtended;
import com.virgilsecurity.sdk.keys.model.PublicKeyOperationConfirmation;
import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.request.ConfirmRequest;
import com.virgilsecurity.sdk.keys.model.request.CreatePublicKeyRequest;
import com.virgilsecurity.sdk.keys.model.request.PublicKeyOperationRequest;
import com.virgilsecurity.sdk.keys.model.request.SearchPublicKeyRequest;
import com.virgilsecurity.sdk.keys.model.request.UniqueRequest;
import com.virgilsecurity.sdk.keys.model.request.UpdatePublicKeyRequest;

import java.util.List;
import java.util.UUID;

/**
 * Created by Andrii Nutskovskyi on 06.10.15.
 */

/**
 * Provides common methods to interact with Public Keys resource endpoints.
 */
public class PublicKeysClient {
    private Connection connection;

    /**
     * Initializes a new instance of the {@link PublicKeysClient} class with the default implementations.
     */
    public PublicKeysClient(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates the new public key.
     *
     * @param publicKey  the public key value.
     * @param privateKey the private key value. Private key is not being sent, but used to sign the HTTP request body.
     * @param userData   the user data collection.
     * @param callback   the callback for receiving the result
     */
    public void create(@NonNull byte[] publicKey, @NonNull byte[] privateKey, @NonNull List<UserData> userData, @NonNull ResponseCallback<PublicKeyExtended> callback) {
        Ensure.argumentNotNull(publicKey, "publicKey");
        Ensure.argumentNotNull(privateKey, "privateKey");
        Ensure.argumentNotNull(userData, "userData");

        CreatePublicKeyRequest body = new CreatePublicKeyRequest(Base64.encodeToString(publicKey, Base64.NO_WRAP), userData, UUID.randomUUID().toString());
        connection.getPublicKeyApi().createPublicKey(SignHelper.sign(privateKey, body), UUID.randomUUID().toString(), body).enqueue(callback);
    }

    /**
     * Updates the specified public key.
     *
     * @param publicKeyId   the public key identifier.
     * @param newPublicKey  the new public key value.
     * @param newPrivateKey the new private key value. Private key is not being sent, but used to sign the part of HTTP request body.
     * @param oldPrivateKey the old private key. Private key is not being sent, but used to sign the HTTP request body.
     */
    public void update(UUID publicKeyId, @NonNull byte[] newPublicKey, @NonNull byte[] newPrivateKey, byte[] oldPrivateKey, @NonNull ResponseCallback<PublicKey> callback) {
        Ensure.argumentNotNull(newPublicKey, "newPublicKey");
        Ensure.argumentNotNull(newPrivateKey, "newPrivateKey");

        String uuid = UUID.randomUUID().toString();
        UpdatePublicKeyRequest body = new UpdatePublicKeyRequest(Base64.encodeToString(newPublicKey, Base64.NO_WRAP), uuid, SignHelper.sign(newPrivateKey, uuid));
        connection.getPublicKeyApi().updatePublicKey(SignHelper.sign(oldPrivateKey, body), publicKeyId.toString(), publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Deletes public key.
     *
     * @param publicKeyId the public key identifier.
     * @param privateKey  the private key valye. Private key is not being sent, but used to sign the HTTP request body.
     */
    public void delete(UUID publicKeyId, @NonNull byte[] privateKey, @NonNull EmptyResponseCallback callback) {
        Ensure.argumentNotNull(privateKey, "privateKey");
        UniqueRequest body = new UniqueRequest(UUID.randomUUID().toString());
        connection.getPublicKeyApi().deletePublicKeySigned(SignHelper.sign(privateKey, body), publicKeyId.toString(), publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Deletes public key without HTTP request sign by known private key.
     * Should be used when private key is lost.
     *
     * @param publicKeyId the public key identifier.
     */
    public void delete(UUID publicKeyId, @NonNull ResponseCallback<PublicKeyOperationRequest> callback) {
        UniqueRequest body = new UniqueRequest(UUID.randomUUID().toString());

        connection.getPublicKeyApi().deletePublicKeyUnsigned(publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Confirms the delete operation.
     *
     * @param publicKeyId  the public key identifier.
     * @param confirmation the {@link PublicKeyOperationConfirmation} object.
     */
    public void confirmDelete(UUID publicKeyId, @NonNull PublicKeyOperationConfirmation confirmation, @NonNull EmptyResponseCallback callback) {
        Ensure.argumentNotNull(confirmation, "confirmation");

        ConfirmRequest body = new ConfirmRequest(confirmation.getActionToken(), confirmation.getConfirmationCodes());
        connection.getPublicKeyApi().confirmDelete(publicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Gets the PublicKey by its identifier.
     *
     * @param publicKeyId the public key identifier.
     */
    public void getById(UUID publicKeyId, @NonNull ResponseCallback<PublicKey> callback) {
        connection.getPublicKeyApi().getPublicKeyById(publicKeyId.toString()).enqueue(callback);
    }

    /**
     * Searches PublicKey by user identifier.
     *
     * @param userId the user identifier.
     */
    public void search(@NonNull String userId, @NonNull ResponseCallback<PublicKey> callback) {
        Ensure.argumentNotNull(userId, "userId");

        SearchPublicKeyRequest body = new SearchPublicKeyRequest(userId, UUID.randomUUID().toString());
        connection.getPublicKeyApi().search(body).enqueue(callback);
    }

    /**
     * Resets the specified old public key with new value.
     *
     * @param oldPublicKeyId the old public key identifier.
     * @param newPublicKey   the new public key value.
     * @param newPrivateKey  the new private key value. Private key is not being sent, but used to sign the HTTP request body.
     */
    public void reset(UUID oldPublicKeyId, @NonNull byte[] newPublicKey, @NonNull byte[] newPrivateKey, @NonNull ResponseCallback<PublicKeyOperationRequest> callback) {
        Ensure.argumentNotNull(newPublicKey, "newPublicKey");
        Ensure.argumentNotNull(newPrivateKey, "newPrivateKey");

        UpdatePublicKeyRequest body = new UpdatePublicKeyRequest(Base64.encodeToString(newPublicKey, Base64.NO_WRAP), UUID.randomUUID().toString());
        connection.getPublicKeyApi().reset(SignHelper.sign(newPrivateKey, body), oldPublicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Confirms the reset.
     *
     * @param oldPublicKeyId the old public key identifier.
     * @param newPrivateKey  the new private key value. Private key is not being sent, but used to sign the HTTP request body.
     * @param confirmation   The {@link PublicKeyOperationConfirmation} object.
     */
    public void confirmReset(UUID oldPublicKeyId, byte[] newPrivateKey, PublicKeyOperationConfirmation confirmation, @NonNull ResponseCallback<PublicKey> callback) {
        Ensure.argumentNotNull(newPrivateKey, "newPrivateKey");
        Ensure.argumentNotNull(confirmation, "confirmation");

        ConfirmRequest body = new ConfirmRequest(confirmation.getActionToken(), confirmation.getConfirmationCodes());
        connection.getPublicKeyApi().confirmReset(oldPublicKeyId.toString(), body).enqueue(callback);
    }

    /**
     * Gets extended public key data by signing request with private key.
     *
     * @param publicKeyId the public key identifier.
     * @param privateKey  the private key value. Private key is not being sent, but used to sign the HTTP request body
     */
    public void searchExtended(UUID publicKeyId, byte[] privateKey, @NonNull ResponseCallback<PublicKey> callback) {
        Ensure.argumentNotNull(privateKey, "privateKey");

        SearchPublicKeyRequest body = new SearchPublicKeyRequest(UUID.randomUUID().toString());
        connection.getPublicKeyApi().searchExtended(SignHelper.sign(privateKey, body), publicKeyId.toString(), body).enqueue(callback);
    }
}
