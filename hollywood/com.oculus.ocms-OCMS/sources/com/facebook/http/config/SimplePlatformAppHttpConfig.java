package com.facebook.http.config;

import android.net.Uri;
import com.facebook.config.application.ApiConnectionType;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class SimplePlatformAppHttpConfig implements PlatformAppHttpConfig {
    private final String domain;
    @ApiConnectionType
    private final Provider<String> mApiConnectionTypeProvider;
    private final String mUploadSandbox;

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    @Nullable
    public String getApiUserAgent() {
        return null;
    }

    public SimplePlatformAppHttpConfig(String str, @ApiConnectionType Provider<String> provider) {
        this.domain = str;
        this.mUploadSandbox = "";
        this.mApiConnectionTypeProvider = provider;
    }

    public SimplePlatformAppHttpConfig(String str, String str2, @ApiConnectionType Provider<String> provider) {
        this.domain = str;
        this.mUploadSandbox = str2;
        this.mApiConnectionTypeProvider = provider;
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getApiUri() {
        return Uri.parse("https://api." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getGraphUri() {
        return Uri.parse("https://graph." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public String getDomain() {
        return this.domain;
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getGraphVideoUri() {
        return Uri.parse("https://graph-video." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getSecureGraphUri() {
        return Uri.parse("https://graph.secure." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getSecureUri() {
        return Uri.parse("https://secure." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getResumableUploadUri() {
        if (!this.mUploadSandbox.trim().isEmpty()) {
            return Uri.parse("http://" + this.mUploadSandbox).buildUpon();
        }
        return Uri.parse("https://rupload." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getHUri() {
        return Uri.parse("http://h." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getMobileUri() {
        return Uri.parse("https://m." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public Uri.Builder getLookasideUri() {
        return Uri.parse("https://lookaside." + this.domain).buildUpon();
    }

    @Override // com.facebook.http.config.PlatformAppHttpConfig
    public String getApiConnectionType() {
        return this.mApiConnectionTypeProvider.get();
    }
}
