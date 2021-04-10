package defpackage;

import android.graphics.PointF;
import android.util.Property;
import java.util.Objects;

/* renamed from: tn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4970tn extends Property {
    public C4970tn(Class cls, String str) {
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
        an.f7695a = Math.round(pointF.x);
        int round = Math.round(pointF.y);
        an.b = round;
        int i = an.f + 1;
        an.f = i;
        if (i == an.g) {
            AbstractC4315pv1.b(an.e, an.f7695a, round, an.c, an.d);
            an.f = 0;
            an.g = 0;
        }
    }
}
