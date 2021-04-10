package defpackage;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import java.util.Iterator;
import java.util.Set;
import org.chromium.content.browser.LauncherThread;

/* renamed from: xh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ComponentCallbacks2C5632xh implements ComponentCallbacks2 {
    public final Set F = new C5271va(0);
    public final int G;
    public final Iterable H;
    public final Runnable I;

    /* renamed from: J  reason: collision with root package name */
    public C5653xo f11625J;

    public ComponentCallbacks2C5632xh(int i, Iterable iterable, Context context) {
        AbstractC1220Ua0.d("BindingManager", "Moderate binding enabled: maxSize=%d", Integer.valueOf(i));
        this.G = i;
        this.H = iterable;
        this.I = new RunnableC5462wh(this);
        context.registerComponentCallbacks(this);
    }

    public static void a(ComponentCallbacks2C5632xh xhVar, float f) {
        int size = xhVar.F.size();
        int i = (int) ((1.0f - f) * ((float) size));
        AbstractC1220Ua0.d("BindingManager", "Reduce connections from %d to %d", Integer.valueOf(size), Integer.valueOf(i));
        xhVar.d(size - i);
        xhVar.c();
    }

    public static void b(ComponentCallbacks2C5632xh xhVar) {
        xhVar.d(xhVar.F.size());
    }

    public final void c() {
        C5653xo xoVar;
        C5653xo xoVar2;
        Iterator it = this.H.iterator();
        if (it.hasNext() && (xoVar = (C5653xo) it.next()) != (xoVar2 = this.f11625J)) {
            if (xoVar2 != null) {
                xoVar2.a();
                this.f11625J = null;
            }
            if (this.F.contains(xoVar)) {
                xoVar.l();
                this.f11625J = xoVar;
            }
        }
    }

    public final void d(int i) {
        int i2 = 0;
        for (C5653xo xoVar : this.H) {
            if (this.F.contains(xoVar)) {
                if (xoVar == this.f11625J) {
                    this.f11625J = null;
                } else {
                    xoVar.l();
                }
                this.F.remove(xoVar);
                i2++;
                if (i2 == i) {
                    return;
                }
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public void onLowMemory() {
        LauncherThread.c.post(new RunnableC5292vh(this));
    }

    public void onTrimMemory(int i) {
        LauncherThread.c.post(new RunnableC5122uh(this, i));
    }
}
