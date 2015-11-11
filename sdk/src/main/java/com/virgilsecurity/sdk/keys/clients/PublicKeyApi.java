package com.virgilsecurity.sdk.keys.clients;

import com.virgilsecurity.sdk.keys.http.Headers;
import com.virgilsecurity.sdk.keys.model.PublicKey;
import com.virgilsecurity.sdk.keys.model.PublicKeyExtended;
import com.virgilsecurity.sdk.keys.model.request.ConfirmRequest;
import com.virgilsecurity.sdk.keys.model.request.CreatePublicKeyRequest;
import com.virgilsecurity.sdk.keys.model.request.PublicKeyOperationRequest;
import com.virgilsecurity.sdk.keys.model.request.SearchPublicKeyRequest;
import com.virgilsecurity.sdk.keys.model.request.UniqueRequest;
import com.virgilsecurity.sdk.keys.model.request.UpdatePublicKeyRequest;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.HTTP;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */
public interface PublicKeyApi {

    @POST("/v2/public-key")
    Call<PublicKeyExtended> createPublicKey(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Body CreatePublicKeyRequest body);

    @PUT("/v2/public-key/{public-key-id}")
    Call<PublicKey> updatePublicKey(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Path("public-key-id") String publicKeyId, @Body UpdatePublicKeyRequest body);

    @HTTP(method = "DELETE", path = "/v2/public-key/{public-key-id}", hasBody = true)
    Call<Object> deletePublicKeySigned(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Path("public-key-id") String publicKeyId, @Body UniqueRequest body);

    @HTTP(method = "DELETE", path = "/v2/public-key/{public-key-id}", hasBody = true)
    Call<PublicKeyOperationRequest> deletePublicKeyUnsigned(@Path("public-key-id") String publicKeyId, @Body UniqueRequest body);

    @POST("/v2/public-key/{public-key-id}/persist")
    Call<Object> confirmDelete(@Path("public-key-id") String publicKeyId, @Body ConfirmRequest body);

    @GET("/v2/public-key/{public-key-id}")
    Call<PublicKey> getPublicKeyById(@Path("public-key-id") String publicKeyId);

    @POST("/v2/public-key/actions/grab")
    Call<PublicKey> search(@Body SearchPublicKeyRequest body);

    @POST("/v2/public-key/{public-key-id}/actions/reset")
    Call<PublicKeyOperationRequest> reset(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Path("public-key-id") String publicKeyId, @Body UpdatePublicKeyRequest body);

    @POST("/v2/public-key/{public-key-id}/persist")
    Call<PublicKey> confirmReset(@Path("public-key-id") String publicKeyId, @Body ConfirmRequest body);

    @POST("/v2/public-key/actions/grab")
    Call<PublicKey> searchExtended(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Body SearchPublicKeyRequest body);
}
