package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.engine.cache.DiskCache;
import java.io.File;

class DataCacheWriter<DataType> implements DiskCache.Writer {
    private final DataType data;
    private final Encoder<DataType> encoder;
    private final Options options;

    DataCacheWriter(Encoder<DataType> encoder2, DataType datatype, Options options2) {
        this.encoder = encoder2;
        this.data = datatype;
        this.options = options2;
    }

    @Override // com.bumptech.glide.load.engine.cache.DiskCache.Writer
    public boolean write(@NonNull File file) {
        return this.encoder.encode(this.data, file, this.options);
    }
}
