package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Yi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1488Yi0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9291a;
    public final C4616ri0 b;
    public final boolean c;
    public final int d;
    public final int e;
    public View f;
    public int g = 8388611;
    public boolean h;
    public AbstractC1886bj0 i;
    public AbstractC1366Wi0 j;
    public PopupWindow.OnDismissListener k;
    public final PopupWindow.OnDismissListener l = new C1427Xi0(this);

    public C1488Yi0(Context context, C4616ri0 ri0, View view, boolean z, int i2, int i3) {
        this.f9291a = context;
        this.b = ri0;
        this.f = view;
        this.c = z;
        this.d = i2;
        this.e = i3;
    }

    public AbstractC1366Wi0 a() {
        AbstractC1366Wi0 wi0;
        if (this.j == null) {
            Display defaultDisplay = ((WindowManager) this.f9291a.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= this.f9291a.getResources().getDimensionPixelSize(R.dimen.f15870_resource_name_obfuscated_RES_2131165206)) {
                wi0 = new View$OnKeyListenerC0886Om(this.f9291a, this.f, this.d, this.e, this.c);
            } else {
                wi0 = new YZ0(this.f9291a, this.b, this.f, this.d, this.e, this.c);
            }
            wi0.l(this.b);
            wi0.r(this.l);
            wi0.n(this.f);
            wi0.e(this.i);
            wi0.o(this.h);
            wi0.p(this.g);
            this.j = wi0;
        }
        return this.j;
    }

    public boolean b() {
        AbstractC1366Wi0 wi0 = this.j;
        return wi0 != null && wi0.b();
    }

    public void c() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void d(AbstractC1886bj0 bj0) {
        this.i = bj0;
        AbstractC1366Wi0 wi0 = this.j;
        if (wi0 != null) {
            wi0.e(bj0);
        }
    }

    public final void e(int i2, int i3, boolean z, boolean z2) {
        AbstractC1366Wi0 a2 = a();
        a2.s(z2);
        if (z) {
            int i4 = this.g;
            View view = this.f;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if ((Gravity.getAbsoluteGravity(i4, view.getLayoutDirection()) & 7) == 5) {
                i2 -= this.f.getWidth();
            }
            a2.q(i2);
            a2.t(i3);
            int i5 = (int) ((this.f9291a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            a2.F = new Rect(i2 - i5, i3 - i5, i2 + i5, i3 + i5);
        }
        a2.d();
    }

    public boolean f() {
        if (b()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        e(0, 0, false, false);
        return true;
    }
}
