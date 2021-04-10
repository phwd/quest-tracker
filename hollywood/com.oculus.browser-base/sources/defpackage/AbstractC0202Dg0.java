package defpackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/* renamed from: Dg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0202Dg0 extends AbstractC0385Gg0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f7903a = new Object();
    public Executor b;
    public C0994Qg0 c;
    public C0869Of0 d;
    public Collection e;

    public String j() {
        return null;
    }

    public String k() {
        return null;
    }

    public final void l(C0869Of0 of0, Collection collection) {
        Objects.requireNonNull(of0, "groupRoute must not be null");
        Objects.requireNonNull(collection, "dynamicRoutes must not be null");
        synchronized (this.f7903a) {
            Executor executor = this.b;
            if (executor != null) {
                executor.execute(new RunnableC0080Bg0(this, this.c, of0, collection));
            } else {
                this.d = of0;
                this.e = new ArrayList(collection);
            }
        }
    }

    public abstract void m(String str);

    public abstract void n(String str);

    public abstract void o(List list);
}
