package defpackage;

import android.view.MotionEvent;
import android.view.View;
import com.oculus.os.Version;

/* renamed from: Vl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1312Vl0 implements AbstractC1251Ul0 {

    /* renamed from: a  reason: collision with root package name */
    public final MotionEvent.PointerProperties[] f9103a = new MotionEvent.PointerProperties[16];
    public final MotionEvent.PointerCoords[] b = new MotionEvent.PointerCoords[16];
    public final View c;
    public long d;

    public C1312Vl0(View view) {
        this.c = view;
    }

    public void a(int i, int i2, long j) {
        switch (i) {
            case 0:
                this.d = j;
                MotionEvent obtain = MotionEvent.obtain(j, j, 0, 1, this.f9103a, this.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.c.dispatchTouchEvent(obtain);
                obtain.recycle();
                if (i2 > 1) {
                    MotionEvent obtain2 = MotionEvent.obtain(this.d, j, 261, i2, this.f9103a, this.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                    this.c.dispatchTouchEvent(obtain2);
                    obtain2.recycle();
                    return;
                }
                return;
            case 1:
                MotionEvent obtain3 = MotionEvent.obtain(this.d, j, 2, i2, this.f9103a, this.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.c.dispatchTouchEvent(obtain3);
                obtain3.recycle();
                return;
            case 2:
                MotionEvent obtain4 = MotionEvent.obtain(this.d, j, 3, 1, this.f9103a, this.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.c.dispatchTouchEvent(obtain4);
                obtain4.recycle();
                return;
            case 3:
                if (i2 > 1) {
                    MotionEvent obtain5 = MotionEvent.obtain(this.d, j, 262, i2, this.f9103a, this.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                    this.c.dispatchTouchEvent(obtain5);
                    obtain5.recycle();
                }
                MotionEvent obtain6 = MotionEvent.obtain(this.d, j, 1, 1, this.f9103a, this.b, 0, 0, 1.0f, 1.0f, 0, 0, 0, 0);
                this.c.dispatchTouchEvent(obtain6);
                obtain6.recycle();
                return;
            case 4:
                MotionEvent obtain7 = MotionEvent.obtain(this.d, j, 8, i2, this.f9103a, this.b, 0, 0, 1.0f, 1.0f, 0, 0, 2, 0);
                this.c.dispatchGenericMotionEvent(obtain7);
                obtain7.recycle();
                return;
            case 5:
            case 6:
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                int i3 = 9;
                if (6 == i) {
                    i3 = 10;
                }
                MotionEvent obtain8 = MotionEvent.obtain(this.d, j, 7 == i ? 7 : i3, i2, this.f9103a, this.b, 0, 0, 1.0f, 1.0f, 0, 0, 2, 0);
                this.c.dispatchGenericMotionEvent(obtain8);
                obtain8.recycle();
                return;
            default:
                return;
        }
    }

    public void b(int i, float f, float f2, int i2, int i3) {
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.x = f;
        pointerCoords.y = f2;
        pointerCoords.pressure = 1.0f;
        this.b[i] = pointerCoords;
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = i2;
        pointerProperties.toolType = i3;
        this.f9103a[i] = pointerProperties;
    }
}
