package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;

public final class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
    private final BitmapPool bitmapPool;

    public boolean handles(@NonNull GifDecoder gifDecoder, @NonNull Options options) {
        return true;
    }

    public GifFrameResourceDecoder(BitmapPool bitmapPool2) {
        this.bitmapPool = bitmapPool2;
    }

    public Resource<Bitmap> decode(@NonNull GifDecoder gifDecoder, int i, int i2, @NonNull Options options) {
        return BitmapResource.obtain(gifDecoder.getNextFrame(), this.bitmapPool);
    }
}
