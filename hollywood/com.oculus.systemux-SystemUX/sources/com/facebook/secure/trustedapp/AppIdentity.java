package com.facebook.secure.trustedapp;

import com.facebook.debug.log.LoggingUtil;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class AppIdentity {
    @Nullable
    private final String domainName;
    private final List<String> packageNames;
    @Nullable
    private final AppSignatureHash signatureHash;
    private final int uid;
    @Nullable
    private final String versionName;

    public AppIdentity(int i, String str, @Nullable AppSignatureHash appSignatureHash, @Nullable String str2, @Nullable String str3) {
        this(i, Collections.singletonList(str), appSignatureHash, str2, str3);
    }

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

    @Nullable
    public AppSignatureHash getSignatureHash() {
        return this.signatureHash;
    }

    @Nullable
    public String getVersionName() {
        return this.versionName;
    }

    @Nullable
    public String getDomainName() {
        return this.domainName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AppIdentity{uid=");
        sb.append(this.uid);
        sb.append(", packageNames=");
        sb.append(this.packageNames);
        sb.append(", sha1=");
        AppSignatureHash appSignatureHash = this.signatureHash;
        sb.append(appSignatureHash == null ? LoggingUtil.NO_HASHCODE : appSignatureHash.getSha1Hash());
        sb.append(", sha2=");
        AppSignatureHash appSignatureHash2 = this.signatureHash;
        sb.append(appSignatureHash2 == null ? LoggingUtil.NO_HASHCODE : appSignatureHash2.getSha256Hash());
        sb.append(", version=");
        String str = this.versionName;
        if (str == null) {
            str = LoggingUtil.NO_HASHCODE;
        }
        sb.append(str);
        sb.append(", domain=");
        String str2 = this.domainName;
        if (str2 == null) {
            str2 = LoggingUtil.NO_HASHCODE;
        }
        sb.append(str2);
        sb.append('}');
        return sb.toString();
    }

    public static String print(@Nullable AppIdentity appIdentity) {
        return appIdentity == null ? LoggingUtil.NO_HASHCODE : appIdentity.toString();
    }

    public static String printPackageName(@Nullable AppIdentity appIdentity) {
        if (appIdentity == null) {
            return "no_app_identity";
        }
        return appIdentity.getPackageName() == null ? LoggingUtil.NO_HASHCODE : appIdentity.getPackageName();
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
