package defpackage;

import android.os.Parcel;
import com.google.android.gms.cast.zzah;

/* renamed from: eH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2331eH1 extends AbstractC1374Wm {
    public final /* synthetic */ String q;
    public final /* synthetic */ String r;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2331eH1(YV yv, String str, String str2) {
        super(yv);
        this.q = str;
        this.r = str2;
    }

    /* renamed from: n */
    public final void j(C3350kF1 kf1) {
        try {
            String str = this.q;
            String str2 = this.r;
            kf1.C(this);
            zzah zzah = new zzah();
            JF1 jf1 = (JF1) kf1.l();
            if (kf1.I()) {
                Parcel c = jf1.c();
                c.writeString(str);
                c.writeString(str2);
                AbstractC4376qF1.c(c, zzah);
                jf1.e0(14, c);
                return;
            }
            kf1.K(2016);
        } catch (IllegalStateException unused) {
            m();
        }
    }
}
