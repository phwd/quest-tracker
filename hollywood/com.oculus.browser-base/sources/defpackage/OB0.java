package defpackage;

import J.N;
import java.io.Serializable;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: OB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OB0 implements Serializable {
    public final boolean F;
    public final String G;
    public final String H;
    public final int I;

    public OB0(int i, String str, String str2, boolean z) {
        this.H = str;
        this.G = str2;
        this.I = i;
        this.F = z;
    }

    public Integer a(BrowserContextHandle browserContextHandle) {
        return Integer.valueOf(N.MUU7dsx0(browserContextHandle, this.I, this.H, b()));
    }

    public String b() {
        String str = this.G;
        return str != null ? str : this.H;
    }
}
