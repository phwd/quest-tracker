package com.facebook.secure.trustedapp;

import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class AppIdentity {
    private final String domainName;
    private final List<String> packageNames;
    private final AppSignatureHash signatureHash;
    private final int uid;
    private final String versionName;

    public AppIdentity(int i, String str, AppSignatureHash appSignatureHash, String str2, String str3) {
        this(i, Collections.singletonList(str), appSignatureHash, str2, str3);
    }

    public AppIdentity(int i, List<String> list, AppSignatureHash appSignatureHash, String str, String str2) {
        this.uid = i;
        this.packageNames = Collections.unmodifiableList(list);
        this.signatureHash = appSignatureHash;
        this.versionName = str;
        this.domainName = str2;
        if (list.isEmpty()) {
            throw new IllegalArgumentException("At least one package name is required");
        }
    }

    public int getUid() {
        return this.uid;
    }

    public List<String> getPackageNames() {
        return this.packageNames;
    }

    public String getPackageName() {
        if (!this.packageNames.isEmpty()) {
            return this.packageNames.iterator().next();
        }
        throw new IllegalStateException("Invalid AppIdentity object: no package names");
    }

    public AppSignatureHash getSignatureHash() {
        return this.signatureHash;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append("AppIdentity{uid=");
        sb.append(this.uid);
        sb.append(", packageNames=");
        sb.append(this.packageNames);
        sb.append(", sha1=");
        AppSignatureHash appSignatureHash = this.signatureHash;
        if (appSignatureHash == null) {
            str = "null";
        } else {
            str = appSignatureHash.getSha1Hash();
        }
        sb.append(str);
        sb.append(", sha2=");
        AppSignatureHash appSignatureHash2 = this.signatureHash;
        if (appSignatureHash2 == null) {
            str2 = "null";
        } else {
            str2 = appSignatureHash2.getSha256Hash();
        }
        sb.append(str2);
        sb.append(", version=");
        String str3 = this.versionName;
        if (str3 == null) {
            str3 = "null";
        }
        sb.append(str3);
        sb.append(", domain=");
        String str4 = this.domainName;
        if (str4 == null) {
            str4 = "null";
        }
        sb.append(str4);
        sb.append('}');
        return sb.toString();
    }

    public static String print(AppIdentity appIdentity) {
        return appIdentity == null ? "null" : appIdentity.toString();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("caller_uid", this.uid);
        String packageName = getPackageName();
        if (packageName != null) {
            jSONObject.put("caller_package_name", packageName);
        }
        String str = this.versionName;
        if (str != null) {
            jSONObject.put("caller_version_name", str);
        }
        String str2 = this.domainName;
        if (str2 != null) {
            jSONObject.put("caller_domain", str2);
        }
        return jSONObject;
    }
}
