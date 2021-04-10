package defpackage;

import android.content.ComponentName;
import android.content.Context;
import java.util.Objects;

/* renamed from: Hg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0446Hg0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8172a;
    public final C0324Fg0 b;
    public final HandlerC0263Eg0 c = new HandlerC0263Eg0(this);
    public C1421Xg0 d;
    public C1052Rf0 e;
    public boolean f;
    public C0507Ig0 g;
    public boolean h;

    public AbstractC0446Hg0(Context context, C0324Fg0 fg0) {
        if (context != null) {
            this.f8172a = context;
            if (fg0 == null) {
                this.b = new C0324Fg0(new ComponentName(context, getClass()));
            } else {
                this.b = fg0;
            }
        } else {
            throw new IllegalArgumentException("context must not be null");
        }
    }

    public AbstractC0202Dg0 c(String str) {
        if (str != null) {
            return null;
        }
        throw new IllegalArgumentException("initialMemberRouteId cannot be null.");
    }

    public abstract AbstractC0385Gg0 d(String str);

    public AbstractC0385Gg0 e(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("routeId cannot be null");
        } else if (str2 != null) {
            return d(str);
        } else {
            throw new IllegalArgumentException("routeGroupId cannot be null");
        }
    }

    public abstract void f(C1052Rf0 rf0);

    public final void g(C0507Ig0 ig0) {
        C3246jh0.b();
        if (this.g != ig0) {
            this.g = ig0;
            if (!this.h) {
                this.h = true;
                this.c.sendEmptyMessage(1);
            }
        }
    }

    public final void h(C1052Rf0 rf0) {
        C3246jh0.b();
        if (!Objects.equals(this.e, rf0)) {
            this.e = rf0;
            if (!this.f) {
                this.f = true;
                this.c.sendEmptyMessage(2);
            }
        }
    }
}
