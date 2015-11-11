package com.virgilsecurity.sdk.keys.clients;

import com.virgilsecurity.sdk.keys.http.Headers;
import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.request.ConfirmUserDataRequest;
import com.virgilsecurity.sdk.keys.model.request.CreateUserDataRequest;
import com.virgilsecurity.sdk.keys.model.request.UniqueRequest;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.HTTP;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */
public interface UserDataApi {
    @HTTP(method = "DELETE", path = "/v2/user-data/{user_data_id}", hasBody = true)
    Call<UserData> delete(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Path("user_data_id") String userDataId, @Body UniqueRequest body);

    @POST("/v2/user-data")
    Call<UserData> create(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Body CreateUserDataRequest body);

    @POST("/v2/user-data/{user_data_id}/persist")
    Call<Object> confirm(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Path("user_data_id") String userDataId, @Body ConfirmUserDataRequest body);

    @POST("/v2/user-data/{user_data_id}/actions/resend-confirmation")
    Call<Object> resendConfirm(@Header(Headers.REQUEST_SIGN_HEADER) String sign, @Header(Headers.REQUEST_SIGN_PUBLIC_KEY_ID_HEADER) String requestId, @Path("user_data_id") String userDataId, @Body UniqueRequest body);
}
