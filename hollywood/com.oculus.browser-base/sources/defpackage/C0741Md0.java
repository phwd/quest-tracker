package defpackage;

import android.content.Context;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.os.RemoteException;
import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* renamed from: Md0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0741Md0 implements AbstractC0620Kd0 {

    /* renamed from: a  reason: collision with root package name */
    public final MediaController f8489a;
    public final Object b = new Object();
    public final List c = new ArrayList();
    public HashMap d = new HashMap();
    public final MediaSessionCompat$Token e;

    public C0741Md0(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.e = mediaSessionCompat$Token;
        MediaController mediaController = new MediaController(context, (MediaSession.Token) mediaSessionCompat$Token.G);
        this.f8489a = mediaController;
        if (mediaSessionCompat$Token.b() == null) {
            mediaController.sendCommand("android.support.v4.media.session.command.GET_EXTRA_BINDER", null, new MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver(this));
        }
    }

    @Override // defpackage.AbstractC0620Kd0
    public AbstractC0863Od0 a() {
        return new C0924Pd0(this.f8489a.getTransportControls());
    }

    public void b() {
        if (this.e.b() != null) {
            for (AbstractC0559Jd0 jd0 : this.c) {
                BinderC0681Ld0 ld0 = new BinderC0681Ld0(jd0);
                this.d.put(jd0, ld0);
                jd0.c = ld0;
                try {
                    this.e.b().W(ld0);
                    jd0.d(13, null, null);
                } catch (RemoteException e2) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e2);
                }
            }
            this.c.clear();
        }
    }

    public final void c(AbstractC0559Jd0 jd0) {
        this.f8489a.unregisterCallback(jd0.f8300a);
        synchronized (this.b) {
            if (this.e.b() != null) {
                try {
                    BinderC0681Ld0 ld0 = (BinderC0681Ld0) this.d.remove(jd0);
                    if (ld0 != null) {
                        jd0.c = null;
                        this.e.b().I(ld0);
                    }
                } catch (RemoteException e2) {
                    Log.e("MediaControllerCompat", "Dead object in unregisterCallback.", e2);
                }
            } else {
                this.c.remove(jd0);
            }
        }
    }
}
