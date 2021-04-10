package com.oculus.common.socialtablet.config;

import X.AbstractC06491aL;
import X.AbstractC08841fc;
import X.AbstractC09081gb;
import X.AnonymousClass1cY;
import X.AnonymousClass1eU;
import X.AnonymousClass1g2;
import X.AnonymousClass1g7;
import X.AnonymousClass1gj;
import X.C07491cP;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class GlideOptions extends AnonymousClass1g7 implements Cloneable {
    public static GlideOptions centerCropTransform2;
    public static GlideOptions centerInsideTransform1;
    public static GlideOptions circleCropTransform3;
    public static GlideOptions fitCenterTransform0;
    public static GlideOptions noAnimation5;
    public static GlideOptions noTransformation4;

    @NonNull
    @CheckResult
    public static GlideOptions bitmapTransform(@NonNull AnonymousClass1eU<Bitmap> r1) {
        return (GlideOptions) super.transform(r1);
    }

    @NonNull
    @CheckResult
    public static GlideOptions centerCropTransform() {
        GlideOptions glideOptions = centerCropTransform2;
        if (glideOptions != null) {
            return glideOptions;
        }
        GlideOptions glideOptions2 = (GlideOptions) super.centerCrop();
        super.autoClone();
        centerCropTransform2 = glideOptions2;
        return glideOptions2;
    }

    @NonNull
    @CheckResult
    public static GlideOptions centerInsideTransform() {
        GlideOptions glideOptions = centerInsideTransform1;
        if (glideOptions != null) {
            return glideOptions;
        }
        GlideOptions glideOptions2 = (GlideOptions) super.centerInside();
        super.autoClone();
        centerInsideTransform1 = glideOptions2;
        return glideOptions2;
    }

    @NonNull
    @CheckResult
    public static GlideOptions circleCropTransform() {
        GlideOptions glideOptions = circleCropTransform3;
        if (glideOptions != null) {
            return glideOptions;
        }
        GlideOptions glideOptions2 = (GlideOptions) super.circleCrop();
        super.autoClone();
        circleCropTransform3 = glideOptions2;
        return glideOptions2;
    }

    @NonNull
    @CheckResult
    public static GlideOptions decodeTypeOf(@NonNull Class<?> cls) {
        return (GlideOptions) super.decode(cls);
    }

    @NonNull
    @CheckResult
    public static GlideOptions diskCacheStrategyOf(@NonNull AbstractC08841fc r1) {
        return (GlideOptions) super.diskCacheStrategy(r1);
    }

    @NonNull
    @CheckResult
    public static GlideOptions downsampleOf(@NonNull AbstractC09081gb r1) {
        return (GlideOptions) super.downsample(r1);
    }

    @NonNull
    @CheckResult
    public static GlideOptions encodeFormatOf(@NonNull Bitmap.CompressFormat compressFormat) {
        return (GlideOptions) super.encodeFormat(compressFormat);
    }

    @NonNull
    @CheckResult
    public static GlideOptions encodeQualityOf(@IntRange(from = 0, to = 100) int i) {
        return (GlideOptions) super.encodeQuality(i);
    }

    @NonNull
    @CheckResult
    public static GlideOptions fitCenterTransform() {
        GlideOptions glideOptions = fitCenterTransform0;
        if (glideOptions != null) {
            return glideOptions;
        }
        GlideOptions glideOptions2 = (GlideOptions) super.fitCenter();
        super.autoClone();
        fitCenterTransform0 = glideOptions2;
        return glideOptions2;
    }

    @NonNull
    @CheckResult
    public static GlideOptions formatOf(@NonNull AnonymousClass1gj r1) {
        return (GlideOptions) super.format(r1);
    }

    @NonNull
    @CheckResult
    public static GlideOptions frameOf(@IntRange(from = 0) long j) {
        return (GlideOptions) super.frame(j);
    }

    @NonNull
    @CheckResult
    public static GlideOptions noAnimation() {
        GlideOptions glideOptions = noAnimation5;
        if (glideOptions != null) {
            return glideOptions;
        }
        GlideOptions glideOptions2 = (GlideOptions) super.dontAnimate();
        super.autoClone();
        noAnimation5 = glideOptions2;
        return glideOptions2;
    }

    @NonNull
    @CheckResult
    public static GlideOptions noTransformation() {
        GlideOptions glideOptions = noTransformation4;
        if (glideOptions != null) {
            return glideOptions;
        }
        GlideOptions glideOptions2 = (GlideOptions) super.dontTransform();
        super.autoClone();
        noTransformation4 = glideOptions2;
        return glideOptions2;
    }

    @NonNull
    @CheckResult
    public static <T> GlideOptions option(@NonNull C07491cP<T> r1, @NonNull T t) {
        return (GlideOptions) super.set((C07491cP) r1, (Object) t);
    }

    @NonNull
    @CheckResult
    public static GlideOptions priorityOf(@NonNull AnonymousClass1cY r1) {
        return (GlideOptions) super.priority(r1);
    }

    @NonNull
    @CheckResult
    public static GlideOptions signatureOf(@NonNull AbstractC06491aL r1) {
        return (GlideOptions) super.signature(r1);
    }

    @NonNull
    @CheckResult
    public static GlideOptions sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (GlideOptions) super.sizeMultiplier(f);
    }

    @NonNull
    @CheckResult
    public static GlideOptions skipMemoryCacheOf(boolean z) {
        return (GlideOptions) super.skipMemoryCache(z);
    }

    @NonNull
    @CheckResult
    public static GlideOptions timeoutOf(@IntRange(from = 0) int i) {
        return (GlideOptions) super.timeout(i);
    }

    @NonNull
    @CheckResult
    public static GlideOptions errorOf(@DrawableRes int i) {
        return (GlideOptions) super.error(i);
    }

    @NonNull
    @CheckResult
    public static GlideOptions errorOf(@Nullable Drawable drawable) {
        return (GlideOptions) super.error(drawable);
    }

    @NonNull
    @CheckResult
    public static GlideOptions overrideOf(int i) {
        return (GlideOptions) super.override(i);
    }

    @NonNull
    @CheckResult
    public static GlideOptions overrideOf(int i, int i2) {
        return (GlideOptions) super.override(i, i2);
    }

    @NonNull
    @CheckResult
    public static GlideOptions placeholderOf(@DrawableRes int i) {
        return (GlideOptions) super.placeholder(i);
    }

    @NonNull
    @CheckResult
    public static GlideOptions placeholderOf(@Nullable Drawable drawable) {
        return (GlideOptions) super.placeholder(drawable);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 apply(@NonNull AnonymousClass1g2<?> r2) {
        return (GlideOptions) super.apply(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    public AnonymousClass1g7 autoClone() {
        super.autoClone();
        return this;
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 centerCrop() {
        return (GlideOptions) super.centerCrop();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 centerInside() {
        return (GlideOptions) super.centerInside();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 circleCrop() {
        return (GlideOptions) super.circleCrop();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // java.lang.Object, X.AnonymousClass1g2, X.AnonymousClass1g2
    @CheckResult
    public AnonymousClass1g7 clone() {
        return (GlideOptions) super.clone();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 decode(@NonNull Class<?> cls) {
        return (GlideOptions) super.decode(cls);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 disallowHardwareConfig() {
        return (GlideOptions) super.disallowHardwareConfig();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 diskCacheStrategy(@NonNull AbstractC08841fc r2) {
        return (GlideOptions) super.diskCacheStrategy(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 dontAnimate() {
        return (GlideOptions) super.dontAnimate();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 dontTransform() {
        return (GlideOptions) super.dontTransform();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 downsample(@NonNull AbstractC09081gb r2) {
        return (GlideOptions) super.downsample(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        return (GlideOptions) super.encodeFormat(compressFormat);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return (GlideOptions) super.encodeQuality(i);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 error(@DrawableRes int i) {
        return (GlideOptions) super.error(i);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 error(@Nullable Drawable drawable) {
        return (GlideOptions) super.error(drawable);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 fallback(@DrawableRes int i) {
        return (GlideOptions) super.fallback(i);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 fallback(@Nullable Drawable drawable) {
        return (GlideOptions) super.fallback(drawable);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 fitCenter() {
        return (GlideOptions) super.fitCenter();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 format(@NonNull AnonymousClass1gj r2) {
        return (GlideOptions) super.format(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 frame(@IntRange(from = 0) long j) {
        return (GlideOptions) super.frame(j);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    public AnonymousClass1g7 lock() {
        super.lock();
        return this;
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 onlyRetrieveFromCache(boolean z) {
        return (GlideOptions) super.onlyRetrieveFromCache(z);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 optionalCenterCrop() {
        return (GlideOptions) super.optionalCenterCrop();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 optionalCenterInside() {
        return (GlideOptions) super.optionalCenterInside();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 optionalCircleCrop() {
        return (GlideOptions) super.optionalCircleCrop();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 optionalFitCenter() {
        return (GlideOptions) super.optionalFitCenter();
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 optionalTransform(@NonNull AnonymousClass1eU<Bitmap> r2) {
        return (GlideOptions) super.optionalTransform(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public <Y> AnonymousClass1g7 optionalTransform(@NonNull Class<Y> cls, @NonNull AnonymousClass1eU<Y> r3) {
        return (GlideOptions) super.optionalTransform((Class) cls, (AnonymousClass1eU) r3);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 override(int i) {
        return (GlideOptions) super.override(i);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 override(int i, int i2) {
        return (GlideOptions) super.override(i, i2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 placeholder(@DrawableRes int i) {
        return (GlideOptions) super.placeholder(i);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 placeholder(@Nullable Drawable drawable) {
        return (GlideOptions) super.placeholder(drawable);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 priority(@NonNull AnonymousClass1cY r2) {
        return (GlideOptions) super.priority(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public <Y> AnonymousClass1g7 set(@NonNull C07491cP<Y> r2, @NonNull Y y) {
        return (GlideOptions) super.set((C07491cP) r2, (Object) y);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 signature(@NonNull AbstractC06491aL r2) {
        return (GlideOptions) super.signature(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (GlideOptions) super.sizeMultiplier(f);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 skipMemoryCache(boolean z) {
        return (GlideOptions) super.skipMemoryCache(z);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 theme(@Nullable Resources.Theme theme) {
        return (GlideOptions) super.theme(theme);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 timeout(@IntRange(from = 0) int i) {
        return (GlideOptions) super.timeout(i);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 transform(@NonNull AnonymousClass1eU<Bitmap> r2) {
        return (GlideOptions) super.transform(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public <Y> AnonymousClass1g7 transform(@NonNull Class<Y> cls, @NonNull AnonymousClass1eU<Y> r3) {
        return (GlideOptions) super.transform((Class) cls, (AnonymousClass1eU) r3);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @SafeVarargs
    @CheckResult
    public final AnonymousClass1g7 transform(@NonNull AnonymousClass1eU<Bitmap>... r2) {
        return (GlideOptions) super.transform(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @Deprecated
    @SafeVarargs
    @CheckResult
    public final AnonymousClass1g7 transforms(@NonNull AnonymousClass1eU<Bitmap>... r2) {
        return (GlideOptions) super.transforms(r2);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 useAnimationPool(boolean z) {
        return (GlideOptions) super.useAnimationPool(z);
    }

    /* Return type fixed from 'com.oculus.common.socialtablet.config.GlideOptions' to match base method */
    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g7 useUnlimitedSourceGeneratorsPool(boolean z) {
        return (GlideOptions) super.useUnlimitedSourceGeneratorsPool(z);
    }
}
