package defpackage;

import android.graphics.Rect;
import android.graphics.RectF;

/* renamed from: G70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G70 {

    /* renamed from: a  reason: collision with root package name */
    public final RectF f8066a;
    public final RectF b;

    public G70(float f, AbstractC3197jM0 jm0) {
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        C3783mp0 b2 = jm0.b();
        if (b2 != null) {
            rect = b2.c;
            rect2 = b2.f;
        }
        Rect a2 = jm0.a();
        this.f8066a = new RectF(((float) rect.left) * f, ((float) rect.top) * f, ((float) rect.right) * f, ((float) rect.bottom) * f);
        this.b = new RectF(((float) a2.left) * f, ((float) a2.top) * f, ((float) a2.right) * f, ((float) a2.bottom) * f);
        new RectF(((float) rect2.left) * f, ((float) rect2.top) * f, ((float) rect2.right) * f, ((float) rect2.bottom) * f);
    }
}
