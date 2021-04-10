package defpackage;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import java.util.Collections;
import java.util.Objects;

/* renamed from: ls  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3620ls implements AbstractC3615lq0 {

    /* renamed from: a  reason: collision with root package name */
    public final C0223Dp0 f10379a;
    public final C0832Np0 b;

    public C3620ls(Context context, String str, C0340Fn fn, C0832Np0 np0) {
        if (Build.VERSION.SDK_INT >= 26 && str != null) {
            fn.b(Collections.emptyList(), Collections.singletonList(str), true);
        }
        C0223Dp0 dp0 = new C0223Dp0(context, str);
        this.f10379a = dp0;
        this.b = np0;
        if (np0 != null) {
            dp0.C.deleteIntent = AbstractC0528Ip0.a(2, 0, np0, null);
        }
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 A(int i) {
        this.f10379a.C.icon = i;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 B(CB0 cb0) {
        this.f10379a.g = AbstractC0528Ip0.a(0, 0, this.b, cb0);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 C(CharSequence charSequence) {
        this.f10379a.C.tickerText = C0223Dp0.c(charSequence);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 D(int i) {
        this.f10379a.v = i;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 E(long[] jArr) {
        this.f10379a.C.vibrate = jArr;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 F(CharSequence charSequence) {
        this.f10379a.d(charSequence);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 G(int i) {
        Notification notification = this.f10379a.C;
        notification.defaults = i;
        if ((i & 4) != 0) {
            notification.flags |= 1;
        }
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 H(CharSequence charSequence) {
        this.f10379a.e(charSequence);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 I(PendingIntent pendingIntent) {
        this.f10379a.g = pendingIntent;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 J(Notification.BigPictureStyle bigPictureStyle) {
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 K(PendingIntent pendingIntent) {
        this.f10379a.C.deleteIntent = pendingIntent;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 L(int i, int i2, boolean z) {
        C0223Dp0 dp0 = this.f10379a;
        dp0.n = i;
        dp0.o = i2;
        dp0.p = z;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 a(Notification.BigTextStyle bigTextStyle) {
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public C3444kq0 b() {
        return new C3444kq0(c(), this.b);
    }

    @Override // defpackage.AbstractC3615lq0
    public Notification c() {
        try {
            return this.f10379a.b();
        } catch (NullPointerException e) {
            AbstractC1220Ua0.a("NotifCompatBuilder", "Failed to build notification.", e);
            return null;
        }
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 d(int i) {
        this.f10379a.w = i;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 e(int i, CharSequence charSequence, CB0 cb0, int i2) {
        this.f10379a.a(i, charSequence, AbstractC0528Ip0.a(1, i2, this.b, cb0));
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 f(long j) {
        this.f10379a.C.when = j;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 g(CharSequence charSequence) {
        C0223Dp0 dp0 = this.f10379a;
        Objects.requireNonNull(dp0);
        dp0.m = C0223Dp0.c(charSequence);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 h(boolean z) {
        this.f10379a.f(8, z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 i(Notification.Action action, int i, int i2) {
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 j(boolean z) {
        this.f10379a.k = z;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 k(C0571Jh0 jh0, int[] iArr) {
        C0284Ep0 ep0 = new C0284Ep0();
        ep0.c = jh0.b();
        ep0.b = iArr;
        C0223Dp0 dp0 = this.f10379a;
        if (dp0.l != ep0) {
            dp0.l = ep0;
            if (ep0.f8041a != dp0) {
                ep0.f8041a = dp0;
                if (ep0 != ep0) {
                    dp0.l = ep0;
                    ep0.f(dp0);
                }
            }
        }
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 l(int i, CharSequence charSequence, PendingIntent pendingIntent) {
        this.f10379a.a(i, charSequence, pendingIntent);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 m(String str) {
        this.f10379a.t = str;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 n(Notification notification) {
        this.f10379a.x = notification;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 o(int i) {
        this.f10379a.j = i;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 p(Icon icon) {
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 q(boolean z) {
        this.f10379a.s = z;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 r(String str) {
        this.f10379a.q = str;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 s(Bundle bundle) {
        C0223Dp0 dp0 = this.f10379a;
        Objects.requireNonNull(dp0);
        Bundle bundle2 = dp0.u;
        if (bundle2 == null) {
            dp0.u = new Bundle(bundle);
        } else {
            bundle2.putAll(bundle);
        }
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 t(Bitmap bitmap) {
        this.f10379a.g(bitmap);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 u(boolean z) {
        this.f10379a.f(2, z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 v(boolean z) {
        this.f10379a.r = z;
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 w(CB0 cb0) {
        K(AbstractC0528Ip0.a(2, 0, this.b, cb0));
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 x(boolean z) {
        this.f10379a.f(16, z);
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public AbstractC3615lq0 y(Notification.Action action) {
        return this;
    }

    @Override // defpackage.AbstractC3615lq0
    public C3444kq0 z(String str) {
        C0162Cp0 cp0 = new C0162Cp0(this.f10379a);
        cp0.g(str);
        C0223Dp0 dp0 = cp0.f8041a;
        return new C3444kq0(dp0 != null ? dp0.b() : null, this.b);
    }
}
