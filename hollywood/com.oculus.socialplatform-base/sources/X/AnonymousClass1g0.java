package X;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: X.1g0  reason: invalid class name */
public class AnonymousClass1g0 implements ComponentCallbacks2, AbstractC08541eo {
    public static final AnonymousClass1g7 DECODE_TYPE_BITMAP;
    public static final AnonymousClass1g7 DECODE_TYPE_GIF;
    public static final AnonymousClass1g7 DOWNLOAD_ONLY_OPTIONS = ((AnonymousClass1g7) AnonymousClass1g7.diskCacheStrategyOf(AbstractC08841fc.A02).priority(AnonymousClass1cY.LOW).skipMemoryCache(true));
    public final Runnable addSelfToLifecycle;
    public final AnonymousClass0Dx connectivityMonitor;
    public final Context context;
    public final CopyOnWriteArrayList<AnonymousClass1fS<Object>> defaultRequestListeners;
    public final ComponentCallbacks2C07631cl glide;
    public final AnonymousClass1hH lifecycle;
    public final Handler mainHandler;
    public boolean pauseAllRequestsOnTrimMemoryModerate;
    @GuardedBy("this")
    public AnonymousClass1g7 requestOptions;
    @GuardedBy("this")
    public final AnonymousClass1SC requestTracker;
    @GuardedBy("this")
    public final C08551ep targetTracker;
    @GuardedBy("this")
    public final AbstractC08531en treeNode;

    private synchronized void updateRequestOptions(@NonNull AnonymousClass1g7 r2) {
        this.requestOptions = (AnonymousClass1g7) this.requestOptions.apply(r2);
    }

    @NonNull
    public synchronized AnonymousClass1g0 applyDefaultRequestOptions(@NonNull AnonymousClass1g7 r2) {
        updateRequestOptions(r2);
        return this;
    }

    public synchronized AnonymousClass1g7 getDefaultRequestOptions() {
        return this.requestOptions;
    }

    public synchronized boolean isPaused() {
        return this.requestTracker.A00;
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // X.AbstractC08541eo
    public synchronized void onDestroy() {
        this.targetTracker.onDestroy();
        for (AbstractC08781fH<?> r0 : C08381eW.A03(this.targetTracker.A00)) {
            clear(r0);
        }
        this.targetTracker.A00.clear();
        AnonymousClass1SC r4 = this.requestTracker;
        for (AnonymousClass1h5 r2 : C08381eW.A03(r4.A02)) {
            if (r2 != null) {
                boolean remove = r4.A02.remove(r2);
                if (r4.A01.remove(r2) || remove) {
                    r2.clear();
                }
            }
        }
        r4.A01.clear();
        this.lifecycle.A9A(this);
        this.lifecycle.A9A(this.connectivityMonitor);
        this.mainHandler.removeCallbacks(this.addSelfToLifecycle);
        List<AnonymousClass1g0> list = this.glide.A07;
        synchronized (list) {
            if (list.contains(this)) {
                list.remove(this);
            } else {
                throw new IllegalStateException("Cannot unregister not yet registered manager");
            }
        }
    }

    public void onLowMemory() {
    }

    @Override // X.AbstractC08541eo
    public synchronized void onStart() {
        resumeRequests();
        this.targetTracker.onStart();
    }

    @Override // X.AbstractC08541eo
    public synchronized void onStop() {
        pauseRequests();
        this.targetTracker.onStop();
    }

    public synchronized void pauseAllRequests() {
        AnonymousClass1SC r3 = this.requestTracker;
        r3.A00 = true;
        for (AnonymousClass1h5 r1 : C08381eW.A03(r3.A02)) {
            if (r1.isRunning() || r1.isComplete()) {
                r1.clear();
                r3.A01.add(r1);
            }
        }
    }

    public synchronized void pauseAllRequestsRecursive() {
        pauseAllRequests();
        for (AnonymousClass1g0 r0 : this.treeNode.A3m()) {
            r0.pauseAllRequests();
        }
    }

    public synchronized void pauseRequests() {
        AnonymousClass1SC r3 = this.requestTracker;
        r3.A00 = true;
        for (AnonymousClass1h5 r1 : C08381eW.A03(r3.A02)) {
            if (r1.isRunning()) {
                r1.pause();
                r3.A01.add(r1);
            }
        }
    }

    public synchronized void pauseRequestsRecursive() {
        pauseRequests();
        for (AnonymousClass1g0 r0 : this.treeNode.A3m()) {
            r0.pauseRequests();
        }
    }

    public synchronized void resumeRequests() {
        AnonymousClass1SC r3 = this.requestTracker;
        r3.A00 = false;
        for (AnonymousClass1h5 r1 : C08381eW.A03(r3.A02)) {
            if (!r1.isComplete() && !r1.isRunning()) {
                r1.A1a();
            }
        }
        r3.A01.clear();
    }

    public synchronized void resumeRequestsRecursive() {
        C08381eW.A04();
        resumeRequests();
        for (AnonymousClass1g0 r0 : this.treeNode.A3m()) {
            r0.resumeRequests();
        }
    }

    @NonNull
    public synchronized AnonymousClass1g0 setDefaultRequestOptions(@NonNull AnonymousClass1g7 r2) {
        setRequestOptions(r2);
        return this;
    }

    public synchronized void setRequestOptions(@NonNull AnonymousClass1g7 r2) {
        AnonymousClass1g7 r0 = (AnonymousClass1g7) r2.clone();
        r0.autoClone();
        this.requestOptions = r0;
    }

    public synchronized String toString() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{tracker=");
        sb.append(this.requestTracker);
        sb.append(", treeNode=");
        sb.append(this.treeNode);
        sb.append("}");
        return sb.toString();
    }

    public synchronized void track(@NonNull AbstractC08781fH<?> r3, @NonNull AnonymousClass1h5 r4) {
        this.targetTracker.A00.add(r3);
        AnonymousClass1SC r1 = this.requestTracker;
        r1.A02.add(r4);
        if (!r1.A00) {
            r4.A1a();
        } else {
            r4.clear();
            r1.A01.add(r4);
        }
    }

    public synchronized boolean untrack(@NonNull AbstractC08781fH<?> r6) {
        AnonymousClass1h5 request = r6.getRequest();
        if (request != null) {
            AnonymousClass1SC r2 = this.requestTracker;
            boolean remove = r2.A02.remove(request);
            if (!r2.A01.remove(request) && !remove) {
                return false;
            }
            request.clear();
            this.targetTracker.A00.remove(r6);
            r6.setRequest(null);
        }
        return true;
    }

    static {
        AnonymousClass1g7 decodeTypeOf = AnonymousClass1g7.decodeTypeOf(Bitmap.class);
        decodeTypeOf.lock();
        DECODE_TYPE_BITMAP = decodeTypeOf;
        AnonymousClass1g7 decodeTypeOf2 = AnonymousClass1g7.decodeTypeOf(AnonymousClass1gA.class);
        decodeTypeOf2.lock();
        DECODE_TYPE_GIF = decodeTypeOf2;
    }

    public AnonymousClass1g0 addDefaultRequestListener(AnonymousClass1fS<Object> r2) {
        this.defaultRequestListeners.add(r2);
        return this;
    }

    @NonNull
    @CheckResult
    public <ResourceType> AnonymousClass1g1<ResourceType> as(@NonNull Class<ResourceType> cls) {
        return new AnonymousClass1g1<>(this.glide, this, cls, this.context);
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Bitmap> asBitmap() {
        return as(Bitmap.class).apply((AnonymousClass1g2<?>) DECODE_TYPE_BITMAP);
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> asDrawable() {
        return as(Drawable.class);
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<File> asFile() {
        return as(File.class).apply((AnonymousClass1g2<?>) AnonymousClass1g7.skipMemoryCacheOf(true));
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<AnonymousClass1gA> asGif() {
        return as(AnonymousClass1gA.class).apply((AnonymousClass1g2<?>) DECODE_TYPE_GIF);
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<File> downloadOnly() {
        return as(File.class).apply((AnonymousClass1g2<?>) DOWNLOAD_ONLY_OPTIONS);
    }

    public List<AnonymousClass1fS<Object>> getDefaultRequestListeners() {
        return this.defaultRequestListeners;
    }

    @NonNull
    public <T> AbstractC08971fp<?, T> getDefaultTransitionOptions(Class<T> cls) {
        Map<Class<?>, AbstractC08971fp<?, ?>> map = this.glide.A00.A06;
        AbstractC08971fp<?, T> r3 = (AbstractC08971fp<?, T>) map.get(cls);
        if (r3 != null) {
            return r3;
        }
        for (Map.Entry<Class<?>, AbstractC08971fp<?, ?>> entry : map.entrySet()) {
            if (entry.getKey().isAssignableFrom(cls)) {
                r3 = (AbstractC08971fp<?, T>) entry.getValue();
            }
        }
        return r3 == null ? (AbstractC08971fp<?, T>) C08731fB.A09 : r3;
    }

    public void onTrimMemory(int i) {
        if (i == 60 && this.pauseAllRequestsOnTrimMemoryModerate) {
            pauseAllRequestsRecursive();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        if (r3 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        r5.setRequest(null);
        r3.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void untrackOrDelegate(@androidx.annotation.NonNull X.AbstractC08781fH<?> r5) {
        /*
            r4 = this;
            boolean r0 = r4.untrack(r5)
            X.1h5 r3 = r5.getRequest()
            if (r0 != 0) goto L_0x0035
            X.1cl r0 = r4.glide
            java.util.List<X.1g0> r2 = r0.A07
            monitor-enter(r2)
            java.util.Iterator r1 = r2.iterator()     // Catch:{ all -> 0x0029 }
        L_0x0013:
            boolean r0 = r1.hasNext()     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0027
            java.lang.Object r0 = r1.next()     // Catch:{ all -> 0x0029 }
            X.1g0 r0 = (X.AnonymousClass1g0) r0     // Catch:{ all -> 0x0029 }
            boolean r0 = r0.untrack(r5)     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0013
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            return
        L_0x0027:
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            goto L_0x002c
        L_0x0029:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0029 }
            throw r0
        L_0x002c:
            if (r3 == 0) goto L_0x0035
            r0 = 0
            r5.setRequest(r0)
            r3.clear()
        L_0x0035:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1g0.untrackOrDelegate(X.1fH):void");
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<File> download(@Nullable Object obj) {
        AnonymousClass1g1<File> downloadOnly = downloadOnly();
        downloadOnly.load(obj);
        return downloadOnly;
    }

    public void setPauseAllRequestsOnTrimMemoryModerate(boolean z) {
        this.pauseAllRequestsOnTrimMemoryModerate = z;
    }

    public AnonymousClass1g0(@NonNull ComponentCallbacks2C07631cl r8, @NonNull AnonymousClass1hH r9, @NonNull AbstractC08531en r10, @NonNull Context context2) {
        this(r8, r9, r10, new AnonymousClass1SC(), r8.A05, context2);
    }

    public AnonymousClass1g0(ComponentCallbacks2C07631cl r5, AnonymousClass1hH r6, AbstractC08531en r7, AnonymousClass1SC r8, AnonymousClass1S9 r9, Context context2) {
        AnonymousClass1g7 r0;
        this.targetTracker = new C08551ep();
        this.addSelfToLifecycle = new AnonymousClass1h8(this);
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.glide = r5;
        this.lifecycle = r6;
        this.treeNode = r7;
        this.requestTracker = r8;
        this.context = context2;
        this.connectivityMonitor = r9.A1p(context2.getApplicationContext(), new AnonymousClass1SD(this, r8));
        if (C08381eW.A05()) {
            this.mainHandler.post(this.addSelfToLifecycle);
        } else {
            r6.A1H(this);
        }
        r6.A1H(this.connectivityMonitor);
        C08731fB r3 = r5.A00;
        this.defaultRequestListeners = new CopyOnWriteArrayList<>(r3.A05);
        synchronized (r3) {
            r0 = r3.A00;
            if (r0 == null) {
                r0 = new AnonymousClass1g7();
                r0.lock();
                r3.A00 = r0;
            }
        }
        setRequestOptions(r0);
        List<AnonymousClass1g0> list = r5.A07;
        synchronized (list) {
            if (!list.contains(this)) {
                list.add(this);
            } else {
                throw new IllegalStateException("Cannot register already registered manager");
            }
        }
    }

    public void clear(@NonNull View view) {
        clear(new AnonymousClass1gq(view));
    }

    public void clear(@Nullable AbstractC08781fH<?> r1) {
        if (r1 != null) {
            untrackOrDelegate(r1);
        }
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> load(@Nullable Bitmap bitmap) {
        return asDrawable().load(bitmap);
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> load(@Nullable Drawable drawable) {
        return asDrawable().load(drawable);
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> load(@Nullable Uri uri) {
        AnonymousClass1g1<Drawable> asDrawable = asDrawable();
        asDrawable.load(uri);
        return asDrawable;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> load(@Nullable File file) {
        AnonymousClass1g1<Drawable> asDrawable = asDrawable();
        asDrawable.load(file);
        return asDrawable;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> load(@Nullable @DrawableRes @RawRes Integer num) {
        return asDrawable().load(num);
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> load(@Nullable Object obj) {
        AnonymousClass1g1<Drawable> asDrawable = asDrawable();
        asDrawable.load(obj);
        return asDrawable;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> load(@Nullable String str) {
        AnonymousClass1g1<Drawable> asDrawable = asDrawable();
        asDrawable.load(str);
        return asDrawable;
    }

    @CheckResult
    @Deprecated
    public AnonymousClass1g1<Drawable> load(@Nullable URL url) {
        AnonymousClass1g1<Drawable> asDrawable = asDrawable();
        asDrawable.load(url);
        return asDrawable;
    }

    @NonNull
    @CheckResult
    public AnonymousClass1g1<Drawable> load(@Nullable byte[] bArr) {
        return asDrawable().load(bArr);
    }
}
