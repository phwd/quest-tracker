package defpackage;

import android.content.Intent;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Handler;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.ref.WeakReference;

/* renamed from: yh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5803yh0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f11694a = new Object();
    public final MediaSession.Callback b = new C5633xh0(this);
    public boolean c;
    public WeakReference d = new WeakReference(null);
    public HandlerC5463wh0 e;

    public void a(AbstractC5973zh0 zh0, Handler handler) {
        long j;
        if (this.c) {
            boolean z = false;
            this.c = false;
            handler.removeMessages(1);
            PlaybackStateCompat b2 = zh0.b();
            if (b2 == null) {
                j = 0;
            } else {
                j = b2.f9453J;
            }
            boolean z2 = b2 != null && b2.F == 3;
            boolean z3 = (516 & j) != 0;
            if ((j & 514) != 0) {
                z = true;
            }
            if (z2 && z) {
                d();
            } else if (!z2 && z3) {
                e();
            }
        }
    }

    public void b() {
    }

    public boolean c(Intent intent) {
        AbstractC5973zh0 zh0;
        HandlerC5463wh0 wh0;
        KeyEvent keyEvent;
        long j;
        if (Build.VERSION.SDK_INT >= 27) {
            return false;
        }
        synchronized (this.f11694a) {
            zh0 = (AbstractC5973zh0) this.d.get();
            wh0 = this.e;
        }
        if (zh0 == null || wh0 == null || (keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT")) == null || keyEvent.getAction() != 0) {
            return false;
        }
        C0997Qh0 m = zh0.m();
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 79 || keyCode == 85) {
            if (keyEvent.getRepeatCount() != 0) {
                a(zh0, wh0);
            } else if (this.c) {
                wh0.removeMessages(1);
                this.c = false;
                PlaybackStateCompat b2 = zh0.b();
                if (b2 == null) {
                    j = 0;
                } else {
                    j = b2.f9453J;
                }
                if ((j & 32) != 0) {
                    h();
                }
            } else {
                this.c = true;
                wh0.sendMessageDelayed(wh0.obtainMessage(1, m), (long) ViewConfiguration.getDoubleTapTimeout());
            }
            return true;
        }
        a(zh0, wh0);
        return false;
    }

    public void d() {
    }

    public void e() {
    }

    public void f() {
    }

    public void g(long j) {
    }

    public void h() {
    }

    public void i() {
    }

    public void j() {
    }

    public void k(AbstractC5973zh0 zh0, Handler handler) {
        synchronized (this.f11694a) {
            this.d = new WeakReference(zh0);
            HandlerC5463wh0 wh0 = this.e;
            HandlerC5463wh0 wh02 = null;
            if (wh0 != null) {
                wh0.removeCallbacksAndMessages(null);
            }
            if (zh0 != null) {
                if (handler != null) {
                    wh02 = new HandlerC5463wh0(this, handler.getLooper());
                }
            }
            this.e = wh02;
        }
    }
}
