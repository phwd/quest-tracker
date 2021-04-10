package defpackage;

import android.widget.CompoundButton;

/* renamed from: v60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5199v60 implements CompoundButton.OnCheckedChangeListener {
    public final /* synthetic */ View$OnClickListenerC5369w60 F;

    public C5199v60(View$OnClickListenerC5369w60 w60, A60 a60) {
        this.F = w60;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            View$OnClickListenerC5369w60 w60 = this.F;
            w60.e0.add(w60.d0);
            return;
        }
        View$OnClickListenerC5369w60 w602 = this.F;
        w602.e0.remove(w602.d0);
    }
}
