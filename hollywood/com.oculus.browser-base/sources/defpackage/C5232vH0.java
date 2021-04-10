package defpackage;

import android.os.Handler;
import java.util.LinkedList;
import java.util.List;
import org.chromium.base.Callback;

/* renamed from: vH0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5232vH0 {

    /* renamed from: a  reason: collision with root package name */
    public int f11471a = 0;
    public Object b;
    public final List c = new LinkedList();
    public Exception d;
    public final List e = new LinkedList();
    public final Thread f = Thread.currentThread();
    public final Handler g = new Handler();
    public boolean h;

    public static C5232vH0 c(Object obj) {
        C5232vH0 vh0 = new C5232vH0();
        vh0.b(obj);
        return vh0;
    }

    public final void a(Callback callback) {
        int i = this.f11471a;
        if (i == 2) {
            this.g.post(callback.a(this.d));
        } else if (i == 0) {
            this.e.add(callback);
        }
    }

    public void b(Object obj) {
        this.f11471a = 1;
        this.b = obj;
        for (Callback callback : this.c) {
            this.g.post(callback.a(obj));
        }
        this.c.clear();
    }

    public boolean d() {
        return this.f11471a == 1;
    }

    public void e(Exception exc) {
        this.f11471a = 2;
        this.d = exc;
        for (Callback callback : this.e) {
            this.g.post(callback.a(exc));
        }
        this.e.clear();
    }

    public C5232vH0 f(WT wt) {
        C5232vH0 vh0 = new C5232vH0();
        h(new C4039oH0(vh0, wt));
        a(new C4210pH0(vh0));
        return vh0;
    }

    public void g(Callback callback) {
        if (this.h) {
            h(callback);
            return;
        }
        C3868nH0 nh0 = new C3868nH0();
        h(callback);
        a(nh0);
        this.h = true;
    }

    public final void h(Callback callback) {
        int i = this.f11471a;
        if (i == 1) {
            this.g.post(callback.a(this.b));
        } else if (i == 0) {
            this.c.add(callback);
        }
    }
}
