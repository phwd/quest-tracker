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

    public AppIdentity(int uid2, String packageName, AppSignatureHash signatureHash2, String versionName2, String domainName2) {
        this(uid2, Collections.singletonList(packageName), signatureHash2, versionName2, domainName2);
    }

    public AppIdentity(int uid2, List<String> packageNames2, AppSignatureHash signatureHash2, String versionName2, String domainName2) {
        this.uid = uid2;
        this.packageNames = Collections.unmodifiableList(packageNames2);
        this.signatureHash = signatureHash2;
        this.versionName = versionName2;
        this.domainName = domainName2;
        if (packageNames2.isEmpty()) {
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

    public String getVersionName() {
        return this.versionName;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public String toString() {
        String str;
        StringBuilder append = new StringBuilder().append("AppIdentity{uid=").append(this.uid).append(", packageNames=").append(this.packageNames).append(", sha1=").append(this.signatureHash == null ? "null" : this.signatureHash.getSha1Hash()).append(", sha2=").append(this.signatureHash == null ? "null" : this.signatureHash.getSha256Hash()).append(", version=").append(this.versionName == null ? "null" : this.versionName).append(", domain=");
        if (this.domainName == null) {
            str = "null";
        } else {
            str = this.domainName;
        }
        return append.append(str).append('}').toString();
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("caller_uid", this.uid);
        String callerPackageName = getPackageName();
        if (callerPackageName != null) {
            jsonObject.put("caller_package_name", callerPackageName);
        }
        if (this.versionName != null) {
            jsonObject.put("caller_version_name", this.versionName);
        }
        if (this.domainName != null) {
            jsonObject.put("caller_domain", this.domainName);
        }
        return jsonObject;
    }
}
