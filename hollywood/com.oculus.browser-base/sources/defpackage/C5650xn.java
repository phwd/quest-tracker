package defpackage;

import android.graphics.PointF;
import android.util.Property;
import android.view.View;

/* renamed from: xn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5650xn extends Property {
    public C5650xn(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public /* bridge */ /* synthetic */ Object get(Object obj) {
        View view = (View) obj;
        return null;
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        View view = (View) obj;
        PointF pointF = (PointF) obj2;
        int round = Math.round(pointF.x);
        int round2 = Math.round(pointF.y);
        AbstractC4315pv1.b(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
    }
}
