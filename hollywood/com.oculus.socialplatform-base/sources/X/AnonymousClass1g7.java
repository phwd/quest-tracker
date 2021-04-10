package X;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* renamed from: X.1g7  reason: invalid class name */
public class AnonymousClass1g7 extends AnonymousClass1g2<AnonymousClass1g7> {
    @Nullable
    public static AnonymousClass1g7 centerCropOptions;
    @Nullable
    public static AnonymousClass1g7 centerInsideOptions;
    @Nullable
    public static AnonymousClass1g7 circleCropOptions;
    @Nullable
    public static AnonymousClass1g7 fitCenterOptions;
    @Nullable
    public static AnonymousClass1g7 noAnimationOptions;
    @Nullable
    public static AnonymousClass1g7 noTransformOptions;
    @Nullable
    public static AnonymousClass1g7 skipMemoryCacheFalseOptions;
    @Nullable
    public static AnonymousClass1g7 skipMemoryCacheTrueOptions;

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 bitmapTransform(@NonNull AnonymousClass1eU<Bitmap> r1) {
        return (AnonymousClass1g7) new AnonymousClass1g7().transform(r1);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 centerCropTransform() {
        AnonymousClass1g7 r0 = centerCropOptions;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1g2 centerCrop = new AnonymousClass1g7().centerCrop();
        centerCrop.autoClone();
        AnonymousClass1g7 r02 = (AnonymousClass1g7) centerCrop;
        centerCropOptions = r02;
        return r02;
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 centerInsideTransform() {
        AnonymousClass1g7 r0 = centerInsideOptions;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1g2 centerInside = new AnonymousClass1g7().centerInside();
        centerInside.autoClone();
        AnonymousClass1g7 r02 = (AnonymousClass1g7) centerInside;
        centerInsideOptions = r02;
        return r02;
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 circleCropTransform() {
        AnonymousClass1g7 r0 = circleCropOptions;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1g2 circleCrop = new AnonymousClass1g7().circleCrop();
        circleCrop.autoClone();
        AnonymousClass1g7 r02 = (AnonymousClass1g7) circleCrop;
        circleCropOptions = r02;
        return r02;
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 decodeTypeOf(@NonNull Class<?> cls) {
        return (AnonymousClass1g7) new AnonymousClass1g7().decode(cls);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 diskCacheStrategyOf(@NonNull AbstractC08841fc r1) {
        return (AnonymousClass1g7) new AnonymousClass1g7().diskCacheStrategy(r1);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 downsampleOf(@NonNull AbstractC09081gb r1) {
        return (AnonymousClass1g7) new AnonymousClass1g7().downsample(r1);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 encodeFormatOf(@NonNull Bitmap.CompressFormat compressFormat) {
        return (AnonymousClass1g7) new AnonymousClass1g7().encodeFormat(compressFormat);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 encodeQualityOf(@IntRange(from = 0, to = 100) int i) {
        return (AnonymousClass1g7) new AnonymousClass1g7().encodeQuality(i);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 fitCenterTransform() {
        AnonymousClass1g7 r0 = fitCenterOptions;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1g2 fitCenter = new AnonymousClass1g7().fitCenter();
        fitCenter.autoClone();
        AnonymousClass1g7 r02 = (AnonymousClass1g7) fitCenter;
        fitCenterOptions = r02;
        return r02;
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 formatOf(@NonNull AnonymousClass1gj r1) {
        return (AnonymousClass1g7) new AnonymousClass1g7().format(r1);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 frameOf(@IntRange(from = 0) long j) {
        return (AnonymousClass1g7) new AnonymousClass1g7().frame(j);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 noAnimation() {
        AnonymousClass1g7 r0 = noAnimationOptions;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1g2 dontAnimate = new AnonymousClass1g7().dontAnimate();
        dontAnimate.autoClone();
        AnonymousClass1g7 r02 = (AnonymousClass1g7) dontAnimate;
        noAnimationOptions = r02;
        return r02;
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 noTransformation() {
        AnonymousClass1g7 r0 = noTransformOptions;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1g2 dontTransform = new AnonymousClass1g7().dontTransform();
        dontTransform.autoClone();
        AnonymousClass1g7 r02 = (AnonymousClass1g7) dontTransform;
        noTransformOptions = r02;
        return r02;
    }

    @NonNull
    @CheckResult
    public static <T> AnonymousClass1g7 option(@NonNull C07491cP<T> r1, @NonNull T t) {
        return (AnonymousClass1g7) new AnonymousClass1g7().set(r1, t);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 priorityOf(@NonNull AnonymousClass1cY r1) {
        return (AnonymousClass1g7) new AnonymousClass1g7().priority(r1);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 signatureOf(@NonNull AbstractC06491aL r1) {
        return (AnonymousClass1g7) new AnonymousClass1g7().signature(r1);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        return (AnonymousClass1g7) new AnonymousClass1g7().sizeMultiplier(f);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 skipMemoryCacheOf(boolean z) {
        if (z) {
            AnonymousClass1g7 r0 = skipMemoryCacheTrueOptions;
            if (r0 != null) {
                return r0;
            }
            AnonymousClass1g2 skipMemoryCache = new AnonymousClass1g7().skipMemoryCache(true);
            skipMemoryCache.autoClone();
            AnonymousClass1g7 r02 = (AnonymousClass1g7) skipMemoryCache;
            skipMemoryCacheTrueOptions = r02;
            return r02;
        }
        AnonymousClass1g7 r03 = skipMemoryCacheFalseOptions;
        if (r03 != null) {
            return r03;
        }
        AnonymousClass1g2 skipMemoryCache2 = new AnonymousClass1g7().skipMemoryCache(false);
        skipMemoryCache2.autoClone();
        AnonymousClass1g7 r04 = (AnonymousClass1g7) skipMemoryCache2;
        skipMemoryCacheFalseOptions = r04;
        return r04;
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 timeoutOf(@IntRange(from = 0) int i) {
        return (AnonymousClass1g7) new AnonymousClass1g7().timeout(i);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 errorOf(@DrawableRes int i) {
        return (AnonymousClass1g7) new AnonymousClass1g7().error(i);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 errorOf(@Nullable Drawable drawable) {
        return (AnonymousClass1g7) new AnonymousClass1g7().error(drawable);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 overrideOf(int i) {
        return overrideOf(i, i);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 overrideOf(int i, int i2) {
        return (AnonymousClass1g7) new AnonymousClass1g7().override(i, i2);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 placeholderOf(@DrawableRes int i) {
        return (AnonymousClass1g7) new AnonymousClass1g7().placeholder(i);
    }

    @NonNull
    @CheckResult
    public static AnonymousClass1g7 placeholderOf(@Nullable Drawable drawable) {
        return (AnonymousClass1g7) new AnonymousClass1g7().placeholder(drawable);
    }
}
