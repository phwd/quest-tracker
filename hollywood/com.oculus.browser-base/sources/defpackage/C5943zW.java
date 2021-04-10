package defpackage;

import android.view.View;

/* renamed from: zW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5943zW extends BW {
    @Override // defpackage.BW
    public int a(View view, int i, int i2) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        int baseline = view.getBaseline();
        if (baseline == -1) {
            return Integer.MIN_VALUE;
        }
        return baseline;
    }

    @Override // defpackage.BW
    public GW b() {
        return new C5773yW(this);
    }

    @Override // defpackage.BW
    public String c() {
        return "BASELINE";
    }

    @Override // defpackage.BW
    public int d(View view, int i) {
        return 0;
    }
}
