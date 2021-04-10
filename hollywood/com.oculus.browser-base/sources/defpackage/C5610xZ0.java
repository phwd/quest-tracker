package defpackage;

import java.io.Serializable;
import java.util.Comparator;

/* renamed from: xZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5610xZ0 implements Comparator, Serializable {
    public C5610xZ0(C3907nZ0 nz0) {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return (int) (((IZ0) obj2).A - ((IZ0) obj).A);
    }
}
