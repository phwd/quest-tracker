package defpackage;

import android.graphics.Paint;
import android.graphics.Path;

/* renamed from: iT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3041iT0 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10141a = new int[3];
    public static final float[] b = {0.0f, 0.5f, 1.0f};
    public static final int[] c = new int[4];
    public static final float[] d = {0.0f, 0.0f, 0.5f, 1.0f};
    public final Paint e = new Paint();
    public final Paint f;
    public final Paint g;
    public int h;
    public int i;
    public int j;
    public final Path k = new Path();
    public Paint l = new Paint();

    public C3041iT0() {
        a(-16777216);
        this.l.setColor(0);
        Paint paint = new Paint(4);
        this.f = paint;
        paint.setStyle(Paint.Style.FILL);
        this.g = new Paint(paint);
    }

    public void a(int i2) {
        this.h = AbstractC1331Vv.h(i2, 68);
        this.i = AbstractC1331Vv.h(i2, 20);
        this.j = AbstractC1331Vv.h(i2, 0);
        this.e.setColor(this.h);
    }
}
