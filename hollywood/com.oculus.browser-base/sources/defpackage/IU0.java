package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.chrome.browser.sharing.SharingServiceProxy;

/* renamed from: IU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class IU0 implements Runnable {
    public final String F;
    public final String G;
    public final String H;

    public IU0(String str, String str2, String str3) {
        this.F = str;
        this.G = str2;
        this.H = str3;
    }

    public void run() {
        String str = this.F;
        String str2 = this.G;
        String str3 = this.H;
        SharingServiceProxy a2 = SharingServiceProxy.a();
        JU0 ju0 = new JU0(str3, str2, str);
        Objects.requireNonNull(a2);
        long j = SharingServiceProxy.b;
        if (j == 0) {
            ju0.onResult(5);
        } else {
            N.ML9GlI7W(j, str, str2, ju0);
        }
    }
}
