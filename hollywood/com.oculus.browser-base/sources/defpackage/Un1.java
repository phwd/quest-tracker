package defpackage;

import android.app.Notification;
import android.graphics.Bitmap;
import android.os.Bundle;
import java.util.Objects;

/* renamed from: Un1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Un1 extends AbstractC2073co1 {

    /* renamed from: a  reason: collision with root package name */
    public final C2414eo1 f9049a;
    public final String b;
    public final AbstractC5827yp0 c;
    public final String d;
    public final int e;
    public final C3273jq0 f;

    public Un1(C2414eo1 eo1, String str, AbstractC5827yp0 yp0, String str2, int i, C3273jq0 jq0) {
        this.f9049a = eo1;
        this.b = str;
        this.c = yp0;
        this.d = str2;
        this.e = i;
        this.f = jq0;
    }

    @Override // defpackage.AbstractC2073co1
    public void a(C4649rt0 rt0, C3268jo1 jo1) {
        C2414eo1 eo1 = this.f9049a;
        String str = this.b;
        AbstractC5827yp0 yp0 = this.c;
        String str2 = this.d;
        int i = this.e;
        C3273jq0 jq0 = this.f;
        Objects.requireNonNull(eo1);
        Objects.requireNonNull(jo1);
        Bundle bundle = new Bundle();
        bundle.putString("android.support.customtabs.trusted.CHANNEL_NAME", str);
        if (!C3097io1.a(((C4077oZ) jo1.f10238a).c(bundle)).f10163a) {
            eo1.c.d(rt0, jo1.b.getPackageName(), 6, false);
            return;
        }
        if (yp0.g() && yp0.h()) {
            eo1.c(0);
        } else if (((C4077oZ) jo1.f10238a).y0() == -1) {
            eo1.c(1);
        } else {
            eo1.c(yp0.g() ? 2 : 3);
            Bitmap bitmap = (Bitmap) ((C4077oZ) jo1.f10238a).e0().getParcelable("android.support.customtabs.trusted.SMALL_ICON_BITMAP");
            if (!yp0.h()) {
                jo1.b.getPackageName();
                yp0.l(bitmap);
            }
            if (!yp0.g()) {
                yp0.k(bitmap);
            }
        }
        Notification notification = yp0.d(new C0832Np0(13, str2, i)).f10306a;
        Bundle bundle2 = new Bundle();
        bundle2.putString("android.support.customtabs.trusted.PLATFORM_TAG", str2);
        bundle2.putInt("android.support.customtabs.trusted.PLATFORM_ID", i);
        bundle2.putParcelable("android.support.customtabs.trusted.NOTIFICATION", notification);
        bundle2.putString("android.support.customtabs.trusted.CHANNEL_NAME", str);
        C3097io1.a(((C4077oZ) jo1.f10238a).z0(bundle2));
        jq0.b(13, notification);
    }
}
