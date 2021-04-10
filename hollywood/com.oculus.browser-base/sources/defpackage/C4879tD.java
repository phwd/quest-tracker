package defpackage;

import java.util.Comparator;

/* renamed from: tD  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4879tD implements Comparator {
    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        boolean z;
        C5389wD wDVar = (C5389wD) obj;
        C5389wD wDVar2 = (C5389wD) obj2;
        int i = wDVar.d;
        int i2 = wDVar2.d;
        if (i != i2) {
            return i - i2;
        }
        if (i != 3 || (z = wDVar.e) == wDVar2.e) {
            return wDVar.f - wDVar2.f;
        }
        return z ? -1 : 1;
    }
}
