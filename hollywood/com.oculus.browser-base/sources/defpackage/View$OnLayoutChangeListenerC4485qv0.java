package defpackage;

import android.view.View;

/* renamed from: qv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC4485qv0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ C5505wv0 F;

    public View$OnLayoutChangeListenerC4485qv0(C5505wv0 wv0) {
        this.F = wv0;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.F.c.removeOnLayoutChangeListener(this);
        this.F.c.setVisibility(0);
        C5505wv0.a(this.F, true, null).start();
    }
}
