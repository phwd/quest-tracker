package com.oculus.secure.trustedapp;

public class AppSignatureHash {
    private String name;
    private String sha1Hash;
    private String sha256Hash;

    public AppSignatureHash(String sha1Hash2, String sha256Hash2) {
        this("test", sha1Hash2, sha256Hash2);
    }

    public AppSignatureHash(String name2, String sha1Hash2, String sha256Hash2) {
        this.name = name2;
        if (sha1Hash2 == null) {
            throw new SecurityException("Must provide SHA1 key hash.");
        }
        setSha1Hash(sha1Hash2);
        if (sha256Hash2 != null) {
            setSha256Hash(sha256Hash2);
        }
    }

    public String getSha1Hash() {
        return this.sha1Hash;
    }

    public void setSha1Hash(String sha1Hash2) {
        if (sha1Hash2.length() != 27) {
            throw new SecurityException("Invalid SHA1 key hash - should be 160-bit.");
        }
        this.sha1Hash = sha1Hash2;
    }

    public String getSha256Hash() {
        return this.sha256Hash;
    }

    public void setSha256Hash(String sha256Hash2) {
        if (sha256Hash2.length() != 43) {
            throw new SecurityException("Invalid SHA256 key hash - should be 256-bit.");
        }
        this.sha256Hash = sha256Hash2;
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
        return this.sha256Hash != null ? this.sha256Hash.hashCode() : this.sha1Hash.hashCode();
    }
}
