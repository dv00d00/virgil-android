package com.virgilsecurity.sdk.keys.http;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */
public abstract class EmptyResponseCallback implements Callback<Object> {

    @Override
    public void onResponse(Response<Object> response, Retrofit retrofit) {
        if (response.code() >= 400) onError(new Error(response.code(), response.errorBody()));
    }

    @Override
    public void onFailure(Throwable t) {
        onError(new Error(t));
    }

    public abstract void onError(Error error);
}
