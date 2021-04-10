package defpackage;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;

/* renamed from: ZZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZZ0 extends AbstractC5827yp0 {
    public final Context v;

    public ZZ0(Context context) {
        super(context.getResources());
        this.v = context;
    }

    @Override // defpackage.AbstractC5827yp0
    public C3444kq0 d(C0832Np0 np0) {
        AbstractC3615lq0 b = AbstractC3786mq0.b(false, this.g, null, np0);
        b.H(this.d);
        b.F(this.e);
        b.g(this.f);
        b.C(this.h);
        if (this.i != null) {
            Notification.BigPictureStyle bigPicture = new Notification.BigPictureStyle().bigPicture(this.i);
            bigPicture.setSummaryText(this.e);
            b.J(bigPicture);
        } else {
            b.a(new Notification.BigTextStyle().bigText(this.e));
        }
        b.t(f());
        int i = this.j;
        Bitmap bitmap = this.k;
        if (bitmap != null) {
            b.p(Icon.createWithBitmap(bitmap));
        } else {
            b.A(i);
        }
        b.B(this.m);
        b.w(this.n);
        for (C5657xp0 xp0 : this.o) {
            AbstractC5827yp0.a(b, xp0);
        }
        C5657xp0 xp02 = this.p;
        if (xp02 != null) {
            AbstractC5827yp0.a(b, xp02);
        }
        b.o(0);
        b.G(this.q);
        long[] jArr = this.r;
        if (jArr != null) {
            b.E(jArr);
        }
        b.f(this.s);
        b.j(true);
        b.h(true ^ this.t);
        AbstractC5827yp0.j(b, this.f);
        b.n(e(this.v));
        return b.b();
    }
}
