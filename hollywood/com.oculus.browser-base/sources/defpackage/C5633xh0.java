package defpackage;

import android.content.Intent;
import android.media.Rating;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.text.TextUtils;
import android.util.Log;
import androidx.versionedparcelable.C1728ParcelImpl;
import java.util.Objects;

/* renamed from: xh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5633xh0 extends MediaSession.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC5803yh0 f11626a;

    public C5633xh0(AbstractC5803yh0 yh0) {
        this.f11626a = yh0;
    }

    public final C0083Bh0 a() {
        C0083Bh0 bh0;
        AbstractC5803yh0 yh0;
        synchronized (this.f11626a.f11694a) {
            bh0 = (C0083Bh0) this.f11626a.d.get();
        }
        AbstractC5803yh0 yh02 = this.f11626a;
        synchronized (bh0.c) {
            yh0 = bh0.i;
        }
        if (yh02 == yh0) {
            return bh0;
        }
        return null;
    }

    public final void b(AbstractC5973zh0 zh0) {
        if (Build.VERSION.SDK_INT < 28) {
            String o = ((C0083Bh0) zh0).o();
            if (TextUtils.isEmpty(o)) {
                o = "android.media.session.MediaController";
            }
            zh0.d(new C0997Qh0(o, -1, -1));
        }
    }

    public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
        IBinder iBinder;
        Ns1 ns1;
        C0083Bh0 a2 = a();
        if (a2 != null) {
            C0571Jh0.a(bundle);
            b(a2);
            try {
                if (str.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                    Bundle bundle2 = new Bundle();
                    MediaSessionCompat$Token mediaSessionCompat$Token = a2.b;
                    TY b = mediaSessionCompat$Token.b();
                    if (b == null) {
                        iBinder = null;
                    } else {
                        iBinder = b.asBinder();
                    }
                    bundle2.putBinder("android.support.v4.media.session.EXTRA_BINDER", iBinder);
                    synchronized (mediaSessionCompat$Token.F) {
                        ns1 = mediaSessionCompat$Token.I;
                    }
                    if (ns1 != null) {
                        Bundle bundle3 = new Bundle();
                        bundle3.putParcelable(AbstractC1585a.f9392a, new C1728ParcelImpl(ns1));
                        bundle2.putParcelable("android.support.v4.media.session.SESSION_TOKEN2", bundle3);
                    }
                    resultReceiver.send(0, bundle2);
                } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                    AbstractC5803yh0 yh0 = this.f11626a;
                    MediaDescriptionCompat mediaDescriptionCompat = (MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
                    Objects.requireNonNull(yh0);
                } else if (str.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                    AbstractC5803yh0 yh02 = this.f11626a;
                    MediaDescriptionCompat mediaDescriptionCompat2 = (MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
                    bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX");
                    Objects.requireNonNull(yh02);
                } else if (str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                    AbstractC5803yh0 yh03 = this.f11626a;
                    MediaDescriptionCompat mediaDescriptionCompat3 = (MediaDescriptionCompat) bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION");
                    Objects.requireNonNull(yh03);
                } else if (!str.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                    Objects.requireNonNull(this.f11626a);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
            }
            a2.d(null);
        }
    }

    public void onCustomAction(String str, Bundle bundle) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            C0571Jh0.a(bundle);
            b(a2);
            try {
                if (str.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                    Uri uri = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                    C0571Jh0.a(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.PREPARE")) {
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                    bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                    C0571Jh0.a(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                    bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                    C0571Jh0.a(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                    Uri uri2 = (Uri) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                    C0571Jh0.a(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                    bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED");
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                    bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE");
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                    bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE");
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.SET_RATING")) {
                    RatingCompat ratingCompat = (RatingCompat) bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
                    C0571Jh0.a(bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS"));
                    Objects.requireNonNull(this.f11626a);
                } else if (str.equals("android.support.v4.media.session.action.SET_PLAYBACK_SPEED")) {
                    bundle.getFloat("android.support.v4.media.session.action.ARGUMENT_PLAYBACK_SPEED", 1.0f);
                    Objects.requireNonNull(this.f11626a);
                } else {
                    Objects.requireNonNull(this.f11626a);
                }
            } catch (BadParcelableException unused) {
                Log.e("MediaSessionCompat", "Could not unparcel the data.");
            }
            a2.d(null);
        }
    }

    public void onFastForward() {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            this.f11626a.b();
            a2.d(null);
        }
    }

    public boolean onMediaButtonEvent(Intent intent) {
        C0083Bh0 a2 = a();
        if (a2 == null) {
            return false;
        }
        b(a2);
        boolean c = this.f11626a.c(intent);
        a2.d(null);
        if (c || super.onMediaButtonEvent(intent)) {
            return true;
        }
        return false;
    }

    public void onPause() {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            this.f11626a.d();
            a2.d(null);
        }
    }

    public void onPlay() {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            this.f11626a.e();
            a2.d(null);
        }
    }

    public void onPlayFromMediaId(String str, Bundle bundle) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            C0571Jh0.a(bundle);
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onPlayFromSearch(String str, Bundle bundle) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            C0571Jh0.a(bundle);
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onPlayFromUri(Uri uri, Bundle bundle) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            C0571Jh0.a(bundle);
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onPrepare() {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onPrepareFromMediaId(String str, Bundle bundle) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            C0571Jh0.a(bundle);
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onPrepareFromSearch(String str, Bundle bundle) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            C0571Jh0.a(bundle);
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onPrepareFromUri(Uri uri, Bundle bundle) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            C0571Jh0.a(bundle);
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onRewind() {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            this.f11626a.f();
            a2.d(null);
        }
    }

    public void onSeekTo(long j) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            this.f11626a.g(j);
            a2.d(null);
        }
    }

    public void onSetPlaybackSpeed(float f) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onSetRating(Rating rating) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            AbstractC5803yh0 yh0 = this.f11626a;
            RatingCompat.b(rating);
            Objects.requireNonNull(yh0);
            a2.d(null);
        }
    }

    public void onSkipToNext() {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            this.f11626a.h();
            a2.d(null);
        }
    }

    public void onSkipToPrevious() {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            this.f11626a.i();
            a2.d(null);
        }
    }

    public void onSkipToQueueItem(long j) {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            Objects.requireNonNull(this.f11626a);
            a2.d(null);
        }
    }

    public void onStop() {
        C0083Bh0 a2 = a();
        if (a2 != null) {
            b(a2);
            this.f11626a.j();
            a2.d(null);
        }
    }
}
