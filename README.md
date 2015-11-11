# virgil-android
Virgil SDK for Android

Sample:

    final Connection myConnection = new Connection("APP_TOKEN", URI.create("https://keys.virgilsecurity.com/v2/"));
    final PublicKeysClient myPublicKeysClient = new PublicKeysClient(myConnection);

    myPublicKeysClient.search("demo@virgilsecurity.com", new ResponseCallback<PublicKey>() {
        @Override
        public void onResult(@Nullable PublicKey object) {
            final String data = "Encrypt Me, Pleeeeeeease.";
            final VirgilCipher cipher = new VirgilCipher();
            final byte[] encryptedData = cipher.encrypt(data.getBytes(Charset.forName("UTF-8")));
            final VirgilSigner virgilSigner = new VirgilSigner();
            final byte[] sign = virgilSigner.sign(encryptedData, myPrivateKetBytes);
        }

        @Override
        public void onError(Error error) {
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
