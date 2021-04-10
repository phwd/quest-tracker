package com.facebook.http.config;

import org.apache.http.HttpHost;

public interface NetworkConfig {

    public interface Listener {
        void onChanged();
    }

    void addListener(Listener listener);

    HttpHost getProxy();

    boolean isConnectingToSandbox();

    boolean shouldCheckSslCerts();
}
