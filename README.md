# virgil-android
Virgil SDK for Android

Sample

    Connection myConnection = new Connection("APP_TOKEN", URI.create("https://keys.virgilsecurity.com/v2/"));
    PublicKeysClient myPublicKeysClient = new PublicKeysClient(myConnection);
    
    myPublicKeysClient.search("demo@virgilsecurity.com", new ResponseCallback<PublicKey>() {
        @Override
        public void onResult(@Nullable PublicKey object) {
            String data = "Encrypt Me, Pleeeeeeease.";
            VirgilCipher cipher = new VirgilCipher();
            final byte[] encryptedData = cipher.encrypt(data.getBytes(Charset.forName("UTF-8")));
            final VirgilSigner virgilSigner = new VirgilSigner();
            virgilSigner.sign(encryptedData, myPrivateKetBytes);
        }
    
        @Override
        public void onError(Error error) {
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
