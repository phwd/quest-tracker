package defpackage;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper;
import android.support.v4.media.session.ParcelableVolumeInfo;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.KeyEvent;
import java.util.List;
import java.util.Objects;

/* renamed from: Ah0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BinderC0022Ah0 extends SY {
    public final /* synthetic */ C0083Bh0 b;

    public BinderC0022Ah0(C0083Bh0 bh0) {
        this.b = bh0;
    }

    @Override // defpackage.TY
    public void A(MediaDescriptionCompat mediaDescriptionCompat) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public PendingIntent B() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public int C() {
        Objects.requireNonNull(this.b);
        return 0;
    }

    @Override // defpackage.TY
    public void D(int i) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public int E() {
        Objects.requireNonNull(this.b);
        return 0;
    }

    @Override // defpackage.TY
    public void F(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public boolean G() {
        Objects.requireNonNull(this.b);
        return false;
    }

    @Override // defpackage.TY
    public void I(QY qy) {
        this.b.f.unregister(qy);
    }

    @Override // defpackage.TY
    public void K() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void O() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void Q(String str, Bundle bundle, MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public List R() {
        return null;
    }

    @Override // defpackage.TY
    public void U(int i) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void V() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void W(QY qy) {
        if (!this.b.e) {
            this.b.f.register(qy, new C0997Qh0("android.media.session.MediaController", Binder.getCallingPid(), Binder.getCallingUid()));
        }
    }

    @Override // defpackage.TY
    public CharSequence X() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void Y() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public MediaMetadataCompat Z() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void a0(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public PlaybackStateCompat b() {
        C0083Bh0 bh0 = this.b;
        return C0571Jh0.c(bh0.g, bh0.h);
    }

    @Override // defpackage.TY
    public Bundle b0() {
        if (this.b.d == null) {
            return null;
        }
        return new Bundle(this.b.d);
    }

    @Override // defpackage.TY
    public void c0(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public long d0() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public int f0() {
        Objects.requireNonNull(this.b);
        return 0;
    }

    @Override // defpackage.TY
    public void g0(long j) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public Bundle getExtras() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void h(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void h0(boolean z) {
    }

    @Override // defpackage.TY
    public void i(int i, int i2, String str) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void i0(String str, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void k(RatingCompat ratingCompat, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public ParcelableVolumeInfo k0() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void l0() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void m(MediaDescriptionCompat mediaDescriptionCompat, int i) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void m0(Uri uri, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void n0(long j) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void next() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public String o() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void o0(int i) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void previous() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public boolean q() {
        return false;
    }

    @Override // defpackage.TY
    public void r(boolean z) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void s(RatingCompat ratingCompat) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public String s0() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void stop() {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void t(int i, int i2, String str) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void u(Uri uri, Bundle bundle) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void v0(float f) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public void x(MediaDescriptionCompat mediaDescriptionCompat) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public boolean x0(KeyEvent keyEvent) {
        throw new AssertionError();
    }

    @Override // defpackage.TY
    public boolean z() {
        throw new AssertionError();
    }
}
