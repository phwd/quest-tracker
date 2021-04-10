package X;

import android.media.AudioManager;

/* renamed from: X.Yk  reason: case insensitive filesystem */
public final class C0440Yk implements AudioManager.OnAudioFocusChangeListener {
    public final void onAudioFocusChange(int i) {
        String str;
        String str2;
        if (i == -3) {
            str = "AudioDuckingManager";
            str2 = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK";
        } else if (i == -2) {
            str = "AudioDuckingManager";
            str2 = "AUDIOFOCUS_LOSS_TRANSIENT";
        } else if (i == -1) {
            str = "AudioDuckingManager";
            str2 = "AUDIOFOCUS_LOSS";
        } else if (i == 1) {
            str = "AudioDuckingManager";
            str2 = "AUDIOFOCUS_GAIN";
        } else {
            return;
        }
        C0139Dd.A0B(str, str2);
    }
}
