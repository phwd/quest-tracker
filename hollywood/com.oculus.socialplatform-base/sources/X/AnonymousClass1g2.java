package X;

import X.AnonymousClass1g2;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Map;

/* renamed from: X.1g2  reason: invalid class name */
public abstract class AnonymousClass1g2<T extends AnonymousClass1g2<T>> implements Cloneable {
    public static final int DISK_CACHE_STRATEGY = 4;
    public static final int ERROR_ID = 32;
    public static final int ERROR_PLACEHOLDER = 16;
    public static final int FALLBACK = 8192;
    public static final int FALLBACK_ID = 16384;
    public static final int IS_CACHEABLE = 256;
    public static final int ONLY_RETRIEVE_FROM_CACHE = 524288;
    public static final int OVERRIDE = 512;
    public static final int PLACEHOLDER = 64;
    public static final int PLACEHOLDER_ID = 128;
    public static final int PRIORITY = 8;
    public static final int RESOURCE_CLASS = 4096;
    public static final int SIGNATURE = 1024;
    public static final int SIZE_MULTIPLIER = 2;
    public static final int THEME = 32768;
    public static final int TRANSFORMATION = 2048;
    public static final int TRANSFORMATION_ALLOWED = 65536;
    public static final int TRANSFORMATION_REQUIRED = 131072;
    public static final int UNSET = -1;
    public static final int USE_ANIMATION_POOL = 1048576;
    public static final int USE_UNLIMITED_SOURCE_GENERATORS_POOL = 262144;
    @NonNull
    public AbstractC08841fc diskCacheStrategy = AbstractC08841fc.A01;
    public int errorId;
    @Nullable
    public Drawable errorPlaceholder;
    @Nullable
    public Drawable fallbackDrawable;
    public int fallbackId;
    public int fields;
    public boolean isAutoCloneEnabled;
    public boolean isCacheable = true;
    public boolean isLocked;
    public boolean isScaleOnlyOrNoTransform = true;
    public boolean isTransformationAllowed = true;
    public boolean isTransformationRequired;
    public boolean onlyRetrieveFromCache;
    @NonNull
    public AnonymousClass1cO options = new AnonymousClass1cO();
    public int overrideHeight = -1;
    public int overrideWidth = -1;
    @Nullable
    public Drawable placeholderDrawable;
    public int placeholderId;
    @NonNull
    public AnonymousClass1cY priority = AnonymousClass1cY.NORMAL;
    @NonNull
    public Class<?> resourceClass = Object.class;
    @NonNull
    public AbstractC06491aL signature = AnonymousClass1hI.A00;
    public float sizeMultiplier = 1.0f;
    @Nullable
    public Resources.Theme theme;
    @NonNull
    public Map<Class<?>, AnonymousClass1eU<?>> transformations = new AnonymousClass1cW();
    public boolean useAnimationPool;
    public boolean useUnlimitedSourceGeneratorsPool;

    public static boolean isSet(int i, int i2) {
        return (i & i2) != 0;
    }

    @NonNull
    private T optionalScaleOnlyTransform(@NonNull AbstractC09081gb r2, @NonNull AnonymousClass1eU<Bitmap> r3) {
        return scaleOnlyTransform(r2, r3, false);
    }

    private T self() {
        return this;
    }

    @NonNull
    public T lock() {
        this.isLocked = true;
        return this;
    }

    @NonNull
    private T selfOrThrowIfLocked() {
        if (!this.isLocked) {
            return this;
        }
        throw new IllegalStateException("You cannot modify locked T, consider clone()");
    }

    @NonNull
    @CheckResult
    public T apply(@NonNull AnonymousClass1g2<?> r5) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().apply(r5);
        }
        int i = r5.fields;
        if ((2 & i) != 0) {
            this.sizeMultiplier = r5.sizeMultiplier;
        }
        if ((262144 & i) != 0) {
            this.useUnlimitedSourceGeneratorsPool = r5.useUnlimitedSourceGeneratorsPool;
        }
        if ((1048576 & i) != 0) {
            this.useAnimationPool = r5.useAnimationPool;
        }
        if ((4 & i) != 0) {
            this.diskCacheStrategy = r5.diskCacheStrategy;
        }
        if ((8 & i) != 0) {
            this.priority = r5.priority;
        }
        boolean z = false;
        if ((i & 16) != 0) {
            z = true;
        }
        if (z) {
            this.errorPlaceholder = r5.errorPlaceholder;
            this.errorId = 0;
            this.fields &= -33;
        }
        boolean z2 = false;
        if ((r5.fields & 32) != 0) {
            z2 = true;
        }
        if (z2) {
            this.errorId = r5.errorId;
            this.errorPlaceholder = null;
            this.fields &= -17;
        }
        if ((r5.fields & 64) != 0) {
            this.placeholderDrawable = r5.placeholderDrawable;
            this.placeholderId = 0;
            this.fields &= -129;
        }
        if ((r5.fields & 128) != 0) {
            this.placeholderId = r5.placeholderId;
            this.placeholderDrawable = null;
            this.fields &= -65;
        }
        int i2 = r5.fields;
        if ((256 & i2) != 0) {
            this.isCacheable = r5.isCacheable;
        }
        if ((512 & i2) != 0) {
            this.overrideWidth = r5.overrideWidth;
            this.overrideHeight = r5.overrideHeight;
        }
        if ((1024 & i2) != 0) {
            this.signature = r5.signature;
        }
        if ((4096 & i2) != 0) {
            this.resourceClass = r5.resourceClass;
        }
        if ((i2 & 8192) != 0) {
            this.fallbackDrawable = r5.fallbackDrawable;
            this.fallbackId = 0;
            this.fields &= -16385;
        }
        if ((r5.fields & 16384) != 0) {
            this.fallbackId = r5.fallbackId;
            this.fallbackDrawable = null;
            this.fields &= -8193;
        }
        int i3 = r5.fields;
        if ((32768 & i3) != 0) {
            this.theme = r5.theme;
        }
        if ((65536 & i3) != 0) {
            this.isTransformationAllowed = r5.isTransformationAllowed;
        }
        if ((131072 & i3) != 0) {
            this.isTransformationRequired = r5.isTransformationRequired;
        }
        if ((i3 & 2048) != 0) {
            this.transformations.putAll(r5.transformations);
            this.isScaleOnlyOrNoTransform = r5.isScaleOnlyOrNoTransform;
        }
        if ((r5.fields & 524288) != 0) {
            this.onlyRetrieveFromCache = r5.onlyRetrieveFromCache;
        }
        if (!this.isTransformationAllowed) {
            this.transformations.clear();
            int i4 = this.fields & -2049;
            this.fields = i4;
            this.isTransformationRequired = false;
            this.fields = i4 & -131073;
            this.isScaleOnlyOrNoTransform = true;
        }
        this.fields |= r5.fields;
        this.options.A00.A09(r5.options.A00);
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    public T autoClone() {
        if (!this.isLocked || this.isAutoCloneEnabled) {
            this.isAutoCloneEnabled = true;
            lock();
            return this;
        }
        throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
    }

    @NonNull
    @CheckResult
    public T centerCrop() {
        return transform(AbstractC09081gb.A04, new CenterCrop());
    }

    @NonNull
    @CheckResult
    public T centerInside() {
        return scaleOnlyTransform(AbstractC09081gb.A03, new CenterInside());
    }

    @NonNull
    @CheckResult
    public T circleCrop() {
        return transform(AbstractC09081gb.A03, new AnonymousClass1gE());
    }

    @NonNull
    @CheckResult
    public T decode(@NonNull Class<?> cls) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().decode(cls);
        }
        AnonymousClass1S2.A00(cls);
        this.resourceClass = cls;
        this.fields |= 4096;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T disallowHardwareConfig() {
        return set((C07491cP<Y>) AnonymousClass1gC.A05, false);
    }

    @NonNull
    @CheckResult
    public T diskCacheStrategy(@NonNull AbstractC08841fc r2) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().diskCacheStrategy(r2);
        }
        AnonymousClass1S2.A00(r2);
        this.diskCacheStrategy = r2;
        this.fields |= 4;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T dontAnimate() {
        return set((C07491cP<Y>) C09141gl.A01, true);
    }

    @NonNull
    @CheckResult
    public T dontTransform() {
        if (this.isAutoCloneEnabled) {
            return (T) clone().dontTransform();
        }
        this.transformations.clear();
        int i = this.fields & -2049;
        this.fields = i;
        this.isTransformationRequired = false;
        int i2 = i & -131073;
        this.fields = i2;
        this.isTransformationAllowed = false;
        this.fields = i2 | 65536;
        this.isScaleOnlyOrNoTransform = true;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T downsample(@NonNull AbstractC09081gb r2) {
        AnonymousClass1S2.A00(r2);
        return set((C07491cP<Y>) AbstractC09081gb.A00, r2);
    }

    @NonNull
    @CheckResult
    public T encodeFormat(@NonNull Bitmap.CompressFormat compressFormat) {
        AnonymousClass1S2.A00(compressFormat);
        return set((C07491cP<Y>) AnonymousClass1dD.A01, compressFormat);
    }

    @NonNull
    @CheckResult
    public T encodeQuality(@IntRange(from = 0, to = 100) int i) {
        return set((C07491cP<Y>) AnonymousClass1dD.A02, Integer.valueOf(i));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass1g2)) {
            return false;
        }
        AnonymousClass1g2 r4 = (AnonymousClass1g2) obj;
        if (Float.compare(r4.sizeMultiplier, this.sizeMultiplier) == 0 && this.errorId == r4.errorId && C08381eW.A07(this.errorPlaceholder, r4.errorPlaceholder) && this.placeholderId == r4.placeholderId && C08381eW.A07(this.placeholderDrawable, r4.placeholderDrawable) && this.fallbackId == r4.fallbackId && C08381eW.A07(this.fallbackDrawable, r4.fallbackDrawable) && this.isCacheable == r4.isCacheable && this.overrideHeight == r4.overrideHeight && this.overrideWidth == r4.overrideWidth && this.isTransformationRequired == r4.isTransformationRequired && this.isTransformationAllowed == r4.isTransformationAllowed && this.useUnlimitedSourceGeneratorsPool == r4.useUnlimitedSourceGeneratorsPool && this.onlyRetrieveFromCache == r4.onlyRetrieveFromCache && this.diskCacheStrategy.equals(r4.diskCacheStrategy) && this.priority == r4.priority && this.options.equals(r4.options) && this.transformations.equals(r4.transformations) && this.resourceClass.equals(r4.resourceClass) && C08381eW.A07(this.signature, r4.signature) && C08381eW.A07(this.theme, r4.theme)) {
            return true;
        }
        return false;
    }

    @NonNull
    @CheckResult
    public T fitCenter() {
        return scaleOnlyTransform(AbstractC09081gb.A06, new FitCenter());
    }

    @NonNull
    @CheckResult
    public T frame(@IntRange(from = 0) long j) {
        return set((C07491cP<Y>) AnonymousClass1dO.A04, Long.valueOf(j));
    }

    @NonNull
    public final AbstractC08841fc getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    public final int getErrorId() {
        return this.errorId;
    }

    @Nullable
    public final Drawable getErrorPlaceholder() {
        return this.errorPlaceholder;
    }

    @Nullable
    public final Drawable getFallbackDrawable() {
        return this.fallbackDrawable;
    }

    public final int getFallbackId() {
        return this.fallbackId;
    }

    public final boolean getOnlyRetrieveFromCache() {
        return this.onlyRetrieveFromCache;
    }

    @NonNull
    public final AnonymousClass1cO getOptions() {
        return this.options;
    }

    public final int getOverrideHeight() {
        return this.overrideHeight;
    }

    public final int getOverrideWidth() {
        return this.overrideWidth;
    }

    @Nullable
    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final int getPlaceholderId() {
        return this.placeholderId;
    }

    @NonNull
    public final AnonymousClass1cY getPriority() {
        return this.priority;
    }

    @NonNull
    public final Class<?> getResourceClass() {
        return this.resourceClass;
    }

    @NonNull
    public final AbstractC06491aL getSignature() {
        return this.signature;
    }

    public final float getSizeMultiplier() {
        return this.sizeMultiplier;
    }

    @Nullable
    public final Resources.Theme getTheme() {
        return this.theme;
    }

    @NonNull
    public final Map<Class<?>, AnonymousClass1eU<?>> getTransformations() {
        return this.transformations;
    }

    public final boolean getUseAnimationPool() {
        return this.useAnimationPool;
    }

    public final boolean getUseUnlimitedSourceGeneratorsPool() {
        return this.useUnlimitedSourceGeneratorsPool;
    }

    public int hashCode() {
        return C08381eW.A02(this.theme, C08381eW.A02(this.signature, C08381eW.A02(this.resourceClass, C08381eW.A02(this.transformations, C08381eW.A02(this.options, C08381eW.A02(this.priority, C08381eW.A02(this.diskCacheStrategy, (((((((((((((C08381eW.A02(this.fallbackDrawable, (C08381eW.A02(this.placeholderDrawable, (C08381eW.A02(this.errorPlaceholder, ((527 + Float.floatToIntBits(this.sizeMultiplier)) * 31) + this.errorId) * 31) + this.placeholderId) * 31) + this.fallbackId) * 31) + (this.isCacheable ? 1 : 0)) * 31) + this.overrideHeight) * 31) + this.overrideWidth) * 31) + (this.isTransformationRequired ? 1 : 0)) * 31) + (this.isTransformationAllowed ? 1 : 0)) * 31) + (this.useUnlimitedSourceGeneratorsPool ? 1 : 0)) * 31) + (this.onlyRetrieveFromCache ? 1 : 0))))))));
    }

    public boolean isAutoCloneEnabled() {
        return this.isAutoCloneEnabled;
    }

    public final boolean isDiskCacheStrategySet() {
        if ((this.fields & 4) != 0) {
            return true;
        }
        return false;
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final boolean isMemoryCacheable() {
        return this.isCacheable;
    }

    public final boolean isPrioritySet() {
        if ((this.fields & 8) != 0) {
            return true;
        }
        return false;
    }

    public boolean isScaleOnlyOrNoTransform() {
        return this.isScaleOnlyOrNoTransform;
    }

    public final boolean isSkipMemoryCacheSet() {
        if ((this.fields & 256) != 0) {
            return true;
        }
        return false;
    }

    public final boolean isTransformationAllowed() {
        return this.isTransformationAllowed;
    }

    public final boolean isTransformationRequired() {
        return this.isTransformationRequired;
    }

    public final boolean isTransformationSet() {
        if ((this.fields & 2048) != 0) {
            return true;
        }
        return false;
    }

    public final boolean isValidOverride() {
        return C08381eW.A06(this.overrideWidth, this.overrideHeight);
    }

    @NonNull
    @CheckResult
    public T onlyRetrieveFromCache(boolean z) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().onlyRetrieveFromCache(z);
        }
        this.onlyRetrieveFromCache = z;
        this.fields |= 524288;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T optionalCenterCrop() {
        return optionalTransform(AbstractC09081gb.A04, new CenterCrop());
    }

    @NonNull
    @CheckResult
    public T optionalCenterInside() {
        return optionalScaleOnlyTransform(AbstractC09081gb.A03, new CenterInside());
    }

    @NonNull
    @CheckResult
    public T optionalCircleCrop() {
        return optionalTransform(AbstractC09081gb.A04, new AnonymousClass1gE());
    }

    @NonNull
    @CheckResult
    public T optionalFitCenter() {
        return optionalScaleOnlyTransform(AbstractC09081gb.A06, new FitCenter());
    }

    @NonNull
    @CheckResult
    public T priority(@NonNull AnonymousClass1cY r2) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().priority(r2);
        }
        AnonymousClass1S2.A00(r2);
        this.priority = r2;
        this.fields |= 8;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public <Y> T set(@NonNull C07491cP<Y> r2, @NonNull Y y) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().set(r2, y);
        }
        AnonymousClass1S2.A00(r2);
        AnonymousClass1S2.A00(y);
        this.options.A00.put(r2, y);
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T signature(@NonNull AbstractC06491aL r2) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().signature(r2);
        }
        AnonymousClass1S2.A00(r2);
        this.signature = r2;
        this.fields |= 1024;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().sizeMultiplier(f);
        }
        if (f < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.sizeMultiplier = f;
        this.fields |= 2;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T skipMemoryCache(boolean z) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().skipMemoryCache(true);
        }
        this.isCacheable = !z;
        this.fields |= 256;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T theme(@Nullable Resources.Theme theme2) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().theme(theme2);
        }
        this.theme = theme2;
        this.fields |= 32768;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T timeout(@IntRange(from = 0) int i) {
        return set((C07491cP<Y>) C07481cI.A01, Integer.valueOf(i));
    }

    @NonNull
    @Deprecated
    @CheckResult
    public T transforms(@NonNull AnonymousClass1eU<Bitmap>... r3) {
        return transform((AnonymousClass1eU<Bitmap>) new C08611ev(r3), true);
    }

    @NonNull
    @CheckResult
    public T useAnimationPool(boolean z) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().useAnimationPool(z);
        }
        this.useAnimationPool = z;
        this.fields |= 1048576;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T useUnlimitedSourceGeneratorsPool(boolean z) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().useUnlimitedSourceGeneratorsPool(z);
        }
        this.useUnlimitedSourceGeneratorsPool = z;
        this.fields |= 262144;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T format(@NonNull AnonymousClass1gj r3) {
        AnonymousClass1S2.A00(r3);
        return (T) set((C07491cP<Y>) AnonymousClass1gC.A06, r3).set(C09141gl.A00, r3);
    }

    private boolean isSet(int i) {
        return (this.fields & i) != 0;
    }

    @NonNull
    private T scaleOnlyTransform(@NonNull AbstractC09081gb r2, @NonNull AnonymousClass1eU<Bitmap> r3) {
        return scaleOnlyTransform(r2, r3, true);
    }

    @NonNull
    private T scaleOnlyTransform(@NonNull AbstractC09081gb r3, @NonNull AnonymousClass1eU<Bitmap> r4, boolean z) {
        T optionalTransform;
        if (z) {
            optionalTransform = transform(r3, r4);
        } else {
            optionalTransform = optionalTransform(r3, r4);
        }
        optionalTransform.isScaleOnlyOrNoTransform = true;
        return optionalTransform;
    }

    @Override // java.lang.Object
    @CheckResult
    public T clone() {
        try {
            T t = (T) ((AnonymousClass1g2) super.clone());
            AnonymousClass1cO r1 = new AnonymousClass1cO();
            t.options = r1;
            r1.A00.A09(this.options.A00);
            AnonymousClass1cW r12 = new AnonymousClass1cW();
            t.transformations = r12;
            r12.putAll(this.transformations);
            t.isLocked = false;
            t.isAutoCloneEnabled = false;
            return t;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    @CheckResult
    public T error(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().error(i);
        }
        this.errorId = i;
        int i2 = this.fields | 32;
        this.fields = i2;
        this.errorPlaceholder = null;
        this.fields = i2 & -17;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T error(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().error(drawable);
        }
        this.errorPlaceholder = drawable;
        int i = this.fields | 16;
        this.fields = i;
        this.errorId = 0;
        this.fields = i & -33;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T fallback(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().fallback(i);
        }
        this.fallbackId = i;
        int i2 = this.fields | 16384;
        this.fields = i2;
        this.fallbackDrawable = null;
        this.fields = i2 & -8193;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T fallback(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().fallback(drawable);
        }
        this.fallbackDrawable = drawable;
        int i = this.fields | 8192;
        this.fields = i;
        this.fallbackId = 0;
        this.fields = i & -16385;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T optionalTransform(@NonNull AnonymousClass1eU<Bitmap> r2) {
        return transform(r2, false);
    }

    @NonNull
    public final T optionalTransform(@NonNull AbstractC09081gb r2, @NonNull AnonymousClass1eU<Bitmap> r3) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().optionalTransform(r2, r3);
        }
        downsample(r2);
        return transform(r3, false);
    }

    @NonNull
    @CheckResult
    public <Y> T optionalTransform(@NonNull Class<Y> cls, @NonNull AnonymousClass1eU<Y> r3) {
        return transform(cls, r3, false);
    }

    @NonNull
    @CheckResult
    public T override(int i) {
        return override(i, i);
    }

    @NonNull
    @CheckResult
    public T override(int i, int i2) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().override(i, i2);
        }
        this.overrideWidth = i;
        this.overrideHeight = i2;
        this.fields |= 512;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T placeholder(@DrawableRes int i) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().placeholder(i);
        }
        this.placeholderId = i;
        int i2 = this.fields | 128;
        this.fields = i2;
        this.placeholderDrawable = null;
        this.fields = i2 & -65;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T placeholder(@Nullable Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().placeholder(drawable);
        }
        this.placeholderDrawable = drawable;
        int i = this.fields | 64;
        this.fields = i;
        this.placeholderId = 0;
        this.fields = i & -129;
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T transform(@NonNull AnonymousClass1eU<Bitmap> r2) {
        return transform(r2, true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: X.1eU<android.graphics.Bitmap> */
    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public T transform(@NonNull AnonymousClass1eU<Bitmap> r3, boolean z) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().transform(r3, z);
        }
        AnonymousClass1dH r1 = new AnonymousClass1dH(r3, z);
        transform(Bitmap.class, r3, z);
        transform(Drawable.class, r1, z);
        transform(BitmapDrawable.class, r1, z);
        transform(AnonymousClass1gA.class, new C08561eq(r3), z);
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public final T transform(@NonNull AbstractC09081gb r2, @NonNull AnonymousClass1eU<Bitmap> r3) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().transform(r2, r3);
        }
        downsample(r2);
        return transform(r3);
    }

    @NonNull
    @CheckResult
    public <Y> T transform(@NonNull Class<Y> cls, @NonNull AnonymousClass1eU<Y> r3) {
        return transform(cls, r3, true);
    }

    @NonNull
    public <Y> T transform(@NonNull Class<Y> cls, @NonNull AnonymousClass1eU<Y> r5, boolean z) {
        if (this.isAutoCloneEnabled) {
            return (T) clone().transform(cls, r5, z);
        }
        AnonymousClass1S2.A00(cls);
        AnonymousClass1S2.A00(r5);
        this.transformations.put(cls, r5);
        int i = this.fields | 2048;
        this.fields = i;
        this.isTransformationAllowed = true;
        int i2 = i | 65536;
        this.fields = i2;
        this.isScaleOnlyOrNoTransform = false;
        if (z) {
            this.fields = i2 | 131072;
            this.isTransformationRequired = true;
        }
        selfOrThrowIfLocked();
        return this;
    }

    @NonNull
    @CheckResult
    public T transform(@NonNull AnonymousClass1eU<Bitmap>... r3) {
        int length = r3.length;
        if (length > 1) {
            return transform((AnonymousClass1eU<Bitmap>) new C08611ev(r3), true);
        }
        if (length == 1) {
            return transform(r3[0]);
        }
        selfOrThrowIfLocked();
        return this;
    }
}
