package defpackage;

import java.util.Comparator;

/* renamed from: ug0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5120ug0 implements Comparator {
    public static final C5120ug0 F = new C5120ug0();

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return ((C2392eh0) obj).d.compareToIgnoreCase(((C2392eh0) obj2).d);
    }
}
