package com.facebook.http.config;

import android.net.Uri;

public interface PlatformAppHttpConfig {
    String getApiConnectionType();

    Uri.Builder getApiUri();

    String getApiUserAgent();

    String getDomain();

    Uri.Builder getGraphUri();

    Uri.Builder getGraphVideoUri();

    Uri.Builder getHUri();

    Uri.Builder getLookasideUri();

    Uri.Builder getMobileUri();

    Uri.Builder getResumableUploadUri();

    Uri.Builder getSecureGraphUri();

    Uri.Builder getSecureUri();
}
