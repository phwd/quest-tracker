package defpackage;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* renamed from: bh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1880bh0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f9556a;
    public final C2392eh0 b;
    public final AbstractC0385Gg0 c;
    public final Map d;
    public final WeakReference e;
    public boolean f = false;

    public C1880bh0(C1543Zg0 zg0, int i) {
        HashMap hashMap = new HashMap();
        this.d = hashMap;
        this.f9556a = i;
        this.b = zg0.r;
        this.c = zg0.s;
        hashMap.putAll(zg0.v);
        this.e = new WeakReference(zg0);
        zg0.k.postDelayed(new RunnableC1700ah0(this), 15000);
    }

    public void a() {
        C3246jh0.b();
        if (!this.f) {
            this.f = true;
            C1543Zg0 zg0 = (C1543Zg0) this.e.get();
            if (zg0 != null && zg0.z == this.b) {
                zg0.z = null;
            }
            AbstractC0385Gg0 gg0 = this.c;
            if (gg0 != null) {
                gg0.h(this.f9556a);
                this.c.d();
            }
            if (!this.d.isEmpty()) {
                for (AbstractC0385Gg0 gg02 : this.d.values()) {
                    gg02.h(this.f9556a);
                    gg02.d();
                }
                this.d.clear();
            }
        }
    }
}
