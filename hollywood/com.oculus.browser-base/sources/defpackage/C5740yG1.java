package defpackage;

import android.os.Parcel;
import com.google.android.gms.cast.LaunchOptions;

/* renamed from: yG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5740yG1 extends AbstractC1374Wm {
    public final /* synthetic */ String q;
    public final /* synthetic */ LaunchOptions r;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5740yG1(YV yv, String str, LaunchOptions launchOptions) {
        super(yv);
        this.q = str;
        this.r = launchOptions;
    }

    /* renamed from: n */
    public final void j(C3350kF1 kf1) {
        try {
            String str = this.q;
            LaunchOptions launchOptions = this.r;
            kf1.C(this);
            JF1 jf1 = (JF1) kf1.l();
            if (kf1.I()) {
                Parcel c = jf1.c();
                c.writeString(str);
                AbstractC4376qF1.c(c, launchOptions);
                jf1.e0(13, c);
                return;
            }
            kf1.K(2016);
        } catch (IllegalStateException unused) {
            m();
        }
    }
}
