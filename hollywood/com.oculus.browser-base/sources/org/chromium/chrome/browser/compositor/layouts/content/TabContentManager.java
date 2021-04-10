package org.chromium.chrome.browser.compositor.layouts.content;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.DeviceFormFactor;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabContentManager {

    /* renamed from: a  reason: collision with root package name */
    public static final C1426Xi f10636a = new C1426Xi("TabGridLayoutAndroid", "allow_to_refetch", false);
    public final Set b = new HashSet();
    public float c;
    public int d;
    public final AbstractC0849Ny e;
    public int[] f;
    public long g;
    public final ArrayList h = new ArrayList();
    public boolean i;
    public final AbstractC2982i61 j;
    public final Context k;
    public boolean l;
    public List m;
    public int n;
    public int o;
    public int p;
    public float q;

    public TabContentManager(Context context, AbstractC0849Ny ny, boolean z, AbstractC2982i61 i61) {
        this.k = context;
        this.e = ny;
        this.j = i61;
        this.i = z;
        this.d = d(context, R.integer.f35750_resource_name_obfuscated_RES_2131492872, "thumbnails");
        float f2 = YF.b(context).e;
        float f3 = 1.0f;
        if (DeviceFormFactor.a(context)) {
            f3 = 1.0f / f2;
        } else if (f2 > 1.5f) {
            f3 = 1.5f / f2;
        }
        this.c = f3;
        this.f = new int[this.d];
        if (AbstractC4772sd1.i() || f10636a.c()) {
            float c2 = (float) AbstractC4772sd1.c.c();
            this.q = c2;
            this.q = AbstractC4089od0.b(c2, 0.5f, 2.0f);
        }
    }

    public static void a(int i2) {
        AbstractC3364kK0.g("GridTabSwitcher.ThumbnailFetchingResult", i2, 4);
    }

    public static int d(Context context, int i2, String str) {
        int i3;
        if (AbstractC4772sd1.b()) {
            i3 = i2 == R.integer.f35750_resource_name_obfuscated_RES_2131492872 ? 2 : -1;
            if (i2 == R.integer.f35730_resource_name_obfuscated_RES_2131492870) {
                i3 = 8;
            }
        } else {
            i3 = context.getResources().getInteger(i2);
        }
        String f2 = AbstractC1575Zv.e().f(str);
        return f2 != null ? Integer.parseInt(f2) : i3;
    }

    public void b(Tab tab) {
        if (this.g != 0 && this.i) {
            c(tab, true, null);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00bb A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(org.chromium.chrome.browser.tab.Tab r18, boolean r19, org.chromium.base.Callback r20) {
        /*
        // Method dump skipped, instructions count: 267
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.layouts.content.TabContentManager.c(org.chromium.chrome.browser.tab.Tab, boolean, org.chromium.base.Callback):void");
    }

    public final void e(int i2, Callback callback) {
        this.n++;
        this.o++;
        TraceEvent.l0("GetTabThumbnailFromDisk", (long) i2);
        C2811h61 h61 = new C2811h61(this, i2, callback);
        Executor executor = AbstractC2032cb.f9616a;
        h61.f();
        ((ExecutorC1463Ya) executor).execute(h61.e);
    }

    public void f(int i2, Callback callback, boolean z, boolean z2) {
        if (this.i) {
            if (!z) {
                e(i2, callback);
            } else if (this.g != 0) {
                e(i2, new C2298e61(this, callback, i2, z2));
            }
        }
    }

    public void g(int i2, String str) {
        long j2 = this.g;
        if (j2 != 0) {
            N.MO5IR90z(j2, this, i2, str);
        }
    }

    public final long getNativePtr() {
        return this.g;
    }

    public final boolean h(Tab tab) {
        return tab.L();
    }

    public final void i() {
        Object obj = ThreadUtils.f10596a;
        List<C2040cd1> list = this.m;
        if (list != null) {
            for (C2040cd1 cd1 : list) {
                cd1.f9619a.l(this.p);
            }
            this.m = null;
        }
    }

    public void j(int i2) {
        long j2 = this.g;
        if (j2 != 0) {
            N.MZeSR4YP(j2, this, i2);
        }
    }

    public void k(List list, int i2) {
        if (this.g != 0) {
            int min = Math.min(this.d, list.size());
            if (min != this.f.length) {
                this.f = new int[min];
            }
            for (int i3 = 0; i3 < min; i3++) {
                this.f[i3] = ((Integer) list.get(i3)).intValue();
            }
            N.MZoWkzRr(this.g, this, this.f, i2);
        }
    }

    public void notifyListenersOfThumbnailChange(int i2) {
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            ((AbstractC2300e70) it.next()).M();
        }
    }
}
