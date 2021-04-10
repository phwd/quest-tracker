package org.chromium.media;

import J.N;
import android.media.MediaPlayer;
import android.os.SystemClock;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaServerCrashListener implements MediaPlayer.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public MediaPlayer f10984a;
    public long b = -1;
    public long c;

    public MediaServerCrashListener(long j) {
        this.c = j;
    }

    public static MediaServerCrashListener create(long j) {
        return new MediaServerCrashListener(j);
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            N.Mm$QSrAo(this.c, this, true);
            releaseWatchdog();
        }
        return true;
    }

    public void releaseWatchdog() {
        MediaPlayer mediaPlayer = this.f10984a;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.f10984a = null;
        }
    }

    public boolean startListening() {
        if (this.f10984a != null) {
            return true;
        }
        try {
            this.f10984a = MediaPlayer.create(ContextUtils.getApplicationContext(), (int) R.raw.f42980_resource_name_obfuscated);
        } catch (IllegalStateException e) {
            AbstractC1220Ua0.a("crMediaCrashListener", "Exception while creating the watchdog player.", e);
        } catch (RuntimeException e2) {
            AbstractC1220Ua0.a("crMediaCrashListener", "Exception while creating the watchdog player.", e2);
        }
        MediaPlayer mediaPlayer = this.f10984a;
        if (mediaPlayer != null) {
            mediaPlayer.setOnErrorListener(this);
            this.b = -1;
            return true;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.b;
        if (j == -1 || elapsedRealtime - j > 5000) {
            AbstractC1220Ua0.a("crMediaCrashListener", "Unable to create watchdog player, treating it as server crash.", new Object[0]);
            N.Mm$QSrAo(this.c, this, false);
            this.b = elapsedRealtime;
        }
        return false;
    }
}
