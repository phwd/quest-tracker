package com.facebook.secure.trustedapp;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class AppIdentity {
    @Nullable
    private final String domainName;
    private final List<String> packageNames;
    @Nullable
    private final AppSignatureHash signatureHash;
    private final int uid;
    @Nullable
    private final String versionName;

    public AppIdentity(int i, List<String> list, @Nullable AppSignatureHash appSignatureHash, @Nullable String str, @Nullable String str2) {
        this.uid = i;
        this.packageNames = Collections.unmodifiableList(list);
        this.signatureHash = appSignatureHash;
        this.versionName = str;
        this.domainName = str2;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("At least one package name is required");
        }
    }

    public List<String> getPackageNames() {
        return this.packageNames;
    }

    @Nullable
    public AppSignatureHash getSignatureHash() {
        return this.signatureHash;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("AppIdentity{uid=");
        sb.append(this.uid);
        sb.append(", packageNames=");
        sb.append(this.packageNames);
        sb.append(", sha1=");
        AppSignatureHash appSignatureHash = this.signatureHash;
        sb.append(appSignatureHash == null ? "null" : appSignatureHash.getSha1Hash());
        sb.append(", sha2=");
        AppSignatureHash appSignatureHash2 = this.signatureHash;
        if (appSignatureHash2 == null) {
            str = "null";
        } else {
            str = appSignatureHash2.getSha256Hash();
        }
        sb.append(str);
        sb.append(", version=");
        String str2 = this.versionName;
        if (str2 == null) {
            str2 = "null";
        }
        sb.append(str2);
        sb.append(", domain=");
        String str3 = this.domainName;
        if (str3 == null) {
            str3 = "null";
        }
        sb.append(str3);
        sb.append('}');
        return sb.toString();
    }
}
