package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.NonNull;
import com.bumptech.glide.load.EncodeStrategy;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.File;

public class BitmapDrawableEncoder implements ResourceEncoder<BitmapDrawable> {
    private final BitmapPool bitmapPool;
    private final ResourceEncoder<Bitmap> encoder;

    public BitmapDrawableEncoder(BitmapPool bitmapPool2, ResourceEncoder<Bitmap> resourceEncoder) {
        this.bitmapPool = bitmapPool2;
        this.encoder = resourceEncoder;
    }

    public boolean encode(@NonNull Resource<BitmapDrawable> resource, @NonNull File file, @NonNull Options options) {
        return this.encoder.encode(new BitmapResource(resource.get().getBitmap(), this.bitmapPool), file, options);
    }

    @Override // com.bumptech.glide.load.ResourceEncoder
    @NonNull
    public EncodeStrategy getEncodeStrategy(@NonNull Options options) {
        return this.encoder.getEncodeStrategy(options);
    }
}
