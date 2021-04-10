package defpackage;

import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: ov1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4144ov1 extends Property {
    public C4144ov1(Class cls, String str) {
        super(cls, str);
    }

    @Override // android.util.Property
    public Object get(Object obj) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        return ((View) obj).getClipBounds();
    }

    @Override // android.util.Property
    public void set(Object obj, Object obj2) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        ((View) obj).setClipBounds((Rect) obj2);
    }
}
