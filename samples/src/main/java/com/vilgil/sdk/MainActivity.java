package com.vilgil.sdk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.virgilsecurity.crypto.VirgilKeyPair;
import com.virgilsecurity.sdk.keys.clients.PublicKeysClient;
import com.virgilsecurity.sdk.keys.clients.UserDataClient;
import com.virgilsecurity.sdk.keys.http.Connection;
import com.virgilsecurity.sdk.keys.http.EmptyResponseCallback;
import com.virgilsecurity.sdk.keys.http.Error;
import com.virgilsecurity.sdk.keys.http.ResponseCallback;
import com.virgilsecurity.sdk.keys.model.PublicKey;
import com.virgilsecurity.sdk.keys.model.PublicKeyExtended;
import com.virgilsecurity.sdk.keys.model.PublicKeyOperationConfirmation;
import com.virgilsecurity.sdk.keys.model.UserData;
import com.virgilsecurity.sdk.keys.model.UserDataClass;
import com.virgilsecurity.sdk.keys.model.UserDataType;
import com.virgilsecurity.sdk.keys.model.request.PublicKeyOperationRequest;
import com.virgilsecurity.sdk.privatekeys.clients.ContainerClient;
import com.virgilsecurity.sdk.privatekeys.clients.PrivateKeysClient;
import com.virgilsecurity.sdk.privatekeys.model.Container;
import com.virgilsecurity.sdk.privatekeys.model.ContainerType;
import com.virgilsecurity.sdk.privatekeys.model.PrivateKey;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String publicKeyId;
    String userDataId;
    byte[] publicKey;
    byte[] privateKey;
    String token;
    private Connection keysConnection = new Connection("72ec86432dc166106289d0b51a879371", URI.create("https://keys.virgilsecurity.com/v2/"));
    private PublicKeysClient publicKeysClient = new PublicKeysClient(keysConnection);
    private UserDataClient userDataClient = new UserDataClient(keysConnection);
    private com.virgilsecurity.sdk.privatekeys.http.Connection privateConnection = new com.virgilsecurity.sdk.privatekeys.http.Connection("72ec86432dc166106289d0b51a879371");
    private ContainerClient containerClient = new ContainerClient(privateConnection);
    private PrivateKeysClient privateKeysClient = new PrivateKeysClient(privateConnection);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.confirm_btn).setOnClickListener(this);
        findViewById(R.id.container_btn).setOnClickListener(this);
        findViewById(R.id.create_btn).setOnClickListener(this);
        findViewById(R.id.search_btn).setOnClickListener(this);
        findViewById(R.id.delete_btn).setOnClickListener(this);
        findViewById(R.id.auth_btn).setOnClickListener(this);
        findViewById(R.id.get_by_id_btn).setOnClickListener(this);
        findViewById(R.id.searchExtended_btn).setOnClickListener(this);
        findViewById(R.id.delete_with_private_btn).setOnClickListener(this);
        findViewById(R.id.confirm_delete_btn).setOnClickListener(this);
        findViewById(R.id.confirm_reset_btn).setOnClickListener(this);
        findViewById(R.id.reset_btn).setOnClickListener(this);
        findViewById(R.id.update_btn).setOnClickListener(this);
        findViewById(R.id.reset_confirm_btn).setOnClickListener(this);
        findViewById(R.id.delete_user_btn).setOnClickListener(this);
        findViewById(R.id.container_type_btn).setOnClickListener(this);
        findViewById(R.id.container_remove_btn).setOnClickListener(this);
        findViewById(R.id.reset_password_btn).setOnClickListener(this);
        findViewById(R.id.confirm_password_reset_btn).setOnClickListener(this);
        findViewById(R.id.get_private_btn).setOnClickListener(this);
        findViewById(R.id.add_private_btn).setOnClickListener(this);
        findViewById(R.id.remove_private_btn).setOnClickListener(this);

        VirgilKeyPair virgilKeyPair = new VirgilKeyPair();
        publicKey = virgilKeyPair.publicKey();
        privateKey = virgilKeyPair.privateKey();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_btn: {
                ArrayList<UserData> list = new ArrayList<>();
                list.add(new UserData(UserDataClass.USER_ID, UserDataType.EMAIL_ID, ((EditText) findViewById(R.id.user_text)).getText().toString()));

                publicKeysClient.create(publicKey, privateKey, list, new ResponseCallback<PublicKeyExtended>() {
                    @Override
                    public void onResult(@Nullable PublicKeyExtended object) {
                        publicKeyId = object.getUserData().get(0).userDataId.getPublicKeyId();
                        userDataId = object.getUserData().get(0).userDataId.getUserDataId();
                        Toast.makeText(MainActivity.this, "Created: " + publicKeyId, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.confirm_btn: {
                userDataClient.confirm(UUID.fromString(userDataId), ((EditText) findViewById(R.id.code_text)).getText().toString(), UUID.fromString(publicKeyId), privateKey, new EmptyResponseCallback() {
                    @Override
                    public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.get_by_id_btn: {
                publicKeysClient.getById(UUID.fromString(publicKeyId), new ResponseCallback<PublicKey>() {
                    @Override
                    public void onResult(@Nullable PublicKey object) {
                        Toast.makeText(MainActivity.this, "Get By Id: " + object.getPublicKeyId().getPublicKeyId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.search_btn: {
                publicKeysClient.search(((EditText) findViewById(R.id.user_text)).getText().toString(), new ResponseCallback<PublicKey>() {
                    @Override
                    public void onResult(@Nullable PublicKey object) {
                        Toast.makeText(MainActivity.this, "Searched for user: " + object.getPublicKeyId().getPublicKeyId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.searchExtended_btn: {
                publicKeysClient.searchExtended(UUID.fromString(publicKeyId), privateKey, new ResponseCallback<PublicKey>() {
                    @Override
                    public void onResult(@Nullable PublicKey object) {
                        Toast.makeText(MainActivity.this, "Searched extended: " + object.getPublicKeyId().getPublicKeyId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.delete_with_private_btn: {
                publicKeysClient.delete(UUID.fromString(publicKeyId), privateKey, new EmptyResponseCallback() {
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.delete_btn: {
                publicKeysClient.delete(UUID.fromString(publicKeyId), new ResponseCallback<PublicKeyOperationRequest>() {
                    @Override
                    public void onResult(@Nullable PublicKeyOperationRequest object) {
                        token = object.actionToken;
                        Toast.makeText(MainActivity.this, "Deleted: " + object.actionToken, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.confirm_delete_btn: {
                publicKeysClient.confirmDelete(UUID.fromString(publicKeyId), new PublicKeyOperationConfirmation(token, ((EditText) findViewById(R.id.delete_confirm_text)).getText().toString()), new EmptyResponseCallback() {
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.reset_btn: {
                VirgilKeyPair virgilKeyPair = new VirgilKeyPair();
                publicKey = virgilKeyPair.publicKey();
                privateKey = virgilKeyPair.privateKey();

                publicKeysClient.reset(UUID.fromString(publicKeyId), publicKey, privateKey, new ResponseCallback<PublicKeyOperationRequest>() {
                    @Override
                    public void onResult(@Nullable PublicKeyOperationRequest object) {
                        token = object.actionToken;
                        Toast.makeText(MainActivity.this, "Reset: " + object.actionToken, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.confirm_reset_btn: {
                publicKeysClient.confirmReset(UUID.fromString(publicKeyId), privateKey, new PublicKeyOperationConfirmation(token, ((EditText) findViewById(R.id.reset_confirm_text)).getText().toString()), new ResponseCallback<PublicKey>() {
                    @Override
                    public void onResult(@Nullable PublicKey object) {
                        Toast.makeText(MainActivity.this, "Confirmed: " + object.getPublicKeyId().getPublicKeyId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.update_btn: {
                VirgilKeyPair virgilKeyPair = new VirgilKeyPair();
                final byte[] newPublicKey = virgilKeyPair.publicKey();
                final byte[] newPrivateKey = virgilKeyPair.privateKey();
                publicKeysClient.update(UUID.fromString(publicKeyId), newPublicKey, newPrivateKey, privateKey, new ResponseCallback<PublicKey>() {
                    @Override
                    public void onResult(@Nullable PublicKey object) {
                        publicKey = newPublicKey;
                        privateKey = newPrivateKey;
                        publicKeyId = object.getPublicKeyId().getPublicKeyId();
                        Toast.makeText(MainActivity.this, "Updated: " + object.getPublicKeyId().getPublicKeyId(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.reset_confirm_btn: {
                userDataClient.resendConfirmation(UUID.fromString(userDataId), UUID.fromString(publicKeyId), privateKey, new EmptyResponseCallback() {
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.delete_user_btn: {
                userDataClient.delete(UUID.fromString(userDataId), UUID.fromString(publicKeyId), privateKey, new ResponseCallback<UserData>() {
                    @Override
                    public void onResult(@Nullable UserData object) {
                        Toast.makeText(MainActivity.this, "Deleted user", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }


            case R.id.container_btn: {
                containerClient.initialize(ContainerType.NORMAL, UUID.fromString(publicKeyId), privateKey, "12345678", new EmptyResponseCallback() {
                    @Override
                    public void onError(com.virgilsecurity.sdk.keys.http.Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.auth_btn: {
                privateConnection.setCredentials(((EditText) findViewById(R.id.user_text)).getText().toString(), "12345678");

                break;
            }

            case R.id.container_type_btn: {
                containerClient.getContainerType(UUID.fromString(publicKeyId), new ResponseCallback<Container>() {
                    @Override
                    public void onResult(@Nullable Container object) {
                        Toast.makeText(MainActivity.this, "Container type: " + object.type, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.container_remove_btn: {
                containerClient.remove(UUID.fromString(publicKeyId), privateKey, new EmptyResponseCallback() {
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            }

            case R.id.reset_password_btn: {
                containerClient.resetPassword(((EditText) findViewById(R.id.user_text)).getText().toString(), "12321", new EmptyResponseCallback() {
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            }

            case R.id.confirm_password_reset_btn: {
                containerClient.confirm(((EditText) findViewById(R.id.reset_password_confirm_text)).getText().toString(), new EmptyResponseCallback() {
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.get_private_btn: {
                privateKeysClient.get(UUID.fromString(publicKeyId), new ResponseCallback<PrivateKey>() {
                    @Override
                    public void onResult(@Nullable PrivateKey object) {
                        Toast.makeText(MainActivity.this, "Private key", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }

            case R.id.add_private_btn: {
                privateKeysClient.add(UUID.fromString(publicKeyId), privateKey, new EmptyResponseCallback() {
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                break;
            }

            case R.id.remove_private_btn: {
                privateKeysClient.remove(UUID.fromString(publicKeyId), privateKey, new EmptyResponseCallback() {
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
        }
    }
}
