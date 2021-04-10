package com.oculus.video.upstream;

import android.content.Context;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.DefaultDataSource;
import com.oculus.android.exoplayer2.upstream.TransferListener;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;

public class OculusDefaultDataSource extends DefaultDataSource {
    private static final String SCHEME_APK = "apk";
    private static final String TAG = "OculusDefaultDataSource";
    private DataSource packageDataSource;

    public OculusDefaultDataSource(Context context, TransferListener<? super DataSource> transferListener, DataSource dataSource) {
        super(context, transferListener, dataSource);
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource, com.oculus.android.exoplayer2.upstream.DefaultDataSource
    public long open(DataSpec dataSpec) throws IOException {
        Assertions.checkState(this.dataSource == null);
        if (!SCHEME_APK.equals(dataSpec.uri.getScheme())) {
            return super.open(dataSpec);
        }
        this.dataSource = getOculusPackageDataSource();
        return this.dataSource.open(dataSpec);
    }

    private DataSource getOculusPackageDataSource() {
        if (this.packageDataSource == null) {
            this.packageDataSource = new OculusPackageDataSource(this.context, this.listener);
        }
        return this.packageDataSource;
    }
}
