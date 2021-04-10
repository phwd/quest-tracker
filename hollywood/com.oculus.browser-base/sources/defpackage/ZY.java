package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import com.oculus.browser.R;

/* renamed from: ZY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZY {

    /* renamed from: a  reason: collision with root package name */
    public static final Runnable f9350a = new YY();
    public Resources b;
    public final String c;
    public String d;
    public String e;
    public boolean f;
    public boolean g = true;
    public boolean h = true;
    public int i;
    public int j;
    public View k;
    public Runnable l;
    public Runnable m;
    public Rect n;
    public long o = 0;
    public ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 p;

    public ZY(Resources resources, String str, int i2, int i3) {
        this.b = resources;
        this.c = str;
        this.i = i2;
        this.j = i3;
    }

    public XY a() {
        if (this.m == null) {
            this.m = f9350a;
        }
        if (this.l == null) {
            this.l = f9350a;
        }
        if (this.d == null) {
            this.d = this.b.getString(this.i);
        }
        if (this.e == null) {
            this.e = this.b.getString(this.j);
        }
        if (this.n == null) {
            this.n = new Rect(0, 0, 0, this.b.getDimensionPixelOffset(R.dimen.f20080_resource_name_obfuscated_RES_2131165627));
        }
        return new XY(this.c, this.d, this.e, this.g, this.f, this.h, this.k, this.m, this.l, this.n, this.o, this.p);
    }
}
