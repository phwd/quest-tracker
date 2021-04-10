package defpackage;

import J.N;
import java.io.Serializable;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: vp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5316vp implements Serializable {
    public final int F;
    public final String G;
    public final String H;
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final String f11500J;
    public final boolean K;

    public C5316vp(int i, String str, String str2, String str3, String str4, boolean z) {
        this.F = i;
        this.G = str;
        this.H = str2;
        this.I = str3;
        this.f11500J = str4;
        this.K = z;
    }

    public void a(BrowserContextHandle browserContextHandle) {
        if (!this.K) {
            N.Mw9TjMNp(browserContextHandle, this.F, this.G, this.H, this.f11500J);
        }
    }
}
