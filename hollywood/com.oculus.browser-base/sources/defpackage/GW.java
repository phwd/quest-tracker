package defpackage;

import android.util.Printer;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

/* renamed from: GW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class GW {

    /* renamed from: a  reason: collision with root package name */
    public int f8094a;
    public int b;
    public int c;

    public GW() {
        c();
    }

    public int a(GridLayout gridLayout, View view, BW bw, int i, boolean z) {
        return this.f8094a - bw.a(view, i, gridLayout.getLayoutMode());
    }

    public void b(int i, int i2) {
        this.f8094a = Math.max(this.f8094a, i);
        this.b = Math.max(this.b, i2);
    }

    public void c() {
        this.f8094a = Integer.MIN_VALUE;
        this.b = Integer.MIN_VALUE;
        this.c = 2;
    }

    public int d(boolean z) {
        if (!z) {
            int i = this.c;
            Printer printer = GridLayout.F;
            if ((i & 2) != 0) {
                return 100000;
            }
        }
        return this.f8094a + this.b;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("Bounds{before=");
        i.append(this.f8094a);
        i.append(", after=");
        i.append(this.b);
        i.append('}');
        return i.toString();
    }
}
