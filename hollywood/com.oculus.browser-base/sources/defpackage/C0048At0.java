package defpackage;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.animation.Interpolator;

/* renamed from: At0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0048At0 {

    /* renamed from: a  reason: collision with root package name */
    public BitmapDrawable f7702a;
    public float b = 1.0f;
    public Rect c;
    public Interpolator d;
    public long e;
    public Rect f;
    public int g;
    public float h = 1.0f;
    public float i = 1.0f;
    public long j;
    public boolean k;
    public boolean l;
    public C4266pf0 m;

    public C0048At0(BitmapDrawable bitmapDrawable, Rect rect) {
        this.f7702a = bitmapDrawable;
        this.f = rect;
        Rect rect2 = new Rect(rect);
        this.c = rect2;
        BitmapDrawable bitmapDrawable2 = this.f7702a;
        if (bitmapDrawable2 != null && rect2 != null) {
            bitmapDrawable2.setAlpha((int) (this.b * 255.0f));
            this.f7702a.setBounds(this.c);
        }
    }
}
