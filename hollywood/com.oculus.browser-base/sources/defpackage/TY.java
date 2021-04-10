package defpackage;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IInterface;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper;
import android.support.v4.media.session.ParcelableVolumeInfo;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;
import java.util.List;

/* renamed from: TY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public interface TY extends IInterface {
    void A(MediaDescriptionCompat mediaDescriptionCompat);

    PendingIntent B();

    int C();

    void D(int i);

    int E();

    void F(String str, Bundle bundle);

    boolean G();

    void I(QY qy);

    void K();

    void O();

    void Q(String str, Bundle bundle, MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper);

    List R();

    void U(int i);

    void V();

    void W(QY qy);

    CharSequence X();

    void Y();

    MediaMetadataCompat Z();

    void a0(String str, Bundle bundle);

    PlaybackStateCompat b();

    Bundle b0();

    void c0(String str, Bundle bundle);

    long d0();

    int f0();

    void g0(long j);

    Bundle getExtras();

    void h(String str, Bundle bundle);

    void h0(boolean z);

    void i(int i, int i2, String str);

    void i0(String str, Bundle bundle);

    void k(RatingCompat ratingCompat, Bundle bundle);

    ParcelableVolumeInfo k0();

    void l0();

    void m(MediaDescriptionCompat mediaDescriptionCompat, int i);

    void m0(Uri uri, Bundle bundle);

    void n0(long j);

    void next();

    String o();

    void o0(int i);

    void previous();

    boolean q();

    void r(boolean z);

    void s(RatingCompat ratingCompat);

    String s0();

    void stop();

    void t(int i, int i2, String str);

    void u(Uri uri, Bundle bundle);

    void v0(float f);

    void x(MediaDescriptionCompat mediaDescriptionCompat);

    boolean x0(KeyEvent keyEvent);

    boolean z();
}
