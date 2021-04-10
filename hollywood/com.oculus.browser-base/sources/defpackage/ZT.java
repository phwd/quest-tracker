package defpackage;

import android.content.Context;

/* renamed from: ZT  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZT implements AbstractC0865Oe {
    @Override // defpackage.AbstractC0865Oe
    public boolean a(Context context, C2046cf1 cf1) {
        return false;
    }

    @Override // defpackage.AbstractC0865Oe
    public boolean b(Context context, C2046cf1 cf1, AbstractC0804Ne ne) {
        C3383kU a2 = C3383kU.a(cf1.b, new C2358eU(null));
        if (a2 == null) {
            AbstractC1220Ua0.a("GCMBackgroundTask", "The received bundle containing message data could not be validated.", new Object[0]);
            return false;
        }
        C0287Er.a(a2);
        return false;
    }

    @Override // defpackage.AbstractC0865Oe
    public void c(Context context) {
    }
}
