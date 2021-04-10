package com.oculus.video.upstream;

import android.content.Context;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.TransferListener;
import com.oculus.video.upstream.OculusCacheDataSource;

public final class OculusDataSourceFactory implements DataSource.Factory {
    private final DataSource.Factory baseDataSourceFactory;
    private final OculusCacheDataSource.Callback cacheDataSourceCallback;
    private final Context context;
    private final TransferListener<? super DataSource> listener;

    public OculusDataSourceFactory(Context context2, TransferListener<? super DataSource> transferListener, DataSource.Factory factory, OculusCacheDataSource.Callback callback) {
        this.context = context2.getApplicationContext();
        this.listener = transferListener;
        this.baseDataSourceFactory = factory;
        this.cacheDataSourceCallback = callback;
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource.Factory
    public DataSource createDataSource() {
        OculusDefaultDataSource oculusDefaultDataSource = new OculusDefaultDataSource(this.context, this.listener, this.baseDataSourceFactory.createDataSource());
        OculusCacheDataSource.Callback callback = this.cacheDataSourceCallback;
        return (callback == null || callback.getCache() == null) ? oculusDefaultDataSource : new OculusCacheDataSource(oculusDefaultDataSource, 2, 2097152, this.cacheDataSourceCallback);
    }
}
