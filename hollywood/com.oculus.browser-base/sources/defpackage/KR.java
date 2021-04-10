package defpackage;

import android.os.Handler;

/* renamed from: KR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KR implements AbstractC5596xS0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC5414wM0 f8363a;
    public final /* synthetic */ Handler b;

    public KR(AbstractC5414wM0 wm0, Handler handler) {
        this.f8363a = wm0;
        this.b = handler;
    }

    @Override // defpackage.AbstractC5596xS0
    public void a(Object obj) {
        QR qr = (QR) obj;
        if (qr == null) {
            this.f8363a.a(1, this.b);
            return;
        }
        int i = qr.b;
        if (i == 0) {
            this.f8363a.b(qr.f8760a, this.b);
        } else {
            this.f8363a.a(i, this.b);
        }
    }
}
