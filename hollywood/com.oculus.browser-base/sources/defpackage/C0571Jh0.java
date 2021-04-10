package defpackage;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.media.session.MediaSession;
import android.os.BadParcelableException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* renamed from: Jh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0571Jh0 {

    /* renamed from: a  reason: collision with root package name */
    public static int f8307a;
    public final AbstractC5973zh0 b;
    public final C0985Qd0 c;
    public final ArrayList d = new ArrayList();

    public C0571Jh0(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
        ComponentName componentName2;
        PendingIntent pendingIntent2;
        MediaSession mediaSession;
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        } else if (!TextUtils.isEmpty(str)) {
            int i = AbstractC4771sd0.f11288a;
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setPackage(context.getPackageName());
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
            if (queryBroadcastReceivers.size() == 1) {
                ActivityInfo activityInfo = queryBroadcastReceivers.get(0).activityInfo;
                componentName2 = new ComponentName(activityInfo.packageName, activityInfo.name);
            } else {
                if (queryBroadcastReceivers.size() > 1) {
                    Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
                }
                componentName2 = null;
            }
            if (componentName2 == null) {
                Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
            }
            if (componentName2 != null) {
                Intent intent2 = new Intent("android.intent.action.MEDIA_BUTTON");
                intent2.setComponent(componentName2);
                pendingIntent2 = PendingIntent.getBroadcast(context, 0, intent2, 0);
            } else {
                pendingIntent2 = null;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29) {
                mediaSession = new MediaSession(context, str, null);
            } else {
                mediaSession = new MediaSession(context, str);
            }
            if (i2 >= 29) {
                this.b = new C0205Dh0(mediaSession, null, null);
            } else if (i2 >= 28) {
                this.b = new C0144Ch0(mediaSession, null, null);
            } else {
                this.b = new C0083Bh0(mediaSession, null, null);
            }
            e(new C5293vh0(this), new Handler(Looper.myLooper() != null ? Looper.myLooper() : Looper.getMainLooper()));
            this.b.l(pendingIntent2);
            this.c = new C0985Qd0(context, this);
            if (f8307a == 0) {
                f8307a = (int) (TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics()) + 0.5f);
            }
        } else {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
    }

    public static void a(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(C0571Jh0.class.getClassLoader());
        }
    }

    public static PlaybackStateCompat c(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        if (playbackStateCompat == null) {
            return playbackStateCompat;
        }
        long j = -1;
        if (playbackStateCompat.G == -1) {
            return playbackStateCompat;
        }
        int i = playbackStateCompat.F;
        if (i != 3 && i != 4 && i != 5) {
            return playbackStateCompat;
        }
        long j2 = playbackStateCompat.M;
        if (j2 <= 0) {
            return playbackStateCompat;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j3 = ((long) (playbackStateCompat.I * ((float) (elapsedRealtime - j2)))) + playbackStateCompat.G;
        if (mediaMetadataCompat != null && mediaMetadataCompat.f9451J.containsKey("android.media.metadata.DURATION")) {
            j = mediaMetadataCompat.f9451J.getLong("android.media.metadata.DURATION", 0);
        }
        long j4 = (j < 0 || j3 <= j) ? j3 < 0 ? 0 : j3 : j;
        ArrayList arrayList = new ArrayList();
        long j5 = playbackStateCompat.H;
        long j6 = playbackStateCompat.f9453J;
        int i2 = playbackStateCompat.K;
        CharSequence charSequence = playbackStateCompat.L;
        List list = playbackStateCompat.N;
        if (list != null) {
            arrayList.addAll(list);
        }
        return new PlaybackStateCompat(playbackStateCompat.F, j4, j5, playbackStateCompat.I, j6, i2, charSequence, elapsedRealtime, arrayList, playbackStateCompat.O, playbackStateCompat.P);
    }

    public static Bundle f(Bundle bundle) {
        a(bundle);
        try {
            bundle.isEmpty();
            return bundle;
        } catch (BadParcelableException unused) {
            Log.e("MediaSessionCompat", "Could not unparcel the data.");
            return null;
        }
    }

    public MediaSessionCompat$Token b() {
        return this.b.f();
    }

    public void d(boolean z) {
        this.b.c(z);
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            Objects.requireNonNull(((C0933Pg0) it.next()).f8705a);
        }
    }

    public void e(AbstractC5803yh0 yh0, Handler handler) {
        if (yh0 == null) {
            this.b.e(null, null);
            return;
        }
        AbstractC5973zh0 zh0 = this.b;
        if (handler == null) {
            handler = new Handler();
        }
        zh0.e(yh0, handler);
    }
}
