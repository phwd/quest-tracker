package defpackage;

import com.oculus.browser.NewTabMessageHandler;

/* renamed from: Tn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1194Tn0 implements Runnable {
    public final /* synthetic */ NewTabMessageHandler F;

    public RunnableC1194Tn0(NewTabMessageHandler newTabMessageHandler) {
        this.F = newTabMessageHandler;
    }

    public void run() {
        String str;
        NewTabMessageHandler newTabMessageHandler = this.F;
        long j = newTabMessageHandler.F;
        if (j != 0 && (str = newTabMessageHandler.G) != null) {
            newTabMessageHandler.nativeSetAccessToken(j, str);
        }
    }
}
