package defpackage;

import J.N;
import android.content.Context;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: zp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5997zp0 extends QX0 {
    public C5997zp0(BrowserContextHandle browserContextHandle) {
        super(browserContextHandle, 14, "");
    }

    @Override // defpackage.QX0
    public boolean g(Context context) {
        if (!N.ManEQDnV("AppNotificationStatusMessaging")) {
            return super.g(context);
        }
        return new C0650Kp0(context).a();
    }
}
