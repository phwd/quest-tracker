package defpackage;

import android.view.View;
import android.view.animation.Interpolator;

/* renamed from: wf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5457wf0 implements View.OnClickListener {
    public final /* synthetic */ DialogC0504If0 F;

    public View$OnClickListenerC5457wf0(DialogC0504If0 if0) {
        this.F = if0;
    }

    public void onClick(View view) {
        Interpolator interpolator;
        DialogC0504If0 if0 = this.F;
        boolean z = !if0.I0;
        if0.I0 = z;
        if (z) {
            if0.i0.setVisibility(0);
        }
        DialogC0504If0 if02 = this.F;
        if (if02.I0) {
            interpolator = if02.P0;
        } else {
            interpolator = if02.Q0;
        }
        if02.O0 = interpolator;
        if02.w(true);
    }
}
