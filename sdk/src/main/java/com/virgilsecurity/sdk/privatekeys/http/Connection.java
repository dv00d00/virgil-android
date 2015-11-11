package com.virgilsecurity.sdk.privatekeys.http;

import android.support.annotation.Nullable;

import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.virgilsecurity.sdk.keys.http.LoggingInterceptor;
import com.virgilsecurity.sdk.keys.http.ResponseCallback;
import com.virgilsecurity.sdk.keys.http.TokenInterceptor;
import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.UserDataClass;
import com.virgilsecurity.sdk.keys.model.UserDataType;
import com.virgilsecurity.sdk.privatekeys.clients.ContainerApi;
import com.virgilsecurity.sdk.privatekeys.clients.PrivateKeysApi;
import com.virgilsecurity.sdk.privatekeys.model.Token;
import com.virgilsecurity.sdk.privatekeys.model.request.AuthRequest;

import java.net.URI;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Andrii Nutskovskyi on 13.10.15.
 */

/**
 * A connection for making HTTP requests against URI endpoints.
 */
public class Connection {
    private static final String defaultPath = "https://keys-private.virgilsecurity.com";
    public URI baseAddress;
    public Credentials credentials;
    private String appToken;
    private String authToken;
    private Retrofit retrofit;
    private ContainerApi containerApi;
    private PrivateKeysApi privateKeysApi;

    /**
     * Initializes a new instance of the {@link Connection} class.
     */
    public Connection(String appToken) {
        this(appToken, URI.create(defaultPath));
    }

    /**
     * Initializes a new instance of the {@link Connection} class.
     */
    public Connection(String appToken, URI baseAddress) {
        this(appToken, null, baseAddress);
    }

    /**
     * Initializes a new instance of the {@link Connection} class.
     */
    public Connection(String appToken, Credentials credentials, URI baseAddress) {
        this.appToken = appToken;
        this.authToken = null;
        this.baseAddress = baseAddress;

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new TokenInterceptor(appToken));
        client.interceptors().add(new LoggingInterceptor());

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseAddress.toString())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().disableHtmlEscaping().create()))
                .client(client)
                .build();

        setCredentials(credentials);
    }

    /**
     * Sets the Private Keys container credentials.
     */
    public void setCredentials(String userId, String password) {
        Credentials credentials = new Credentials(userId, password);
        setCredentials(credentials);
    }

    @Nullable
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Nullable
    private <T> T getAPIService(final Class<T> service) {
        return retrofit != null ? retrofit.create(service) : null;
    }

    public PrivateKeysApi getPrivateKeysApi() {
        return privateKeysApi != null ? privateKeysApi : getAPIService(PrivateKeysApi.class);
    }

    public ContainerApi getContainerApi() {
        return containerApi != null ? containerApi : getAPIService(ContainerApi.class);
    }

    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets the Private Keys container credentials.
     */
    public void setCredentials(Credentials credentials) {
        if (credentials == null) return;
        this.credentials = credentials;
        AuthRequest body = new AuthRequest(credentials.password, new UserData(UserDataClass.USER_ID, UserDataType.EMAIL_ID, credentials.userName));
        getPrivateKeysApi().authentications(body).enqueue(new ResponseCallback<Token>() {
            @Override
            public void onResult(@Nullable Token object) {
                setAuthToken(object.getToken());
            }

            @Override
            public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                Connection.this.credentials = null;
            }
        });
    }
}
