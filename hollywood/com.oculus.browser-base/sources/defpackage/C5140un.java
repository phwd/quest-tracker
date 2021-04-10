package defpackage;

import android.graphics.PointF;
import android.util.Property;
import java.util.Objects;

/* renamed from: un  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5140un extends Property {
    public C5140un(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public /* bridge */ /* synthetic */ Object get(Object obj) {
        C0035An an = (C0035An) obj;
        return null;
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        C0035An an = (C0035An) obj;
        PointF pointF = (PointF) obj2;
        Objects.requireNonNull(an);
        an.c = Math.round(pointF.x);
        int round = Math.round(pointF.y);
        an.d = round;
        int i = an.g + 1;
        an.g = i;
        if (an.f == i) {
            AbstractC4315pv1.b(an.e, an.f7695a, an.b, an.c, round);
            an.f = 0;
            an.g = 0;
        }
    }
}
