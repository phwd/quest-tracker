package defpackage;

import android.media.session.MediaController;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;

/* renamed from: Jd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0559Jd0 implements IBinder.DeathRecipient {

    /* renamed from: a  reason: collision with root package name */
    public final MediaController.Callback f8300a = new C0376Gd0(this);
    public HandlerC0437Hd0 b;
    public QY c;

    public abstract void a(MediaMetadataCompat mediaMetadataCompat);

    public void b(PlaybackStateCompat playbackStateCompat) {
    }

    public void binderDied() {
        d(8, null, null);
    }

    public abstract void c();

    public void d(int i, Object obj, Bundle bundle) {
        HandlerC0437Hd0 hd0 = this.b;
        if (hd0 != null) {
            Message obtainMessage = hd0.obtainMessage(i, obj);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }

    public void e(Handler handler) {
        if (handler == null) {
            HandlerC0437Hd0 hd0 = this.b;
            if (hd0 != null) {
                hd0.f8166a = false;
                hd0.removeCallbacksAndMessages(null);
                this.b = null;
                return;
            }
            return;
        }
        HandlerC0437Hd0 hd02 = new HandlerC0437Hd0(this, handler.getLooper());
        this.b = hd02;
        hd02.f8166a = true;
    }
}
