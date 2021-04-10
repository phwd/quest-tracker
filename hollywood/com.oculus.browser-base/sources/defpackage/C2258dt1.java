package defpackage;

import java.util.Comparator;

/* renamed from: dt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2258dt1 implements Comparator {
    public final /* synthetic */ int F;

    public C2258dt1(int i) {
        this.F = i;
    }

    public int a(C2429et1 et1) {
        int i = et1.f9889a;
        int i2 = i < 8000 ? i * 1 : ((i - 8000) * 4) + 8000;
        int abs = Math.abs(this.F - et1.b);
        return i2 + (abs < 5000 ? abs * 1 : ((abs - 5000) * 3) + 5000);
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return a((C2429et1) obj) - a((C2429et1) obj2);
    }
}
