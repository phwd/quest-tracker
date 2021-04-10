package defpackage;

import android.widget.CompoundButton;

/* renamed from: DZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DZ implements CompoundButton.OnCheckedChangeListener {
    public final FZ F;

    public DZ(FZ fz) {
        this.F = fz;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        FZ fz = this.F;
        if (fz.K.e()) {
            fz.P = z;
        } else {
            fz.O = z;
        }
    }
}
