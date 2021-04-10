package defpackage;

import android.content.Context;
import android.media.MediaMetadata;
import android.os.Handler;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.util.Log;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: Qd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0985Qd0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0620Kd0 f8773a;
    public final MediaSessionCompat$Token b;
    public final ConcurrentHashMap c = new ConcurrentHashMap();

    public C0985Qd0(Context context, C0571Jh0 jh0) {
        MediaSessionCompat$Token b2 = jh0.b();
        this.b = b2;
        this.f8773a = new C0741Md0(context, b2);
    }

    public MediaMetadataCompat a() {
        MediaMetadata metadata = ((C0741Md0) this.f8773a).f8489a.getMetadata();
        if (metadata != null) {
            return MediaMetadataCompat.b(metadata);
        }
        return null;
    }

    public AbstractC0863Od0 b() {
        return this.f8773a.a();
    }

    public void c(AbstractC0559Jd0 jd0) {
        if (jd0 == null) {
            throw new IllegalArgumentException("callback must not be null");
        } else if (this.c.putIfAbsent(jd0, Boolean.TRUE) != null) {
            Log.w("MediaControllerCompat", "the callback has already been registered");
        } else {
            Handler handler = new Handler();
            jd0.e(handler);
            C0741Md0 md0 = (C0741Md0) this.f8773a;
            md0.f8489a.registerCallback(jd0.f8300a, handler);
            synchronized (md0.b) {
                if (md0.e.b() != null) {
                    BinderC0681Ld0 ld0 = new BinderC0681Ld0(jd0);
                    md0.d.put(jd0, ld0);
                    jd0.c = ld0;
                    try {
                        md0.e.b().W(ld0);
                        jd0.d(13, null, null);
                    } catch (RemoteException e) {
                        Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                    }
                } else {
                    jd0.c = null;
                    md0.c.add(jd0);
                }
            }
        }
    }

    public void d(AbstractC0559Jd0 jd0) {
        if (jd0 == null) {
            throw new IllegalArgumentException("callback must not be null");
        } else if (this.c.remove(jd0) == null) {
            Log.w("MediaControllerCompat", "the callback has never been registered");
        } else {
            try {
                ((C0741Md0) this.f8773a).c(jd0);
            } finally {
                jd0.e(null);
            }
        }
    }

    public C0985Qd0(Context context, MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.b = mediaSessionCompat$Token;
        this.f8773a = new C0741Md0(context, mediaSessionCompat$Token);
    }
}
