package com.oculus.android.exoplayer2.upstream;

import android.net.Uri;
import com.oculus.android.exoplayer2.upstream.DataSource;
import java.io.IOException;

public final class DummyDataSource implements DataSource {
    public static final DataSource.Factory FACTORY = new DataSource.Factory() {
        /* class com.oculus.android.exoplayer2.upstream.DummyDataSource.AnonymousClass1 */

        @Override // com.oculus.android.exoplayer2.upstream.DataSource.Factory
        public DataSource createDataSource() {
            return new DummyDataSource();
        }
    };
    public static final DummyDataSource INSTANCE = new DummyDataSource();

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public void close() throws IOException {
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public Uri getUri() {
        return null;
    }

    private DummyDataSource() {
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        throw new IOException("Dummy source");
    }

    @Override // com.oculus.android.exoplayer2.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        throw new UnsupportedOperationException();
    }
}
