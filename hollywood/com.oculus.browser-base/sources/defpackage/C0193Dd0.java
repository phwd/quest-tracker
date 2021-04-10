package defpackage;

import android.widget.SeekBar;
import java.util.Objects;
import org.chromium.third_party.android.media.MediaController;

/* renamed from: Dd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0193Dd0 implements SeekBar.OnSeekBarChangeListener {
    public final /* synthetic */ MediaController F;

    public C0193Dd0(MediaController mediaController) {
        this.F = mediaController;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        MediaController mediaController = this.F;
        int i2 = MediaController.F;
        Objects.requireNonNull(mediaController);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        this.F.L = true;
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        MediaController mediaController = this.F;
        mediaController.L = false;
        Objects.requireNonNull(mediaController);
    }
}
