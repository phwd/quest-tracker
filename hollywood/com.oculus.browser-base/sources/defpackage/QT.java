package defpackage;

import android.view.View;

/* renamed from: QT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QT implements View.OnLayoutChangeListener {
    public final /* synthetic */ View F;
    public final /* synthetic */ RT G;

    public QT(RT rt, View view) {
        this.G = rt;
        this.F = view;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.G.sendEmptyMessageDelayed(2, 20);
        this.F.removeOnLayoutChangeListener(this);
    }
}
