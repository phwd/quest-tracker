package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: X.1g1  reason: invalid class name */
public class AnonymousClass1g1<TranscodeType> extends AnonymousClass1g2<AnonymousClass1g1<TranscodeType>> implements Cloneable {
    public static final AnonymousClass1g7 DOWNLOAD_ONLY_OPTIONS = ((AnonymousClass1g7) new AnonymousClass1g7().diskCacheStrategy(AbstractC08841fc.A02).priority(AnonymousClass1cY.LOW).skipMemoryCache(true));
    public final Context context;
    @Nullable
    public AnonymousClass1g1<TranscodeType> errorBuilder;
    public final ComponentCallbacks2C07631cl glide;
    public final C08731fB glideContext;
    public boolean isDefaultTransitionOptionsSet;
    public boolean isModelSet;
    public boolean isThumbnailBuilt;
    @Nullable
    public Object model;
    @Nullable
    public List<AnonymousClass1fS<TranscodeType>> requestListeners;
    public final AnonymousClass1g0 requestManager;
    @Nullable
    public Float thumbSizeMultiplier;
    @Nullable
    public AnonymousClass1g1<TranscodeType> thumbnailBuilder;
    public final Class<TranscodeType> transcodeClass;
    @NonNull
    public AbstractC08971fp<?, ? super TranscodeType> transitionOptions;

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> listener(@Nullable AnonymousClass1fS<TranscodeType> r2) {
        this.requestListeners = null;
        addListener(r2);
        return this;
    }

    private AnonymousClass1h5 buildRequest(AbstractC08781fH<TranscodeType> r12, @Nullable AnonymousClass1fS<TranscodeType> r13, AnonymousClass1g2<?> r14, Executor executor) {
        return buildRequestRecursive(new Object(), r12, r13, null, this.transitionOptions, r14.priority, r14.overrideWidth, r14.overrideHeight, r14, executor);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: X.1gO */
    /* JADX DEBUG: Multi-variable search result rejected for r4v1, resolved type: X.1gO */
    /* JADX DEBUG: Multi-variable search result rejected for r4v2, resolved type: X.1gO */
    /* JADX WARN: Multi-variable type inference failed */
    private AnonymousClass1h5 buildRequestRecursive(Object obj, AbstractC08781fH<TranscodeType> r19, @Nullable AnonymousClass1fS<TranscodeType> r20, @Nullable AbstractC08811fX r21, AbstractC08971fp<?, ? super TranscodeType> r22, AnonymousClass1cY r23, int i, int i2, AnonymousClass1g2<?> r26, Executor executor) {
        C09061gO r4;
        AbstractC08811fX r10;
        if (this.errorBuilder != null) {
            r10 = new C09061gO(obj, r21);
            r4 = r10;
        } else {
            r4 = 0;
            r10 = r21;
        }
        AnonymousClass1h5 buildThumbnailRequestRecursive = buildThumbnailRequestRecursive(obj, r19, r20, r10, r22, r23, i, i2, r26, executor);
        if (r4 == 0) {
            return buildThumbnailRequestRecursive;
        }
        AnonymousClass1g1<TranscodeType> r6 = this.errorBuilder;
        int i3 = r6.overrideWidth;
        int i4 = r6.overrideHeight;
        if (C08381eW.A06(i, i2) && !r6.isValidOverride()) {
            i3 = r26.overrideWidth;
            i4 = r26.overrideHeight;
        }
        AnonymousClass1h5 buildRequestRecursive = r6.buildRequestRecursive(obj, r19, r20, r4, r6.transitionOptions, r6.priority, i3, i4, r6, executor);
        r4.A05 = buildThumbnailRequestRecursive;
        r4.A04 = buildRequestRecursive;
        return r4;
    }

    private boolean isSkipMemoryCacheWithCompletePreviousRequest(AnonymousClass1g2<?> r3, AnonymousClass1h5 r4) {
        if (r3.isCacheable || !r4.isComplete()) {
            return false;
        }
        return true;
    }

    @NonNull
    private AnonymousClass1g1<TranscodeType> loadGeneric(@Nullable Object obj) {
        this.model = obj;
        this.isModelSet = true;
        return this;
    }

    private AnonymousClass1h5 obtainRequest(Object obj, AbstractC08781fH<TranscodeType> r19, AnonymousClass1fS<TranscodeType> r20, AnonymousClass1g2<?> r21, AbstractC08811fX r22, AbstractC08971fp<?, ? super TranscodeType> r23, AnonymousClass1cY r24, int i, int i2, Executor executor) {
        Context context2 = this.context;
        C08731fB r2 = this.glideContext;
        return new AnonymousClass1f3(context2, r2, obj, this.model, this.transcodeClass, r21, i, i2, r24, r19, r20, this.requestListeners, r22, r2.A03, r23.A00, executor);
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> addListener(@Nullable AnonymousClass1fS<TranscodeType> r2) {
        if (r2 != null) {
            List list = this.requestListeners;
            if (list == null) {
                list = new ArrayList();
                this.requestListeners = list;
            }
            list.add(r2);
        }
        return this;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<File> getDownloadOnlyRequest() {
        return new AnonymousClass1g1(File.class, this).apply((AnonymousClass1g2<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    @NonNull
    private AnonymousClass1cY getThumbnailPriority(@NonNull AnonymousClass1cY r3) {
        switch (r3.ordinal()) {
            case 0:
            case 1:
                return AnonymousClass1cY.IMMEDIATE;
            case 2:
                return AnonymousClass1cY.HIGH;
            case 3:
                return AnonymousClass1cY.NORMAL;
            default:
                StringBuilder sb = new StringBuilder("unknown priority: ");
                sb.append(this.priority);
                throw new IllegalArgumentException(sb.toString());
        }
    }

    @SuppressLint({"CheckResult"})
    private void initRequestListeners(List<AnonymousClass1fS<Object>> list) {
        for (AnonymousClass1fS<Object> r0 : list) {
            addListener(r0);
        }
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> transition(@NonNull AbstractC08971fp<?, ? super TranscodeType> r2) {
        AnonymousClass1S2.A00(r2);
        this.transitionOptions = r2;
        this.isDefaultTransitionOptionsSet = false;
        return this;
    }

    @NonNull
    public AnonymousClass1g1<TranscodeType> error(@Nullable AnonymousClass1g1<TranscodeType> r1) {
        this.errorBuilder = r1;
        return this;
    }

    @SuppressLint({"CheckResult"})
    public AnonymousClass1g1(@NonNull ComponentCallbacks2C07631cl r2, AnonymousClass1g0 r3, Class<TranscodeType> cls, Context context2) {
        this.isDefaultTransitionOptionsSet = true;
        this.glide = r2;
        this.requestManager = r3;
        this.transcodeClass = cls;
        this.context = context2;
        this.transitionOptions = r3.getDefaultTransitionOptions(cls);
        this.glideContext = r2.A00;
        initRequestListeners(r3.defaultRequestListeners);
        apply((AnonymousClass1g2<?>) r3.getDefaultRequestOptions());
    }

    @SuppressLint({"CheckResult"})
    public AnonymousClass1g1(Class<TranscodeType> cls, AnonymousClass1g1<?> r5) {
        this(r5.glide, r5.requestManager, cls, r5.context);
        this.model = r5.model;
        this.isModelSet = r5.isModelSet;
        apply((AnonymousClass1g2<?>) r5);
    }

    private AnonymousClass1h5 buildThumbnailRequestRecursive(Object obj, AbstractC08781fH<TranscodeType> r33, AnonymousClass1fS<TranscodeType> r34, @Nullable AbstractC08811fX r35, AbstractC08971fp<?, ? super TranscodeType> r36, AnonymousClass1cY r37, int i, int i2, AnonymousClass1g2<?> r40, Executor executor) {
        AnonymousClass1cY thumbnailPriority;
        AnonymousClass1g1<TranscodeType> r1 = this.thumbnailBuilder;
        if (r1 != null) {
            if (!this.isThumbnailBuilt) {
                AbstractC08971fp<?, ? super TranscodeType> r8 = r1.transitionOptions;
                if (r1.isDefaultTransitionOptionsSet) {
                    r8 = r36;
                }
                if (r1.isPrioritySet()) {
                    thumbnailPriority = r1.priority;
                } else {
                    thumbnailPriority = getThumbnailPriority(r37);
                }
                AnonymousClass1g1<TranscodeType> r9 = this.thumbnailBuilder;
                int i3 = r9.overrideWidth;
                int i4 = r9.overrideHeight;
                if (C08381eW.A06(i, i2) && !r9.isValidOverride()) {
                    i3 = r40.overrideWidth;
                    i4 = r40.overrideHeight;
                }
                C09051gN r15 = new C09051gN(obj, r35);
                AnonymousClass1h5 obtainRequest = obtainRequest(obj, r33, r34, r40, r15, r36, r37, i, i2, executor);
                this.isThumbnailBuilt = true;
                AnonymousClass1g1<TranscodeType> r0 = this.thumbnailBuilder;
                AnonymousClass1h5 buildRequestRecursive = r0.buildRequestRecursive(obj, r33, r34, r15, r8, thumbnailPriority, i3, i4, r0, executor);
                this.isThumbnailBuilt = false;
                r15.A05 = obtainRequest;
                r15.A06 = buildRequestRecursive;
                return r15;
            }
            throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
        } else if (this.thumbSizeMultiplier == null) {
            return obtainRequest(obj, r33, r34, r40, r35, r36, r37, i, i2, executor);
        } else {
            C09051gN r152 = new C09051gN(obj, r35);
            AnonymousClass1h5 obtainRequest2 = obtainRequest(obj, r33, r34, r40, r152, r36, r37, i, i2, executor);
            AnonymousClass1h5 obtainRequest3 = obtainRequest(obj, r33, r34, r40.clone().sizeMultiplier(this.thumbSizeMultiplier.floatValue()), r152, r36, getThumbnailPriority(r37), i, i2, executor);
            r152.A05 = obtainRequest2;
            r152.A06 = obtainRequest3;
            return r152;
        }
    }

    private <Y extends AbstractC08781fH<TranscodeType>> Y into(@NonNull Y y, @Nullable AnonymousClass1fS<TranscodeType> r5, AnonymousClass1g2<?> r6, Executor executor) {
        AnonymousClass1S2.A00(y);
        if (this.isModelSet) {
            AnonymousClass1h5 buildRequest = buildRequest(y, r5, r6, executor);
            AnonymousClass1h5 request = y.getRequest();
            if (!buildRequest.A5v(request) || isSkipMemoryCacheWithCompletePreviousRequest(r6, request)) {
                this.requestManager.clear((AbstractC08781fH<?>) y);
                y.setRequest(buildRequest);
                this.requestManager.track(y, buildRequest);
                return y;
            }
            AnonymousClass1S2.A00(request);
            if (!request.isRunning()) {
                request.A1a();
            }
            return y;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    @Override // X.AnonymousClass1g2
    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> apply(@NonNull AnonymousClass1g2<?> r2) {
        AnonymousClass1S2.A00(r2);
        return (AnonymousClass1g1) super.apply(r2);
    }

    @Override // java.lang.Object, X.AnonymousClass1g2, X.AnonymousClass1g2
    @CheckResult
    public AnonymousClass1g1<TranscodeType> clone() {
        AnonymousClass1g1<TranscodeType> r1 = (AnonymousClass1g1) super.clone();
        r1.transitionOptions = r1.transitionOptions.clone();
        return r1;
    }

    @CheckResult
    @Deprecated
    public AnonymousClass0Dy<File> downloadOnly(int i, int i2) {
        return getDownloadOnlyRequest().submit(i, i2);
    }

    @CheckResult
    @Deprecated
    public <Y extends AbstractC08781fH<File>> Y downloadOnly(@NonNull Y y) {
        getDownloadOnlyRequest().into(y);
        return y;
    }

    @Deprecated
    public AnonymousClass0Dy<TranscodeType> into(int i, int i2) {
        return submit(i, i2);
    }

    @NonNull
    public <Y extends AbstractC08781fH<TranscodeType>> Y into(@NonNull Y y) {
        into(y, null, this, C07681cq.A01);
        return y;
    }

    @NonNull
    public <Y extends AbstractC08781fH<TranscodeType>> Y into(@NonNull Y y, @Nullable AnonymousClass1fS<TranscodeType> r2, Executor executor) {
        into(y, r2, this, executor);
        return y;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003c  */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public X.AbstractC09221gz<android.widget.ImageView, TranscodeType> into(@androidx.annotation.NonNull android.widget.ImageView r5) {
        /*
        // Method dump skipped, instructions count: 142
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1g1.into(android.widget.ImageView):X.1gz");
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> load(@Nullable Bitmap bitmap) {
        this.model = bitmap;
        this.isModelSet = true;
        return apply((AnonymousClass1g2<?>) AnonymousClass1g7.diskCacheStrategyOf(AbstractC08841fc.A03));
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> load(@Nullable Drawable drawable) {
        this.model = drawable;
        this.isModelSet = true;
        return apply((AnonymousClass1g2<?>) AnonymousClass1g7.diskCacheStrategyOf(AbstractC08841fc.A03));
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> load(@Nullable Uri uri) {
        this.model = uri;
        this.isModelSet = true;
        return this;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> load(@Nullable File file) {
        this.model = file;
        this.isModelSet = true;
        return this;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0051  */
    @androidx.annotation.NonNull
    @androidx.annotation.CheckResult
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public X.AnonymousClass1g1<TranscodeType> load(@androidx.annotation.Nullable @androidx.annotation.DrawableRes @androidx.annotation.RawRes java.lang.Integer r7) {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1g1.load(java.lang.Integer):X.1g1");
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> load(@Nullable Object obj) {
        this.model = obj;
        this.isModelSet = true;
        return this;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> load(@Nullable String str) {
        this.model = str;
        this.isModelSet = true;
        return this;
    }

    @CheckResult
    @Deprecated
    public AnonymousClass1g1<TranscodeType> load(@Nullable URL url) {
        this.model = url;
        this.isModelSet = true;
        return this;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> load(@Nullable byte[] bArr) {
        this.model = bArr;
        this.isModelSet = true;
        AnonymousClass1g1<TranscodeType> r1 = this;
        if (!isDiskCacheStrategySet()) {
            r1 = apply((AnonymousClass1g2<?>) AnonymousClass1g7.diskCacheStrategyOf(AbstractC08841fc.A03));
        }
        return !r1.isSkipMemoryCacheSet() ? r1.apply((AnonymousClass1g2<?>) AnonymousClass1g7.skipMemoryCacheOf(true)) : r1;
    }

    @NonNull
    public AbstractC08781fH<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    public AbstractC08781fH<TranscodeType> preload(int i, int i2) {
        C09071gP r0 = new C09071gP(this.requestManager, i, i2);
        into(r0);
        return r0;
    }

    @NonNull
    public AnonymousClass0Dy<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @NonNull
    public AnonymousClass0Dy<TranscodeType> submit(int i, int i2) {
        AnonymousClass1fT r1 = new AnonymousClass1fT(i, i2);
        into(r1, r1, this, C07681cq.A00);
        return r1;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> thumbnail(float f) {
        if (f < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z || f > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.thumbSizeMultiplier = Float.valueOf(f);
        return this;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> thumbnail(@Nullable AnonymousClass1g1<TranscodeType> r1) {
        this.thumbnailBuilder = r1;
        return this;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<TranscodeType> thumbnail(@Nullable AnonymousClass1g1<TranscodeType>... r4) {
        AnonymousClass1g1<TranscodeType> r2 = null;
        if (r4 != null && (r1 = r4.length) != 0) {
            while (true) {
                int length = length - 1;
                if (length < 0) {
                    break;
                }
                AnonymousClass1g1<TranscodeType> r0 = r4[length];
                if (r0 != null) {
                    if (r2 != null) {
                        r0.thumbnail(r2);
                    }
                    r2 = r0;
                }
            }
        }
        thumbnail(r2);
        return this;
    }
}
