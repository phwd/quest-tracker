package com.android.volley.toolbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

public class NetworkImageView extends ImageView {
    @Nullable
    Bitmap mDefaultImageBitmap;
    private int mDefaultImageId;
    @Nullable
    private Bitmap mErrorImageBitmap;
    private int mErrorImageId;
    private ImageLoader.ImageContainer mImageContainer;
    private ImageLoader mImageLoader;
    private String mUrl;

    public NetworkImageView(Context context) {
        this(context, null);
    }

    public NetworkImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @MainThread
    public void setImageUrl(String url, ImageLoader imageLoader) {
        Threads.throwIfNotOnMainThread();
        this.mUrl = url;
        this.mImageLoader = imageLoader;
        loadImageIfNecessary(false);
    }

    public void setDefaultImageResId(int defaultImage) {
        this.mDefaultImageBitmap = null;
        this.mDefaultImageId = defaultImage;
    }

    public void setDefaultImageBitmap(Bitmap defaultImage) {
        this.mDefaultImageId = 0;
        this.mDefaultImageBitmap = defaultImage;
    }

    public void setErrorImageResId(int errorImage) {
        this.mErrorImageBitmap = null;
        this.mErrorImageId = errorImage;
    }

    public void setErrorImageBitmap(Bitmap errorImage) {
        this.mErrorImageId = 0;
        this.mErrorImageBitmap = errorImage;
    }

    /* access modifiers changed from: package-private */
    public void loadImageIfNecessary(final boolean isInLayoutPass) {
        boolean wrapHeight;
        boolean wrapWidth;
        int width = getWidth();
        int height = getHeight();
        ImageView.ScaleType scaleType = getScaleType();
        boolean isFullyWrapContent = true;
        int maxHeight = 0;
        if (getLayoutParams() != null) {
            wrapWidth = getLayoutParams().width == -2;
            wrapHeight = getLayoutParams().height == -2;
        } else {
            wrapWidth = false;
            wrapHeight = false;
        }
        if (!wrapWidth || !wrapHeight) {
            isFullyWrapContent = false;
        }
        if (width != 0 || height != 0 || isFullyWrapContent) {
            if (TextUtils.isEmpty(this.mUrl)) {
                ImageLoader.ImageContainer imageContainer = this.mImageContainer;
                if (imageContainer != null) {
                    imageContainer.cancelRequest();
                    this.mImageContainer = null;
                }
                setDefaultImageOrNull();
                return;
            }
            ImageLoader.ImageContainer imageContainer2 = this.mImageContainer;
            if (!(imageContainer2 == null || imageContainer2.getRequestUrl() == null)) {
                if (!this.mImageContainer.getRequestUrl().equals(this.mUrl)) {
                    this.mImageContainer.cancelRequest();
                    setDefaultImageOrNull();
                } else {
                    return;
                }
            }
            int maxWidth = wrapWidth ? 0 : width;
            if (!wrapHeight) {
                maxHeight = height;
            }
            this.mImageContainer = this.mImageLoader.get(this.mUrl, new ImageLoader.ImageListener() {
                /* class com.android.volley.toolbox.NetworkImageView.AnonymousClass1 */

                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError error) {
                    if (NetworkImageView.this.mErrorImageId != 0) {
                        NetworkImageView networkImageView = NetworkImageView.this;
                        networkImageView.setImageResource(networkImageView.mErrorImageId);
                    } else if (NetworkImageView.this.mErrorImageBitmap != null) {
                        NetworkImageView networkImageView2 = NetworkImageView.this;
                        networkImageView2.setImageBitmap(networkImageView2.mErrorImageBitmap);
                    }
                }

                @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                public void onResponse(final ImageLoader.ImageContainer response, boolean isImmediate) {
                    if (isImmediate && isInLayoutPass) {
                        NetworkImageView.this.post(new Runnable() {
                            /* class com.android.volley.toolbox.NetworkImageView.AnonymousClass1.AnonymousClass1 */

                            public void run() {
                                AnonymousClass1.this.onResponse(response, false);
                            }
                        });
                    } else if (response.getBitmap() != null) {
                        NetworkImageView.this.setImageBitmap(response.getBitmap());
                    } else if (NetworkImageView.this.mDefaultImageId != 0) {
                        NetworkImageView networkImageView = NetworkImageView.this;
                        networkImageView.setImageResource(networkImageView.mDefaultImageId);
                    } else if (NetworkImageView.this.mDefaultImageBitmap != null) {
                        NetworkImageView networkImageView2 = NetworkImageView.this;
                        networkImageView2.setImageBitmap(networkImageView2.mDefaultImageBitmap);
                    }
                }
            }, maxWidth, maxHeight, scaleType);
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
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        loadImageIfNecessary(true);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        ImageLoader.ImageContainer imageContainer = this.mImageContainer;
        if (imageContainer != null) {
            imageContainer.cancelRequest();
            setImageBitmap(null);
            this.mImageContainer = null;
        }
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
