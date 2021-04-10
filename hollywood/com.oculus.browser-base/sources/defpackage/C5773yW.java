package defpackage;

import android.view.View;
import androidx.gridlayout.widget.GridLayout;

/* renamed from: yW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5773yW extends GW {
    public int d;

    public C5773yW(C5943zW zWVar) {
    }

    @Override // defpackage.GW
    public int a(GridLayout gridLayout, View view, BW bw, int i, boolean z) {
        return Math.max(0, this.f8094a - bw.a(view, i, gridLayout.getLayoutMode()));
    }

    @Override // defpackage.GW
    public void b(int i, int i2) {
        this.f8094a = Math.max(this.f8094a, i);
        this.b = Math.max(this.b, i2);
        this.d = Math.max(this.d, i + i2);
    }

    @Override // defpackage.GW
    public void c() {
        super.c();
        this.d = Integer.MIN_VALUE;
    }

    @Override // defpackage.GW
    public int d(boolean z) {
        return Math.max(super.d(z), this.d);
    }
}
