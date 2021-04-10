package defpackage;

import java.io.IOException;
import java.security.SecureRandom;

/* renamed from: V20  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class V20 extends AbstractC0500Ie {
    public V20(X20 x20) {
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        SecureRandom secureRandom;
        IOException e;
        try {
            secureRandom = new SecureRandom();
            try {
                HR0.a(secureRandom);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            secureRandom = null;
            e = e3;
            AbstractC1220Ua0.a("MetadataHandler", "Cannot initialize SecureRandom", e);
            return secureRandom;
        }
        return secureRandom;
    }
}
