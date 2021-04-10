package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: ch0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2051ch0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0446Hg0 f9625a;
    public final List b = new ArrayList();
    public final C0324Fg0 c;
    public C0507Ig0 d;

    public C2051ch0(AbstractC0446Hg0 hg0) {
        this.f9625a = hg0;
        this.c = hg0.b;
    }

    public C2392eh0 a(String str) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            if (((C2392eh0) this.b.get(i)).b.equals(str)) {
                return (C2392eh0) this.b.get(i);
            }
        }
        return null;
    }

    public List b() {
        C3246jh0.b();
        return Collections.unmodifiableList(this.b);
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("MediaRouter.RouteProviderInfo{ packageName=");
        i.append(this.c.f8030a.getPackageName());
        i.append(" }");
        return i.toString();
    }
}
