package defpackage;

import java.util.HashSet;
import java.util.Set;

/* renamed from: ej1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2399ej1 {

    /* renamed from: a  reason: collision with root package name */
    public int f9875a;
    public final Set b = new HashSet();
    public final Runnable c;

    public C2399ej1(Runnable runnable) {
        this.c = runnable;
    }

    public int a() {
        int i = this.f9875a;
        this.f9875a = i + 1;
        this.b.add(Integer.valueOf(i));
        if (this.b.size() == 1) {
            this.c.run();
        }
        return i;
    }

    public boolean b() {
        return !this.b.isEmpty();
    }

    public void c(int i) {
        if (this.b.remove(Integer.valueOf(i)) && this.b.isEmpty()) {
            this.c.run();
        }
    }
}
