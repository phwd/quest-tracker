package com.oculus.video.upstream;

import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.HttpDataSource;
import com.oculus.android.exoplayer2.upstream.TransferListener;

public class OculusHttpDataSourceFactory extends HttpDataSource.BaseFactory {
    private final boolean allowCrossProtocolRedirects;
    private final int connectTimeoutMillis;
    private final TransferListener<? super DataSource> listener;
    private final int readTimeoutMillis;
    private final String userAgent;

    public OculusHttpDataSourceFactory(String str, TransferListener<? super DataSource> transferListener) {
        this(str, transferListener, 8000, 8000, false);
    }

    public OculusHttpDataSourceFactory(String str, TransferListener<? super DataSource> transferListener, int i, int i2, boolean z) {
        this.userAgent = str;
        this.listener = transferListener;
        this.connectTimeoutMillis = i;
        this.readTimeoutMillis = i2;
        this.allowCrossProtocolRedirects = z;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.upstream.HttpDataSource.BaseFactory
    public HttpDataSource createDataSourceInternal(HttpDataSource.RequestProperties requestProperties) {
        return new OculusHttpDataSource(this.userAgent, null, this.listener, this.connectTimeoutMillis, this.readTimeoutMillis, this.allowCrossProtocolRedirects, requestProperties);
    }
}
