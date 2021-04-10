package defpackage;

import android.view.View;
import java.lang.ref.WeakReference;

/* renamed from: Zu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Zu1 {

    /* renamed from: a  reason: collision with root package name */
    public WeakReference f9382a;
    public int b = -1;

    public Zu1(View view) {
        this.f9382a = new WeakReference(view);
    }

    public Zu1 a(float f) {
        View view = (View) this.f9382a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void b() {
        View view = (View) this.f9382a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public Zu1 c(long j) {
        View view = (View) this.f9382a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public Zu1 d(AbstractC2094cv1 cv1) {
        View view = (View) this.f9382a.get();
        if (view != null) {
            e(view, cv1);
        }
        return this;
    }

    public final void e(View view, AbstractC2094cv1 cv1) {
        if (cv1 != null) {
            view.animate().setListener(new Xu1(this, cv1, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public Zu1 f(Ry1 ry1) {
        View view = (View) this.f9382a.get();
        if (view != null) {
            Yu1 yu1 = null;
            if (ry1 != null) {
                yu1 = new Yu1(this, ry1, view);
            }
            view.animate().setUpdateListener(yu1);
        }
        return this;
    }

    public Zu1 g(float f) {
        View view = (View) this.f9382a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }
}
