# virgil-android
Virgil SDK for Android

Installation:

SDK Is Available in the Maven Central and JCenter repositories as well as in 

    maven {
        url  "http://dl.bintray.com/dv00d00/maven"
    }

Package ID: 

    dependencies {
        ...
        compile 'com.vergilsecurity.android:sdk:1.0.5'
        ...
    }

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

Full samples list could be found here: https://github.com/VirgilSecurity/virgil-android/blob/master/samples/src/main/java/com/vilgil/sdk/MainActivity.java
