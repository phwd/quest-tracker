package com.facebook.secure.sanitizer;

import android.text.TextUtils;
import com.facebook.analytics.structuredlogger.base.SemanticTypeConverter;
import com.facebook.secure.sanitizer.SanitizerConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class SanitizedURI implements SemanticTypeConverter {
    public static final SanitizerConfig DEFAULT_CONFIG = new SanitizerConfig.Builder().build();
    private String mAuthority;
    private String mPath;
    private String mQuery;
    private String mScheme;

    private SanitizedURI(String str, String str2, String str3, String str4) {
        this.mScheme = str;
        this.mAuthority = str2;
        this.mPath = str3;
        this.mQuery = str4;
    }

    static class Builder {
        private String mAuthority;
        private String mPath;
        private String mQuery;
        private String mScheme;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public Builder setScheme(String str) {
            this.mScheme = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuthority(String str) {
            this.mAuthority = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setPath(String str) {
            this.mPath = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setQuery(String str) {
            this.mQuery = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public SanitizedURI build() {
            return new SanitizedURI(this.mScheme, this.mAuthority, this.mPath, this.mQuery);
        }
    }

    public JSONObject getURIJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.mScheme)) {
                jSONObject.put("scheme", this.mScheme);
            }
            if (!TextUtils.isEmpty(this.mAuthority)) {
                jSONObject.put("authority", this.mAuthority);
            }
            if (!TextUtils.isEmpty(this.mPath)) {
                jSONObject.put("path", this.mPath);
            }
            if (!TextUtils.isEmpty(this.mQuery)) {
                jSONObject.put("query", this.mQuery);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public String toSerializableType() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.mScheme)) {
            sb.append(this.mScheme);
            sb.append(':');
        }
        if (!TextUtils.isEmpty(this.mAuthority)) {
            sb.append("//");
            sb.append(this.mAuthority);
        }
        if (!TextUtils.isEmpty(this.mPath)) {
            sb.append(this.mPath);
        }
        if (!TextUtils.isEmpty(this.mQuery)) {
            sb.append('?');
            sb.append(this.mQuery);
        }
        return sb.toString();
    }

    public String getURIString() {
        return toSerializableType();
    }
}
