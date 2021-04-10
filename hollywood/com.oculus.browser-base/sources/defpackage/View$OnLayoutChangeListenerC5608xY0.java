package defpackage;

import android.animation.ObjectAnimator;
import android.view.View;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: xY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC5608xY0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ C5948zY0 F;

    public View$OnLayoutChangeListenerC5608xY0(C5948zY0 zy0) {
        this.F = zy0;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.F.b.removeOnLayoutChangeListener(this);
        C5948zY0 zy0 = this.F;
        zy0.b.setTranslationY((float) zy0.d());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.F.b, View.TRANSLATION_Y, 0.0f);
        ofFloat.setInterpolator(G30.f8058a);
        ofFloat.setDuration((long) this.F.g);
        WindowAndroid windowAndroid = this.F.f11749a;
        if (windowAndroid != null) {
            windowAndroid.I0(ofFloat);
        } else {
            ofFloat.start();
        }
    }
}
