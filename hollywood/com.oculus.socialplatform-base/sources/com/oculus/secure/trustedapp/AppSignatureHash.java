package com.oculus.secure.trustedapp;

public class AppSignatureHash {
    public static final int SHA1_KEY_HASH_LENGTH = 27;
    public static final String SHA256 = "SHA-256";
    public static final int SHA256_KEY_HASH_LENGTH = 43;
    public String name;
    public String sha1Hash;
    public String sha256Hash;

    public boolean equals(Object obj) {
        if (!(obj instanceof AppSignatureHash)) {
            return false;
        }
        AppSignatureHash appSignatureHash = (AppSignatureHash) obj;
        String str = this.sha256Hash;
        String str2 = appSignatureHash.sha256Hash;
        if (str != null && str2 != null) {
            return str.equals(str2);
        }
        String str3 = this.sha1Hash;
        String str4 = appSignatureHash.sha1Hash;
        if (str3 == null || str4 == null || !str3.equals(str4)) {
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

    public String getName() {
        return this.name;
    }

    public String getSha1Hash() {
        return this.sha1Hash;
    }

    public String getSha256Hash() {
        return this.sha256Hash;
    }

    public void setSha1Hash(String str) {
        if (str.length() == 27) {
            this.sha1Hash = str;
            return;
        }
        throw new SecurityException("Invalid SHA1 key hash - should be 160-bit.");
    }

    public void setSha256Hash(String str) {
        if (str.length() == 43) {
            this.sha256Hash = str;
            return;
        }
        throw new SecurityException("Invalid SHA256 key hash - should be 256-bit.");
    }

    public void setName(String str) {
        this.name = str;
    }

    public AppSignatureHash(String str, String str2) {
        this("test", str, str2);
    }

    public AppSignatureHash(String str, String str2, String str3) {
        this.name = str;
        if (str2 != null) {
            setSha1Hash(str2);
            if (str3 != null) {
                setSha256Hash(str3);
                return;
            }
            return;
        }
        throw new SecurityException("Must provide SHA1 key hash.");
    }
}
