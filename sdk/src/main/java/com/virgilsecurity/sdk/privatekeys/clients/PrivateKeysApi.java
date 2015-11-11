package com.virgilsecurity.sdk.privatekeys.clients;

import com.virgilsecurity.sdk.keys.http.Headers;
import com.virgilsecurity.sdk.keys.model.request.UniqueRequest;
import com.virgilsecurity.sdk.privatekeys.model.PrivateKey;
import com.virgilsecurity.sdk.privatekeys.model.Token;
import com.virgilsecurity.sdk.privatekeys.model.request.AddRequest;
import com.virgilsecurity.sdk.privatekeys.model.request.AuthRequest;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.HTTP;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public interface PrivateKeysApi {
    @GET("/v2/private-key/public-key-id/{public-key-id}")
    Call<PrivateKey> get(@Header(Headers.REQUEST_AUTHENTICATION_HEADER) String authToken, @Path("public-key-id") String publicKeyId);

    @POST("/v2/private-key")
    Call<Object> add(@Header(Headers.REQUEST_AUTHENTICATION_HEADER) String authToken, @Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Body AddRequest body);

    @HTTP(method = "DELETE", path = "/v2/private-key", hasBody = true)
    Call<Object> remove(@Header(Headers.REQUEST_AUTHENTICATION_HEADER) String authToken, @Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Body UniqueRequest body);

    @POST("/v2/authentication/get-token")
    Call<Token> authentications(@Body AuthRequest body);
}
