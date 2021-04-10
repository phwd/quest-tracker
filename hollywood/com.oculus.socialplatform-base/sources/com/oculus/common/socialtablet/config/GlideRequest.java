package com.oculus.common.socialtablet.config;

import X.AbstractC06491aL;
import X.AbstractC08841fc;
import X.AbstractC08971fp;
import X.AbstractC09081gb;
import X.AnonymousClass1cY;
import X.AnonymousClass1eU;
import X.AnonymousClass1fS;
import X.AnonymousClass1g0;
import X.AnonymousClass1g1;
import X.AnonymousClass1g2;
import X.AnonymousClass1gj;
import X.C07491cP;
import X.ComponentCallbacks2C07631cl;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.net.URL;

public class GlideRequest<TranscodeType> extends AnonymousClass1g1<TranscodeType> implements Cloneable {
    public GlideRequest(@NonNull ComponentCallbacks2C07631cl r1, @NonNull AnonymousClass1g0 r2, @NonNull Class<TranscodeType> cls, @NonNull Context context) {
        super(r1, r2, cls, context);
    }

    public GlideRequest(@NonNull Class<TranscodeType> cls, @NonNull AnonymousClass1g1<?> r2) {
        super(cls, r2);
    }

    @Override // X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> addListener(@Nullable AnonymousClass1fS<TranscodeType> r1) {
        super.addListener((AnonymousClass1fS) r1);
        return this;
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1, X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> apply(@NonNull AnonymousClass1g2<?> r2) {
        return (GlideRequest) super.apply(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> centerCrop() {
        return (GlideRequest) super.centerCrop();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> centerInside() {
        return (GlideRequest) super.centerInside();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> circleCrop() {
        return (GlideRequest) super.circleCrop();
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1, X.AnonymousClass1g1, java.lang.Object, X.AnonymousClass1g2, X.AnonymousClass1g2
    @CheckResult
    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest) super.clone();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> decode(@NonNull Class<?> cls) {
        return (GlideRequest) super.decode(cls);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        return (GlideRequest) super.disallowHardwareConfig();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> diskCacheStrategy(@NonNull AbstractC08841fc r2) {
        return (GlideRequest) super.diskCacheStrategy(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> dontAnimate() {
        return (GlideRequest) super.dontAnimate();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> dontTransform() {
        return (GlideRequest) super.dontTransform();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> downsample(@NonNull AbstractC09081gb r2) {
        return (GlideRequest) super.downsample(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        return (GlideRequest) super.encodeFormat(compressFormat);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return (GlideRequest) super.encodeQuality(i);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(@DrawableRes int i) {
        return (GlideRequest) super.error(i);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(@Nullable Drawable drawable) {
        return (GlideRequest) super.error(drawable);
    }

    @Override // X.AnonymousClass1g1
    @NonNull
    public GlideRequest<TranscodeType> error(@Nullable AnonymousClass1g1<TranscodeType> r1) {
        super.error((AnonymousClass1g1) r1);
        return this;
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fallback(@DrawableRes int i) {
        return (GlideRequest) super.fallback(i);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fallback(@Nullable Drawable drawable) {
        return (GlideRequest) super.fallback(drawable);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fitCenter() {
        return (GlideRequest) super.fitCenter();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> format(@NonNull AnonymousClass1gj r2) {
        return (GlideRequest) super.format(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> frame(@IntRange(from = 0) long j) {
        return (GlideRequest) super.frame(j);
    }

    @Override // X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<File> getDownloadOnlyRequest() {
        return (GlideRequest) super.apply((AnonymousClass1g2<?>) AnonymousClass1g1.DOWNLOAD_ONLY_OPTIONS);
    }

    @Override // X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> listener(@Nullable AnonymousClass1fS<TranscodeType> r1) {
        super.listener((AnonymousClass1fS) r1);
        return this;
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Bitmap bitmap) {
        return (GlideRequest) super.load(bitmap);
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Drawable drawable) {
        return (GlideRequest) super.load(drawable);
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Uri uri) {
        super.load(uri);
        return this;
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable File file) {
        super.load(file);
        return this;
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable @DrawableRes @RawRes Integer num) {
        return (GlideRequest) super.load(num);
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable Object obj) {
        super.load(obj);
        return this;
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable String str) {
        super.load(str);
        return this;
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @CheckResult
    @Deprecated
    public GlideRequest<TranscodeType> load(@Nullable URL url) {
        super.load(url);
        return this;
    }

    @Override // X.AnonymousClass1g1, X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable byte[] bArr) {
        return (GlideRequest) super.load(bArr);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean z) {
        return (GlideRequest) super.onlyRetrieveFromCache(z);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterCrop() {
        return (GlideRequest) super.optionalCenterCrop();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterInside() {
        return (GlideRequest) super.optionalCenterInside();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCircleCrop() {
        return (GlideRequest) super.optionalCircleCrop();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalFitCenter() {
        return (GlideRequest) super.optionalFitCenter();
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalTransform(@NonNull AnonymousClass1eU<Bitmap> r2) {
        return (GlideRequest) super.optionalTransform(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> optionalTransform(@NonNull Class<Y> cls, @NonNull AnonymousClass1eU<Y> r3) {
        return (GlideRequest) super.optionalTransform((Class) cls, (AnonymousClass1eU) r3);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> override(int i) {
        return (GlideRequest) super.override(i);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> override(int i, int i2) {
        return (GlideRequest) super.override(i, i2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@DrawableRes int i) {
        return (GlideRequest) super.placeholder(i);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@Nullable Drawable drawable) {
        return (GlideRequest) super.placeholder(drawable);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> priority(@NonNull AnonymousClass1cY r2) {
        return (GlideRequest) super.priority(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> set(@NonNull C07491cP<Y> r2, @NonNull Y y) {
        return (GlideRequest) super.set((C07491cP) r2, (Object) y);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> signature(@NonNull AbstractC06491aL r2) {
        return (GlideRequest) super.signature(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (GlideRequest) super.sizeMultiplier(f);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> skipMemoryCache(boolean z) {
        return (GlideRequest) super.skipMemoryCache(z);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> theme(@Nullable Resources.Theme theme) {
        return (GlideRequest) super.theme(theme);
    }

    @Override // X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(float f) {
        super.thumbnail(f);
        return this;
    }

    @Override // X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(@Nullable AnonymousClass1g1<TranscodeType> r1) {
        super.thumbnail((AnonymousClass1g1) r1);
        return this;
    }

    @Override // X.AnonymousClass1g1
    @NonNull
    @SafeVarargs
    @CheckResult
    public final GlideRequest<TranscodeType> thumbnail(@Nullable AnonymousClass1g1<TranscodeType>... r1) {
        super.thumbnail((AnonymousClass1g1[]) r1);
        return this;
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> timeout(@IntRange(from = 0) int i) {
        return (GlideRequest) super.timeout(i);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull AnonymousClass1eU<Bitmap> r2) {
        return (GlideRequest) super.transform(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> transform(@NonNull Class<Y> cls, @NonNull AnonymousClass1eU<Y> r3) {
        return (GlideRequest) super.transform((Class) cls, (AnonymousClass1eU) r3);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull AnonymousClass1eU<Bitmap>... r2) {
        return (GlideRequest) super.transform(r2);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @Deprecated
    @CheckResult
    public GlideRequest<TranscodeType> transforms(@NonNull AnonymousClass1eU<Bitmap>... r2) {
        return (GlideRequest) super.transforms(r2);
    }

    @Override // X.AnonymousClass1g1
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transition(@NonNull AbstractC08971fp<?, ? super TranscodeType> r1) {
        super.transition((AbstractC08971fp) r1);
        return this;
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> useAnimationPool(boolean z) {
        return (GlideRequest) super.useAnimationPool(z);
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean z) {
        return (GlideRequest) super.useUnlimitedSourceGeneratorsPool(z);
    }
}
