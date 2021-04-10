package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.TypedValue;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.WeakHashMap;

/* renamed from: tM0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4904tM0 {

    /* renamed from: a  reason: collision with root package name */
    public static final PorterDuff.Mode f11339a = PorterDuff.Mode.SRC_IN;
    public static C4904tM0 b;
    public static final C4564rM0 c = new C4564rM0(6);
    public WeakHashMap d;
    public BW0 e;
    public MY0 f;
    public final WeakHashMap g = new WeakHashMap(0);
    public TypedValue h;
    public boolean i;
    public C3669m8 j;

    public static synchronized C4904tM0 c() {
        C4904tM0 tm0;
        synchronized (C4904tM0.class) {
            if (b == null) {
                b = new C4904tM0();
            }
            tm0 = b;
        }
        return tm0;
    }

    public static synchronized PorterDuffColorFilter g(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (C4904tM0.class) {
            C4564rM0 rm0 = c;
            Objects.requireNonNull(rm0);
            int i3 = (i2 + 31) * 31;
            porterDuffColorFilter = (PorterDuffColorFilter) rm0.b(Integer.valueOf(mode.hashCode() + i3));
            if (porterDuffColorFilter == null) {
                porterDuffColorFilter = new PorterDuffColorFilter(i2, mode);
                Objects.requireNonNull(rm0);
                PorterDuffColorFilter porterDuffColorFilter2 = (PorterDuffColorFilter) rm0.c(Integer.valueOf(mode.hashCode() + i3), porterDuffColorFilter);
            }
        }
        return porterDuffColorFilter;
    }

    public final synchronized boolean a(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        C4083ob0 ob0 = (C4083ob0) this.g.get(context);
        if (ob0 == null) {
            ob0 = new C4083ob0();
            this.g.put(context, ob0);
        }
        ob0.i(j2, new WeakReference(constantState));
        return true;
    }

    public final Drawable b(Context context, int i2) {
        if (this.h == null) {
            this.h = new TypedValue();
        }
        TypedValue typedValue = this.h;
        context.getResources().getValue(i2, typedValue, true);
        long j2 = (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
        Drawable d2 = d(context, j2);
        if (d2 != null) {
            return d2;
        }
        LayerDrawable layerDrawable = null;
        if (this.j != null && i2 == R.drawable.f27410_resource_name_obfuscated_RES_2131230781) {
            layerDrawable = new LayerDrawable(new Drawable[]{e(context, R.drawable.f27400_resource_name_obfuscated_RES_2131230780), e(context, R.drawable.f27420_resource_name_obfuscated_RES_2131230782)});
        }
        if (layerDrawable != null) {
            layerDrawable.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, j2, layerDrawable);
        }
        return layerDrawable;
    }

    public final synchronized Drawable d(Context context, long j2) {
        C4083ob0 ob0 = (C4083ob0) this.g.get(context);
        if (ob0 == null) {
            return null;
        }
        WeakReference weakReference = (WeakReference) ob0.f(j2, null);
        if (weakReference != null) {
            Drawable.ConstantState constantState = (Drawable.ConstantState) weakReference.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            ob0.j(j2);
        }
        return null;
    }

    public synchronized Drawable e(Context context, int i2) {
        return f(context, i2, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        if (r0 != false) goto L_0x002b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.drawable.Drawable f(android.content.Context r5, int r6, boolean r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.i     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x0006
            goto L_0x002b
        L_0x0006:
            r0 = 1
            r4.i = r0     // Catch:{ all -> 0x0049 }
            r1 = 2131230850(0x7f080082, float:1.8077764E38)
            android.graphics.drawable.Drawable r1 = r4.e(r5, r1)     // Catch:{ all -> 0x0049 }
            r2 = 0
            if (r1 == 0) goto L_0x004b
            boolean r3 = r1 instanceof defpackage.Fs1     // Catch:{ all -> 0x0049 }
            if (r3 != 0) goto L_0x0029
            java.lang.String r3 = "android.graphics.drawable.VectorDrawable"
            java.lang.Class r1 = r1.getClass()     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = r1.getName()     // Catch:{ all -> 0x0049 }
            boolean r1 = r3.equals(r1)     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0028
            goto L_0x0029
        L_0x0028:
            r0 = r2
        L_0x0029:
            if (r0 == 0) goto L_0x004b
        L_0x002b:
            android.graphics.drawable.Drawable r0 = r4.i(r5, r6)     // Catch:{ all -> 0x0049 }
            if (r0 != 0) goto L_0x0035
            android.graphics.drawable.Drawable r0 = r4.b(r5, r6)     // Catch:{ all -> 0x0049 }
        L_0x0035:
            if (r0 != 0) goto L_0x003d
            java.lang.Object r0 = defpackage.K2.f8337a     // Catch:{ all -> 0x0049 }
            android.graphics.drawable.Drawable r0 = r5.getDrawable(r6)     // Catch:{ all -> 0x0049 }
        L_0x003d:
            if (r0 == 0) goto L_0x0043
            android.graphics.drawable.Drawable r0 = r4.j(r5, r6, r7, r0)     // Catch:{ all -> 0x0049 }
        L_0x0043:
            if (r0 == 0) goto L_0x0047
            int[] r5 = defpackage.XI.f9200a     // Catch:{ all -> 0x0049 }
        L_0x0047:
            monitor-exit(r4)
            return r0
        L_0x0049:
            r5 = move-exception
            goto L_0x0055
        L_0x004b:
            r4.i = r2
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat."
            r5.<init>(r6)
            throw r5
        L_0x0055:
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4904tM0.f(android.content.Context, int, boolean):android.graphics.drawable.Drawable");
    }

    public synchronized ColorStateList h(Context context, int i2) {
        ColorStateList colorStateList;
        MY0 my0;
        WeakHashMap weakHashMap = this.d;
        ColorStateList colorStateList2 = null;
        colorStateList = (weakHashMap == null || (my0 = (MY0) weakHashMap.get(context)) == null) ? null : (ColorStateList) my0.e(i2, null);
        if (colorStateList == null) {
            C3669m8 m8Var = this.j;
            if (m8Var != null) {
                colorStateList2 = m8Var.c(context, i2);
            }
            if (colorStateList2 != null) {
                if (this.d == null) {
                    this.d = new WeakHashMap();
                }
                MY0 my02 = (MY0) this.d.get(context);
                if (my02 == null) {
                    my02 = new MY0(10);
                    this.d.put(context, my02);
                }
                my02.a(i2, colorStateList2);
            }
            colorStateList = colorStateList2;
        }
        return colorStateList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b A[Catch:{ Exception -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a1 A[Catch:{ Exception -> 0x00a9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.graphics.drawable.Drawable i(android.content.Context r11, int r12) {
        /*
        // Method dump skipped, instructions count: 186
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4904tM0.i(android.content.Context, int):android.graphics.drawable.Drawable");
    }

    public final Drawable j(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList h2 = h(context, i2);
        PorterDuff.Mode mode = null;
        if (h2 != null) {
            if (XI.a(drawable)) {
                drawable = drawable.mutate();
            }
            drawable.setTintList(h2);
            if (this.j != null && i2 == R.drawable.f27940_resource_name_obfuscated_RES_2131230834) {
                mode = PorterDuff.Mode.MULTIPLY;
            }
            if (mode == null) {
                return drawable;
            }
            drawable.setTintMode(mode);
            return drawable;
        }
        C3669m8 m8Var = this.j;
        if (m8Var != null) {
            boolean z2 = true;
            if (i2 == R.drawable.f27910_resource_name_obfuscated_RES_2131230831) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(16908288);
                int c2 = AbstractC1361Wg1.c(context, R.attr.f3080_resource_name_obfuscated_RES_2130968754);
                PorterDuff.Mode mode2 = C3840n8.f10473a;
                m8Var.d(findDrawableByLayerId, c2, mode2);
                m8Var.d(layerDrawable.findDrawableByLayerId(16908303), AbstractC1361Wg1.c(context, R.attr.f3080_resource_name_obfuscated_RES_2130968754), mode2);
                m8Var.d(layerDrawable.findDrawableByLayerId(16908301), AbstractC1361Wg1.c(context, R.attr.f3060_resource_name_obfuscated_RES_2130968752), mode2);
            } else if (i2 == R.drawable.f27820_resource_name_obfuscated_RES_2131230822 || i2 == R.drawable.f27810_resource_name_obfuscated_RES_2131230821 || i2 == R.drawable.f27830_resource_name_obfuscated_RES_2131230823) {
                LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                Drawable findDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(16908288);
                int b2 = AbstractC1361Wg1.b(context, R.attr.f3080_resource_name_obfuscated_RES_2130968754);
                PorterDuff.Mode mode3 = C3840n8.f10473a;
                m8Var.d(findDrawableByLayerId2, b2, mode3);
                m8Var.d(layerDrawable2.findDrawableByLayerId(16908303), AbstractC1361Wg1.c(context, R.attr.f3060_resource_name_obfuscated_RES_2130968752), mode3);
                m8Var.d(layerDrawable2.findDrawableByLayerId(16908301), AbstractC1361Wg1.c(context, R.attr.f3060_resource_name_obfuscated_RES_2130968752), mode3);
            } else {
                z2 = false;
            }
            if (z2) {
                return drawable;
            }
        }
        if (k(context, i2, drawable) || !z) {
            return drawable;
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean k(android.content.Context r8, int r9, android.graphics.drawable.Drawable r10) {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4904tM0.k(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
    }
}
