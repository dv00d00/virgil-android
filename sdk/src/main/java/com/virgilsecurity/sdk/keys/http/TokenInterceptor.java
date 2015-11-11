package com.virgilsecurity.sdk.keys.http;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */
public class TokenInterceptor implements Interceptor {
    private final String appToken;

    public TokenInterceptor(String appToken) {
        this.appToken = appToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest.newBuilder()
                .addHeader(Headers.REQUEST_APPLICATION_TOKEN, appToken)
                .build();
        return chain.proceed(requestWithUserAgent);
    }

}
