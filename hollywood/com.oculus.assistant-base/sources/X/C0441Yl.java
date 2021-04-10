package X;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;

/* renamed from: X.Yl  reason: case insensitive filesystem */
public final class C0441Yl {
    public static C0441Yl A05;
    public AudioFocusRequest A00;
    public int A01;
    public boolean A02;
    public final AudioManager.OnAudioFocusChangeListener A03 = new C0440Yk();
    public final AudioManager A04 = ((AudioManager) BX.A00().getSystemService("audio"));

    public static C0441Yl A00() {
        C0441Yl yl = A05;
        if (yl != null) {
            return yl;
        }
        C0441Yl yl2 = new C0441Yl();
        A05 = yl2;
        return yl2;
    }

    public final void A02() {
        int requestAudioFocus;
        AudioFocusRequest audioFocusRequest;
        if (Build.VERSION.SDK_INT < 26 || (audioFocusRequest = this.A00) == null) {
            requestAudioFocus = this.A04.requestAudioFocus(this.A03, 5, 3);
        } else {
            requestAudioFocus = this.A04.requestAudioFocus(audioFocusRequest);
        }
        if (requestAudioFocus == 1) {
            C0139Dd.A0B("AudioDuckingManager", "Audio focus success");
        } else {
            C0139Dd.A0A("AudioDuckingManager", "Audio focus request failed");
        }
    }

    public final void A04() {
        int abandonAudioFocus;
        AudioFocusRequest audioFocusRequest;
        if (Build.VERSION.SDK_INT < 26 || (audioFocusRequest = this.A00) == null) {
            abandonAudioFocus = this.A04.abandonAudioFocus(this.A03);
        } else {
            abandonAudioFocus = this.A04.abandonAudioFocusRequest(audioFocusRequest);
        }
        if (abandonAudioFocus == 1) {
            C0139Dd.A0B("AudioDuckingManager", "Abandon audio focus success");
        } else {
            C0139Dd.A0A("AudioDuckingManager", "Abandon audio focus failed");
        }
    }

    public C0441Yl() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.A00 = new AudioFocusRequest.Builder(3).setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(5).setContentType(1).setUsage(16).build()).setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(this.A03).build();
        }
    }

    public final void A01() {
        if (W0.A00().A00.getBoolean("audio_ducking_enabled", false) && !this.A02) {
            this.A02 = true;
            this.A01 = ((AudioManager) BX.A00().getSystemService("audio")).getStreamVolume(3);
            int min = Math.min(((AudioManager) BX.A00().getSystemService("audio")).getStreamVolume(3) - 3, 3);
            ((AudioManager) BX.A00().getSystemService("audio")).setStreamVolume(3, Math.min(this.A01, min), 0);
            C0139Dd.A09("AudioDuckingManager", AnonymousClass08.A00("Ducking audio setting volume to ", min));
        }
    }

    public final void A03() {
        if (W0.A00().A00.getBoolean("audio_ducking_enabled", false) && this.A02) {
            C0139Dd.A09("AudioDuckingManager", AnonymousClass08.A00("Unducking audio setting volume to ", this.A01));
            ((AudioManager) BX.A00().getSystemService("audio")).setStreamVolume(3, this.A01, 0);
            this.A02 = false;
        }
    }
}
