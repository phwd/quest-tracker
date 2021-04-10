package com.oculus.vrapi;

public class AppSignatureHash {
    private static final int SHA1_KEY_HASH_LENGTH = 27;
    public static final String SHA256 = "SHA-256";
    private static final int SHA256_KEY_HASH_LENGTH = 43;
    private String name;
    private String sha1Hash;
    private String sha256Hash;

    public AppSignatureHash(String sha1Hash2, String sha256Hash2) {
        this("test", sha1Hash2, sha256Hash2);
    }

    public AppSignatureHash(String name2, String sha1Hash2, String sha256Hash2) {
        this.name = name2;
        if (sha1Hash2 != null) {
            setSha1Hash(sha1Hash2);
            if (sha256Hash2 != null) {
                setSha256Hash(sha256Hash2);
                return;
            }
            return;
        }
        throw new SecurityException("Must provide SHA1 key hash.");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getSha1Hash() {
        return this.sha1Hash;
    }

    public void setSha1Hash(String sha1Hash2) {
        if (sha1Hash2.length() == SHA1_KEY_HASH_LENGTH) {
            this.sha1Hash = sha1Hash2;
            return;
        }
        throw new SecurityException("Invalid SHA1 key hash - should be 160-bit.");
    }

    public String getSha256Hash() {
        return this.sha256Hash;
    }

    public void setSha256Hash(String sha256Hash2) {
        if (sha256Hash2.length() == SHA256_KEY_HASH_LENGTH) {
            this.sha256Hash = sha256Hash2;
            return;
        }
        throw new SecurityException("Invalid SHA256 key hash - should be 256-bit.");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AppSignatureHash)) {
            return false;
        }
        AppSignatureHash signature = (AppSignatureHash) obj;
        String mySha2 = getSha256Hash();
        String signatureSha2 = signature.getSha256Hash();
        if (mySha2 != null && signatureSha2 != null) {
            return mySha2.equals(signatureSha2);
        }
        String mySha1 = getSha1Hash();
        String signatureSha1 = signature.getSha1Hash();
        if (mySha1 == null || signatureSha1 == null || !mySha1.equals(signatureSha1)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.sha256Hash;
        if (str == null) {
            str = this.sha1Hash;
        }
        return str.hashCode();
    }
}
