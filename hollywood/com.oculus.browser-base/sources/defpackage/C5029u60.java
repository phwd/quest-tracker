package defpackage;

import java.util.Comparator;
import java.util.LinkedHashSet;

/* renamed from: u60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5029u60 implements Comparator {
    public final /* synthetic */ LinkedHashSet F;
    public final /* synthetic */ A60 G;

    public C5029u60(A60 a60, LinkedHashSet linkedHashSet) {
        this.G = a60;
        this.F = linkedHashSet;
    }

    public final int a(B60 b60) {
        if (this.F.contains(b60.f7716a)) {
            return -2;
        }
        return this.G.H.contains(b60.f7716a) ? -1 : 0;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return a((B60) obj) - a((B60) obj2);
    }
}
