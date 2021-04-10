package com.oculus.android.exoplayer2.upstream;

import android.net.Uri;
import com.oculus.android.exoplayer2.upstream.Loader;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;
import java.io.InputStream;

public final class ParsingLoadable<T> implements Loader.Loadable {
    private volatile long bytesLoaded;
    private final DataSource dataSource;
    public final DataSpec dataSpec;
    private volatile boolean isCanceled;
    private final Parser<? extends T> parser;
    private volatile T result;
    public final int type;

    public interface Parser<T> {
        T parse(Uri uri, InputStream inputStream) throws IOException;
    }

    public ParsingLoadable(DataSource dataSource2, Uri uri, int i, Parser<? extends T> parser2) {
        this(dataSource2, new DataSpec(uri, 3), i, parser2);
    }

    public ParsingLoadable(DataSource dataSource2, DataSpec dataSpec2, int i, Parser<? extends T> parser2) {
        this.dataSource = dataSource2;
        this.dataSpec = dataSpec2;
        this.type = i;
        this.parser = parser2;
    }

    public final T getResult() {
        return this.result;
    }

    public long bytesLoaded() {
        return this.bytesLoaded;
    }

    @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
    public final void cancelLoad() {
        this.isCanceled = true;
    }

    @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
    public final boolean isLoadCanceled() {
        return this.isCanceled;
    }

    @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
    public final void load() throws IOException {
        DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(this.dataSource, this.dataSpec);
        try {
            dataSourceInputStream.open();
            this.result = (T) this.parser.parse(this.dataSource.getUri(), dataSourceInputStream);
        } finally {
            this.bytesLoaded = dataSourceInputStream.bytesRead();
            Util.closeQuietly(dataSourceInputStream);
        }
    }
}
