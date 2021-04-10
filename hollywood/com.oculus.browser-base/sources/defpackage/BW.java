package defpackage;

import android.view.View;

/* renamed from: BW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class BW {
    public abstract int a(View view, int i, int i2);

    public GW b() {
        return new GW();
    }

    public abstract String c();

    public abstract int d(View view, int i);

    public int e(View view, int i, int i2) {
        return i;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("Alignment:");
        i.append(c());
        return i.toString();
    }
}
