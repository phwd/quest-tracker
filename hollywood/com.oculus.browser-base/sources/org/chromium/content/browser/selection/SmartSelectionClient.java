package org.chromium.content.browser.selection;

import J.N;
import android.os.Build;
import android.text.TextUtils;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SmartSelectionClient extends AbstractC2185dS0 {

    /* renamed from: a  reason: collision with root package name */
    public long f10932a;
    public C2197dY0 b;
    public C4406qS0 c;
    public ZX0 d;

    public SmartSelectionClient(C4406qS0 qs0, WebContents webContents) {
        this.b = new C2197dY0(qs0, webContents);
        this.c = qs0;
        if (Build.VERSION.SDK_INT >= 28) {
            this.d = ZX0.b(webContents);
        }
        this.f10932a = N.MFA_dMJC(this, webContents);
    }

    @Override // defpackage.AbstractC2185dS0
    public void a() {
        long j = this.f10932a;
        if (j != 0) {
            N.MVHq2mA2(j, this);
        }
        C2197dY0 dy0 = this.b;
        C2026cY0 cy0 = dy0.c;
        if (cy0 != null) {
            cy0.b(false);
            dy0.c = null;
        }
    }

    @Override // defpackage.AbstractC2185dS0
    public ZX0 c() {
        return this.d;
    }

    @Override // defpackage.AbstractC2185dS0
    public void d(String str) {
    }

    @Override // defpackage.AbstractC2185dS0
    public void e(int i, float f, float f2) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.AbstractC2185dS0
    public boolean f(boolean z) {
        long j = this.f10932a;
        if (j == 0) {
            onSurroundingTextReceived(z, "", 0, 0);
            return true;
        }
        N.M2GZ6ZNR(j, this, 240, z ? 1 : 0);
        return true;
    }

    @Override // defpackage.AbstractC2185dS0
    public void g(boolean z, int i, int i2) {
    }

    public final void onNativeSideDestroyed(long j) {
        this.f10932a = 0;
        C2197dY0 dy0 = this.b;
        C2026cY0 cy0 = dy0.c;
        if (cy0 != null) {
            cy0.b(false);
            dy0.c = null;
        }
    }

    public final void onSurroundingTextReceived(int i, String str, int i2, int i3) {
        if (!(!TextUtils.isEmpty(str) && i2 >= 0 && i2 < i3 && i3 <= str.length())) {
            this.c.a(new C2355eS0());
        } else if (i == 0) {
            this.b.a(0, str, i2, i3);
        } else if (i == 1) {
            this.b.a(1, str, i2, i3);
        }
    }
}
