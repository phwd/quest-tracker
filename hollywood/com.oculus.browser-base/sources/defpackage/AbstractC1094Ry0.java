package defpackage;

import org.chromium.components.payments.Address;

/* renamed from: Ry0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1094Ry0 {
    public static C1033Qy0 a(Address address) {
        if (address == null) {
            return null;
        }
        C1033Qy0 qy0 = new C1033Qy0();
        qy0.d = address.b;
        qy0.e = address.c;
        qy0.f = address.d;
        qy0.g = address.e;
        qy0.h = address.f;
        qy0.i = address.g;
        qy0.j = address.h;
        qy0.k = address.i;
        qy0.l = address.j;
        qy0.m = address.k;
        return qy0;
    }
}
