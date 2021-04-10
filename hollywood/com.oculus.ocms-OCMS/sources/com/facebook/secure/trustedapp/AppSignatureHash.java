package com.facebook.secure.trustedapp;

import java.io.Serializable;
import javax.annotation.Nullable;

public class AppSignatureHash implements Serializable {
    private static final int SHA1_KEY_HASH_LENGTH = 27;
    public static final String SHA256 = "SHA-256";
    private static final int SHA256_KEY_HASH_LENGTH = 43;
    private static final long serialVersionUID = 0;
    private String name;
    private String sha1Hash;
    @Nullable
    private String sha256Hash;

    public AppSignatureHash(@Nullable String str, @Nullable String str2) {
        this("test", str, str2);
    }

    public AppSignatureHash(String str, @Nullable String str2, @Nullable String str3) {
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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getSha1Hash() {
        return this.sha1Hash;
    }

    public void setSha1Hash(String str) {
        if (str.length() == 27) {
            this.sha1Hash = str;
            return;
        }
        throw new SecurityException("Invalid SHA1 key hash - should be 160-bit.");
    }

    @Nullable
    public String getSha256Hash() {
        return this.sha256Hash;
    }

    public void setSha256Hash(String str) {
        if (str.length() == 43) {
            this.sha256Hash = str;
            return;
        }
        throw new SecurityException("Invalid SHA256 key hash - should be 256-bit.");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AppSignatureHash)) {
            return false;
        }
        AppSignatureHash appSignatureHash = (AppSignatureHash) obj;
        String sha256Hash2 = getSha256Hash();
        String sha256Hash3 = appSignatureHash.getSha256Hash();
        if (sha256Hash2 != null && sha256Hash3 != null) {
            return sha256Hash2.equals(sha256Hash3);
        }
        String sha1Hash2 = getSha1Hash();
        String sha1Hash3 = appSignatureHash.getSha1Hash();
        if (sha1Hash2 == null || sha1Hash3 == null || !sha1Hash2.equals(sha1Hash3)) {
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
