package defpackage;

import android.widget.SeekBar;

/* renamed from: Gf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0382Gf0 implements SeekBar.OnSeekBarChangeListener {
    public final Runnable F = new RunnableC0321Ff0(this);
    public final /* synthetic */ DialogC0504If0 G;

    public C0382Gf0(DialogC0504If0 if0) {
        this.G = if0;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            int i2 = DialogC0504If0.I;
            ((C2392eh0) seekBar.getTag()).k(i);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        DialogC0504If0 if0 = this.G;
        if (if0.q0 != null) {
            if0.o0.removeCallbacks(this.F);
        }
        this.G.q0 = (C2392eh0) seekBar.getTag();
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.G.o0.postDelayed(this.F, 500);
    }
}
