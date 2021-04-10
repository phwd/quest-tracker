package defpackage;

import java.util.Comparator;

/* renamed from: ag0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1697ag0 implements Comparator {
    public static final C1697ag0 F = new C1697ag0();

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return ((C2392eh0) obj).d.compareToIgnoreCase(((C2392eh0) obj2).d);
    }
}
