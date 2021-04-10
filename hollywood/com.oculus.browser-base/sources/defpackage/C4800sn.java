package defpackage;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;

/* renamed from: sn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4800sn extends Property {

    /* renamed from: a  reason: collision with root package name */
    public Rect f11300a = new Rect();

    public C4800sn(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        ((Drawable) obj).copyBounds(this.f11300a);
        Rect rect = this.f11300a;
        return new PointF((float) rect.left, (float) rect.top);
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        Drawable drawable = (Drawable) obj;
        PointF pointF = (PointF) obj2;
        drawable.copyBounds(this.f11300a);
        this.f11300a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
        drawable.setBounds(this.f11300a);
    }
}
