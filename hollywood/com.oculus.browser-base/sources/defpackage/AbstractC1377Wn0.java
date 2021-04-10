package defpackage;

import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import org.chromium.base.Callback;

/* renamed from: Wn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface AbstractC1377Wn0 {

    /* renamed from: a  reason: collision with root package name */
    public static final AbstractC1377Wn0 f9173a = new C1316Vn0();

    void a(float f);

    boolean b();

    void c(Drawable drawable);

    void d(float f);

    boolean dispatchTouchEvent(MotionEvent motionEvent);

    void e(Callback callback);

    void f(Rect rect, Point point);

    boolean g();

    boolean h();

    boolean i();

    void j(float f);
}
