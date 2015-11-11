package com.virgilsecurity.sdk.keys.http;

import android.support.annotation.Nullable;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */
public abstract class ResponseCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Response<T> response, Retrofit retrofit) {
        if (response.code() < 400) onResult(response.body());
        else onError(new Error(response.code(), response.errorBody()));
    }

    @Override
    public void onFailure(Throwable t) {
        onError(new Error(t));
    }

    public abstract void onResult(@Nullable T object);

    public abstract void onError(Error error);
}
