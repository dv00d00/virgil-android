package com.virgilsecurity.sdk.keys.http;

import android.support.annotation.Nullable;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.virgilsecurity.sdk.keys.clients.PublicKeyApi;
import com.virgilsecurity.sdk.keys.clients.UserDataApi;

import java.net.URI;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Andrii Nutskovskyi on 08.10.15.
 */
public class Connection {
    private String appToken;
    private URI baseAddress;
    private Retrofit retrofit;
    private PublicKeyApi publicKeyApi;
    private UserDataApi userDataApi;

    public Connection(String appToken, URI baseAddress) {
        this.appToken = appToken;
        this.baseAddress = baseAddress;

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new TokenInterceptor(appToken));
        client.interceptors().add(new LoggingInterceptor());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseAddress.toString())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().disableHtmlEscaping().create()))
                .client(client)
                .build();
    }

    @Nullable
    private <T> T getAPIService(final Class<T> service) {
        return retrofit != null ? retrofit.create(service) : null;
    }

    public PublicKeyApi getPublicKeyApi() {
        return publicKeyApi != null ? publicKeyApi : getAPIService(PublicKeyApi.class);
    }

    public UserDataApi getUserDataApi() {
        return userDataApi != null ? userDataApi : getAPIService(UserDataApi.class);
    }
}
