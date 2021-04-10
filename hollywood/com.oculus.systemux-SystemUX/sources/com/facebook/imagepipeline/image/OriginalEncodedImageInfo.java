package com.facebook.imagepipeline.image;

import android.net.Uri;
import javax.annotation.Nullable;

public class OriginalEncodedImageInfo {
    public static final OriginalEncodedImageInfo EMPTY = new OriginalEncodedImageInfo();
    @Nullable
    private final Object mCallerContext;
    private final int mHeight;
    @Nullable
    private final EncodedImageOrigin mOrigin;
    private final int mSize;
    @Nullable
    private final Uri mUri;
    private final int mWidth;

    private OriginalEncodedImageInfo() {
        this.mUri = null;
        this.mOrigin = EncodedImageOrigin.NOT_SET;
        this.mCallerContext = null;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSize = -1;
    }

    public OriginalEncodedImageInfo(Uri uri, EncodedImageOrigin encodedImageOrigin, @Nullable Object obj, int i, int i2, int i3) {
        this.mUri = uri;
        this.mOrigin = encodedImageOrigin;
        this.mCallerContext = obj;
        this.mWidth = i;
        this.mHeight = i2;
        this.mSize = i3;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getSize() {
        return this.mSize;
    }

    @Nullable
    public Uri getUri() {
        return this.mUri;
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public EncodedImageOrigin getOrigin() {
        return this.mOrigin;
    }
}
