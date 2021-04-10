package defpackage;

import J.N;
import org.chromium.content_public.browser.WebContents;

/* renamed from: H40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H40 {

    /* renamed from: a  reason: collision with root package name */
    public long f8134a;
    public boolean b;

    public H40(boolean z, WebContents webContents) {
        this.f8134a = N.MgtvkzAJ(this, z, webContents);
    }

    public void a(int i) {
        if (!this.b) {
            this.b = true;
            N.MMB_UdCu(this.f8134a, this, i);
        }
    }

    public void b(int i, int i2, boolean z) {
        N.MPFG5SwC(this.f8134a, this, i, i2, z);
    }
}
