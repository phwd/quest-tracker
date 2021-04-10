package com.bumptech.glide.load.data;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class InputStreamRewinder implements DataRewinder<InputStream> {
    private static final int MARK_READ_LIMIT = 5242880;
    private final RecyclableBufferedInputStream bufferedStream;

    public InputStreamRewinder(InputStream inputStream, ArrayPool arrayPool) {
        this.bufferedStream = new RecyclableBufferedInputStream(inputStream, arrayPool);
        this.bufferedStream.mark(MARK_READ_LIMIT);
    }

    @Override // com.bumptech.glide.load.data.DataRewinder
    @NonNull
    public InputStream rewindAndGet() throws IOException {
        this.bufferedStream.reset();
        return this.bufferedStream;
    }

    @Override // com.bumptech.glide.load.data.DataRewinder
    public void cleanup() {
        this.bufferedStream.release();
    }

    public void fixMarkLimits() {
        this.bufferedStream.fixMarkLimit();
    }

    public static final class Factory implements DataRewinder.Factory<InputStream> {
        private final ArrayPool byteArrayPool;

        public Factory(ArrayPool arrayPool) {
            this.byteArrayPool = arrayPool;
        }

        @NonNull
        public DataRewinder<InputStream> build(InputStream inputStream) {
            return new InputStreamRewinder(inputStream, this.byteArrayPool);
        }

        @Override // com.bumptech.glide.load.data.DataRewinder.Factory
        @NonNull
        public Class<InputStream> getDataClass() {
            return InputStream.class;
        }
    }
}
