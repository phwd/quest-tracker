package com.bumptech.glide.request;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;

public class RequestOptions extends BaseRequestOptions<RequestOptions> {
    @Nullable
    private static RequestOptions centerCropOptions;
    @Nullable
    private static RequestOptions centerInsideOptions;
    @Nullable
    private static RequestOptions circleCropOptions;
    @Nullable
    private static RequestOptions fitCenterOptions;
    @Nullable
    private static RequestOptions noAnimationOptions;
    @Nullable
    private static RequestOptions noTransformOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheFalseOptions;
    @Nullable
    private static RequestOptions skipMemoryCacheTrueOptions;

    @NonNull
    @CheckResult
    public static RequestOptions sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (RequestOptions) new RequestOptions().sizeMultiplier(f);
    }

    @NonNull
    @CheckResult
    public static RequestOptions diskCacheStrategyOf(@NonNull DiskCacheStrategy diskCacheStrategy) {
        return (RequestOptions) new RequestOptions().diskCacheStrategy(diskCacheStrategy);
    }

    @NonNull
    @CheckResult
    public static RequestOptions priorityOf(@NonNull Priority priority) {
        return (RequestOptions) new RequestOptions().priority(priority);
    }

    @NonNull
    @CheckResult
    public static RequestOptions placeholderOf(@Nullable Drawable drawable) {
        return (RequestOptions) new RequestOptions().placeholder(drawable);
    }

    @NonNull
    @CheckResult
    public static RequestOptions placeholderOf(@DrawableRes int i) {
        return (RequestOptions) new RequestOptions().placeholder(i);
    }

    @NonNull
    @CheckResult
    public static RequestOptions errorOf(@Nullable Drawable drawable) {
        return (RequestOptions) new RequestOptions().error(drawable);
    }

    @NonNull
    @CheckResult
    public static RequestOptions errorOf(@DrawableRes int i) {
        return (RequestOptions) new RequestOptions().error(i);
    }

    @NonNull
    @CheckResult
    public static RequestOptions skipMemoryCacheOf(boolean z) {
        if (z) {
            if (skipMemoryCacheTrueOptions == null) {
                skipMemoryCacheTrueOptions = (RequestOptions) ((RequestOptions) new RequestOptions().skipMemoryCache(true)).autoClone();
            }
            return skipMemoryCacheTrueOptions;
        }
        if (skipMemoryCacheFalseOptions == null) {
            skipMemoryCacheFalseOptions = (RequestOptions) ((RequestOptions) new RequestOptions().skipMemoryCache(false)).autoClone();
        }
        return skipMemoryCacheFalseOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions overrideOf(int i, int i2) {
        return (RequestOptions) new RequestOptions().override(i, i2);
    }

    @NonNull
    @CheckResult
    public static RequestOptions overrideOf(int i) {
        return overrideOf(i, i);
    }

    @NonNull
    @CheckResult
    public static RequestOptions signatureOf(@NonNull Key key) {
        return (RequestOptions) new RequestOptions().signature(key);
    }

    @NonNull
    @CheckResult
    public static RequestOptions fitCenterTransform() {
        if (fitCenterOptions == null) {
            fitCenterOptions = (RequestOptions) ((RequestOptions) new RequestOptions().fitCenter()).autoClone();
        }
        return fitCenterOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions centerInsideTransform() {
        if (centerInsideOptions == null) {
            centerInsideOptions = (RequestOptions) ((RequestOptions) new RequestOptions().centerInside()).autoClone();
        }
        return centerInsideOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions centerCropTransform() {
        if (centerCropOptions == null) {
            centerCropOptions = (RequestOptions) ((RequestOptions) new RequestOptions().centerCrop()).autoClone();
        }
        return centerCropOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions circleCropTransform() {
        if (circleCropOptions == null) {
            circleCropOptions = (RequestOptions) ((RequestOptions) new RequestOptions().circleCrop()).autoClone();
        }
        return circleCropOptions;
    }

    @NonNull
    @CheckResult
    public static RequestOptions bitmapTransform(@NonNull Transformation<Bitmap> transformation) {
        return (RequestOptions) new RequestOptions().transform(transformation);
    }

    @NonNull
    @CheckResult
    public static RequestOptions noTransformation() {
        if (noTransformOptions == null) {
            noTransformOptions = (RequestOptions) ((RequestOptions) new RequestOptions().dontTransform()).autoClone();
        }
        return noTransformOptions;
    }

    @NonNull
    @CheckResult
    public static <T> RequestOptions option(@NonNull Option<T> option, @NonNull T t) {
        return (RequestOptions) new RequestOptions().set(option, t);
    }

    @NonNull
    @CheckResult
    public static RequestOptions decodeTypeOf(@NonNull Class<?> cls) {
        return (RequestOptions) new RequestOptions().decode(cls);
    }

    @NonNull
    @CheckResult
    public static RequestOptions formatOf(@NonNull DecodeFormat decodeFormat) {
        return (RequestOptions) new RequestOptions().format(decodeFormat);
    }

    @NonNull
    @CheckResult
    public static RequestOptions frameOf(@IntRange(from = 0) long j) {
        return (RequestOptions) new RequestOptions().frame(j);
    }

    @NonNull
    @CheckResult
    public static RequestOptions downsampleOf(@NonNull DownsampleStrategy downsampleStrategy) {
        return (RequestOptions) new RequestOptions().downsample(downsampleStrategy);
    }

    @NonNull
    @CheckResult
    public static RequestOptions timeoutOf(@IntRange(from = 0) int i) {
        return (RequestOptions) new RequestOptions().timeout(i);
    }

    @NonNull
    @CheckResult
    public static RequestOptions encodeQualityOf(@IntRange(from = 0, to = 100) int i) {
        return (RequestOptions) new RequestOptions().encodeQuality(i);
    }

    @NonNull
    @CheckResult
    public static RequestOptions encodeFormatOf(@NonNull Bitmap.CompressFormat compressFormat) {
        return (RequestOptions) new RequestOptions().encodeFormat(compressFormat);
    }

    @NonNull
    @CheckResult
    public static RequestOptions noAnimation() {
        if (noAnimationOptions == null) {
            noAnimationOptions = (RequestOptions) ((RequestOptions) new RequestOptions().dontAnimate()).autoClone();
        }
        return noAnimationOptions;
    }
}
