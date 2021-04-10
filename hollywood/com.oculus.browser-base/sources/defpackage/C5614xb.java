package defpackage;

import J.N;
import java.nio.ByteBuffer;
import org.chromium.chrome.browser.webauth.AuthenticatorImpl;

/* renamed from: xb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5614xb implements AbstractC5104ub {

    /* renamed from: a  reason: collision with root package name */
    public final AuthenticatorImpl f11617a;

    public C5614xb(AuthenticatorImpl authenticatorImpl) {
        this.f11617a = authenticatorImpl;
    }

    @Override // defpackage.AbstractC1555Zl
    public void a(Object obj, Object obj2) {
        ByteBuffer byteBuffer;
        C0431Hb0 hb0 = (C0431Hb0) obj2;
        long longValue = this.f11617a.I.longValue();
        int intValue = ((Integer) obj).intValue();
        if (hb0 == null) {
            byteBuffer = null;
        } else {
            byteBuffer = hb0.b();
        }
        N.MLDEEMb6(longValue, intValue, byteBuffer);
    }
}
