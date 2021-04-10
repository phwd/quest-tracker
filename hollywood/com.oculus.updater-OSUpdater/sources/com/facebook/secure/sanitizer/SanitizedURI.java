package com.facebook.secure.sanitizer;

import android.text.TextUtils;
import com.facebook.secure.sanitizer.SanitizerConfig;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class SanitizedURI {
    public static final SanitizerConfig DEFAULT_CONFIG = new SanitizerConfig.Builder().build();
    @Nullable
    private String mAuthority;
    @Nullable
    private String mPath;
    @Nullable
    private String mQuery;
    @Nullable
    private String mScheme;

    private SanitizedURI(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        this.mScheme = str;
        this.mAuthority = str2;
        this.mPath = str3;
        this.mQuery = str4;
    }

    /* access modifiers changed from: package-private */
    public static class Builder {
        @Nullable
        private String mAuthority;
        @Nullable
        private String mPath;
        @Nullable
        private String mQuery;
        @Nullable
        private String mScheme;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public Builder setScheme(@Nullable String str) {
            this.mScheme = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setAuthority(@Nullable String str) {
            this.mAuthority = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setPath(@Nullable String str) {
            this.mPath = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setQuery(@Nullable String str) {
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
