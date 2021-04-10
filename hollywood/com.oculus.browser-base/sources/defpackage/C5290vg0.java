package defpackage;

import android.widget.SeekBar;

/* renamed from: vg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5290vg0 implements SeekBar.OnSeekBarChangeListener {
    public final /* synthetic */ DialogC5460wg0 F;

    public C5290vg0(DialogC5460wg0 wg0) {
        this.F = wg0;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            C2392eh0 eh0 = (C2392eh0) seekBar.getTag();
            AbstractC3072ig0 ig0 = (AbstractC3072ig0) this.F.Y.get(eh0.c);
            if (ig0 != null) {
                ig0.y(i == 0);
            }
            eh0.k(i);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        DialogC5460wg0 wg0 = this.F;
        if (wg0.Z != null) {
            wg0.U.removeMessages(2);
        }
        this.F.Z = (C2392eh0) seekBar.getTag();
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.F.U.sendEmptyMessageDelayed(2, 500);
    }
}
