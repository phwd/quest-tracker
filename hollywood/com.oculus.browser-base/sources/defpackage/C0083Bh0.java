package defpackage;

import android.app.PendingIntent;
import android.media.AudioAttributes;
import android.media.MediaMetadata;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.lang.reflect.Field;

/* renamed from: Bh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0083Bh0 implements AbstractC5973zh0 {

    /* renamed from: a  reason: collision with root package name */
    public final MediaSession f7748a;
    public final MediaSessionCompat$Token b;
    public final Object c = new Object();
    public Bundle d;
    public boolean e = false;
    public final RemoteCallbackList f = new RemoteCallbackList();
    public PlaybackStateCompat g;
    public MediaMetadataCompat h;
    public AbstractC5803yh0 i;
    public C0997Qh0 j;

    public C0083Bh0(MediaSession mediaSession, Ns1 ns1, Bundle bundle) {
        this.f7748a = mediaSession;
        this.b = new MediaSessionCompat$Token(mediaSession.getSessionToken(), new BinderC0022Ah0(this), ns1);
        this.d = bundle;
        mediaSession.setFlags(3);
    }

    @Override // defpackage.AbstractC5973zh0
    public void a() {
        this.e = true;
        this.f.kill();
        if (Build.VERSION.SDK_INT == 27) {
            try {
                Field declaredField = this.f7748a.getClass().getDeclaredField("mCallback");
                declaredField.setAccessible(true);
                Handler handler = (Handler) declaredField.get(this.f7748a);
                if (handler != null) {
                    handler.removeCallbacksAndMessages(null);
                }
            } catch (Exception e2) {
                Log.w("MediaSessionCompat", "Exception happened while accessing MediaSession.mCallback.", e2);
            }
        }
        this.f7748a.setCallback(null);
        this.f7748a.release();
    }

    @Override // defpackage.AbstractC5973zh0
    public PlaybackStateCompat b() {
        return this.g;
    }

    @Override // defpackage.AbstractC5973zh0
    public void c(boolean z) {
        this.f7748a.setActive(z);
    }

    @Override // defpackage.AbstractC5973zh0
    public void d(C0997Qh0 qh0) {
        synchronized (this.c) {
            this.j = qh0;
        }
    }

    @Override // defpackage.AbstractC5973zh0
    public void e(AbstractC5803yh0 yh0, Handler handler) {
        synchronized (this.c) {
            this.i = yh0;
            this.f7748a.setCallback(yh0 == null ? null : yh0.b, handler);
            if (yh0 != null) {
                yh0.k(this, handler);
            }
        }
    }

    @Override // defpackage.AbstractC5973zh0
    public MediaSessionCompat$Token f() {
        return this.b;
    }

    @Override // defpackage.AbstractC5973zh0
    public void g(PlaybackStateCompat playbackStateCompat) {
        PlaybackState playbackState;
        this.g = playbackStateCompat;
        for (int beginBroadcast = this.f.beginBroadcast() - 1; beginBroadcast >= 0; beginBroadcast--) {
            try {
                ((QY) this.f.getBroadcastItem(beginBroadcast)).u0(playbackStateCompat);
            } catch (RemoteException unused) {
            }
        }
        this.f.finishBroadcast();
        MediaSession mediaSession = this.f7748a;
        if (playbackStateCompat == null) {
            playbackState = null;
        } else {
            if (playbackStateCompat.Q == null) {
                PlaybackState.Builder builder = new PlaybackState.Builder();
                builder.setState(playbackStateCompat.F, playbackStateCompat.G, playbackStateCompat.I, playbackStateCompat.M);
                builder.setBufferedPosition(playbackStateCompat.H);
                builder.setActions(playbackStateCompat.f9453J);
                builder.setErrorMessage(playbackStateCompat.L);
                for (PlaybackStateCompat.CustomAction customAction : playbackStateCompat.N) {
                    PlaybackState.CustomAction customAction2 = customAction.f9454J;
                    if (customAction2 == null) {
                        PlaybackState.CustomAction.Builder builder2 = new PlaybackState.CustomAction.Builder(customAction.F, customAction.G, customAction.H);
                        builder2.setExtras(customAction.I);
                        customAction2 = builder2.build();
                    }
                    builder.addCustomAction(customAction2);
                }
                builder.setActiveQueueItemId(playbackStateCompat.O);
                builder.setExtras(playbackStateCompat.P);
                playbackStateCompat.Q = builder.build();
            }
            playbackState = playbackStateCompat.Q;
        }
        mediaSession.setPlaybackState(playbackState);
    }

    @Override // defpackage.AbstractC5973zh0
    public Object h() {
        return this.f7748a;
    }

    @Override // defpackage.AbstractC5973zh0
    public void i(int i2) {
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setLegacyStreamType(i2);
        this.f7748a.setPlaybackToLocal(builder.build());
    }

    @Override // defpackage.AbstractC5973zh0
    public AbstractC5803yh0 j() {
        AbstractC5803yh0 yh0;
        synchronized (this.c) {
            yh0 = this.i;
        }
        return yh0;
    }

    @Override // defpackage.AbstractC5973zh0
    public void k(MediaMetadataCompat mediaMetadataCompat) {
        MediaMetadata mediaMetadata;
        this.h = mediaMetadataCompat;
        MediaSession mediaSession = this.f7748a;
        if (mediaMetadataCompat == null) {
            mediaMetadata = null;
        } else {
            if (mediaMetadataCompat.K == null) {
                Parcel obtain = Parcel.obtain();
                obtain.writeBundle(mediaMetadataCompat.f9451J);
                obtain.setDataPosition(0);
                mediaMetadataCompat.K = (MediaMetadata) MediaMetadata.CREATOR.createFromParcel(obtain);
                obtain.recycle();
            }
            mediaMetadata = mediaMetadataCompat.K;
        }
        mediaSession.setMetadata(mediaMetadata);
    }

    @Override // defpackage.AbstractC5973zh0
    public void l(PendingIntent pendingIntent) {
        this.f7748a.setMediaButtonReceiver(pendingIntent);
    }

    @Override // defpackage.AbstractC5973zh0
    public C0997Qh0 m() {
        C0997Qh0 qh0;
        synchronized (this.c) {
            qh0 = this.j;
        }
        return qh0;
    }

    @Override // defpackage.AbstractC5973zh0
    public void n(C1238Ug0 ug0) {
        this.f7748a.setPlaybackToRemote((VolumeProvider) ug0.a());
    }

    public String o() {
        try {
            return (String) this.f7748a.getClass().getMethod("getCallingPackage", new Class[0]).invoke(this.f7748a, new Object[0]);
        } catch (Exception e2) {
            Log.e("MediaSessionCompat", "Cannot execute MediaSession.getCallingPackage()", e2);
            return null;
        }
    }
}
