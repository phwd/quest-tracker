package org.chromium.ui.resources;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.SparseArray;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ResourceManager implements AbstractC4223pM0 {

    /* renamed from: a  reason: collision with root package name */
    public final SparseArray f11027a;
    public final SparseArray b = new SparseArray();
    public final float c;
    public long d;

    public ResourceManager(Resources resources, int i, long j) {
        SparseArray sparseArray = new SparseArray();
        this.f11027a = sparseArray;
        this.c = 1.0f / resources.getDisplayMetrics().density;
        sparseArray.put(0, new H11(0, this, resources));
        sparseArray.put(1, new IJ(1, this));
        sparseArray.put(2, new IJ(2, this));
        sparseArray.put(3, new Q51(3, this, i));
        this.d = j;
    }

    public static ResourceManager create(WindowAndroid windowAndroid, long j) {
        Context context = (Context) windowAndroid.f11022J.get();
        if (context != null) {
            Point point = windowAndroid.I.d;
            return new ResourceManager(context.getResources(), Math.min(point.x, point.y), j);
        }
        throw new IllegalStateException("Context should not be null during initialization.");
    }

    public IJ a() {
        return (IJ) this.f11027a.get(1);
    }

    public final void destroy() {
        this.d = 0;
    }

    public final long getNativePtr() {
        return this.d;
    }

    public final void preloadResource(int i, int i2) {
        AbstractC4394qM0 qm0 = (AbstractC4394qM0) this.f11027a.get(i);
        if (qm0 != null) {
            qm0.c(i2);
        }
    }

    public final void resourceRequested(int i, int i2) {
        AbstractC4394qM0 qm0 = (AbstractC4394qM0) this.f11027a.get(i);
        if (qm0 != null) {
            qm0.a(i2);
        }
    }
}
