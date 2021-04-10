package com.facebook.internal;

import android.graphics.Bitmap;

public class ImageResponse {
    public Bitmap bitmap;
    public Exception error;
    public boolean isCachedRedirect;
    public ImageRequest request;

    public ImageResponse(ImageRequest imageRequest, Exception exc, boolean z, Bitmap bitmap2) {
        this.request = imageRequest;
        this.error = exc;
        this.bitmap = bitmap2;
        this.isCachedRedirect = z;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public Exception getError() {
        return this.error;
    }

    public ImageRequest getRequest() {
        return this.request;
    }

    public boolean isCachedRedirect() {
        return this.isCachedRedirect;
    }
}
