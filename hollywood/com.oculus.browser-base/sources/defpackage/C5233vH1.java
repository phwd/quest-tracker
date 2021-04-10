package defpackage;

import android.text.TextUtils;
import com.google.android.gms.common.api.Status;

/* renamed from: vH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5233vH1 extends IF1 {
    public final /* synthetic */ String q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5233vH1(YV yv, String str) {
        super(yv);
        this.q = str;
    }

    /* renamed from: n */
    public final void j(C3350kF1 kf1) {
        if (TextUtils.isEmpty(this.q)) {
            f(new Status(1, 2001, "IllegalArgument: sessionId cannot be null or empty", null));
            return;
        }
        try {
            kf1.D(this.q, this);
        } catch (IllegalStateException unused) {
            m();
        }
    }
}
