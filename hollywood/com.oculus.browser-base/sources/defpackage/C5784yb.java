package defpackage;

import J.N;
import java.nio.ByteBuffer;
import org.chromium.chrome.browser.webauth.AuthenticatorImpl;

/* renamed from: yb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5784yb implements AbstractC4764sb {

    /* renamed from: a  reason: collision with root package name */
    public final AuthenticatorImpl f11688a;

    public C5784yb(AuthenticatorImpl authenticatorImpl) {
        this.f11688a = authenticatorImpl;
    }

    @Override // defpackage.AbstractC1555Zl
    public void a(Object obj, Object obj2) {
        ByteBuffer byteBuffer;
        C5941zV zVVar = (C5941zV) obj2;
        long longValue = this.f11688a.I.longValue();
        int intValue = ((Integer) obj).intValue();
        if (zVVar == null) {
            byteBuffer = null;
        } else {
            byteBuffer = zVVar.b();
        }
        N.MD9Vi9_f(longValue, intValue, byteBuffer);
    }
}
