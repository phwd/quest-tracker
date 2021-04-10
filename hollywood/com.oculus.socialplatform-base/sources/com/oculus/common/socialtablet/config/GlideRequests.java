package com.oculus.common.socialtablet.config;

import X.AbstractC08531en;
import X.AnonymousClass1fS;
import X.AnonymousClass1g0;
import X.AnonymousClass1g2;
import X.AnonymousClass1g7;
import X.AnonymousClass1gA;
import X.AnonymousClass1hH;
import X.ComponentCallbacks2C07631cl;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.net.URL;

public class GlideRequests extends AnonymousClass1g0 {
    @Override // X.AnonymousClass1g0
    public void setRequestOptions(@NonNull AnonymousClass1g7 r2) {
        if (r2 instanceof GlideOptions) {
            super.setRequestOptions(r2);
        } else {
            super.setRequestOptions(new GlideOptions().apply((AnonymousClass1g2<?>) r2));
        }
    }

    public GlideRequests(@NonNull ComponentCallbacks2C07631cl r1, @NonNull AnonymousClass1hH r2, @NonNull AbstractC08531en r3, @NonNull Context context) {
        super(r1, r2, r3, context);
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    public GlideRequests addDefaultRequestListener(AnonymousClass1fS<Object> r1) {
        super.addDefaultRequestListener(r1);
        return this;
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    public synchronized GlideRequests applyDefaultRequestOptions(@NonNull AnonymousClass1g7 r2) {
        super.applyDefaultRequestOptions(r2);
        return this;
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public <ResourceType> GlideRequest<ResourceType> as(@NonNull Class<ResourceType> cls) {
        return new GlideRequest<>(this.glide, this, cls, this.context);
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Bitmap> asBitmap() {
        return (GlideRequest) super.asBitmap();
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> asDrawable() {
        return (GlideRequest) super.asDrawable();
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<File> asFile() {
        return (GlideRequest) super.asFile();
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<AnonymousClass1gA> asGif() {
        return (GlideRequest) super.asGif();
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<File> download(@Nullable Object obj) {
        return (GlideRequest) super.download(obj);
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<File> downloadOnly() {
        return (GlideRequest) super.downloadOnly();
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable Bitmap bitmap) {
        return (GlideRequest) super.load(bitmap);
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable Drawable drawable) {
        return (GlideRequest) super.load(drawable);
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable Uri uri) {
        return (GlideRequest) super.load(uri);
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable File file) {
        return (GlideRequest) super.load(file);
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable @DrawableRes @RawRes Integer num) {
        return (GlideRequest) super.load(num);
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable Object obj) {
        return (GlideRequest) super.load(obj);
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable String str) {
        return (GlideRequest) super.load(str);
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @CheckResult
    @Deprecated
    public GlideRequest<Drawable> load(@Nullable URL url) {
        return (GlideRequest) super.load(url);
    }

    @Override // X.AnonymousClass1g0, X.AnonymousClass1g0
    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable byte[] bArr) {
        return (GlideRequest) super.load(bArr);
    }

    @Override // X.AnonymousClass1g0
    @NonNull
    public synchronized GlideRequests setDefaultRequestOptions(@NonNull AnonymousClass1g7 r2) {
        super.setDefaultRequestOptions(r2);
        return this;
    }
}
