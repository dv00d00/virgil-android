package com.virgilsecurity.sdk.privatekeys.clients;

import android.support.annotation.NonNull;

import com.virgilsecurity.sdk.keys.http.Headers;
import com.virgilsecurity.sdk.keys.model.request.UniqueRequest;
import com.virgilsecurity.sdk.privatekeys.model.Container;
import com.virgilsecurity.sdk.privatekeys.model.request.ConfirmRequest;
import com.virgilsecurity.sdk.privatekeys.model.request.InitializeRequest;
import com.virgilsecurity.sdk.privatekeys.model.request.ResetPasswordRequest;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.HTTP;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public interface ContainerApi {

    @POST("/v2/container")
    Call<Object> initialize(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Body InitializeRequest body);

    @GET("/v2/container/public-key-id/{public-key-id}")
    Call<Container> getContainerType(@NonNull @Header(Headers.REQUEST_AUTHENTICATION_HEADER) String authToken, @Path("public-key-id") String publicKeyId);

    @HTTP(method = "DELETE", path = "/v2/container", hasBody = true)
    Call<Object> remove(@NonNull @Header(Headers.REQUEST_AUTHENTICATION_HEADER) String authToken, @Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Body UniqueRequest body);

    @PUT("/v2/container/actions/reset-password")
    Call<Object> resetPassword(@Body ResetPasswordRequest body);

    @POST("/v2/container/actions/persist")
    Call<Object> confirm(@Body ConfirmRequest body);
}
