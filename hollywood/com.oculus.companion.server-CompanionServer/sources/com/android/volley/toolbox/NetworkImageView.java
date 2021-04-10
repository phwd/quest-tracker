package com.android.volley.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.volley.toolbox.ImageLoader;

public class NetworkImageView extends ImageView {
    Bitmap mDefaultImageBitmap;
    private int mDefaultImageId;
    private ImageLoader.ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: package-private */
    public void loadImageIfNecessary(final boolean z) {
        boolean z2;
        boolean z3;
        int width = getWidth();
        int height = getHeight();
        ImageView.ScaleType scaleType = getScaleType();
        boolean z4 = true;
        if (getLayoutParams() != null) {
            z3 = getLayoutParams().width == -2;
            z2 = getLayoutParams().height == -2;
        } else {
            z3 = false;
            z2 = false;
        }
        if (!z3 || !z2) {
            z4 = false;
        }
        if (width != 0 || height != 0 || z4) {
            if (TextUtils.isEmpty(this.mUrl)) {
                ImageLoader.ImageContainer imageContainer = this.mImageContainer;
                if (imageContainer == null) {
                    setDefaultImageOrNull();
                } else {
                    imageContainer.cancelRequest();
                    throw null;
                }
            } else {
                ImageLoader.ImageContainer imageContainer2 = this.mImageContainer;
                if (imageContainer2 == null) {
                    if (z3) {
                        width = 0;
                    }
                    this.mImageLoader.get(this.mUrl, new ImageLoader.ImageListener() {
                        /* class com.android.volley.toolbox.NetworkImageView.AnonymousClass1 */
                    }, width, z2 ? 0 : height, scaleType);
                    throw null;
                }
                imageContainer2.getRequestUrl();
                throw null;
            }
        }
    }

    private void setDefaultImageOrNull() {
        int i = this.mDefaultImageId;
        if (i != 0) {
            setImageResource(i);
            return;
        }
        Bitmap bitmap = this.mDefaultImageBitmap;
        if (bitmap != null) {
            setImageBitmap(bitmap);
        } else {
            setImageBitmap(null);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        loadImageIfNecessary(true);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        ImageLoader.ImageContainer imageContainer = this.mImageContainer;
        if (imageContainer == null) {
            super.onDetachedFromWindow();
        } else {
            imageContainer.cancelRequest();
            throw null;
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
